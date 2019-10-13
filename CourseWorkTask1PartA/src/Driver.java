import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Driver {

    public static void main(String[] args) throws Exception {
        if(args.length !=3){
            System.err.println("Invalid Command");
            System.err.println("Usage: WordCount <input path> <output path> <cut of percentage over X>");
            System.exit(0);
        }
        Configuration conf = new Configuration();
        Job job = new Job(conf, "findoverpaidemployees");
        job.setJarByClass(Driver.class);
        job.setJobName("Find Overpaid Employees");
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        PercentageAllowed.percentageAllowed = Integer.parseInt(args[2]);
        
        job.setMapperClass(MapData.class);
        job.setReducerClass(ReduceData.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        System.exit(job.waitForCompletion(true)?0:1);
    }
}