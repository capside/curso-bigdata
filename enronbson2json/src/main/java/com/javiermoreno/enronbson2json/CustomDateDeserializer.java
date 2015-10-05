package com.javiermoreno.enronbson2json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author ciberado
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        String textDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM YYYY HH:mm:ss Z", Locale.ENGLISH);
            
            textDate = jp.getText();
            if (textDate.isEmpty() == true) {
                return null;
            }
            Date resultDate = sdf.parse(textDate);
            return resultDate;
        } catch (ParseException ex) {
            System.err.println("Error parsing date: " + textDate);
            return null;
        }
    }

}
