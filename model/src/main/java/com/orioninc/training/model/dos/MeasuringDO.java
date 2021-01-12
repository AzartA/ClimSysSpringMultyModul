package com.orioninc.training.model.dos;

import com.orioninc.training.model.entities.Measuring;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class MeasuringDO implements Measuring, Serializable {
    private static final long serialVersionUID = 5474563217901L;

    @Transient
    private final SimpleDateFormat DATE_TIME_FORMAT = Measuring.DATE_TIME_FORMAT;//new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "measured_on", insertable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "sensor")
    private SensorDO sensor;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
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
        Date date = new Date();
        if (timestamp != null) {
            date.setTime(timestamp.getTime());
        }
        return "MeasuringDO{"
                + "id=" + id
                + ", timestamp=" + DATE_TIME_FORMAT.format(date)
                + ", sensor=" + (sensor == null ? "null" : sensor.getName())
                + ", parameter=" + (parameter == null ? "null" : parameter.getName())
                + ", value=" + value
                + '}';
    }
}
