<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>





<div class="row">
	<!-- Profile picture card-->
	<div class="card mb-4 mb-xl-0 shadow">
		<div class="card-header">Address Details</div>
		<div class="card-body text-center row">
			<!-- Profile picture image-->
			<div class="col-xl-6 ">
				<strong>Permanent Address</strong> <br>
				<div class="col-12">

					<form:input path="permaddressline1" placeholder="Address Line 1"
						class=" form-control" required="true" autocomplete="off" />
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrperm1err">This is required</span>
				</div>
				<br>
				<div class="col-12">

					<form:input path="permaddressline2" placeholder="Address Line 2"
						class=" form-control"  autocomplete="off" />
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrperm2err">This is required</span>
				</div>
				<br>
				<div class="col-12">

					<form:input path="permaddressline3" placeholder="Address Line 3"
						class=" form-control"  autocomplete="off" />
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrperm3err">This is required</span>
				</div>
				<br>

				<div class="col-12">

					<form:select path="permCountryCode" class="form-select">
						<form:options items="${countries }" itemValue="countryCode"
							itemLabel="countryName"  required="true"/>
					</form:select>
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrperm3err">This is required</span>
				</div>
				<br>
				<div class="col-12">
					<form:select path="permStateCode" class="form-select" required="true">
						<c:if test="${not empty student.permStateCode }">
							<form:options items="${permStates }" itemValue="stateCode"
								itemLabel="stateName" />
						</c:if>
					</form:select>
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrperm3err">This is required</span>
				</div>
				<br>
				<div class="col-12">
					<form:select path="permDistrictCode" class="form-select" required="true">
						<c:if test="${not empty student.permDistrictCode }" >
							<form:options items="${permDistricts }" itemValue="districtCode"
								itemLabel="districtName" />
						</c:if>
					</form:select>
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrperm3err">This is required</span>
				</div>


			</div>
			<div class="col-xl-6">
				<!-- Account details card-->

				<strong>Present Address</strong>

				<!-- Profile picture image-->
				<br>
				<div class="col-12 check"><input type="checkbox" class="form-check-input" id="presentAddrCheck"><label for="presentAddrCheck" class="form-check-label">Same as permanent address</label></div>
				<br>
				<div class="col-12">

					<form:input path="preaddressline1" placeholder="Address Line 1"
						class=" form-control" required="true" autocomplete="off" />
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrpre1err">This is required</span>
				</div>
				<br>
				<div class="col-12">

					<form:input path="preaddressline2" placeholder="Address Line 2"
						class=" form-control" autocomplete="off" />
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrpre2err">This is required</span>
				</div>
				<br>
				<div class="col-12">

					<form:input path="preaddressline3" placeholder="Address Line 3"
						class=" form-control" autocomplete="off" />
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrpre3err">This is required</span>
				</div>
				<br>
				<div class="col-12">

					<form:select path="preCountryCode" class="form-select" required="true">
						<form:options items="${countries }" itemValue="countryCode"
							itemLabel="countryName" />
					</form:select>
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrperm3err">This is required</span>
				</div>
				<br>
				<div class="col-12">
					<form:select path="preStateCode" class="form-select" required="true">
						<c:if test="${not empty student.preStateCode }">
							<form:options items="${preStates }" itemValue="stateCode"
								itemLabel="stateName" />
						</c:if>
					</form:select>
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrperm3err">This is required</span>
				</div>
				<br>
				<div class="col-12">
					<form:select path="preDistrictCode" class="form-select" required="true">
						<c:if test="${not empty student.preDistrictCode }">
							<form:options items="${preDistricts }" itemValue="districtCode"
								itemLabel="districtName" />
						</c:if>
					</form:select>
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrperm3err">This is required</span>
				</div>
			</div>
		</div>
	</div>
</div>


</html>

