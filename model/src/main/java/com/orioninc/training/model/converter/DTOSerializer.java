package com.orioninc.training.model.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.orioninc.training.model.api.entities.Entity;

import java.io.IOException;
import java.util.*;

public class DTOSerializer extends StdSerializer<Collection<Entity>> {

    public DTOSerializer() {
        this(null);
    }

    public DTOSerializer(Class<Collection<Entity>> t) {
        super(t);
    }

    @Override
    public void serialize(Collection<Entity> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for(Entity e :value){
            gen.writeStartObject();
            gen.writeNumberField("id",e.getId());
            gen.writeStringField("name",e.getName());
            gen.writeEndObject();
        }
        gen.writeEndArray();
    }
}
