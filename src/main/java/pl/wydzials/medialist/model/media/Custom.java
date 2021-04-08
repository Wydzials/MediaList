package pl.wydzials.medialist.model.media;

import pl.wydzials.medialist.model.User;

import javax.persistence.Entity;

@Entity
public class Custom extends Medium {

    private String type;

    public Custom(String title, int priority, String type, int timeInMinutes, User user) {
        super(title, priority, timeInMinutes, user);
        this.type = type;
    }

    public Custom() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void edit(Medium medium) {
        super.edit(medium);
        if (medium instanceof Custom) {
            Custom custom = (Custom) medium;
            type = custom.type;
        }
    }
}
