package quizcreator.model;

import javax.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private int lineNumber;

    private String answer;

    public Answer(User user, Quiz quiz, int lineNumber, String answer) {
        this.user = user;
        this.quiz = quiz;
        this.lineNumber = lineNumber;
        this.answer = answer;
    }
}
