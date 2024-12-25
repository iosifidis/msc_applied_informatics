// Εισαγωγή απαραίτητων βιβλιοθηκών του Hadoop
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Social extends Configured implements Tool { 
    // Κλάση Mapper: Υλοποιεί το βήμα Map
    public static class SocialMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        Text mapKey = new Text(); // Κλειδί εξόδου του Mapper
        IntWritable mapValue = new IntWritable(); // Τιμή εξόδου του Mapper

        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            // Ανάγνωση κάθε γραμμής από το αρχείο εισόδου
            String line = value.toString();
            try {
                // Διαχωρισμός της γραμμής στα δύο ονόματα χρηστών
                String record[] = line.split(",");
                mapValue.set(1); // Η τιμή για κάθε όνομα είναι 1
                for (int i = 0; i < record.length; i++) {
                    mapKey.set(record[i]); // Ορισμός του ονόματος χρήστη ως κλειδί
                    context.write(mapKey, mapValue); // Εκπομπή ζεύγους <Όνομα, 1>
                }
            } catch (Exception e) {
                e.printStackTrace(); // Καταγραφή τυχόν σφαλμάτων
            }
        }
    }

    // Κλάση Reducer: Υλοποιεί το βήμα Reduce
    public static class SocialReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable(); // Αποθηκεύει το αποτέλεσμα για κάθε κλειδί

        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            // Άθροισμα των τιμών για κάθε κλειδί (πλήθος φίλων)
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum); // Ορισμός του αθροίσματος ως αποτέλεσμα
            context.write(key, result); // Εκπομπή του ζεύγους <Όνομα, Πλήθος Φίλων>
        }
    }

    @Override
    public int run(String[] args) throws Exception {
        // Δημιουργία και διαμόρφωση εργασίας Hadoop
        Job job = Job.getInstance(getConf());
        job.setJarByClass(Social.class); // Ορισμός της κύριας κλάσης
        job.setNumReduceTasks(2); // Ορισμός του αριθμού Reducers
        job.setMapperClass(SocialMapper.class); // Ορισμός της κλάσης Mapper
        job.setReducerClass(SocialReducer.class); // Ορισμός της κλάσης Reducer
        job.setOutputKeyClass(Text.class); // Τύπος εξόδου κλειδιών
        job.setOutputValueClass(IntWritable.class); // Τύπος εξόδου τιμών
        FileInputFormat.addInputPath(job, new Path(args[0])); // Καθορισμός αρχείων εισόδου
        FileOutputFormat.setOutputPath(job, new Path(args[1])); // Καθορισμός αρχείων εξόδου
        return job.waitForCompletion(true) ? 0 : 1; // Εκκίνηση εργασίας και αναμονή ολοκλήρωσης
    }

    public static void main(String[] args) throws Exception {
        // Εκκίνηση της εφαρμογής Hadoop MapReduce
        ToolRunner.run(new Configuration(), new Social(), args);
    }
}

/*
Επεξηγήσεις:

1. Mapper:

- Διαχωρίζει κάθε γραμμή του αρχείου εισόδου σε ζεύγη φίλων.
- Για κάθε όνομα χρήστη, εκπέμπει ζεύγος <Όνομα, 1>.

2. Reducer:

- Λαμβάνει ζεύγη κλειδιού-τιμής από όλους τους Mappers.
- Αθροίζει όλες τις τιμές για κάθε κλειδί (όνομα χρήστη).
- Εκπέμπει το τελικό αποτέλεσμα <Όνομα, Πλήθος Φίλων>.

3. Job Configuration:

- Ορίζει τη χρήση 2 διεργασιών Reduce.
- Καθορίζει τα αρχεία εισόδου/εξόδου.
- Εκκινεί την εργασία και αναμένει την ολοκλήρωσή της.
*/
