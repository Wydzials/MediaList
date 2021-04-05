package pl.wydzials.medialist.model.media;

import pl.wydzials.medialist.model.User;

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
    protected int timeInMinutes;
    protected LocalDateTime created;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn
    protected User user;

    public Medium(String title, int priority, int timeInMinutes, User user) {
        this.title = title;
        this.priority = priority;
        this.user = user;
        this.timeInMinutes = timeInMinutes;
        user.getMedia().add(this);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.getMedia().add(this);
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public void edit(Medium medium) {
        title = medium.title;
        priority = medium.priority;
        timeInMinutes = medium.timeInMinutes;
    }

    public String getTimeFormatted() {
        int hours = timeInMinutes / 60;
        int minutes = timeInMinutes % 60;
        return hours + ":" + String.format("%02d", minutes);
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
