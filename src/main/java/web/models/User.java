package web.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String lastName;

    public User() { }

    public User(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getLastName() { return lastName; }

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setLastName(String lastName) { this.lastName = lastName; }
}
