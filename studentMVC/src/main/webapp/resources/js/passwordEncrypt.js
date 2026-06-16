

export function encryptPassword(password) {
	
    const encrypt = new JSEncrypt();
    encrypt.setPublicKey(publicKey);

    let passwordField = document.getElementById(password);

    let encryptedPassword = encrypt.encrypt(passwordField.value);

    passwordField.value = encryptedPassword;
	console.log("Encrypt password called")
}
