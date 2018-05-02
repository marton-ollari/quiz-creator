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
            if(document.getElementsByClassName("quiz-name")[0].value !== "") {
                document.getElementsByClassName("question-dropdown")[0].innerHTML = "<h2>" + quizName + "</h2><select id=\"list\" class=\"btn btn-warning \">\n" +
                    "    <option value=\"QA\">Question - Answer</option>" +
                    "    <option value=\"CTP\">Connect to Pictures</option>" +
                    "    <option value=\"AN\">Anagram</option>" +
                    "    <option value=\"AC\">Association Circle</option>" +
                    "</select>";
                $.post("/save-quiz", {quizname: quizName});
                document.getElementById("list").addEventListener("click", dropdownEventListeners);
            }
        });
}

function dropdownEventListeners() {
    var e = document.getElementById("list");
    document.getElementsByClassName("question-form")[0].innerHTML = questionform[e.options[e.selectedIndex].value] +
            "<button id=\"left-arrow\" class = \"btn btn-warning \"> <i class=\"far fa-3x fa-caret-square-left\"></i> </button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
            "<button id=\"right-arrow\" class = \"btn btn-warning \"> <i class=\"far fa-3x fa-caret-square-right\"></i> </button><br>";
    document.getElementById("right-arrow").addEventListener("click", rightArrowEventListener);
}

function rightArrowEventListener() {
    var e = document.getElementById("list");
    switch(e.options[e.selectedIndex].value) {
        case "QA":
            saveQuestions("simple", 1);
            break;
        case "AN":
            saveQuestions("anagram", 5);
            break;
        case "AC":
            saveQuestions("association_circle", 10);
            break;
        case "CTP":
            saveQuestions("connect_to_pictures", 5);
            break;
    }
    document.getElementsByClassName("question-form")[0].innerHTML = "Question saved";
}


