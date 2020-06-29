package am.basic.jdbcStart.repository.impl.spring.jpa;

import am.basic.jdbcStart.model.Comment;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
@Transactional
public class CommentRepositorySpringJpaImpl {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Comment> getByUserId(int userId) throws SQLException {
        return (List<Comment>) sessionFactory.getCurrentSession().find(Comment.class, userId);
    }

    public void save(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
    }

    public void delete(Comment comment) {
        sessionFactory.getCurrentSession().delete(comment);
    }

    public Comment getById(int id) {
        return sessionFactory.getCurrentSession().find(Comment.class, id);
    }
}
