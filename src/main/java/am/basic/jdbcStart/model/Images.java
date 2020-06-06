package am.basic.jdbcStart.model;

import java.util.Objects;

public class Images {
    private int id;
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Images img = (Images) o;
        return id == img.id &&
                Objects.equals(path, img.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + id + '\'' +
                ", surname='" + path + '\'' +
                '}';
    }
}
