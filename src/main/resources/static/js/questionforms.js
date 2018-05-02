var questionform = {
    "QA" : createQuestionForms("simple", 1),


    "CTP" : createPictureQuestionForm("connect_to_pictures"),


    "AN" : createQuestionForms("anagram", 5),


    "AC": createQuestionForms("association_circle", 10),


    "SQ": createQuestionForms("speed_questions", 10)
};


function createQuestionForms(name, number) {
    var form = "<div class='form-container'>";
    for (var i=0; i<number; i++){
      form += "<div class='question-container'><h4>" +name+(i+1) +
          "</h4>Question:<span><input type=\"text\" name=\""+name + (i+1) + "question\"/></span>" +
          "Answer:<span><input type=\"text\" name=\""+name + (i+1) + "answer\"/></span></div>";
    };
    form += "</div>";
    return form;
}

function createPictureQuestionForm(name) {
    var form = "<div class='form-container'>";
    for (var i=0; i<5; i++){
        form += "<div class='question-container'><h4>" +name+(i+1) +
            "</h4>Picture URL:<span><input type=\"text\" name=\""+name + (i+1) + "question\"/></span>" +
            "Answer:<span><input type=\"text\" name=\""+name + (i+1) + "answer\"/></span></div>";
    };
    form += "</div>";
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
            alert("Fill every question and answer");
            return;
        }
        json["question"+(i+1)]= question;
        json["answer"+(i+1)] = answer;
    }
    $.post("/save-question", json);
}