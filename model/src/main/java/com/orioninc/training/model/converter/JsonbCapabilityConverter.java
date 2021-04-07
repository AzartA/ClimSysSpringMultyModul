package com.orioninc.training.model.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orioninc.training.model.dos.CapabilityImpl;
import org.postgresql.util.PGobject;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JsonbCapabilityConverter implements AttributeConverter<CapabilityImpl, PGobject> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public PGobject convertToDatabaseColumn(CapabilityImpl entity) {
        try {
            PGobject out = new PGobject();
            out.setType("jsonb");
            out.setValue(mapper.writeValueAsString(entity));
            return out;
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable serialize to jsonb field from entity object", e);
        }
    }

    @Override
    public CapabilityImpl convertToEntityAttribute(PGobject jsonb) {
        try {

            return mapper.readValue(jsonb.getValue(), CapabilityImpl.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable deserialize to entity object from jsonb field ", e);
        }
    }
}


