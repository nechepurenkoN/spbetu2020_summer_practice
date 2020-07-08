package parser;

import java.util.Objects;

public class ItemData {
    public Integer id;
    public String name;
    public String photo;

    public ItemData(Integer id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[Name: " + name + " id: " + id + " img: " + photo + "]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemData itemData = (ItemData) o;
        return Objects.equals(id, itemData.id) &&
                Objects.equals(name, itemData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
