<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 로그인시 --%>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.username" var="username" />
    <button class="btn btn-outline-info" onclick="javascript:alert('[<c:out value="${username}" />] 계정으로 로그인 중입니다.')"><c:out value="${username}" /></button>
    <form class="form-inline" method="POST" action="/logout" style="margin-left: 6px;">
        <button class="btn btn-outline-secondary" type="submit">Sign Out</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</sec:authorize>

<%-- 비로그인시 --%>
<sec:authorize access="!isAuthenticated()">
    <a class="btn btn-outline-success" href="/login">Sign in</a>
</sec:authorize>