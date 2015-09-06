<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${techprofile eq null }">
<c:redirect url="/SessionExpired.jsp?action=login"></c:redirect>
</c:if>