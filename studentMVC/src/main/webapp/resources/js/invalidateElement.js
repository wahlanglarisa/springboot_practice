export function invalidateElement(elementMarkInvalid) {
    elementMarkInvalid.removeClass("is-valid");
    elementMarkInvalid.addClass("is-invalid");
}
export function validateElement(elementMarkValid) {
    elementMarkValid.removeClass("is-invalid");

    elementMarkValid.addClass("is-valid");
}
