var questionform = {
    "QA" : createQuestionForms("simple", 1),


    "CTP" : createPictureQuestionForm("connect_to_pictures"),


    "AN" : createQuestionForms("anagram", 5),


    "AC": createQuestionForms("association_circle", 10),


    "SQ": createQuestionForms("speed_questions", 10)
};


function createQuestionForms(name, number) {
    var form = "<table class=\"table table-bordered center\"><thead><tr>\n" +
        "        <th>Question</th>\n" +
        "        <th>Answer</th>\n" +
        "        </tr></thead><tbody>\n";
    for (var i=0; i<number; i++) {
        form += "<tr>" +
            "<td>Q" + (i + 1) + ":<span><input type=\"text\" name=\"" + name + (i + 1) + "question\"/></span></td>" +
        "<td>A" + (i + 1) + ":<span><input type=\"text\" name=\"" + name + (i + 1) + "answer\"/></span></td>" +
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
    for (var i=0; i<5; i++) {
        form += "<tr>" +
            "<td>URL" + (i + 1) + ":<span><input type=\"text\" name=\"" + name + (i + 1) + "question\"/></span></td>" +
            "<td>A" + (i + 1) + ":<span><input type=\"text\" name=\"" + name + (i + 1) + "answer\"/></span></td>" +
            "</tr>";
    }
    form += "</tbody></table>";
    return form;
}

function saveQuestions(name, number) {
    var json = {};
    json["type"] = name;
    json["number"] = number;
    for (var i=0; i<number; i++){
        var question = document.getElementsByName(name+(i+1)+"question")[0].value;
        var answer = document.getElementsByName(name+(i+1)+"answer")[0].value;
        if (question === "" || answer === ""){
            document.getElementsByClassName("question-form")[0].innerHTML = "Fill all question and answer";
            return;
        }
        json["question"+(i+1)]= question;
        json["answer"+(i+1)] = answer;
    }
    $.post("/save-question", json);
    document.getElementsByClassName("question-form")[0].innerHTML = "Question saved";
}