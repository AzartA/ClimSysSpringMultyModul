package com.orioninc.training.model.dos;

import com.orioninc.training.model_api.model.Role;
import com.orioninc.training.model_api.model.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class RoleDO implements Role, Serializable {
    private static final long serialVersionUID = 5474563217892L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false, length = 48, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
