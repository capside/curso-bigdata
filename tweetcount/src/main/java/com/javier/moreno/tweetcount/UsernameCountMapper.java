package com.javier.moreno.tweetcount;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * 
 *
 * @author ciberado
 */
public class UsernameCountMapper extends Mapper<Object, Text, Text, LongWritable> 
implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static final long INTERVALO = 1000 * 60 * 5; // cinco minutos
    
    private static final LongWritable ONE = new LongWritable (1);
    private Text username = new Text();
    private ObjectMapper mapper;

    public UsernameCountMapper() {
        mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZ yyyy", Locale.ENGLISH));
    }
    
    public void map(Object key, Text value, Context context) {
    	try {
    	    String line = value.toString();
    	    if (line.isEmpty() == false) {
                Tweet tweet = mapper.readValue(line, Tweet.class);
                username.set(tweet.getUser().getName());
                context.write(username, ONE); 
    	    }
        } catch (IOException exc) {
            context.getCounter("Errores", "IOException").increment(1);
        } catch (InterruptedException exc) {
            context.getCounter("Errores", "InterruptedException").increment(1);
    	}
    }
    
}
