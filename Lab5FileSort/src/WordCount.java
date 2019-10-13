	import java.io.File;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

    public static void main(String[] args) throws Exception {
        if(args.length !=2){
            System.err.println("Invalid Command");
            System.err.println("Usage: WordCount <input path> <output path>");
            System.exit(0);
        }
        Configuration conf = new Configuration();
        Job job = new Job(conf, "wordcount");
        job.setJarByClass(WordCount.class);
        job.setJobName("Word Count");

        //
        FileInputFormat.addInputPath(job, new Path("input-dir/shakespeare/poems"));
        FileInputFormat.addInputPath(job, new Path("input-dir/shakespeare/comedies"));
        FileInputFormat.addInputPath(job, new Path("input-dir/shakespeare/histories"));
        FileInputFormat.addInputPath(job, new Path("input-dir/shakespeare/tragedies"));
        //
//        File folder = new File(new Path(args[0]).toString());
//        System.out.print(args[0].toString());
//        File[] listOfFiles = folder.listFiles();
//        System.out.print("Number of Files found " + listOfFiles.length);
//        for(File file : listOfFiles){
//        	if(file.isFile()){
//        		System.out.print(file.getPath());
//        		FileInputFormat.addInputPath(job,new Path(file.getPath()));
//        	}
//        }
        //
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        System.exit(job.waitForCompletion(true)?0:1);
    }
}