
function validateForm() {
    var x = document.forms["changePassword"]["newPassword"].value;
    var y = document.forms["changePassword"]["confirmNewPassword"].value;
    if (x != y ) {
        alert("confirm password and new password must match ");
        return false;
    }
}