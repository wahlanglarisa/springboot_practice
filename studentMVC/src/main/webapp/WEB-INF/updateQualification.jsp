<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>





<div class="row">
	<!-- Profile picture card-->
	<div class="modal fade" id="deleteStQualModal"
		data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">

		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<h1 class="modal-title fs-5">Delete Confirmation</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<div class="modal-body"> </div>

				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">No</button>

					<button id="confirmDeleteBtn" type="button" class="btn btn-danger">
						Delete </button>

				</div>

			</div>
		</div>
	</div>
	<div class="card mb-4 mb-xl-0 shadow">
		<div class="card-header">Educational Information</div>
		<br>
		<div class="d-flex justify-content-end"></div>
		<div class="card-body  row" id="qualCard">
			<!-- Profile pictu<re image-->
			<div class="text-danger" id="eduAddErr"></div>
			<div class="row">


				<div class="col-3 form-group">
					<label for="qualtype" class="form-label">Board :</label> <select
						class="form-select" id="qualtype">
						<c:forEach items="${qualifications }" var="qual">
							<option value="${qual.id }">${qual.name }</option>

						</c:forEach>

					</select>
				</div>
				<div class="col-3 form-group">
					<label for="stream" class="form-label">Stream :</label> <input
						type="text" class="form-control"
						placeholder="Enter the Stream/Honours" id="stream" />

				</div>
				<div class="col-3 form-group">
					<label for="percentage" class="form-label">Percentage :</label> <input
						type="number" class="form-control"
						placeholder="Enter the percentage" max="100" id="percentage" />

				</div>
				<div class="col-3 form-group">
					<button class="btn btn-primary " type="button" id="addQualButton">Add</button>
				</div>

			</div>
			<table class="table table-striped" id="qualTable">
				<thead>
					<tr>
						<td>Qualification</td>
						<td>Stream/Honours</td>
						<td>Percentage</td>
						<td>Action</td>

					</tr>
				</thead>
				<tbody>
					<c:if test="${studentQual.size()>0 }">
						<c:forEach items="${studentQual }" var="stQual">
							<tr>
								<td>${stQual.qualification }</td>
								<td>${stQual.stream }</td>
								<td>${stQual.percentage }<input type="hidden"
									name="${stQual.stQid}" id="${stQual.stQid}" /></td>
								<td><button type="button" class="btn btn-danger delete-btn"
										data-bs-toggle="modal" data-bs-target="#deleteStQualModal"
										data-studentqual-id="${stQual.stQid}">Delete</button></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>



		</div>
		<table class="table table-striped" id="delQualTable" hidden="hidden">
		<tbody>
		
		</tbody>
		</table>
	</div>
	<form:button type="submit" class="btn btn-primary"
		id="qualificationsSumbit"> Submit
									</form:button>
</div>


</html>