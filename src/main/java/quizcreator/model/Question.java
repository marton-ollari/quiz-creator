package quizcreator.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String question;

    private String answer;

    private int lineNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "questionGroup_id")
    private QuestionGroup questionGroup;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Answer> answers;

    public Question() {
    }

    public Question(String question, String answer, int lineNumber, QuestionGroup questionGroup) {
        this.question = question;
        this.answer = answer;
        this.lineNumber = lineNumber;
        this.questionGroup = questionGroup;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
