$(document).ready(function () {
    addEventListeners()
});

function addEventListeners() {
    $.getJSON("/islogged", function (isLogged) {
        if (isLogged === "false"){
            $(".login-modal-button").removeClass("hidden");
            $(".register-modal-button").removeClass("hidden");
        } else {
            $(".create-quiz-button").removeClass("hidden");
            $(".play-button").removeClass("hidden");
            $(".logout-button").removeClass("hidden");
            createQuizEventListener();
        }
    })
}

function createQuizEventListener() {
    document.getElementsByClassName("create-quiz-button")[0].addEventListener("click", function () {
        document.getElementsByClassName("question-dropdown")[0].innerHTML = "<select id=\"list\">\n" +
            "    <option value=\"QA\">Question - Answer</option>\n" +
            "    <option value=\"CTP\">Connect to Pictures</option>\n" +
            "    <option value=\"AN\">Anagram</option>\n" +
            "    <option value=\"AC\">Association Circle</option>\n" +
            "</select>"

        document.getElementById("list").addEventListener("click", dropdownEventListeners );
    });
}

function dropdownEventListeners() {
    var e = document.getElementById("list");
    document.getElementsByClassName("question-form")[0].innerHTML = questionform[e.options[e.selectedIndex].value]
}


