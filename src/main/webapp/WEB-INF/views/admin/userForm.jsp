<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>사용자 ${user.id == null ? "등록" : "수정"}</title>

<!-- Bootstrap 5 CDN -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet" />
</head>

<body class="bg-light">
	<div class="container py-5">
		<div class="card shadow-sm">
			<div class="card-header bg-primary text-white">
				<h4 class="mb-0">
					<i class="bi bi-person-circle me-2"></i> 사용자 ${user.id == null ? "등록" : "수정"}
				</h4>
			</div>
			<div class="card-body">
				<form
					action="<c:url value='${user.id == null ? "/admin/users/add" : "/admin/users/edit"}'/>"
					method="post" autocomplete="off">

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<c:if test="${user.id != null}">
						<input type="hidden" name="id" value="${user.id}" />
					</c:if>

					<!-- 아이디 (name) -->
					<div class="mb-3">
						<label for="name" class="form-label">아이디</label>
						<c:if test="${user.id == null}">
							<input type="text" id="name" name="name" class="form-control"
								placeholder="아이디 입력" autocomplete="off" required />
						</c:if>
						<c:if test="${user.id != null}">
							<input type="text" class="form-control-plaintext" readonly
								value="${user.name}" />
						</c:if>
					</div>

					<!-- 비밀번호 (등록 시에만) -->
					<c:if test="${user.id == null}">
						<div class="mb-3">
							<label for="password" class="form-label">비밀번호</label> <input
								type="password" id="password" name="password"
								class="form-control" placeholder="비밀번호 입력"
								autocomplete="new-password" required />
						</div>
					</c:if>

					<!-- 이메일 -->
					<div class="mb-3">
						<label for="email" class="form-label">이메일</label> <input
							type="email" id="email" name="email" class="form-control"
							placeholder="example@example.com" value="${user.email}"
							autocomplete="off" required />
					</div>

					<!-- 연락처 (hp) -->
					<div class="mb-3">
						<label for="hp" class="form-label">연락처</label> <input type="text"
							id="hp" name="hp" class="form-control"
							placeholder="010-0000-0000" value="${user.hp}" autocomplete="off" />
					</div>

					<!--  권한 -->
					<div class="mb-4">
						<label for="role" class="form-label">권한</label> <select id="role"
							name="role" class="form-select" autocomplete="off">
							<option value="CUSTOMER"
								${user.role == 'CUSTOMER' ? 'selected' : ''}>CUSTOMER</option>
							<option value="ADMIN" ${user.role == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
						</select>
					</div>


					<div class="d-flex justify-content-between">
						<button type="submit" class="btn btn-primary">
							<i class="bi bi-check-circle me-1"></i> ${user.id == null ? "등록" : "수정"}
						</button>
						<a href="<c:url value='/admin/users'/>"
							class="btn btn-outline-secondary"> <i class="bi bi-list me-1"></i>
							목록으로
						</a>
					</div>

				</form>
			</div>
		</div>
	</div>

	<!-- ✅ Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
