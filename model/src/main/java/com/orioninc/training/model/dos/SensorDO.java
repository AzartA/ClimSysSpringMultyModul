package com.orioninc.training.model.dos;

import com.orioninc.training.model.api.entities.Sensor;
import com.orioninc.training.model.api.entities.User;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class SensorDO implements Sensor, Serializable {
    private static final long serialVersionUID = 5474563217894L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private long id;

    @EqualsAndHashCode.Include
    @Column(name = "name", nullable = false, length = 48, unique = true)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "location")
    private LocationDO location;

    @Column(name = "x")
    private long x;

    @Column(name = "y")
    private long y;

    @Setter(AccessLevel.NONE)
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "type")
    private SensorTypeDO type;

    @ManyToMany(mappedBy = "sensors", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<UserDO> users;

    @OneToMany(mappedBy = "sensor", cascade = {CascadeType.ALL})
    private List<MeasuringDO> measurings;

    public SensorDO(String name) {
        this.name = name;
    }

    public SensorDO(Sensor sensor) {
        name = sensor.getName();
        x = sensor.getX();
        y = sensor.getY();
    }

    public void setType(SensorTypeDO type) {
        type.getSensors().add(this);
        this.type = type;
    }

    public boolean addUsers(Set<UserDO> users) {
        boolean usersAdded = this.users.addAll(users);
        boolean sensorAdded = users.stream().map(u -> u.getSensors().add(this)).reduce(true, (a, b) -> a && b);
        return usersAdded && sensorAdded;
    }

    @ToString.Include
    public String users() {
        return "[" + users.stream().map(User::getName).collect(Collectors.joining(",")) + "]";
    }
}
