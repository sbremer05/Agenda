package program.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    private String name;

    private String description;

    public Task() {}

    public Task(Section section, String name, String description) {
        this.section = section;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String task) {
        this.name = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task1 = (Task) o;
        return Objects.equals(section, task1.section) &&
                Objects.equals(name, task1.name) &&
                Objects.equals(description, task1.description);
    }
}
