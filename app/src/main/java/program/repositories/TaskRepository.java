package program.repositories;

import org.hibernate.Session;

import org.hibernate.query.Query;
import program.HibernateUtil;
import program.models.Section;
import program.models.Task;

import java.util.List;

public class TaskRepository {
    private Session session;

    public List<Task> getTasks(Section section) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("from Task t where t.section = :section", Task.class).list();
//        }
        session = HibernateUtil.getSessionFactory().openSession();
        Query<Task> query = session.createQuery("from Task t where t.section = :section", Task.class);
        query.setParameter("section", section);
        List<Task> tasks = query.getResultList();
        session.close();
        return tasks;
    }

    public void addTask(Task task) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(task);
        session.getTransaction().commit();
        session.close();
    }
}
