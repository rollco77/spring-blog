<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>SB Admin 2 - Dashboard 2</title>

	<!-- Custom fonts for this template-->
	<link href="${pageContext.request.contextPath}/statics/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	<link
			href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
			rel="stylesheet">

	<!-- Custom styles for this template-->
	<link href="${pageContext.request.contextPath}/statics/css/sb-admin-2.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/statics/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">
	<%@ include file="/WEB-INF/jspf/scraping/nav.jspf" %>
	<!-- Sidebar -->

	<!-- End of Sidebar -->

	<!-- Content Wrapper -->
	<div id="content-wrapper" class="d-flex flex-column">

		<!-- Main Content -->
		<div id="content">

			<!-- Topbar -->

			<!-- End of Topbar -->
			<%@ include file="/WEB-INF/jspf/scraping/topbar.jspf" %>
			<!-- Begin Page Content -->
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="d-sm-flex align-items-center justify-content-between mb-4">
					<h1 class="h3 mb-0 text-gray-800">스크래핑 작업목록</h1>
				</div>


					<!-- Page Heading -->
					<%--<h1 class="h3 mb-2 text-gray-800">Tables</h1>--%>
					<p class="mb-4"></p>
						<div style="height: 30px;">
							<div style="float:left;width:40%;">
								<h6 class="m-0 font-weight-bold text-primary">상품 목록</h6>
							</div>
		<%--					<div class="d-flex justify-content-end" style="float:left;vertical-align: middle;width:60%;">
								<a href="#" id="myModal" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
										class="fas fa-sm text-white-50"></i> 스크래핑 등록</a>
							</div>--%>
						</div>
						<div class="card-body">
							<div class="row">
								<!-- Earnings (Monthly) Card Example -->
								<div class="col-xl-12 col-md-12 mb-4">
									<div class="card border-left-primary shadow h-100 py-2">
										<div class="card-body">
											<div class="row no-gutters align-items-center">
												<div class="col mr-2">
													<div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
														스크래핑 정보</div>
													<div class="h5 mb-0 font-weight-bold text-gray-800">
														채널: ${scraping.channel} <br>
														키워드: ${scraping.keyword} <br>
													</div>
												</div>
												<div class="col-auto">
													<i class="fas fa-calendar fa-2x text-gray-300"></i>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th style="text-align: center;width: 48%;">상품 제목</th>
											<th style="text-align: center;width: 7%;">리뷰 수집여부</th>
											<th style="text-align: center;width: 20%;">상품몰 명</th>
											<th style="text-align: center;width: 10%;">등록일시</th>
											<th style="text-align: center;width: 7%;">리뷰개수</th>
											<th style="text-align: center;width: 8%;">리뷰목록</th>
										</tr>
									</thead>
									<tfoot>
									</tfoot>
									<tbody>
									<c:forEach var="result" items="${productList}" varStatus="status">
										<tr>
											<td>${result.title}</td>
											<td>${result.collectCommentYn}</td>
											<td><c:out value="${result.mallName}"></c:out></td>
											<td><c:out value="${result.regDate}"></c:out></td>
											<td><c:out value="${result.reviewCount}"></c:out></td>
											<td><button type="button" class="btn-sm btn-success" onclick="go_productReviewList('<c:out value="${result.id}"></c:out>')">
											리뷰 목록 >> </button></td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>

			</div>

			<!-- /.container-fluid -->

		</div>
		<!-- End of Main Content -->

		<!-- Footer -->
		<footer class="sticky-footer bg-white">
			<div class="container my-auto">
				<div class="copyright text-center my-auto">
					<span>Copyright &copy; Your Website 2021</span>
				</div>
			</div>
		</footer>
		<!-- End of Footer -->

	</div>
	<!-- End of Content Wrapper -->
</div>
<!-- End of Page Wrapper -->
<!-- Modal -->

<div class="modal fade" id="myModal" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal modal-dialog-centered">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title"> 등록</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<div class="mb-3">
					<label for="scrapingChannel" class="form-label">채널 선택</label>
					<br>
					<select id="scrapingChannel" class="custom-select" aria-label="Default select example">
						<option selected >선택</option>
						<option value="NV">네이버쇼핑</option>
					</select>
				</div>
				<div class="mb-3">
					<label for="keyword" class="form-label">검색 키워드</label>
					<input id="keyword" class="form-control" type="text" placeholder="Default input" aria-label="default input example">
				</div>
				<div>
					ID <input id="scrapingId" class="form-control disabled" type="text" placeholder="Default input" aria-label="default input example" disabled>
				</div>
				$('#scrapingChannel').val(scrapingChannel);
				<div class="mb-3" id="success_text">

				</div>
				<div class="mb-3" id="result_text">

				</div>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" id="btn-scraping-insert" class="btn btn-primary">등록</button>
				<button type="button" id="btn-scraping-insert-close" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>

		</div>
</div>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
	<i class="fas fa-angle-up"></i>
</a>



<!-- Bootstrap core JavaScript-->
<script src="${pageContext.request.contextPath}/statics/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="${pageContext.request.contextPath}/statics/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="${pageContext.request.contextPath}/statics/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="${pageContext.request.contextPath}/statics/vendor/chart.js/Chart.min.js"></script>
<script >

	$(function(){
		$("#btn-scraping-new").click(function(){
			$('#scrapingChannel').val("");
			$('#keyword').val("");
			$('#scrapingId').val("");
		});

		$("#btn-scraping-insert").click(function(){
			insertScraping();
		});

		$("#btn-scraping-insert-close").click(function(){
			document.location.reload();
		});
	});

	function openDetail(scrapingId,scrapingChannel,keyword){
		$('#myModal').modal('show');
		$('#scrapingChannel').val(scrapingChannel);
		$('#keyword').val(keyword);
		$('#scrapingId').val(scrapingId);
	}

	function go_productReviewList(productId){
		location.href="${pageContext.request.contextPath}/scraping/review/list/"+productId;
 	}
</script>
<!-- Page level custom scripts -->
<%--
<script src="../statics/js/demo/chart-area-demo.js"></script>
--%>
<%--<script src="../statics/js/demo/chart-pie-demo.js"></script>--%>

</body>

</html>