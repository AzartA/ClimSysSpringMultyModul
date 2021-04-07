package com.orioninc.training.model.dos;

import com.orioninc.training.model.api.entities.Role;
import com.orioninc.training.model.api.entities.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Version;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
@Entity
public class RoleDO implements Role, Serializable {
    private static final long serialVersionUID = 5474563217892L;

    @Version
    private  int version;

    @Id
    @SequenceGenerator(name = "role_seq", sequenceName = "role_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    private long id;

    @Column(name = "name", nullable = false, length = 48, unique = true)
    private String name;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "roles")
    private Set<UserDO> users;

    public RoleDO(String name) {
        this.name = name;
        users = new HashSet<>();
    }

    public boolean addUsers(Set<UserDO> users) {
        boolean usersAdded = this.users.addAll(users);
        boolean roleAdded = users.stream().map(u -> u.getRoles().add(this)).reduce(true, (a, b) -> a && b);
        return usersAdded && roleAdded;
    }

    @ToString.Include
    public String users() {
        return "[" + users.stream().map(User::getName).collect(Collectors.joining(",")) + "]";
    }

}
