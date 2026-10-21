$(document).ready(function(){


    $("#signupForm").on("submit", function(action){
        action.preventDefault(); // Prevent default form submission
        if(!isFormValid()){
            return;
        }
        let id = sessionStorage.getItem("user");
        let username = $("#username").val();
        let password = $("#password").val();
        let passwordAgain = $("#passwordAgain").val();
        $.ajax({
            url: "http://localhost:8080/users/signup",
            type: "POST",
            data: {username: username, password: password, role: "USER"},
            success: function() {
                alert("User Successfully added!");
                window.location.href = "http://localhost:8080/index.html";
            },
            error: function(xhr, status, error) {
                alert("Something went wrong, please try again. Error: " + xhr.status);
            }
        });
    });

    function isFormValid(){
        isValid = true;

        if($("#username").val().length == 0){
            isValid = false;
            $("#usernameError").html("Username is required");
            $("#usernameError").css("color", "red");
        }

        if($("#password").val().length ==0){
            $("#passwordAgainError").html("Password is required");
            $("#passwordAgainError").css("color", "red");
            isValid = false;
        }

        if($("#password").val() != $("#passwordAgain").val()){
            $("#passwordAgainError").html("Passwords must match!");
            $("#passwordAgainError").css("color", "red");
            isValid = false;
        }
        return isValid;
    }

    // $('#signUpSubmit').click(function(){
    //     window.location.href='index.html';
    //     if(!isFormValid()){
    //         //action.preventDefault();
                // Something like this to stop button loading
    //     }
    // });

});