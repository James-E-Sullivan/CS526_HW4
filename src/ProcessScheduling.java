
import net.datastructures.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProcessScheduling {

    //private static LinkedQueue processList;

    public static LinkedQueue readProcesses(){
        LinkedQueue<Process> processList = new LinkedQueue<>();
        String lineFromFile;

        try{
            FileInputStream processFile = new FileInputStream("resources/process_scheduling_in.txt");
            Scanner fileInput = new Scanner(processFile);

            while (fileInput.hasNextLine()){

                lineFromFile = fileInput.nextLine();
                String[] splitLine = lineFromFile.split(" ");

                Process p = new Process(Integer.valueOf(splitLine[0]),
                        Integer.valueOf(splitLine[1]),
                        Integer.valueOf(splitLine[2]),
                        Integer.valueOf(splitLine[3]));

                processList.enqueue(p);
            }

        }

        // fileNotFoundException error
        catch (FileNotFoundException ex){
            System.err.println(ex.getMessage() + ": File not found. Exiting.");
            ex.printStackTrace();
            System.exit(0);
        }

        // all other exceptions, catch-all
        catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            System.exit(0);
        }
        return processList;
    }


    public static void main(String[] args) {

        LinkedQueue<Process> processQueue = readProcesses();
        while (processQueue.size() > 0) {
            Process p = processQueue.dequeue();
            System.out.println("\n" + p.getPriority());
            System.out.println(p.getId());
            System.out.println(p.getArrivalTime());
            System.out.println(p.getDuration());
        }
    }
}
