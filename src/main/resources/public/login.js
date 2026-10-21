$(document).ready(function(){

    var usernameAvailable = true;

    $("#loginForm").on("submit", function(action){
        action.preventDefault(); // Prevent default form submission
        if(!isFormValid()){
            return;
        }
        let username = $("#loginUserName").val();
        let password = $("#loginPassword").val();
        $.ajax({
            url: "http://localhost:8080/users/login",
            type: "POST",
            data: {username: username, password: password},
            success: function(response) {
                if (response === "Login successful") {
                    alert("Login successful!");
                    window.location.href = "http://localhost:8080/homeUser.html";
                } else {
                    alert(response);
                }
            },
            error: function(xhr, status, error) {
                alert("Login failed. Error: " + xhr.status);
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