<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="login_box">
    <div class="login">
        <h4>Come in please.</h4>
        <c:if test="${not empty error}">
            <p class="errorLogin">${error}</p>
        </c:if>
        <form method="post" action="<c:url value="/login"/>">
            <p><input type="text" class="login-text-field" required id="username" name="username" maxlength="30"
                      placeholder="Username"></p>
            <p><input type="text" class="login-text-field" required id="password" name="password" maxlength="30"
                      placeholder="Password"></p>
            <p class="login-help"><a href="registration">Registration</a></p>
            <p class="submit"><input class="button" type="submit" value="Login"></p>
        </form>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />

    </div>
</div>
