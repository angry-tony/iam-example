package org.opencloudengine.garuda.web.rest;

import org.opencloudengine.garuda.model.User;
import org.opencloudengine.garuda.web.management.Management;

import javax.servlet.http.HttpServletRequest;

public interface RestAuthService {

    User userParser(HttpServletRequest request);

    Management managementParser(HttpServletRequest request);
}
