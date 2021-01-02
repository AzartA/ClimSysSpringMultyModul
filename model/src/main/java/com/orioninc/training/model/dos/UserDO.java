package com.orioninc.training.model.dos;

import com.orioninc.training.model_api.model.Sensor;
import com.orioninc.training.model_api.model.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@NamedQueries({
        @NamedQuery(name = UserDO.GET_ALL, query = "SELECT u FROM UserDO AS u"),
        @NamedQuery(name = UserDO.GET_BY_LOGIN, query = "SELECT u FROM UserDO AS u WHERE u.login = :login"),
        @NamedQuery(name = UserDO.GET_BY_ID, query = "SELECT u FROM UserDO AS u WHERE u.id = :id"),
        @NamedQuery(name = UserDO.GET_BY_ID_OR_LOGIN, query = "SELECT u FROM UserDO AS u WHERE u.id = :id OR u.login = :login")
})
public class UserDO implements User, Serializable {
    private static final long serialVersionUID = 5474563217891L;
    public static final String GET_ALL = "Users.getAll";
    public static final String GET_BY_LOGIN = "Users.getByLogin";
    public static final String GET_BY_ID = "Users.getById";
    public static final String GET_BY_ID_OR_LOGIN = "Users.getByIdOrLogin";

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "login", length = 48, nullable = false, unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_properties",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<String> properties = new HashSet<>(4);

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "USER_SENSOR_SET")
    private Set<SensorDO> sensors;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE_SET")
    private Set<RoleDO> roles;

    public UserDO(User user) {
        name = user.getName();
        login = user.getLogin();
        password = user.getPassword();
        properties = user.getProperties();
        sensors = new HashSet<>();
        roles = new HashSet<>();
    }

    public UserDO(String name,String password) {
        this.name = name;
        this.login = name;
        this.password = password;
        this.properties = new HashSet<>();
    }

    public boolean addRoles(Set<RoleDO> roles) {
        boolean addedRoles = this.roles.addAll(roles);
        boolean rolesAdded = roles.stream().map(s -> s.getUsers().add(this)).reduce(true, (a, b) -> a && b);
        return rolesAdded && addedRoles;
    }

    public void removeRoles(Set<RoleDO> roles) {
        this.roles.removeAll(roles);
        roles.forEach(s -> s.getUsers().remove(this));
    }

    public void addSensors(Set<SensorDO> sensors) {
        this.sensors.addAll(sensors);
        sensors.forEach(s -> s.getUsers().add(this));
    }

    @ToString.Include
    public String sensors() {
        return "[" + sensors.stream().map(Sensor::getName).collect(Collectors.joining(",")) + "]";
    }

    @ToString.Include
    public String roles() {
        return "[" + roles.stream().map(RoleDO::getName).collect(Collectors.joining(",")) + "]";
    }
}
