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
    @JoinColumn(name = "quiestion_id")
    private Question question;

    private String answer;

    public Answer(User user, Question question, String answer) {
        this.user = user;
        this.question = question;
        this.answer = answer;
    }
}
