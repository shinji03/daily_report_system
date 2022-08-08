<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst"%>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commApp" value="${ForwardConst.CMD_APPROVAL.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>日報 詳細ページ</h2>

        <table>
            <tbody>

                <tr>
                    <th>氏名</th>
                    <td><c:out value="${report.employee.name}" /></td>
                </tr>

                <tr>
                    <th>日付</th>
                    <fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd"
                        var="reportDay" type="date" />
                    <td><fmt:formatDate value='${reportDay}' pattern='yyyy-MM-dd' /></td>
                </tr>

                <tr>
                    <th>内容</th>
                    <td><pre>
                            <c:out value="${report.content}" />
                        </pre></td>
                </tr>

                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${report.createdAt}"
                        pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}"
                            pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>

                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${report.updatedAt}"
                        pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}"
                            pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>

                <tr>
                    <th>承認状況</th>
                    <td><c:choose>
                            <c:when
                                test="${report.approvalFlag == AttributeConst.APPROVAL_FLAF_TRUE.getIntegerValue()}">承認済み</c:when>
                            <c:otherwise>未承認</c:otherwise>
                        </c:choose></td>
                </tr>



            </tbody>
        </table>

        <!-- 以下の内容で作成者かどうか判別している -->

        <c:if test="${sessionScope.login_employee.id == report.employee.id}">
            <p>
                <a
                    href="<c:url value='?action=${actRep}&command=${commEdt}&id=${report.id}' />">この日報を編集する</a>
            </p>
        </c:if>

        <c:if test="${sessionScope.login_employee.managementFlag != 0}">
            <p><a href="#" onclick="confimApproval();">この日報を承認する</a></p>
            <form method="POST" action="<c:url value='?action=${actRep}&command=${commApp}' />">
                <input type="hidden" name="${AttributeConst.REP_ID.getValue()}"value="${report.id}" />
                <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            </form>

            <script>
                function confimApproval() {
                    if (confirm("この日報を承認しますか？")) {
                        document.forms[0].submit();}
                }
            </script>

        </c:if>

        <p>
            <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>