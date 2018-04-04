package quizcreator.model;

import javax.persistence.*;

@Entity
@Table(name="questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String question;

    private String answer;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    private int lineNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public Question() {
    }

    public Question(String question, String answer, QuestionType type, int lineNumber, Quiz quiz) {
        this.question = question;
        this.answer = answer;
        this.type = type;
        this.lineNumber = lineNumber;
        this.quiz = quiz;
    }
}
