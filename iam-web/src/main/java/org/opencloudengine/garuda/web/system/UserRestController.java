/**
 * Copyright (C) 2011 Flamingo Project (http://www.opencloudengine.org).
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.opencloudengine.garuda.web.system;

import org.opencloudengine.garuda.model.User;
import org.opencloudengine.garuda.web.configuration.ConfigurationHelper;
import org.opencloudengine.garuda.web.configuration.DefaultController;
import org.opencloudengine.garuda.web.security.AESPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Security Auth Controller
 *
 * @author Seungpil PARK
 * @since 2.0
 */
@Controller
@RequestMapping("/rest/v1")
public class UserRestController extends DefaultController {

    @Autowired
    private UserService userService;

    @Autowired
    ConfigurationHelper configurationHelper;

    @Autowired
    AESPasswordEncoder passwordEncoder;

    /**
     * SLF4J Logging
     */
    private Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @RequestMapping(value = "/access_token", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Map> accessToken(HttpServletRequest request) {

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = userService.selectByUserEmail(username);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (!user.getEnabled()) {
                logger.info("Not enabled user. {}", username + "/" + password);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            String decodePassword = passwordEncoder.decode(user.getPassword());

            if (password.equals(decodePassword)) {

                String sessionToken = userService.generateSessionToken(username, user.getPassword());
                Map map = new HashMap();
                map.put("access_token", sessionToken);
                return new ResponseEntity<>(map, HttpStatus.CREATED);
            } else {
                logger.info("Invalid Credentials.");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/token_info", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> tokenInfo(HttpServletRequest request, HttpServletResponse response
    ) throws IOException {

        try {
            String sessionToken = request.getParameter("authorization");
            User user = userService.validateSessionToken(sessionToken);
            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
