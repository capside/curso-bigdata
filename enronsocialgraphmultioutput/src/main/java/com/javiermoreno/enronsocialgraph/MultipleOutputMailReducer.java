package com.javiermoreno.enronsocialgraph;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

/**
 *
 * @author ciberado
 */
public class MultipleOutputMailReducer extends Reducer<MailRelationship, LongWritable,  MailRelationship, ArrayLongWritable> implements Serializable {
    private static final long serialVersionUID = 1L;
    private final ArrayLongWritable timestamps = new ArrayLongWritable();

    private MultipleOutputs<NullWritable, NullWritable> moContext;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        moContext = new MultipleOutputs(context);
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        moContext.close();
    }
    
    
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
        
        moContext.write("counter", relationship, new LongWritable(timestampValues.size()), "counters");
        moContext.write("timestamps", relationship, timestamps, "timestamps");
        
    }
    
    
}
