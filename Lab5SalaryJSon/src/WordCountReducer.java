import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, FloatWritable,Text,FloatWritable> {
    @Override
    public void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException{


        int count = 0;
        float total = 0;
        float average = 0;
        for (FloatWritable value:values)
        {
        	//increment count for average
            count += count++;//value.get();
            
            total = total + value.get();
            System.out.print("REDUCE:: Count " + count+"     Total " + total);
        }
        
        average = total/count;
        System.out.print("Average " + average);
        context.write(key, new FloatWritable(average));

    }
}