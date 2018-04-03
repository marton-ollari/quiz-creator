package quizcreator.model;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table(name="questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private HashMap<String, String> questionAndAnswer; //???????

    private QuestionType type;

    private int lineNumber;

    private long quizId;
}
