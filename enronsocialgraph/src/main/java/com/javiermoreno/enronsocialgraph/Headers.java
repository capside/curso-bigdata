package com.javiermoreno.enronsocialgraph;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

/**
 *
 * @author ciberado
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Headers {
    @JsonProperty("From")
    public String from;
    
    @JsonProperty("Date")
    public Date date;
        
    @JsonProperty("Subject")
    public String subject;
    
    @JsonProperty("To")
    public String to;
    
    @JsonProperty("X-cc")
    public String cc;
    
    @JsonProperty("X-bcc")
    public String bcc;
    
    @Override
    public String toString() {
        return "Headers{" + "from=" + from + ", subject=" + subject + ", to=" + to + '}';
    }
    
    
}
