package controls;

public class Exam {

    private String name;

    public Exam(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "name='" + name + '\'' +
                '}';
    }
}
