package pl.wydzials.medialist.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Medium {

    @Id
    @GeneratedValue
    protected Long id;
    protected String title;
    protected int priority;
    protected LocalDateTime created;

    public Medium(String title, int priority) {
        this.title = title;
        this.priority = priority;
        this.created = LocalDateTime.now();
    }

    public Medium() {
        this.created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Medium{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", priority=" + priority +
                ", created=" + created +
                '}';
    }
}