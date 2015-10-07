/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiermoreno.enronsocialgraph;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author ciberado
 */
public class MailReducer extends Reducer<MailRelationship, LongWritable,  MailRelationship, ArrayLongWritable> implements Serializable {
    private static final long serialVersionUID = 1L;
    private final ArrayLongWritable timestamps = new ArrayLongWritable();

    @Override
    public void reduce(MailRelationship relationship, Iterable<LongWritable> timestampsIt, Context context) 
    throws IOException, InterruptedException {
        List<LongWritable> timestampValues = new ArrayList<>();
        for (LongWritable val : timestampsIt) {
            timestampValues.add(val);
        }
        Collections.sort(timestampValues, new LongWritable.Comparator());
        LongWritable[] timestampsArray = timestampValues.toArray(new LongWritable[0]);
        timestamps.set(timestampsArray);
        context.write(relationship, timestamps);
    }
    
    
}









