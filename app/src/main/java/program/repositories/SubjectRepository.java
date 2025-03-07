package program.repositories;

import org.hibernate.Session;

import program.HibernateUtil;
import program.models.Subject;

import java.util.List;

public class SubjectRepository {
    private Session session;

    public List<Subject> getAllSubjects() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Subject> subjects = session.createQuery("from Subject").list();
        session.close();
        return subjects;
    }

    public void addSubject(Subject subject) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(subject);
        session.getTransaction().commit();
        session.close();
    }
}
