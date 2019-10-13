import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, Text,Text,Text> {
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{


        int count = 0;
        String test ="";
        for (Text value:values)
        {
            //count += value.get();
            
            test = value.toString() + ", " + value.toString();
        }
        String output = "is in file: " + test;


        context.write(key, new Text(output));

    }
}