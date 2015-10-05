package com.javier.moreno.tweetcount;

import java.io.IOException;
import java.text.MessageFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.log4j.BasicConfigurator;

public class TweetCountApplication {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        BasicConfigurator.configure();
        Configuration conf = new Configuration();
        conf.set("mapred.job.tracker", "local");
        conf.set("fs.default.name", "local");
        
        Job job = Job.getInstance(conf, "contador"); // Job clonarÃ¡ la conf que se le pase como parÃ¡metro
        job.setJarByClass(TweetCountApplication.class);
        // Si queremos agregar un fichero a la cache distribuÃ­da: 
        //DistributedCache.addCacheFile(new URI("/user/peter/cacheFile/testCache1"), job.getConfiguration());
        job.setMapperClass(UsernameCountMapper.class);
        job.setReducerClass(DifferentUsersReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        String inPath = args.length > 0 ? args[0] : "file:///C:\\Datos\\Dropbox\\cursos\\bigdata\\prisa/tweets.json";
        FileInputFormat.addInputPath(job, new Path(inPath));
        String outPath = args.length > 0 ? args[1] : "file:///C:/tmp/tweets_json/results{0}/";
        FileOutputFormat.setOutputPath(job, new Path(MessageFormat.format(outPath, System.currentTimeMillis())));

        boolean result = job.waitForCompletion(true);
        System.out.println("Fin: " + result + ".");
    }
}
