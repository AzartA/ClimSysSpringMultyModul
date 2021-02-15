package com.orioninc.training.model.dos;

import com.orioninc.training.model.converter.JsonbCapabilityConverter;
import com.orioninc.training.model.api.entities.ClimateParameter;
import com.orioninc.training.model.api.entities.Sensor;
import com.orioninc.training.model.api.entities.SensorType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
public class SensorTypeDO implements SensorType, Serializable {
    private static final long serialVersionUID = 5474563217896L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private long id;

    @EqualsAndHashCode.Include
    @Column(length = 48,nullable = false, unique = true)
    private String name;

    @Convert(converter = JsonbCapabilityConverter.class)
    @Column(columnDefinition = "jsonb")
    private CapabilityImpl capability;

    @Column(name = "min_time")
    private int minTime;

    @OneToMany(mappedBy = "type", cascade = {CascadeType.ALL})
    private Set<SensorDO> sensors;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "SENSOR_PARAMETER_SET")
    private Set<ClimateParameterDO> parameters;

    public SensorTypeDO(String name) {
        this.name = name;
    }

    public SensorTypeDO(long id, String name, CapabilityImpl capability) {
        this.id = id;
        this.name = name;
        this.capability = capability;
    }

    public SensorTypeDO(SensorType type) {
        this.name = type.getName();
        this.minTime = type.getMinTime();
        this.capability = type.getCapability() == null ? null : new CapabilityImpl(type.getCapability());
        parameters = new HashSet<>();
        sensors = new HashSet<>();
    }

    public void update(SensorType type) {
        this.name = type.getName();
        this.minTime = type.getMinTime();
        this.capability = type.getCapability() == null ? null : new CapabilityImpl(type.getCapability());
        parameters = new HashSet<>();
        sensors = new HashSet<>();
    }

    public void addParameters(Set<ClimateParameterDO> parameters) {
        this.parameters.addAll(parameters);
        parameters.forEach(u -> u.getSensorTypes().add(this));
    }

    @ToString.Include
    public String parameters(){
        return "[" + parameters.stream().map(ClimateParameter::getName).collect(Collectors.joining(",")) + "]";
    }

    @ToString.Include
    public String sensors(){
        return "[" + sensors.stream().map(Sensor::getName).collect(Collectors.joining(",")) + "]";
    }

}
