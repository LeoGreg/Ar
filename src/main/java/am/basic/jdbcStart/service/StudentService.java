package am.basic.jdbcStart.service;

import am.basic.jdbcStart.repository.impl.spring.jpa.StudentRepSpringJpa;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class StudentService {
    private StudentRepSpringJpa studentRepSpringJpa;

    public StudentRepSpringJpa getStudentRepSpringJpa() {
        return studentRepSpringJpa;
    }

    public void setStudentRepSpringJpa(StudentRepSpringJpa studentRepSpringJpa) {
        this.studentRepSpringJpa = studentRepSpringJpa;
    }
}
