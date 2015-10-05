package com.javier.moreno.wordcount;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;

/**
 *
 * 
 *
 * @author ciberado
 */
public class TaggerMapper extends Mapper<Object, Text, Text, LongWritable> 
implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static final long INTERVALO = 1000 * 60 * 5; // cinco minutos
    
    private static final LongWritable POSITIVE = new LongWritable (1);
    private static final LongWritable NEGATIVE = new LongWritable (-1);
    private Text wordText = new Text();
    private ObjectMapper mapper;

    
    public TaggerMapper() {
        mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZ yyyy", Locale.ENGLISH));
    }
    
    public void map(Object key, Text value, Context context) {
    	try {
    	    String line = value.toString();
    	    if (line.isEmpty() == false) {
                Tweet tweet = mapper.readValue(line, Tweet.class);
                String text = tweet.getText().toLowerCase();
                String[] words = text.split("[\\.\"',: \\s!@#$%^&*()]+");
                for (String word : words) {
                    if (Arrays.binarySearch(Stopwords.WORDS, word) < 0) {
                        wordText.set(word);
                        context.write(wordText, Math.random() > 0.5 ? POSITIVE : POSITIVE); 
                    }
                }
    	    }
        } catch (IOException exc) {
            context.getCounter("Errores", "IOException").increment(1);
        } catch (InterruptedException exc) {
            context.getCounter("Errores", "InterruptedException").increment(1);
    	}
    }
    
}
