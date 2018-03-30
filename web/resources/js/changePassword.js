
function validateForm() {
    var x = document.forms["changePassword"]["newPassword"].value;
    var y = document.forms["changePassword"]["confirmNewPassword"].value;
    if (x != y ) {
        alert("new password and confirm password must match ");
        return false;
    }
    if(x.length<5 || y.length<5){
        alert("password length must have min 6 symbols");
        return false;
    }
}