package quizcreator.model;

import javax.persistence.*;

@Entity
@Table(name="quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private long userId;
}
