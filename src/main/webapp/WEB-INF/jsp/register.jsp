<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1.0">

<title>新規会員登録 | Honey Bloom</title>

<style>
@charset "UTF-8";

* {
    box-sizing: border-box;
}

html,
body {
    margin: 0;
    min-height: 100%;
}

body {
    background-color: #fffaf2;
    color: #595959;
    font-family: "Yu Gothic", "Meiryo", sans-serif;
}

/* ページ全体 */
.registrationPage {
    position: relative;
    width: min(1100px, 96%);
    min-height: 690px;
    margin: 18px auto;
    padding: 22px 35px 30px;
    overflow: hidden;

    background-color: #fffdf8;
    border: 1px solid #d8c6a8;
}

/* 黄色い屋根 */
.awningImage {
    position: absolute;
    top: 0;
    left: 0;

    width: 100%;
    height: 190px;
    object-fit: fill;

    z-index: 1;
    pointer-events: none;
}

/* タイトル部分 */
.headerArea {
    position: relative;
    z-index: 3;

    text-align: center;
}

.title {
    margin: 0;

    color: #783f04;
    font-family: Georgia, "Times New Roman", serif;
    font-size: 38px;
}

.icon {
    width: 45px;
    height: auto;

    margin-left: 5px;
    vertical-align: middle;
}

.subTitle {
    margin: 6px 0;

    color: #783f04;
    font-size: 14px;
}

.pageTitle {
    margin: 8px 0 14px;

    color: #783f04;
    font-size: 21px;
    font-weight: normal;
}

/* 左側の看板 */
.signImage {
    position: absolute;
    top: 215px;
    left: 15px;

    width: 185px;
    height: auto;

    z-index: 2;
}

/* 右下のレモンタルト */
.lemonImage {
    position: absolute;
    right: 15px;
    bottom: 10px;

    width: 285px;
    height: auto;

    z-index: 2;
}

/* エラーメッセージ */
.errorMessage {
    position: relative;
    z-index: 4;

    width: 560px;
    max-width: 100%;
    margin: 0 auto 10px;
    padding: 8px 12px;

    color: #b00020;
    background-color: #fff0f0;
    border: 1px solid #e2a5a5;

    font-size: 13px;
}

/* フォーム */
.registrationForm {
    position: relative;
    z-index: 4;

    width: 570px;
    max-width: 100%;
    margin: 0 auto;
}

/* 左右2列 */
.formGrid {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 7px 28px;
}

.formGroup {
    min-width: 0;
}

.formGroup.fullWidth {
    grid-column: 1 / -1;
}

label {
    display: block;

    margin-bottom: 2px;

    color: #595959;
    font-size: 12px;
    font-weight: bold;
}

input,
select {
    width: 100%;
    height: 32px;

    padding: 5px 8px;

    background-color: #ffffff;
    border: 1px solid #aaa;
    border-radius: 0;

    color: #595959;
    font-size: 13px;
}

input:focus,
select:focus {
    border-color: #a15b13;
    outline: 1px solid #d8aa70;
}

/* 郵便番号 */
.postalRow {
    display: flex;
    gap: 5px;
}

.postalRow input {
    flex: 1;
}

.addressButton {
    width: auto;
    min-width: 72px;
    height: 32px;

    padding: 4px 8px;

    background-color: #fff8ed;
    border: 1px solid #a15b13;

    color: #783f04;
    font-size: 11px;
    cursor: pointer;
}

/* 説明文 */
.helpText {
    display: none;
}

/* 登録ボタン */
.buttonArea {
    display: flex;
    justify-content: center;

    margin-top: 12px;
}

.registerButton {
    width: 190px;

    padding: 11px 20px;

    background-color: #914a00;
    border: none;
    border-radius: 8px;

    color: white;
    font-size: 14px;
    font-weight: bold;

    cursor: pointer;
}

.registerButton:hover {
    background-color: #783f04;
}

