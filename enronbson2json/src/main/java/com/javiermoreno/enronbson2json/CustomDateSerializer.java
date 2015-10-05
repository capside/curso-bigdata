package com.javiermoreno.enronbson2json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author ciberado
 */
public class CustomDateSerializer extends JsonSerializer<String> {


    @Override
    public void serialize(String originalTextDate, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        try {
            SimpleDateFormat originalDateFormat = new SimpleDateFormat("EEE, dd MMM YYYY HH:mm:ss Z", Locale.ENGLISH);
            Date date = originalDateFormat.parse(originalTextDate);
            ISO8601DateFormat isoDateFormat = new ISO8601DateFormat();
            String isoTextDate = isoDateFormat.format(date);
            jg.writeString(isoTextDate);
        } catch (ParseException ex) {
            throw new RuntimeException(originalTextDate + " produced this error: " + ex.getMessage());
        }
    }

}
