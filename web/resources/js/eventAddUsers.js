function myFunction() {
    var users = document.forms["addUsersInEvent"];
    var i;
    var z=0;
    for (i = 0; i < users.length; i++) {
        if (users[i].checked) {
            z++;
        }
    }
    if(z>0){
        document.getElementById("subBtn").disabled = false;
    }
    else{
        document.getElementById("subBtn").disabled = true;
    }

}
