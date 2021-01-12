package com.orioninc.training.model.dos;

import com.orioninc.training.model.entities.Location;
import com.orioninc.training.model.entities.Sensor;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@NamedQueries({
        @NamedQuery(name = LocationDO.GET_ALL, query = "SELECT l FROM LocationDO AS l"),
        @NamedQuery(name = LocationDO.GET_BY_NAME, query = "SELECT l FROM LocationDO AS l WHERE l.name = :name"),
        @NamedQuery(name = LocationDO.GET_BY_ID, query = "SELECT l FROM LocationDO AS l WHERE l.id = :id"),
        @NamedQuery(name = LocationDO.GET_BY_ID_OR_NAME, query = "SELECT l FROM LocationDO AS l WHERE l.id = :id OR l.name = :name")
})
public class LocationDO implements Location, Serializable {
    private static final long serialVersionUID = 5474563217895L;
    public static final String GET_ALL = "Locations.getAll";
    public static final String GET_BY_NAME = "Locations.getByName";
    public static final String GET_BY_ID = "Locations.getById";
    public static final String GET_BY_ID_OR_NAME = "Locations.getByIdOrName";

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private long id;

    @EqualsAndHashCode.Include
    @Column(name = "name", length = 48, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "location", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<SensorDO> sensors;

    @Column(columnDefinition = "oid")
    private long planOid;

    @Column(name = "picture_type", length = 32)
    private String pictureType;

    public LocationDO(String name) {
        this.name = name;
        sensors = new HashSet<>();
    }

    @ToString.Include
    public String sensors() {
        return sensors.stream().map(Sensor::getName).collect(Collectors.joining(","));
    }

}
