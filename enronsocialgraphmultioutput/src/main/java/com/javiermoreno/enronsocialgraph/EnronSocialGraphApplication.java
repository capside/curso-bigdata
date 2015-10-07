package com.javiermoreno.enronsocialgraph;

import java.io.IOException;
import java.text.MessageFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class EnronSocialGraphApplication {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        conf.set("mapred.job.tracker", "local");
        conf.set("fs.default.name", "local");

        Job job = new Job(conf, "contador"); // Job clonarÃ¡ la conf que se le pase como parÃ¡metro

        // Si queremos agregar un fichero a la cache distribuÃ­da: 
        //DistributedCache.addCacheFile(new URI("/user/peter/cacheFile/testCache1"), job.getConfiguration());

        job.setJarByClass(EnronSocialGraphApplication.class);
        
        job.setMapperClass(MailMapper.class);
        
        job.setOutputKeyClass(MailRelationship.class);
        job.setOutputValueClass(LongWritable.class);

        //job.setReducerClass(MailReducer.class);

        job.setReducerClass(MultipleOutputMailReducer.class);
        MultipleOutputs.addNamedOutput(job, "counter", TextOutputFormat.class, Mail.class, LongWritable.class);
        MultipleOutputs.addNamedOutput(job, "timestamps", TextOutputFormat.class, Mail.class, ArrayLongWritable.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        String inPath = args.length > 0 ? args[0] : "file:///C:\\Datos\\Dropbox\\cursos\\bigdata\\prisa\\messages_inbox.json";
        FileInputFormat.addInputPath(job, new Path(inPath));
        String outPath = args.length > 0 ? args[1] : "file:///C:/tmp/enron_results/results{0}/";
        FileOutputFormat.setOutputPath(job, new Path(MessageFormat.format(outPath, System.currentTimeMillis())));
        
        job.waitForCompletion(true);
    }
}






