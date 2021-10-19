

$(document).ready(function(){

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

    function isLoginFormValid(){
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

    $('#signUpBtn').click(function(){
        window.location.href='signUp.html';
    })

    $("#signupForm").on("submit", function(event){
        alert("Submitting Signup form...");
        if(!isSignUpFormValid()){
            event.preventDefault();
        }
        //isSignUpFormValid();
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
                for(user in result){
                    if($("#username").val() == user){
                        isValid = false;
                    }
                }
            }
        });

        if($("#username").val().length == 0){
            isValid = false;
            $("#usernameError").html("Username is required");
            $("#usernameError").css("color", "red");
        }
        // Check password field is not blank
        if($("#password").val().length ==0){
            $("#passwordError").html("Password is required");
            $("#passwordError").css("color", "red");
            isValid = false;
        }
        return isValid;
    }

});