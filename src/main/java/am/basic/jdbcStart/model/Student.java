package am.basic.jdbcStart.model;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Student {
//    @Nullable wrong when input is null
//    @NotEmpty wrong for "" and null
//    @NotBlank wrong for "    "   "" and null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    private int balance;

    @OneToOne // default EAGER
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private List<Book> books;


    @ManyToMany(fetch =FetchType.LAZY)
    @JoinTable(name = "students_teachers",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"))
    private List<Teacher> teachers;
}
