$(document).ready(function(){


    $("#itemForm").on("submit", function(action){
       // alert("submitting form");
        if(!isFormValid()){
            action.preventDefault();
            return;
        }
        let id = sessionStorage.getItem("user");
        let name = $("#itemName").val();
        let desc = $("#itemDesc").val();
        let link = $("#itemLink").val();
        let image = $("#itemImage").val()
        let list = $("#itemList").val();
        $.ajax({
            url: "http://localhost:8080/items/additem",
            type: "POST",
            async: false,
            data: {id: id, name: name, desc: desc, link: link, image: image, list: list},
            success: function() {
                alert("Item Successfully added!");
            },
            error: function() {
                alert("Something went wrong, please try again");
                action.preventDefault();
            }
        });
    });

    function isFormValid(){
        isValid = true;

        if($("#itemName").val().length == 0){
            isValid = false;
            $("#nameError").html("Item Name is required");
            $("#nameError").css("color", "red");
        }

        if($("#itemList").val().length ==0){
            $("#listError").html("List Name is required");
            $("#listError").css("color", "red");
            isValid = false;
        }
        return isValid;
    }

});