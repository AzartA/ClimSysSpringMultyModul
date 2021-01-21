package com.orioninc.training.model.dos;

import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.orioninc.training.model.entities.Measuring;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class MeasuringDO implements Measuring, Serializable {
    private static final long serialVersionUID = 5474563217901L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "measured_on", insertable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime timestamp;

    @ManyToOne(optional = false, fetch = LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "sensor")
    private SensorDO sensor;

    @ManyToOne(optional = false, fetch = LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "parameter")
    private ClimateParameterDO parameter;

    @Column(columnDefinition = "real")
    private float value;

    public MeasuringDO(float value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return Long.toString(id);
    }

    @Override
    public String toString() {

        return "MeasuringDO{"
                + "id=" + id
                + ", timestamp=" + (timestamp == null ? "null" : timestamp.format(DATE_TIME_FORMATTER))
                + ", sensor=" + (sensor == null ? "null" : sensor.getName())
                + ", parameter=" + (parameter == null ? "null" : parameter.getName())
                + ", value=" + value
                + '}';
    }
}
