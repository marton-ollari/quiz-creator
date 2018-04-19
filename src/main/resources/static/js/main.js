$(document).ready(function () {
    addEventListeners()
});

function addEventListeners() {
    createQuizEventListener();
    $.getJSON("/islogged", function (isLogged) {
        if (isLogged === "false"){
            $(".login-modal-button").removeClass("hidden");
            $(".register-modal-button").removeClass("hidden");
        } else {
            $(".create-quiz-button").removeClass("hidden");
            $(".play-button").removeClass("hidden");
            $(".logout-button").removeClass("hidden");
        }
    })
}

function createQuizEventListener() {
    document.getElementsByClassName("save-quiz-button")[0].addEventListener("click", function () {
        var quizName = document.getElementsByClassName("quiz-name")[0].value;
        document.getElementsByClassName("question-dropdown")[0].innerHTML = "<h2>"+  quizName +"</h2><select id=\"list\" class=\"btn btn-warning \">\n" +
            "    <option value=\"QA\">Question - Answer</option>\n" +
            "    <option value=\"CTP\">Connect to Pictures</option>\n" +
            "    <option value=\"AN\">Anagram</option>\n" +
            "    <option value=\"AC\">Association Circle</option>\n" +
            "</select>"
        //save quiz to db
        $.post( "/save-quiz", { quizname : quizName } );
        document.getElementById("list").addEventListener("click", dropdownEventListeners );
    });
}

function dropdownEventListeners() {
    var e = document.getElementById("list");
    document.getElementsByClassName("question-form")[0].innerHTML = questionform[e.options[e.selectedIndex].value] +
            "    <button id=\"left-arrow\" type=\"button\" class = \"btn btn-warning \"> <i class=\"far fa-3x fa-caret-square-left\"></i> </button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
            "    <button id=\"right-arrow\" type=\"button\" class = \"btn btn-warning \"> <i class=\"far fa-3x fa-caret-square-right\"></i> </button><br>\n"


}


