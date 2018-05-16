var questionform = {
    "SIMPLE" : createQuestionForms(1),

    "CONNECT_TO_PICTURES" : createPictureQuestionForm(),

    "ANAGRAM" : createQuestionForms(5),

    "ASSOCIATION_CIRCLE": createQuestionForms(10),

    "SPEED_QUESTION": createQuestionForms(10)
};

function createQuestionDropdown() {
    document.getElementsByClassName("question-dropdown")[0].innerHTML = "<select id=\"list\" class=\"btn btn-warning \">\n" +
        "    <option value=\"SIMPLE\">Question - Answer</option>" +
        "    <option value=\"CONNECT_TO_PICTURES\">Connect to Pictures</option>" +
        "    <option value=\"ANAGRAM\">Anagram</option>" +
        "    <option value=\"ASSOCIATION_CIRCLE\">Association Circle</option>" +
        "</select>";
    document.getElementById("list").addEventListener("click", dropdownEventListeners);
}


function createQuestionForms(number) {
    var form = "<table class=\"table table-bordered center\"><thead><tr>\n" +
        "        <th>Question</th>\n" +
        "        <th>Answer</th>\n" +
        "        </tr></thead><tbody>\n";
    for (var i=1; i<=number; i++) {
        form += "<tr>" +
            "<td>Q" + i + ":<span><input type=\"text\" name=\"question" + i + "\"/></span></td>" +
        "<td>A" + i + ":<span><input type=\"text\" name=\"answer" + i  + "\"/></span></td>" +
        "</tr>";
    }
    form += "</tbody></table>";
    return form;
}

function createPictureQuestionForm(name) {
    var form = "<table class=\"table table-bordered center\"><thead><tr>\n" +
        "        <th>Picture URL</th>\n" +
        "        <th>Answer</th>\n" +
        "        </tr></thead><tbody>\n";
    for (var i=1; i<=5; i++) {
        form += "<tr>" +
            "<td>URL" + i + ":<span><input type=\"text\" name=\"question" +i + "\"/></span></td>" +
            "<td>A" + i + ":<span><input type=\"text\" name=\"answer"+ i + "\"/></span></td>" +
            "</tr>";
    }
    form += "</tbody></table>";
    return form;
}

function saveQuestions(name, number) {
    var json = {};
    json["type"] = name;
    json["number"] = number;
    for (var i=1; i<=number; i++){
        var question = document.getElementsByName("question"+i)[0].value;
        var answer = document.getElementsByName("answer"+i)[0].value;
        if (question === "" || answer === ""){
            document.getElementsByClassName("message")[0].innerHTML = "Fill all question and answer";
            return;
        }
        json["question"+i]= question;
        json["answer"+i] = answer;
    }
    $.post("/save-question", json);
    document.getElementsByClassName("message")[0].innerHTML = "Question saved";
    document.getElementById("all_groups").innerHTML= parseInt(document.getElementById("all_groups").innerHTML)+1;
    updateGroupNumber(1);
    dropdownEventListeners();
    createArrowButtons();
}

function getSavedQuestionGroup() {
    var groupNumber = parseInt(document.getElementById("group_number").innerHTML);
    $.getJSON("/group/"+groupNumber, function(questions) {
        console.log(questions);
        console.log(questions['type']);
    });

}