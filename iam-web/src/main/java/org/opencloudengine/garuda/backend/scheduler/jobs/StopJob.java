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
                Integer refreshTokenLifetime = client.getRefreshTokenLifetime(); //sec
                if (refreshTokenLifetime == null) {
                    refreshTokenLifetime = 0;
                }

                Integer jwtTokenLifetime = client.getJwtTokenLifetime();
                Long jwtExpirationTime = nowTime - (jwtTokenLifetime * 1000) - (refreshTokenLifetime * 1000) - (idelTime * 1000);

                Integer bearerTokenLifetime = client.getAccessTokenLifetime();
                Long bearerExpirationTime = nowTime - (bearerTokenLifetime * 1000) - (refreshTokenLifetime * 1000) - (idelTime * 1000);

                oauthTokenRepository.deleteExpiredToken(client.get_id(), jwtExpirationTime, "JWT");
                oauthTokenRepository.deleteExpiredToken(client.get_id(), bearerExpirationTime, "Bearer");
            }

            System.out.println("start");

        } catch (Exception ex) {
            throw new ServiceException("Unable to run scheduled jobs", ex);
        }
    }
}
