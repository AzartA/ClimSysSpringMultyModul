package com.orioninc.training.model.dos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orioninc.training.model_api.model.Capability;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CapabilityImpl implements Serializable, Capability {
    private static final long serialVersionUID = 5474563217890L;
    @JsonProperty
    private String range;
    @JsonProperty
    private String accuracy;
    @JsonProperty
    private String resolution;

    public CapabilityImpl(Capability capability) {
        this.range = capability.getRange();
        this.accuracy = capability.getAccuracy();
        this.resolution = capability.getResolution();
    }


}
