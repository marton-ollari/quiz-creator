package quizcreator.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class QuestionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @OneToMany(mappedBy = "questionGroup", fetch = FetchType.LAZY)
    private List<Question> questions;

    private int lineNumber;

    public QuestionGroup() {
    }

    public QuestionGroup(Quiz quiz, QuestionType type, int lineNumber) {
        this.quiz = quiz;
        this.type = type;
        this.lineNumber = lineNumber;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public QuestionType getType() {
        return type;
    }
}
