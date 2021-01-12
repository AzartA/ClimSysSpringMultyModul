package com.orioninc.training.model.dos;

import com.orioninc.training.model.entities.ClimateParameter;
import com.orioninc.training.model.entities.SensorType;
import com.orioninc.training.model.entities.Unit;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NamedQueries({
        @NamedQuery(name = ClimateParameterDO.GET_ALL, query = "SELECT u FROM ClimateParameterDO AS u"),
        @NamedQuery(name = ClimateParameterDO.GET_BY_NAME, query = "SELECT u FROM ClimateParameterDO AS u WHERE u.name = :name"),
        @NamedQuery(name = ClimateParameterDO.GET_BY_ID, query = "SELECT u FROM ClimateParameterDO AS u WHERE u.id = :id"),
        @NamedQuery(name = ClimateParameterDO.GET_BY_ID_OR_NAME, query = "SELECT u FROM ClimateParameterDO AS u WHERE u.id = :id OR u.name = :name")
})
@Entity
public class ClimateParameterDO implements ClimateParameter, Serializable {
    private static final long serialVersionUID = 5474563217898L;
    public static final String GET_ALL = "Params.getAll";
    public static final String GET_BY_NAME = "Params.getByName";
    public static final String GET_BY_ID = "Params.getById";
    public static final String GET_BY_ID_OR_NAME = "Params.getByIdOrName";

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private long id;

    @EqualsAndHashCode.Include
    @Column(name = "name", length = 48, nullable = false, unique = true)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "PARAMETER_UNIT_SET")
    private Set<UnitDO> units;

    @ManyToMany(mappedBy = "parameters", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<SensorTypeDO> sensorTypes;

    @ToString.Exclude
    @OneToMany(mappedBy = "parameter", cascade = {CascadeType.ALL})
    private List<MeasuringDO> measurings;

    public ClimateParameterDO(String name) {
        this.name = name;
        units = new HashSet<>();
        sensorTypes = new HashSet<>();
        measurings = new ArrayList<>();
    }

    public boolean addUnits(Set<UnitDO> units) {
        boolean unitsAdded = this.units.addAll(units);
        boolean paramAdded = units.stream().map(u -> u.getClimateParameters().add(this)).reduce(true, (a, b) -> a && b);
        return unitsAdded && paramAdded;
    }

    @ToString.Include
    public String sensorTypes() {
        return "[" + sensorTypes.stream().map(SensorType::getName).collect(Collectors.joining(",")) + "]";
    }
    @ToString.Include
    public String units() {
        return "[" + units.stream().map(Unit::getName).collect(Collectors.joining(",")) + "]";
    }

}
