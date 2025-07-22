<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>마이페이지 - 북스토어</title>
    <!-- Bootstrap CSS (CDN) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f9f9f9;
        }
        .profile-card {
            max-width: 600px;
            margin: 50px auto;
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            padding: 30px;
        }
        .profile-title {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 30px;
            color: #343a40;
            text-align: center;
        }
        .field-label {
            font-weight: bold;
            color: #555;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="profile-card">
        <div class="profile-title">📚 마이페이지</div>
        <div class="row mb-3">
            <div class="col-sm-4 field-label">아이디</div>
            <div class="col-sm-8">${member.username}</div>
        </div>
        <div class="row mb-3">
            <div class="col-sm-4 field-label">비밀번호</div>
            <div class="col-sm-8">●●●●●●</div>
        </div>
        <div class="row mb-3">
            <div class="col-sm-4 field-label">이름</div>
            <div class="col-sm-8">${member.name}</div>
        </div>
        <div class="row mb-3">
            <div class="col-sm-4 field-label">이메일</div>
            <div class="col-sm-8">${member.email}</div>
        </div>
        <div class="row mb-3">
            <div class="col-sm-4 field-label">전화번호</div>
            <div class="col-sm-8">${member.hp}</div>
        </div>
        <div class="text-center mt-4">
            <a href="/mypage/edit" class="btn btn-primary">회원정보 수정</a>
            <a href="/logout" class="btn btn-outline-secondary">로그아웃</a>
        </div>
        
    </div>
</div>

<!-- Bootstrap JS (Optional) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
