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
                    "</select>" +
                    "<h4>Question Group <span id=\"group_number\">1</span>/<span id=\"all_groups\">1</span></h4>";
                $.post("/save-quiz", {quizname: quizName});
                document.getElementById("list").addEventListener("click", dropdownEventListeners);
            }
        });
}

function dropdownEventListeners() {
    var dropdown = document.getElementById("list");
    document.getElementsByClassName("question-form")[0].innerHTML = questionform[dropdown.options[dropdown.selectedIndex].value] +
            "<button id=\"left-arrow\" class = \"btn btn-warning \"> <i class=\"far fa-3x fa-caret-square-left\"></i> </button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
            "<button id=\"right-arrow\" class = \"btn btn-warning \"> <i class=\"far fa-3x fa-caret-square-right\"></i> </button><br>";
    document.getElementById("right-arrow").addEventListener("click", rightArrowEventListener);
    document.getElementById("left-arrow").addEventListener("click", leftArrowEventListener);
}

function rightArrowEventListener() {
    updateGroupNumber(1);
    if(parseInt(document.getElementById("group_number").innerHTML) == parseInt(document.getElementById("all_groups").innerHTML)){
        //TODO show dropdown
        var dropdown = document.getElementById("list");
        switch(dropdown.options[dropdown.selectedIndex].value) {
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
    } else {
        // TODO update questions
        // TODO fill form with saved questions
        getSavedQuestionGroup();
    }
}


function leftArrowEventListener() {
    updateGroupNumber(-1);
    // TODO fill form with saved questions
    // TODO hide dropdown
    getSavedQuestionGroup();
}

function updateGroupNumber(num) {
    document.getElementById("group_number").innerHTML= parseInt(document.getElementById("group_number").innerHTML)+num;
}

function getSavedQuestionGroup() {

}

