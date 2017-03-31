package org.opencloudengine.garuda.backend.scheduler.jobs;

import org.opencloudengine.garuda.common.exception.ServiceException;
import org.opencloudengine.garuda.util.ApplicationContextRegistry;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClient;
import org.opencloudengine.garuda.web.console.oauthclient.OauthClientRepository;
import org.opencloudengine.garuda.web.oauth.OauthTokenRepository;
import org.opencloudengine.garuda.web.oauth.OauthTokenService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ClientJob 의 Stopping Job 을 확인해서 종료시키는 작업.
 *
 * @author Seungpil, Park
 * @since 2.0
 */
public class StopJob extends QuartzJobBean {
    /**
     * SLF4J Logging
     */
    private Logger logger = LoggerFactory.getLogger(StopJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            //logger.debug("Now check the stopping jobs of HRM.");
            ApplicationContext applicationContext = ApplicationContextRegistry.getApplicationContext();
            OauthTokenRepository oauthTokenRepository = applicationContext.getBean(OauthTokenRepository.class);
            OauthClientRepository clientRepository = applicationContext.getBean(OauthClientRepository.class);

            List<OauthClient> clientList = clientRepository.selectByAutoDeletionToken();
            for (OauthClient client : clientList) {
                long nowTime = new Date().getTime();
                long idelTime = 60; //sec
                Long refreshTokenLifetime = new Long(0);
                if (client.getRefreshTokenLifetime() != null) {
                    refreshTokenLifetime = new Long(client.getRefreshTokenLifetime());
                }

                Long jwtTokenLifetime = new Long(0);
                if (client.getJwtTokenLifetime() != null) {
                    jwtTokenLifetime = new Long(client.getJwtTokenLifetime());
                }
                Long jwtExpirationTime = nowTime - (jwtTokenLifetime * 1000) - (refreshTokenLifetime * 1000) - (idelTime * 1000);


                Long bearerTokenLifetime = new Long(0);
                if (client.getAccessTokenLifetime() != null) {
                    bearerTokenLifetime = new Long(client.getAccessTokenLifetime());
                }
                Long bearerExpirationTime = nowTime - (bearerTokenLifetime * 1000) - (refreshTokenLifetime * 1000) - (idelTime * 1000);
                Date date1 = new Date(bearerTokenLifetime);


                oauthTokenRepository.deleteExpiredToken(client.get_id(), jwtExpirationTime, "JWT");
                oauthTokenRepository.deleteExpiredToken(client.get_id(), bearerExpirationTime, "Bearer");
            }

            System.out.println("start");

        } catch (Exception ex) {
            throw new ServiceException("Unable to run scheduled jobs", ex);
        }
    }
}
