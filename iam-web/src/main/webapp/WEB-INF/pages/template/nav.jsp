<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav metismenu" id="side-menu">
            <li class="nav-header">
                <div class="profile-element">
                    <span><img alt="image" class="img-circle" src="/resources/images/oce-logo.png"/></span>
                    <a href="/index"><span class="block m-t-xs"> <strong class="font-bold">uEngine IAM</strong>
                             </span></a>
                </div>
            </li>
            <li name="list-menu-index">
                <a href="/index"><i class="fa fa-th-large"></i> <span class="nav-label">Home</span></a>
            </li>
            <li name="list-menu-user">
                <a href="/user/list"><i class="fa fa-th-large"></i> <span class="nav-label">Users</span></a>
            </li>
            <li name="list-menu-client">
                <a href="/client/list"><i class="fa fa-th-large"></i> <span class="nav-label">Clients</span></a>
            </li>
            <li name="list-menu-scope">
                <a href="/scope/list"><i class="fa fa-th-large"></i> <span class="nav-label">Scopes</span></a>
            </li>
            <li name="list-menu-custom">
                <a href="/management/custom"><i class="fa fa-th-large"></i> <span class="nav-label">Custom token issuance</span></a>
            </li>
            <li name="list-menu-management">
                <a href="#"><i class="fa fa-gear"></i> <span class="nav-label">Management</span><span class="fa arrow"></span></a>
                <ul class="nav nav-second-level collapse">
                    <li name="list-menu-management-profile"><a href="/management/profile">Management Profile</a></li>
                    <li name="list-menu-management-list"><a href="/management/list">Management list</a></li>
                </ul>
            </li>
            <li name="list-menu-api">
                <a href="/api"><i class="fa fa-gears"></i> <span class="nav-label">Api Docs</span></a>
            </li>
        </ul>

    </div>
</nav>