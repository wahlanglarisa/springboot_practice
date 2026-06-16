export async function addState(stateElement, countryElement) {
    stateElement.empty();
    stateElement.removeAttr('disabled');

    stateElement.append(`<option value="0">
			Select a State</option>`);
    await $.ajax({
        "url": contextPath + "/getState_codes",
        dataType: "json",
        "data": {
            "countryCode": countryElement.val()
        },
        "success": function(response) {
            response.forEach((element) => {
                stateElement.append(`<option value="${element.stateCode}">${element.stateName}</option>`);
            });
            console.log(response);
        },
        "method": "get"
    });
}
export async function addDistrict(districtElement, stateElement) {
    districtElement.empty();
    districtElement.removeAttr('disabled');

    districtElement.append(`<option value="0">Select a District</option>`);

    await $.ajax({
        "url": contextPath + "/getDistrict_codes",
        dataType: "json",
        "data": {
            "stateCode": stateElement.val()
        },
        "success": function(response) {
            response.forEach((element) => {
                districtElement.append(`<option value="${element.districtCode}">${element.districtName}</option>`);
            });
            console.log(response);
        },
        "method": "get"
    });
}
