<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst"%>


<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commApp" value="${ForwardConst.CMD_APPROVAL.getValue()}" />
<c:set var="commAppBack" value="${ForwardConst.CMD_APPROVAL_BACK.getValue()}" />
<c:set var="commAppCancel"
    value="${ForwardConst.CMD_APPROVAL_CANCEL.getValue()}" />
<c:set var="commAppli"
    value="${ForwardConst.CMD_APPLICATION.getValue()}" />
<c:set var="commAppliCancel"
    value="${ForwardConst.CMD_APPLICATION_CANCEL.getValue()}" />


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
                    <td><pre> <c:out value="${report.content}" /></pre></td>
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
                    <th>申請状況</th>
                    <td><c:choose>
                            <c:when
                                test="${report.applicationFlag == AttributeConst.APPLICATION_FLAF_TRUE.getIntegerValue()}">
                            確認済み
                            </c:when>
                            <c:when
                                test="${report.applicationFlag == AttributeConst.APPLICATION_FLAF_SEE.getIntegerValue()}">
                            申請中
                            </c:when>
                            <c:when
                                test="${report.applicationFlag == AttributeConst.APPLICATION_FLAF_RE.getIntegerValue()}">
                            再提出
                            </c:when>
                            <c:otherwise>
                            未申請
                            </c:otherwise>
                        </c:choose></td>
                </tr>

                <tr>
                    <th>承認状況</th>
                    <td><c:choose>
                            <c:when
                                test="${report.approvalFlag == AttributeConst.APPROVAL_FLAF_TRUE.getIntegerValue()}">
                                <c:out value="${report.approvalStaff}" />が承認
                            </c:when>
                            <c:otherwise>未承認</c:otherwise>
                        </c:choose></td>
                </tr>



            </tbody>
        </table>

        <c:choose>
            <c:when
                test="${report.approvalFlag == AttributeConst.APPROVAL_FLAF_TRUE.getIntegerValue() and
                sessionScope.login_employee.managementFlag != AttributeConst.POSITION_GENERAL.getIntegerValue() }">
                <p>
                    <a href="#" onclick="confimApplication_cancel();">確定を解除する</a>
                </p>

                <form name="commAppCancel" method="POST"
                    action="<c:url value='?action=${actRep}&command=${commAppCancel}' />">
                    <input type="hidden" name="${AttributeConst.REP_ID.getValue()}"
                        value="${report.id}" /> <input type="hidden"
                        name="${AttributeConst.REP_APPROVAL_STAFF.getValue()}"
                        value="${sessionScope.login_employee.name}" /> <input
                        type="hidden" name="${AttributeConst.TOKEN.getValue()}"
                        value="${_token}" />
                </form>
                <script>
                    function confimApplication_cancel() {
                        if (confirm("この日報の確定を解除しますか？")) {
                            document.forms.commAppCancel.submit();
                        }
                    }
                </script>

            </c:when>

            <c:otherwise>
                <c:if test="${sessionScope.login_employee.id == report.employee.id}">
                    <c:if
                        test="${report.applicationFlag == AttributeConst.APPLICATION_FLAF_STAY.getIntegerValue() or
                           report.applicationFlag == AttributeConst.APPLICATION_FLAF_RE.getIntegerValue()}" >
                        <p>
                            <a
                                href="<c:url value='?action=${actRep}&command=${commEdt}&id=${report.id}' />">この日報を編集する</a>
                        </p>
                    </c:if>

                    <c:if
                        test="${report.applicationFlag == AttributeConst.APPLICATION_FLAF_SEE.getIntegerValue()}">
                        <p>
                            <a href="#" onclick="confimApplication_cancel();">申請を取り消す</a>
                        </p>
                        <form name="commAppliCancel" method="POST"
                            action="<c:url value='?action=${actRep}&command=${commAppliCancel}' />">
                            <input type="hidden" name="${AttributeConst.REP_ID.getValue()}"
                                value="${report.id}" /> <input type="hidden"
                                name="${AttributeConst.REP_APPROVAL_STAFF.getValue()}"
                                value="${sessionScope.login_employee.name}" /> <input
                                type="hidden" name="${AttributeConst.TOKEN.getValue()}"
                                value="${_token}" />
                        </form>
                        <script>
                            function confimApplication_cancel() {
                                if (confirm("申請を取り消しますか？")) {
                                    document.forms.commAppliCancel.submit();
                                }
                            }
                        </script>
                    </c:if>

                    <c:if
                        test="${report.applicationFlag == AttributeConst.APPLICATION_FLAF_STAY.getIntegerValue()}">
                        <p>
                            <a href="#" onclick="confimApplication();">この日報を申請する</a>
                        </p>
                        <form name="commAppli" method="POST"
                            action="<c:url value='?action=${actRep}&command=${commAppli}' />">
                            <input type="hidden" name="${AttributeConst.REP_ID.getValue()}"
                                value="${report.id}" /> <input type="hidden"
                                name="${AttributeConst.REP_APPROVAL_STAFF.getValue()}"
                                value="${sessionScope.login_employee.name}" /> <input
                                type="hidden" name="${AttributeConst.TOKEN.getValue()}"
                                value="${_token}" />
                        </form>
                        <script>
                            function confimApplication() {
                                if (confirm("この日報を申請しますか？")) {
                                    document.forms.commAppli.submit();
                                }
                            }
                        </script>
                    </c:if>
                </c:if>

                <c:if
                    test="${sessionScope.login_employee.managementFlag != AttributeConst.POSITION_GENERAL.getIntegerValue()}">
                    <c:choose>
                        <c:when
                            test="${report.applicationFlag == AttributeConst.APPLICATION_FLAF_SEE.getIntegerValue()}">
                            <p>
                                <a href="#" onclick="confimApproval();">この日報を承認する</a>
                            </p>
                            <form name="commApp" method="POST" action="<c:url value='?action=${actRep}&command=${commApp}' />">
                                <input type="hidden" name="${AttributeConst.REP_ID.getValue()}"  value="${report.id}" />
                                <input type="hidden" name="${AttributeConst.REP_APPROVAL_STAFF.getValue()}" value="${sessionScope.login_employee.name}" />
                                <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
                            </form>

                            <script>
                                function confimApproval() {
                                    if (confirm("この日報を承認しますか？")) {
                                        document.forms.commApp.submit();
                                    }
                                }
                            </script>

                            <p>
                                <a href="#" onclick="confimApproval();">この日報を取り下げる</a>
                            </p>
                            <form name="commAppBack" method="POST" action="<c:url value='?action=${actRep}&command=${commAppBack}' />">
                                <input type="hidden" name="${AttributeConst.REP_ID.getValue()}"  value="${report.id}" />
                                <input type="hidden" name="${AttributeConst.REP_APPROVAL_STAFF.getValue()}" value="${sessionScope.login_employee.name}" />
                                <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
                            </form>

                            <script>
                                function confimApproval_back() {
                                    if (confirm("この日報を取り下げますか？")) {
                                        document.forms.commAppBack.submit();
                                    }
                                }
                            </script>

                        </c:when>

                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </c:otherwise>
        </c:choose>

        <p>
            <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>