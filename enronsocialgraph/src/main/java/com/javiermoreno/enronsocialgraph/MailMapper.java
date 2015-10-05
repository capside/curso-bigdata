package com.javiermoreno.enronsocialgraph;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * 
 *
 * @author ciberado
 */
public class MailMapper extends Mapper<Object, Text, MailRelationship, LongWritable> 
implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    private MailRelationship mr;
    private LongWritable date;
    private ObjectMapper mapper;

    public MailMapper() {
        this.date = new LongWritable (0);
        this.mr = new MailRelationship();
        this.mapper = new ObjectMapper();
    }
    
    @Override
    public void map(Object key, Text value, Context context) {
    	try {
    	    String line = value.toString();
    	    if (line.isEmpty() == false) {
                Mail mail = mapper.readValue(line, Mail.class);
                if (mail.headers.to == null) {
                    context.getCounter("Format", "EmptyDestination").increment(1);                    
                    return;
                }
                mr.setFrom(mail.headers.from);
                date.set(mail.headers.date.getTime());
                // regexp: \r\n or , or spaces or tabs
                String[] destinataries = mail.headers.to.split("(\\r\\n|[, \t])+");
                for (String currentDestinatary : destinataries) {
                    mr.setTo(currentDestinatary);
                    context.write(mr, date); 
                }
    	    }
        } catch (IOException exc) {
            context.getCounter("Errores", "IOException").increment(1);
        } catch (InterruptedException exc) {
            context.getCounter("Errores", "InterruptedException").increment(1);
    	}
    }
    
}
