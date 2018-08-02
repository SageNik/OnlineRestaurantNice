<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="login_box">
    <div class="login">
        <h4>User registration.</h4>
        <c:if test="${not empty error}">
            <p class="errorLogin">${error}</p>
        </c:if>
        <form method="post" action= "<c:url value="/registration"/>">
            <p><input type="text" class="login-text-field" required id="usernameReg" name="usernameReg" maxlength="30"
                      placeholder="Username"></p>
            <p><input type="text" class="login-text-field" required id="passwordReg" name="passwordReg" maxlength="30"
                      placeholder="Password"></p>
            <select class="regSelect" name="selectRole" id="selectRole">
                <option value="ROLE_ADMIN">Admin</option>
                <option value="ROLE_USER">User</option>
            </select>
            <p class="submit"><input class="button" type="button" value="Cancel" onclick="location.href='/'"/>
                <input type="submit" class="button" value="Registration"></p>
        </form>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
    </div>
</div>