$(document).ready(function(){

    var usernameAvailable = false;

    $("#userName").on("change", async function(){
        alert($("#username").val());
        // Use database to determine if username is available
        let desiredUsername = $("#username").val();
        $("#usernameError").html("Username:" + desiredUsername);
        $("#usernameError").css("color", "green");
        if (desiredUsername.equals("user")){
            usernameAvailable = true;
        }
    });

    $("#loginForm").on("submit", function(event){
        //alert("Submitting form...");
        // if(!isFormValid()){
        //     event.preventDefault();
        // }
    });

    function isFormValid(){
        isValid = true;
        // Check if Username is available
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

});