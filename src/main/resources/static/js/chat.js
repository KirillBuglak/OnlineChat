$(function () {
    let getMessageElement = function (message) {
        let item = $("<div class=\"message-item\"></div>");
        let header = $("<div class=\"message-header\"></div>");
        header.append($("<div class=\"datetime\">" + message.datetime + "</div>"));
        header.append($("<div class=\"username\">" + message.username + "</div>"));
        let textElement = $("<div class=\"message-text\"></div>");
        textElement.text(message.text);
        item.append(header, textElement);
        return item;
    };
    let getUserElement = function (user) {
        let item = $("<div class=\"user-item\"></div>");
        item.append($("<div class=\"uid\">ID: " + user.id + "</div>"));
        item.append($("<div class=\"uname\">" + user.name + "</div>"));
        return item;
    };
    let updateMessages = function () {
        $.get("/message", {}, function (response) {
            if (response.length == 0) {
                return;
            }
            $(".messages-list").html("");
            for (i in response) {
                let element = getMessageElement(response[i]);
                $(".messages-list").append(element);
            }
        });
    };
    let updateUsers = function () {
        $.get("/user", {}, function (response) {
            if (response.length == 0) {
                return;
            }
            $(".users-list").html("");
            for (i in response) {
                let element = getUserElement(response[i]);
                $(".users-list").append(element);
            }
        })
    }
    let initApplication = function () {
        $(".mesuse-lists").css({display: "flex"});
        $(".controls").css({display: "flex"});
        $('.button').on("click", function () {
            let text = $(".new-message").val();
            $.post("/message", {text: text}, function (response) {
                if (response.result) {
                    $(".new-message").val("");
                } else {
                    alert("Failed to send the message");
                }
            });
        });
        setInterval(updateMessages, 10000);
        updateUsers();//todo may be update is needed
    };
    let registerUser = function (name) {
        $.post("/auth", {name: name}, function (response) {
            if (response.result) {
                initApplication();
            }
        });
    };

    $.get("/init", {}, function (response) {
        if (!response.result) {
            let name = prompt("Enter your name");
            registerUser(name);
            return;
        }
        initApplication();
    });
});