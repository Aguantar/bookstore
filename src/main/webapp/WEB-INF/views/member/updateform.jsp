<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원정보 수정 - 북스토어</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f9f9f9;
        }
        .update-form {
            max-width: 600px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }
        .form-title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 30px;
            text-align: center;
            color: #343a40;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="update-form">
        <div class="form-title">회원정보 수정</div>
        <form action="update" method="post">
            <div class="mb-3">
                <label class="form-label">아이디</label>
                <input type="text" name="username" class="form-control" value="${member.username}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">비밀번호</label>
                <input type="password" name="password" class="form-control" placeholder="새 비밀번호 입력">
            </div>
            <div class="mb-3">
                <label class="form-label">이름</label>
                <input type="text" name="name" class="form-control" value="${member.name}">
            </div>
            <div class="mb-3">
                <label class="form-label">이메일</label>
                <input type="email" name="email" class="form-control" value="${member.email}">
            </div>
            <div class="mb-3">
                <label class="form-label">전화번호</label>
                <input type="text" name="hp" class="form-control" value="${member.hp}">
            </div>
 
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

            <div class="text-center mt-4">
                <button type="submit" class="btn btn-success">수정 완료</button>
                <a href="/member/mypage" class="btn btn-outline-secondary">취소</a>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
