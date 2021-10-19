$(document).ready(function(){

    var usernameAvailable = false;

    $("#userName").on("change", async function(){
        alert($("#username").val());
        // Use database to determine if username is available
        let desiredUsername = $("#username").val();
        $("#usernameError").html("Username:" + desiredUsername);
        $("#usernameError").css("color", "green");
        if (desiredUsername === "user"){
            usernameAvailable = true;
        }
    });

    $("#loginForm").on("submit", function(event){
        alert("Submitting Login form...");
        if(!isLoginFormValid()){
            event.preventDefault();
        }
    });

    function isLoginFormValid(){
        isValid = true;
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

    $('#signUpBtn').click(function(){
        window.location.href='signUp.html';
    })

    $("#signupForm").on("submit", function(event){
        alert("Submitting Signup form...");
        if(!isSignUpFormValid()){
            event.preventDefault();
        }
    });

    async function isSignUpFormValid(){
        isValid = true;
        // Check if Username is available
        await $.ajax({
            url: "https://cst438-project2-groupa-front.herokuapp.com/users/allUsers",
            success: function(result) {
                $("#SignUpSuccess").html('<span id ="SignUpSuccess">' + result + '</span> <br><br>');
                alert("Usernames: " + result);
                console.log(result);
            }
        });

        if(!usernameAvailable){
            isValid = false;
            $("#usernameError").html("Username is Not Available");
            $("#usernameError").css("color", "red");
        }
        // Check if Username field is blank
        if($("#loginUserName").val().length == 0){
            isValid = false;
            $("#usernameError").html("Username is required");
            $("#usernameError").css("color", "red");
        }
        // Check password field is not blank
        if($("#loginPassword").val().length ==0){
            $("#passwordError").html("Password is required");
            $("#passwordError").css("color", "red");
            isValid = false;
        }
        // Create account

        return isValid;
    }

});