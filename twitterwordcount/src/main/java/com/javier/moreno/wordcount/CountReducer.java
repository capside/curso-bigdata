/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javier.moreno.wordcount;

import java.io.IOException;
import java.io.Serializable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author ciberado
 */
public class CountReducer extends Reducer<Text, LongWritable,  Text, LongWritable> implements Serializable {
    private static final long serialVersionUID = 1L;
    private final LongWritable counter = new LongWritable();

    @Override
    public void reduce(Text key, Iterable<LongWritable> values, Context context) 
    throws IOException, InterruptedException {
        long count = 0;
        for (LongWritable val : values) {
           count = count + 1;
        }
        counter.set(count);
        context.write(key, counter);
    }
    
}
