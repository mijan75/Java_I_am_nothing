import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class main {

    public main(){
        try  {
            RandomAccessFile input = new RandomAccessFile("input.txt", "r");
            RandomAccessFile output = new RandomAccessFile("output.txt", "rw");

            int numberOfline = 0;
            String line=null;
            String newLine=null;

            while (true){
                line = input.readLine();
                if (line == null)
                    break;
                numberOfline++;
                if (numberOfline <= 155)
                    newLine = line.substring(4, line.length())+"\n";
                else
                    newLine = line.substring(5, line.length())+"\n";


                output.writeBytes(newLine);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args){
        new main();
    }
}
