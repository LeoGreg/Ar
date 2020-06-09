package am.basic.jdbcStart.model;

import java.util.Objects;

public class Note {
    private int note_id;
    private String writing;
    private String title;
    private int user_id;
    private String time;

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toString() {
        return
               "\n"+
                       "TITLE :" + title + " NOTE :" + writing +
                       "\n ";
    }
}

