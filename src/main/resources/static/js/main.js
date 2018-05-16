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
            var quizName = document.getElementsByClassName("quiz-name-input")[0].value;
            if(document.getElementsByClassName("quiz-name")[0].value !== "") {
                document.getElementsByClassName("quiz-name")[0].innerHTML = "<h4>" + quizName + "</h4>";
                document.getElementsByClassName("group-numbers")[0].innerHTML = "<h4>Question Group <span id=\"group_number\">1</span>/<span id=\"all_groups\">1</span></h4>";
                $.post("/save-quiz", {quizname: quizName});
                createQuestionDropdown();
                createArrowButtons();
                document.getElementsByClassName("question-form")[0].innerHTML = questionform["SIMPLE"];
            }
        });
}

function dropdownEventListeners() {
    var dropdown = document.getElementById("list");
    document.getElementsByClassName("question-form")[0].innerHTML = questionform[dropdown.options[dropdown.selectedIndex].value];
}

function rightArrowEventListener() {
    if(document.getElementById("group_number").innerHTML == document.getElementById("all_groups").innerHTML){
        var dropdown = document.getElementById("list");
        switch(dropdown.options[dropdown.selectedIndex].value) {
            case "SIMPLE":
                saveQuestions("SIMPLE", 1);
                break;
            case "ANAGRAM":
                saveQuestions("ANAGRAM", 5);
                break;
            case "ASSOCIATION_CIRCLE":
                saveQuestions("ASSOCIATION_CIRCLE", 10);
                break;
            case "CONNECT_TO_PICTURES":
                saveQuestions("CONNECT_TO_PICTURES", 5);
                break;
        }
    } else {
        if (parseInt(document.getElementById("group_number").innerHTML)+1 == parseInt(document.getElementById("all_groups").innerHTML)){
            createQuestionDropdown();
            updateGroupNumber(1);
        }  else {
            getSavedQuestionGroup();
            updateGroupNumber(1);

        }
        createArrowButtons();
        // TODO update questions
        }
}

function leftArrowEventListener() {
    if (document.getElementsByClassName("question-dropdown")[0].innerHTML != null){
        document.getElementsByClassName("question-dropdown")[0].innerHTML = "";
    }
    updateGroupNumber(-1);
    document.getElementsByClassName("message")[0].innerHTML = "";
    // TODO fill form with saved questions
    getSavedQuestionGroup();
    createArrowButtons();
}



function createArrowButtons() {
    document.getElementsByClassName("arrow-buttons")[0].innerHTML = "<button id=\"left-arrow\" class = \"btn btn-warning \"> <i class=\"far fa-3x fa-caret-square-left\"></i> </button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
        "<button id=\"right-arrow\" class = \"btn btn-warning \"> <i class=\"far fa-3x fa-caret-square-right\"></i> </button><br>";
    if (document.getElementById("group_number").innerHTML == "1"){
        document.getElementById("left-arrow").disabled = true;
    }
    document.getElementById("right-arrow").addEventListener("click", rightArrowEventListener);
    document.getElementById("left-arrow").addEventListener("click", leftArrowEventListener);
}

function updateGroupNumber(num) {
    document.getElementById("group_number").innerHTML= parseInt(document.getElementById("group_number").innerHTML)+num;
}

