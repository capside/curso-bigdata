/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javier.moreno.tweetcount;

import java.io.IOException;
import java.io.Serializable;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author ciberado
 */
public class DifferentUsersReducer extends Reducer<Text, LongWritable,  Text, IntWritable> implements Serializable {
    private static final long serialVersionUID = 1L;
    private IntWritable userCounts = new IntWritable();

    public void reduce(Text key, Iterable<LongWritable> values, Context context) 
    throws IOException, InterruptedException {
        long count = 0;
        for (LongWritable val : values) {
           count = count + val.get();
        }
        userCounts.set((int) count);
        context.write(key, userCounts);
    }
    
}
