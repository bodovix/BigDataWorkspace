import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;




public class WordCountMapper extends Mapper<LongWritable,Text,Text,Text> {
@Override

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{

    String line = value.toString();
    //break into words
    for (String word : line.split(" ")){
        if(word.length()>0){
        	String fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
            context.write(new Text(word),new Text(fileName));
        }
    }
}

}