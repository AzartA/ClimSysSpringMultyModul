package com.orioninc.training.model.dos;

import com.orioninc.training.model.api.entities.ClimateParameter;
import com.orioninc.training.model.api.entities.Unit;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NamedQueries({
        @NamedQuery(name = UnitDO.GET_ALL, query = "SELECT u FROM UnitDO AS u"),
        @NamedQuery(name = UnitDO.GET_BY_NAME, query = "SELECT u FROM UnitDO AS u WHERE u.name = :name"),
        @NamedQuery(name = UnitDO.GET_BY_ID, query = "SELECT u FROM UnitDO AS u WHERE u.id = :id"),
        @NamedQuery(name = UnitDO.GET_BY_ID_OR_NAME, query = "SELECT u FROM UnitDO AS u WHERE u.id = :id OR u.name = :name")
})
@Entity
public class UnitDO implements Unit, Serializable {
    private static final long serialVersionUID = 5474563217899L;
    public static final String GET_ALL = "Units.getAll";
    public static final String GET_BY_NAME = "Units.getByName";
    public static final String GET_BY_ID = "Units.getById";
    public static final String GET_BY_ID_OR_NAME = "Units.getByIdOrName";

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private long id;

    @EqualsAndHashCode.Include
    @Column(name = "name", length = 48, nullable = false, unique = true)
    private String name;

    @Column(name = "notation", length = 32)
    private String notation;

    @ManyToMany(mappedBy = "units", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<ClimateParameterDO> climateParameters;

    public UnitDO(String name, String notation) {
        this.name = name;
        this.notation = notation;
    }

    @ToString.Include
    public String climateParameters() {
        return "[" + climateParameters.stream().map(ClimateParameter::getName).collect(Collectors.joining(",")) + "]";
    }
}
