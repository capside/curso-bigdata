package com.javiermoreno.enronbson2json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.undercouch.bson4jackson.types.ObjectId;
import java.io.IOException;


/**
 *
 * @author ciberado
 */
public class CustomObjectIdSerializer extends JsonSerializer<ObjectId> {

    @Override
    public void serialize(ObjectId objectId, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        String hex = org.bson.types.ObjectId.createFromLegacyFormat(objectId.getTime(), objectId.getMachine(), objectId.getInc()).toHexString();
        jg.writeString(hex);
    }

}