/* ログイン画面へ戻る */
.backLink {
    position: absolute;
    left: 30px;
    bottom: 25px;

    z-index: 5;

    color: #783f04;
    font-size: 14px;
    text-decoration: none;
}

.backLink:hover {
    text-decoration: underline;
}

/* 小さい画面用 */
@media screen and (max-width: 850px) {

    .registrationPage {
        width: 100%;
        min-height: 100vh;
        margin: 0;
        padding: 25px 20px 75px;
    }

    .awningImage {
        height: 145px;
    }

    .title {
        font-size: 30px;
    }

    .icon {
        width: 36px;
    }

    .formGrid {
        grid-template-columns: 1fr;
    }

    .formGroup.fullWidth {
        grid-column: auto;
    }

    .signImage,
    .lemonImage {
        opacity: 0.15;
    }

    .signImage {
        top: 200px;
        left: 0;
    }

    .lemonImage {
        right: 0;
        bottom: 30px;
    }
}
</style>
</head>

<body>

<div class="registrationPage">

    <!-- 黄色い屋根 -->
    <img
        src="${pageContext.request.contextPath}/images/top1.png"
        alt=""
        class="awningImage">

    <!-- 左側の看板 -->
    <img
        src="${pageContext.request.contextPath}/images/side_sign.png"
        alt="Honey Bloom"
        class="signImage">

    <!-- 右下のレモンタルト -->
    <img
        src="${pageContext.request.contextPath}/images/top_lemon.png"
        alt="レモンタルト"
        class="lemonImage">

    <!-- タイトル -->
    <header class="headerArea">

        <h1 class="title">
            Honey Bloom...
            <img
                src="${pageContext.request.contextPath}/images/icon.png"
                alt="ハチのアイコン"
                class="icon">
        </h1>

        <p class="subTitle">
            はちみつ香る、しあわせなお菓子時間。
        </p>

        <h2 class="pageTitle">
            新規会員登録
        </h2>

    </header>

    <%-- Controllerからエラーメッセージが渡された場合 --%>
    <% if (request.getAttribute("errorMsg") != null) { %>

        <div class="errorMessage">
            <%= request.getAttribute("errorMsg") %>
        </div>

    <% } %>

    <form
        action="${pageContext.request.contextPath}/register"
        method="post"
        class="registrationForm">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

        <div class="formGrid">

            <!-- 左上：会員ID -->
            <div class="formGroup">

                <label for="memberId">
                    会員ID
                </label>

                <input
                    type="text"
                    id="memberId"
                    name="memberId"
                    value="${memberId}"
                    minlength="4"
                    maxlength="20"
                    pattern="[A-Za-z0-9]+"
                    title="会員IDは半角英数字で入力してください"
                    autocomplete="username"
                    required>

            </div>

            <!-- 右上：氏名 -->
            <div class="formGroup">

                <label for="memberName">
                    氏名
                </label>

                <input
                    type="text"
                    id="memberName"
                    name="name"
                    value="${memberName}"
                    maxlength="50"
                    autocomplete="name"
                    required>

            </div>

            <!-- 左：パスワード -->
            <div class="formGroup">

                <label for="password">
                    パスワード
                </label>

                <input
                    type="password"
                    id="password"
                    name="password"
                    minlength="8"
                    maxlength="64"
                    pattern="(?=.*[A-Za-z])(?=.*[0-9]).{8,64}"
                    title="英字と数字を含む8文字以上で入力してください"
                    placeholder="パスワードを入力してください"
                    autocomplete="new-password"
                    required>

            </div>

            <!-- 右：生年月日 -->
            <div class="formGroup">

                <label for="birthDate">
                    生年月日
                </label>

                <input
                    type="date"
                    id="birthDate"
                    name="birthDate"
                    value="${birthDate}"
                    autocomplete="bday"
                    required>

            </div>

            <!-- 左：パスワード確認 -->
            <div class="formGroup">

                <label for="passwordConfirm">
                    パスワード（確認）
                </label>

                <input
                    type="password"
                    id="passwordConfirm"
                    name="passwordConfirm"
                    minlength="8"
                    maxlength="64"
                    placeholder="もう一度入力してください"
                    autocomplete="new-password"
                    required>

            </div>

            <!-- 右：電話番号 -->
            <div class="formGroup">

                <label for="phoneNumber">
                    電話番号
                </label>

                <input
                    type="tel"
                    id="phoneNumber"
                    name="phone"
                    value="${phoneNumber}"
                    inputmode="tel"
                    maxlength="15"
                    pattern="0\d{1,4}-?\d{1,4}-?\d{3,4}"
                    placeholder="090-1234-5678"
                    title="電話番号を正しい形式で入力してください"
                    autocomplete="tel"
                    required>

            </div>

            <!-- 郵便番号 -->
            <div class="formGroup">

                <label for="postalCode">
                    郵便番号
                </label>

                <div class="postalRow">

                    <input
                        type="text"
                        id="postalCode"
                        name="postalCode"
                        value="${postalCode}"
                        inputmode="numeric"
                        maxlength="8"
                        pattern="\d{3}-?\d{4}"
                        placeholder="123-4567"
                        title="郵便番号は123-4567の形式で入力してください"
                        autocomplete="postal-code"
                        required>

                    <button
                        type="button"
                        class="addressButton"
                        id="addressSearchButton">
                        住所検索
                    </button>

                </div>

            </div>

            <!-- 住所 -->
            <div class="formGroup fullWidth">

                <label for="address">
                    住所
                </label>

                <input
                    type="text"
                    id="address"
                    name="address"
                    value="${address}"
                    maxlength="150"
                    autocomplete="street-address"
                    required>

            </div>

            <!-- メールアドレス -->
            <div class="formGroup fullWidth">

                <label for="email">
                    メールアドレス
                </label>

                <input
                    type="email"
                    id="email"
                    name="email"
                    value="${email}"
                    maxlength="254"
                    placeholder="example@example.com"
                    autocomplete="email"
                    required>

            </div>

            <!-- 支払方法 -->
            <div class="formGroup fullWidth">

                <label for="paymentMethod">
                    支払方法
                </label>

                <select
                    id="paymentMethod"
                    name="paymentMethod"
                    required>

                    <option value="">
                        選択してください
                    </option>

                    <option value="CREDIT_CARD">
                        クレジットカード
                    </option>

                    <option value="BANK_TRANSFER">
                        銀行振込
                    </option>

                    <option value="CASH_ON_DELIVERY">
                        代金引換
                    </option>

                    <option value="CASH_ON_DELIVERY">
                        コンビニ払い
                    </option>

                </select>

            </div>

        </div>

        <div class="buttonArea">

            <button
                type="submit"
                class="registerButton">
                会員登録する
            </button>

        </div>

    </form>

    <a
        href="${pageContext.request.contextPath}/"
        class="backLink">
        ←　ログイン画面へ戻る
    </a>

</div>

<script>
"use strict";

const form = document.querySelector(".registrationForm");
const password = document.getElementById("password");
const passwordConfirm =
        document.getElementById("passwordConfirm");

form.addEventListener("submit", function(event) {

    passwordConfirm.setCustomValidity("");

    if (password.value !== passwordConfirm.value) {

        event.preventDefault();

        passwordConfirm.setCustomValidity(
                "確認用パスワードが一致していません");

        passwordConfirm.reportValidity();
    }
});

passwordConfirm.addEventListener("input", function() {

    if (password.value === passwordConfirm.value) {
        passwordConfirm.setCustomValidity("");
    }
});

document.getElementById("addressSearchButton")
        .addEventListener("click", function() {

    const postalCode =
            document.getElementById("postalCode").value;

    const normalizedPostalCode =
            postalCode.replace(/-/g, "");

    if (!/^\d{7}$/.test(normalizedPostalCode)) {

        alert("郵便番号を7桁で入力してください。");
        return;
    }

    alert(
        "郵便番号から住所を検索する処理は"
        + "Controller完成後に接続します。");
});
</script>

</body>
</html>
