import java.io.*;
import java.net.URL;
import java.util.logging.Logger;


/*
The driver class to run the parking lot system
 */
public class parking_lot {

    private final static Logger LOGGER = Logger.getLogger(parking_lot.class.getName());

    public static void readFromFile(){

        URL filePath = parking_lot.class.getResource("file_inputs.txt");
        String path = filePath.getPath();
        String fileName = path.substring(path.lastIndexOf('/') + 1);
        System.out.println(fileName);
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(path);

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
            System.out.println(ex);
        }
        catch(IOException ex) {
            System.out.println(ex);
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
    }


    public static void main(String args[]){
        System.out.println("Hello world");

        if(args.length>1){
            for (String val:args)
                System.out.println(val);
        }else{
            System.out.println("No command line "+
                    "arguments found.");
        }


        readFromFile();



    }
}
