package am.basic.jdbcStart.repository.impl.jpa;

import am.basic.jdbcStart.model.Comment;
import am.basic.jdbcStart.repository.impl.CommentRepository;
import am.basic.jdbcStart.util.HibernateUtil;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.lang.annotation.Native;
import java.sql.SQLException;
import java.util.List;
@Data
public class CommentRepositoryJpaIml implements CommentRepository {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Comment> getByUserId(int userId) throws SQLException {
        Session session = sessionFactory.openSession();
        String query = "SELECT * FROM comment WHERE user_id=:user_ID";
        NativeQuery<Comment> nativeQuery = session.createNativeQuery(query, Comment.class);
        nativeQuery.setParameter("user_ID", userId);
        List<Comment> list = nativeQuery.getResultList();
        return list;
    }

    @Override
    public void save(Comment comment) throws SQLException {
        Session ses = sessionFactory.openSession();
        ses.beginTransaction();
        ses.save(comment);
        ses.getTransaction().commit();
        ses.close();

    }

    @Override
    public void delete(Comment comment) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(comment);
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public Comment getById(int id)   {
        Session session = sessionFactory.openSession();
        Comment comment = session.find(Comment.class, id);
        return comment;
    }
}
