package program.repositories;

import org.hibernate.Session;

import org.hibernate.query.Query;
import program.HibernateUtil;
import program.models.Section;
import program.models.Task;

import java.util.List;

public class SectionRepository {
    private Session session;

    public List<Section> getAllSections() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Section", Section.class).list();
        }
    }

    public void addSection(Section section) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(section);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteSection(Section section) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(section);
            session.getTransaction().commit();
        }
    }


    public Section findByName(String name) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query<Section> query = session.createQuery("from Section s where s.name = :name", Section.class);
        query.setParameter("name", name);
        Section section = query.uniqueResult();
        session.close();
        return section;
    }

    public void addTask(Section section, Task task) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        section.addTask(task);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteTask(Section section, Task task) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        section.removeTask(task);
        session.getTransaction().commit();
        session.close();
    }
}
