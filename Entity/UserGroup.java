package Entity;

public class UserGroup {

    private int id;
    private String name;

    //constructor to add new Group
    public UserGroup(String name) {
        setName(name);
    }

    //constuctor to get group from DB
    public UserGroup(int id, String name) {
        setId(id);
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name='" + name;

    }
}
