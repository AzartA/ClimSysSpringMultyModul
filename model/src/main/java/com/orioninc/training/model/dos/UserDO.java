package com.orioninc.training.model.dos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.orioninc.training.model.api.entities.Sensor;
import com.orioninc.training.model.api.entities.User;
import com.orioninc.training.model.converter.DTOSerializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@NamedQueries({
        @NamedQuery(name = UserDO.GET_BY_ID_OR_LOGIN, query = "SELECT u FROM UserDO AS u WHERE u.id = :id OR u.login = :login")
})
public class UserDO implements User, Serializable {
    private static final long serialVersionUID = 5474563217891L;
    public static final String GET_ALL = "Users.getAll";
    public static final String GET_BY_LOGIN = "Users.getByLogin";
    public static final String GET_BY_ID = "Users.getById";
    public static final String GET_BY_ID_OR_LOGIN = "Users.getByIdOrLogin";

    @Version
    private  int version;

    @EqualsAndHashCode.Include
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private long id;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @EqualsAndHashCode.Include
    @Column(name = "login", length = 48, nullable = false, unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @ElementCollection(fetch = FetchType.LAZY )
    @CollectionTable(name = "user_properties")
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<String> properties = new HashSet<>(4);

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_SENSOR_SET")
    private Set<SensorDO> sensors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE_SET")
    @JsonSerialize(using = DTOSerializer.class)
    private Set<RoleDO> roles = new HashSet<>();

    public UserDO(User user) {
        name = user.getName();
        login = user.getLogin();
        password = user.getPassword();
        properties = user.getProperties();
    }

    public UserDO(String name, String password) {
        this.name = name;
        this.login = name;
        this.password = password;
        this.properties = new HashSet<>();
    }

    public UserDO(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.properties = new HashSet<>();
    }

    public boolean addRoles(Set<RoleDO> roles) {
        return this.roles.addAll(roles);
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

    /*private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeLong(id);
        out.writeObject(name);
        out.writeObject(login);
        out.writeObject(roles);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        id = in.readLong();
        name = (String) in.readObject();
        login = (String) in.readObject();
        roles = (Set<RoleDO>) in.readObject();
    }*/




}
