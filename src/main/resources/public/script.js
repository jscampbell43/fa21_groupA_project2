$(document).ready(function(){

    var usernameAvailable = true;

    $("#loginForm").on("submit", function(action){
        if(!isFormValid()){
            action.preventDefault();
            return;
        }
        let username = $("#loginUserName").val();
        let password = $("#loginPassword").val();
        $.ajax({
            url: "http://localhost:8080/users/" + username,
            type: "GET",
            async: false,
            success:function(data) {
                let dbUser = data.username;
                let dbPass = data.password;
                if (username==dbUser && password==dbPass) {
                    alert("login Success");
                    sessionStorage.setItem("user", data.user_id);
                    //alert(sessionStorage.getItem("user"));
                } else {
                    action.preventDefault();
                    alert("login failed");
                }
            }
        });
    });

    function isFormValid(){
        isValid = true;
        // Check if Username is available
        if(!usernameAvailable){
            isValid = false;
        }

        // Check if Username field is blank
        if($("#loginUserName").val().length == 0){
            isValid = false;
            $("#usernameError").html("Username is required");
            $("#usernameError").css("color", "red");
        }
        // Check passwords match
        if($("#loginPassword").val().length ==0){
            $("#passwordError").html("Password is required");
            $("#passwordError").css("color", "red");
            isValid = false;
        }
        return isValid;
    }

    $("#signUpBtn").on("click", function() {
       window.location.href = "/signUp.html";
    });
});