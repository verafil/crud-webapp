package web.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    public Role() { }

//    public Role(Long id, String role) {
//        this.id = id;
//        this.name = role;
//    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() { return id; }

    public String getRole() { return name; }

    public void setId(Long id) { this.id = id; }

    public void setRole(String role) { this.name = role; }

    @Override
    public String getAuthority() { return name; }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }
}
