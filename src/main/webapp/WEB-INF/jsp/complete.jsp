<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="common/header.jspf" %><section class="complete"><div class="check">✓</div><h1>注文が完了しました</h1><p>ご注文ありがとうございます。仮メール送信処理を実行しました。</p><p>注文番号: <strong>#${order.id}</strong></p><a class="button" href="${pageContext.request.contextPath}/menu">メニューへ戻る</a></section><%@ include file="common/footer.jspf" %>

