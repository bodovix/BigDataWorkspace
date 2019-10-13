import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.IOException;




public class WordCountMapper extends Mapper<LongWritable,Text,Text,FloatWritable> {
@Override

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{


	String id;
	float quantity;
    String line = value.toString();
    String[] tuple = line.split("\\n");
 
    for(int i=0; i < tuple.length;i++){
    	if(i ==0)
    		continue;
    	//get required values from JSon object
    	String json = tuple[i].toString();
    	String[] data = tuple[i].split(",");
    	id = data[0];
    	quantity = Float.parseFloat( data[3]);
    	System.out.print("MAP::: ID: " + id + "    " + quantity);
    	//add to output key pair
    	context.write(new Text(id), new FloatWritable(quantity));
    }
    
//    for (String word : line.split(" ")){
//        if(word.length()>0){
//            context.write(new Text(word),new IntWritable(1));
//        }
//    }
}

}