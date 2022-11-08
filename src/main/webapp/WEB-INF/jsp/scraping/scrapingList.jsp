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

	<title>SB Admin 2 - Dashboard</title>

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
								<h6 class="m-0 font-weight-bold text-primary">작업 목록</h6>
							</div>
							<div class="d-flex justify-content-end" style="float:left;vertical-align: middle;width:60%;">
								<button type="button" id="btn-scraping-new" class="btn-sm btn-primary" data-toggle="modal" data-target="#myModal">
									스크래핑 등록
								</button>
							</div>
		<%--					<div class="d-flex justify-content-end" style="float:left;vertical-align: middle;width:60%;">
								<a href="#" id="myModal" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
										class="fas fa-sm text-white-50"></i> 스크래핑 등록</a>
							</div>--%>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th style="text-align: center">등록일시</th>
											<th style="text-align: center">수집채널</th>
											<th style="text-align: center">키워드</th>
											<th style="text-align: center">수집상태</th>
											<th style="text-align: center">수집업체 개수</th>
											<th style="text-align: center">상품정보 이동</th>
										</tr>
									</thead>
									<tfoot>
									</tfoot>
									<tbody>
									<c:forEach var="result" items="${scrapingList}" varStatus="status">
										<tr>
											<td>${result.createdAt}</td>
											<td><c:choose>
													<c:when test='${result.channel eq "NV"}'>
														네이버쇼핑
													</c:when>
   											    </c:choose>
    										</td>
											<td><a onclick="javascript:openDetail('<c:out value="${result.id}"></c:out>'
													,'<c:out value="${result.channel}"></c:out>'
													,'<c:out value="${result.keyword}"></c:out>'
													,'<c:out value="${result.status}"></c:out>'
													,'<c:out value="${result.baseScoreNegative}"></c:out>'
													,'<c:out value="${result.baseScorePositive}"></c:out>'
													,'<c:out value="${result.baseScoreNeutral}"></c:out>'
													)" href="#"><c:out value="${result.keyword}"></c:out></a></td>
											<td><c:out value="${result.status.description}"></c:out></td>
											<td><c:out value="${result.noc}"></c:out></td>
											<td><button type="button" class="btn-sm btn-success" onclick="go_productList('<c:out value="${result.id}"></c:out>')">
												상품목록 >>
											</button></td>
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
				<h4 class="modal-title">스크래핑 등록</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<div class="mb-3">
					<label for="scrapingChannel" class="form-label">채널 선택</label>
					<br>
					<select id="scrapingChannel" class="custom-select" aria-label="Default select example">
						<option value="" selected >선택</option>
						<option value="NV">네이버쇼핑</option>
					</select>
				</div>
				<div class="mb-3">
					<label for="keyword" class="form-label">검색 키워드</label>
					<input id="keyword" class="form-control" type="text" placeholder="검색키워드 입력" aria-label="검색키워드 입력">
				</div>
				<div class="mb-3">
					<label for="scrapingStatus" class="form-label">수집상태</label>
					<input id="scrapingStatus" class="form-control" type="text" placeholder="" aria-label="" disabled>
				</div>
				<div class="mb-1">
					<label for="baseScorePositive" class="form-label">감정분석 최소점수 설정</label>
					<br>
					<label for="baseScorePositive">긍정 </label>
					<select class=" form-control-sm" id="baseScorePositive" size="1">
						<option>30.0</option>
						<option>35.0</option>
						<option>40.0</option>
						<option>45.0</option>
						<option>50.0</option>
						<option>55.0</option>
						<option>60.0</option>
						<option>65.0</option>
						<option>70.0</option>
					</select>
					<label for="baseScoreNegative">부정 </label>
					<select class=" form-control-sm" id="baseScoreNegative" size="1">
						<option>30.0</option>
						<option>35.0</option>
						<option>40.0</option>
						<option>45.0</option>
						<option>50.0</option>
						<option>55.0</option>
						<option>60.0</option>
						<option>65.0</option>
						<option>70.0</option>
					</select>
					<label for="baseScoreNeutral">중립</label>
					<select class=" form-control-sm" id="baseScoreNeutral" size="1">
						<option>30.0</option>
						<option>35.0</option>
						<option>40.0</option>
						<option>45.0</option>
						<option>50.0</option>
						<option>55.0</option>
						<option>60.0</option>
						<option>65.0</option>
						<option>70.0</option>
					</select>
				</div>
				<br>
				<div>
					* 현재 키워드당 상품몰은 광고를 제외한 상위 5개 업체의 리뷰수집을 하고 있습니다.
				</div>
				<div>
					ID <input id="scrapingId" class="form-control disabled" type="text" placeholder="" aria-label="" disabled>
				</div>
				<div class="mb-3" id="success_text">

				</div>
				<div class="mb-3" id="result_text">

				</div>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" id="btn-scraping-insert" class="btn btn-primary">등록</button>
				<button type="button" id="btn-scraping-start" class="btn btn-primary">수집 시작</button>
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

