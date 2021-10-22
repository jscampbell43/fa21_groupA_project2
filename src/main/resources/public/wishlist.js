$(document).ready(function(){
    let id = sessionStorage.getItem("user");
    console.log(id);
    $.ajax({
        url: "http://localhost:8080/items/user/" + id,
        type: "GET",
        success: function (data) {
           // alert("Call success");
            console.log(data);
            $(data).each(function(){
                console.log(this);
                let itemInfo = '<p>Item id: '+this.id+' Item name: ' +this.name+' Item Link: <a href="'+this.item_link+'">'+this.item_link+'</a></p>';
                let itemImage = '<img src="'+this.img_url+'" alt="image"><br>';
                let itemInfo2 = '<p>Description: '+this.description+'<br>List name: '+this.list_name+'</p><br><br>';
                $("#wishlistsDiv").append(itemInfo, itemImage, itemInfo2);
            });
        },
        error: function () {
            $("#wishlistsDiv").empty();
            $("#wishlistsDiv").append("Something went wrong, please enter an existing list name");
        }
    });

    $("#searchButton").on("click", function() {
        $.ajax({
            url: "http://localhost:8080/items/list/" + $("#searchLists").val() + "/user/" + id,
            type: "GET",
            success: function (data) {
                $("#wishlistsDiv").empty();
                // alert("Call success");
                console.log(data);
                $(data).each(function () {
                    console.log(this);
                    let itemInfo = '<p>Item id: ' + this.id + ' Item name: ' + this.name + ' Item Link: <a href="' + this.item_link + '">' + this.item_link + '</a></p>';
                    let itemImage = '<img src="' + this.img_url + '" alt="image"><br>';
                    let itemInfo2 = '<p>Description: ' + this.description + '<br>List name: ' + this.list_name + '</p><br><br>';
                    $("#wishlistsDiv").append(itemInfo, itemImage, itemInfo2);
                });
            },
            error: function () {
                $("#wishlistsDiv").empty();
                $("#wishlistsDiv").append("Something went wrong, please enter an existing list name");
            }
        });
    });
});