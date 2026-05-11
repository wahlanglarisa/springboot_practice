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
		<div class="card-body row">
			<!-- Profile picture image-->
			<div class="col-xl-6 ">
				<strong>Permanent Address</strong> <br> <br>
				<div class="col-12 form-group">
					<label for="permaddressline1">Address Line 1 : </label>
					<form:input path="permaddressline1" placeholder="Address Line 1"
						class=" form-control" autocomplete="off" />
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrperm1err">This is required</span>
				</div>
				<br>
				<div class="col-12 form-group">
					<label for="permaddressline2">Address Line 2 : </label>

					<form:input path="permaddressline2" placeholder="Address Line 2"
						class=" form-control" autocomplete="off" />
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrperm2err">This is required</span>
				</div>
				<br>
				<div class="col-12 form-group">
					<label for="permaddressline3">Address Line 3 : </label>

					<form:input path="permaddressline3" placeholder="Address Line 3"
						class=" form-control" autocomplete="off" />
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrperm3err">This is required</span>
				</div>
				<br>

				<div class="col-12 form-group">
					<label for="permCountryCode">Country : </label>

					<form:select path="permCountryCode" class="form-select">
						<form:option value="0" selected="selected">Select a country</form:option>
						<form:options items="${countries }" itemValue="countryCode"
							itemLabel="countryName" />
					</form:select>
					<span class="text-danger invalid-feedback" hidden="true"
						id="permCountryErr">This is required</span>
				</div>
				<br>
				<div class="col-12 form-group">
					<label for="permStateCode">State : </label>

					<form:select path="permStateCode" class="form-select">

						<c:if test="${not empty student.permStateCode }">
							<form:options items="${permStates }" itemValue="stateCode"
								itemLabel="stateName" />
						</c:if>
					</form:select>
					<span class="text-danger invalid-feedback" hidden="true"
						id="permStateErr">This is required</span>
				</div>
				<br>
				<div class="col-12 form-group">
					<label for="permDistrictCode">District : </label>

					<form:select path="permDistrictCode" class="form-select">
						<c:if test="${not empty student.permDistrictCode }">
							<form:options items="${permDistricts }" itemValue="districtCode"
								itemLabel="districtName" />
						</c:if>
					</form:select>
					<span class="text-danger invalid-feedback" hidden="true"
						id="permDistrictErr">This is required</span>
				</div>


			</div>
			<div class="col-xl-6">
				<!-- Account details card-->

				<strong>Present Address</strong>

				<!-- Profile picture image-->
				<br>
				<div class="col-12 check">
					<input type="checkbox" class="form-check-input"
						id="presentAddrCheck"><label for="presentAddrCheck"
						class="form-check-label">Same as permanent address</label>
				</div>
				<br>
				<div class="col-12 form-group">
					<label for="preaddressline1">Address Line 1 : </label>

					<form:input path="preaddressline1" placeholder="Address Line 1"
						class=" form-control" autocomplete="off" />
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrpre1err">This is required</span>
				</div>
				<br>
				<div class="col-12 form-group">
					<label for="preaddressline2">Address Line 2 : </label>

					<form:input path="preaddressline2" placeholder="Address Line 2"
						class=" form-control" autocomplete="off" />
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrpre2err">This is required</span>
				</div>
				<br>
				<div class="col-12 form-group">
					<label for="preaddressline3">Address Line 3 : </label>

					<form:input path="preaddressline3" placeholder="Address Line 3"
						class=" form-control" autocomplete="off" />
					<span class="text-danger invalid-feedback" hidden="true"
						id="addrpre3err">This is required</span>
				</div>
				<br>
				<div class="col-12 form-group">
					<label for="preCountryCode">Country : </label>

					<form:select path="preCountryCode" class="form-select">
						<form:option value="0" selected="selected">Select a country</form:option>

						<form:options items="${countries }" itemValue="countryCode"
							itemLabel="countryName" />
					</form:select>
					<span class="text-danger invalid-feedback" hidden="true"
						id="preCountryErr">This is required</span>
				</div>
				<br>
				<div class="col-12 form-group">
					<label for="preCountryCode">Country : </label>

					<form:select path="preStateCode" class="form-select">
						<c:if test="${not empty student.preStateCode }">
							<form:options items="${preStates }" itemValue="stateCode"
								itemLabel="stateName" />
						</c:if>
					</form:select>
					<span class="text-danger invalid-feedback" hidden="true"
						id="preStateErr">This is required</span>
				</div>
				<br>
				<div class="col-12">
					<label for="preDistrictCode">District : </label>

					<form:select path="preDistrictCode" class="form-select">
						<c:if test="${not empty student.preDistrictCode }">
							<form:options items="${preDistricts }" itemValue="districtCode"
								itemLabel="districtName" />
						</c:if>
					</form:select>
					<span class="text-danger invalid-feedback" hidden="true"
						id="preDistrictErr">This is required</span>
				</div>
			</div>
		</div>
	</div>

	<c:choose>
		<c:when test="${user.user_creation_status == 'NR'}">
			<form:button type="button" class="btn btn-primary" id="AddressButton"> Next &gt;&gt;
									</form:button>&nbsp;&nbsp;
		</c:when>
		<c:otherwise>
			<div class="row justify-content-center d-flex">
				<form:button type="submit" class="btn btn-primary col-5"
					id="AddressSubmitButton"> Submit
									</form:button>
				&nbsp;&nbsp;

				<form:button type="button" class="btn btn-primary col-5"
					id="AddressButton"> Next &gt;&gt;
									</form:button>
			</div>
		</c:otherwise>
	</c:choose>

</div>


</html>

