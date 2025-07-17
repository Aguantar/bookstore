<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>북마켓 회원 목록</title>

  <!-- 구글 폰트 -->
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,700&display=swap" rel="stylesheet">
  <!-- 부트스트랩 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

  <style>
    body {
      background-color: #f8f9fa;
      font-family: 'Noto Sans KR', Arial, sans-serif;
    }
    h1 {
      font-weight: 700;
      margin-bottom: 30px;
      color: #222;
    }
    .table thead {
      background-color: #343a40;
      color: white;
    }
    .table-striped tbody tr:nth-of-type(odd) {
      background-color: #f0f4f8;
    }
    .btn-primary {
      background-color: #36c;
      border: none;
    }
    .btn-primary:hover {
      background-color: #2546a6;
    }
    .btn-outline-secondary {
      border-color: #6c757d;
    }
    .table td, .table th {
      vertical-align: middle;
    }
  </style>
</head>
<body class="container py-4">
  <h1>북마켓 회원 목록</h1>

  <div class="mb-4 d-flex justify-content-end gap-2">
  <a href="<c:url value='/admin/users/add'/>" class="btn btn-primary">+ 새 사용자 등록</a>
  <a href="<c:url value='/admin/bookManage'/>" class="btn btn-secondary">목록으로 돌아가기</a>
</div>
  <c:if test="${not empty users}">
    <div class="table-responsive">
      <table class="table table-bordered table-striped text-center align-middle">
        <thead>
          <tr>
            <th>ID</th>
            <th>이름</th>
            <th>Email</th>
            <th>전화번호</th>
            <th>권한</th>
            <th>관리</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="u" items="${users}">
            <tr>
              <td>${u.id}</td>
              <td>${u.name}</td>
              <td>${u.email}</td>
              <td>${u.hp}</td>
              <td>${u.role}</td>
              <td>
                <a href="<c:url value='/admin/users/edit/${u.id}'/>" class="btn btn-sm btn-outline-secondary">수정</a>
                <form action="<c:url value='/admin/users/delete/${u.id}'/>" method="post" style="display:inline">
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                  <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</button>
                </form>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </c:if>

  <c:if test="${empty users}">
    <div class="text-center mt-4">
      <p class="text-muted">등록된 사용자가 없습니다.</p>
    </div>
  </c:if>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>