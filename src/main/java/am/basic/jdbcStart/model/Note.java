package am.basic.jdbcStart.model;

import java.util.Objects;

public class Note {
    private int userId;
    private String writing;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return userId == note.userId &&
                Objects.equals(note, note.writing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, writing);
    }

    @Override
    public String toString() {
        return "id{" +
                "id=" + userId +
                ", note='" + writing + '\'' +

                '}';
    }
}