<!-- Page level plugins -->
<script src="${pageContext.request.contextPath}/statics/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<script >

	$(document).ready(function() {
		$('#dataTable').DataTable(
				{ order: [ [ 1, "desc" ] ]}
		);
	});

	$(function(){
		$("#btn-scraping-new").click(function(){
			$('#scrapingChannel').val("");
			$('#keyword').val("");
			$('#scrapingId').val("");
			$('#baseScoreNeutral').val("50");
			$('#baseScoreNegative').val("50");
			$('#baseScorePositive').val("50");
			$("#btn-scraping-insert").show();
			$("#btn-scraping-start").hide();
		});

		$("#btn-scraping-insert").click(function(){
			insertScraping();
		});

		$("#btn-scraping-insert-close").click(function(){
			document.location.reload();
		});

		$("#btn-scraping-start").click(function(){
			startCraping();
		})
	});

	function openDetail(scrapingId,scrapingChannel,keyword,status,baseScoreNegative,baseScorePositive,baseScoreNeutral){
		$('#myModal').modal('show');
		$("#btn-scraping-insert").hide();
		$('#scrapingChannel').val(scrapingChannel);
		$('#keyword').val(keyword);
		$('#scrapingId').val(scrapingId);
		$('#scrapingStatus').val(status);
		$('#baseScoreNegative').val(baseScoreNegative);
		$('#baseScorePositive').val(baseScorePositive);
		$('#baseScoreNeutral').val(baseScoreNeutral);
		$("#btn-scraping-start").show();
	}

	function insertScraping(){
		$.ajax({
			url: "${pageContext.request.contextPath}/scraping/scrap/insert", // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
			data: {
				keyword: $("#keyword").val() ,
				channel: $("#scrapingChannel").val() ,
				baseScoreNeutral : $("#baseScoreNeutral").val() ,
				baseScorePositive : $("#baseScorePositive").val() ,
				baseScoreNegative : $("#baseScoreNegative").val()
			},  // HTTP 요청과 함께 서버로 보낼 데이터
			//method: "POST",   // HTTP 요청 메소드(GET, POST 등)
			dataType: "json" // 서버에서 보내줄 데이터의 타입
		})
		// HTTP 요청이 성공하면 요청한 데이터가 done() 메소드로 전달됨.
		.done(function(json) {
			$("#success_text").html("등록되었습니다.");
			//$("<h1>").text(json.title).appendTo("body");
			//$("<div class=\"content\">").html(json.html).appendTo("body");
		})
		// HTTP 요청이 실패하면 오류와 상태에 관한 정보가 fail() 메소드로 전달됨.
		.fail(function(xhr, status, errorThrown) {
			$("#result_text").html("오류가 발생했다.<br>")
					.append("오류명: " + errorThrown + "<br>")
					.append("상태: " + status);
		})
		//
		.always(function(xhr, status) {
			$("#result_text").html("요청이 완료되었습니다!");
		});
	}

	//수집단계 0:미시작 1:수집요청 2:수집중 3:수집완료 4:
	function startCraping(){
		var scrapingStatus = $('#scrapingStatus').val();
		if(scrapingStatus != "" && scrapingStatus != "P1"){
			alert("이미 수집완료 되었거나 수집중 상태입니다.");
			return;
		}
		var scrapingId = $('#scrapingId').val();
		if(scrapingId == ""){
			alert("수집 작업 등록 후 진행바랍니다.");
			return;
		}

		$.ajax({
			url: "${pageContext.request.contextPath}/scraping/scrap/start/" + scrapingId, // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
			data: {
				keyword: $("#keyword").val() ,
				channel: $("#scrapingChannel").val() ,
				baseScoreNeutral  : $("#baseScoreNeutral").val() ,
				baseScorePositive : $("#baseScorePositive").val() ,
				baseScoreNegative : $("#baseScoreNegative").val()
			},  // HTTP 요청과 함께 서버로 보낼 데이터
			//method: "POST",   // HTTP 요청 메소드(GET, POST 등)
			dataType: "json" // 서버에서 보내줄 데이터의 타입
		})
		// HTTP 요청이 성공하면 요청한 데이터가 done() 메소드로 전달됨.
		.done(function(json) {
			$("#success_text").html("작업 요청이 완료되었습니다.");
			//$("<h1>").text(json.title).appendTo("body");
			//$("<div class=\"content\">").html(json.html).appendTo("body");
		})
		// HTTP 요청이 실패하면 오류와 상태에 관한 정보가 fail() 메소드로 전달됨.
		.fail(function(xhr, status, errorThrown) {
			$("#result_text").html("오류가 발생했다.<br>")
					.append("오류명: " + errorThrown + "<br>")
					.append("상태: " + status);
		})
		//
		.always(function(xhr, status) {
			$("#result_text").html("요청이 완료되었습니다!");
		});
	}

	function go_productList(scrapingId){
		location.href = "${pageContext.request.contextPath}/scraping/product/list/"+scrapingId;
 	}
</script>
<!-- Page level custom scripts -->
<%--
<script src="../statics/js/demo/chart-area-demo.js"></script>
--%>
<%--<script src="../statics/js/demo/chart-pie-demo.js"></script>--%>

</body>

</html>