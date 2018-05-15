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
            }
        });
}

function dropdownEventListeners() {
    var dropdown = document.getElementById("list");
    document.getElementsByClassName("question-form")[0].innerHTML = questionform[dropdown.options[dropdown.selectedIndex].value] +
            "<button id=\"left-arrow\" class = \"btn btn-warning \"> <i class=\"far fa-3x fa-caret-square-left\"></i> </button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
            "<button id=\"right-arrow\" class = \"btn btn-warning \"> <i class=\"far fa-3x fa-caret-square-right\"></i> </button><br>";
    disableButton();
    document.getElementById("right-arrow").addEventListener("click", rightArrowEventListener);
    document.getElementById("left-arrow").addEventListener("click", leftArrowEventListener);
}

function rightArrowEventListener() {
    if(document.getElementById("group_number").innerHTML == document.getElementById("all_groups").innerHTML){
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
        if (parseInt(document.getElementById("group_number").innerHTML)+1 == parseInt(document.getElementById("all_groups").innerHTML)){
            createQuestionDropdown();
        }  else {
            getSavedQuestionGroup();
        }
        // TODO update questions
        updateGroupNumber(1);
        dropdownEventListeners()
        }
}

function leftArrowEventListener() {
    if (document.getElementsByClassName("question-dropdown")[0].innerHTML != null){
        document.getElementsByClassName("question-dropdown")[0].innerHTML = "";
    }
    updateGroupNumber(-1);
    document.getElementsByClassName("message")[0].innerHTML = "";
    // TODO fill form with saved questions
    disableButton();
    getSavedQuestionGroup();
}



function disableButton() {
    if (document.getElementById("group_number").innerHTML == "1"){
        document.getElementById("left-arrow").disabled = true;
    }
}

function updateGroupNumber(num) {
    document.getElementById("group_number").innerHTML= parseInt(document.getElementById("group_number").innerHTML)+num;
}

