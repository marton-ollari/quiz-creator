package quizcreator.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int questionNumber = 50;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<QuestionGroup> questionGroups;

    public Quiz() {
    }

    public Quiz(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void decreaseQuestionNumber(int number) {
        this.questionNumber -= number;
    }

    public int getQuestionGroupSize(){
        return questionGroups.size();
    };
}
