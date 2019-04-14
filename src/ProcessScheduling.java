
import net.datastructures.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProcessScheduling {

     private static HeapPriorityQueue readProcesses(){
        HeapPriorityQueue<Integer, Process> D = new HeapPriorityQueue<>();
        //LinkedQueue<Process> processList = new LinkedQueue<>();
        String lineFromFile;

        try{
            FileInputStream processFile = new FileInputStream(
                    "resources/process_scheduling_in.txt");
            Scanner fileInput = new Scanner(processFile);

            while (fileInput.hasNextLine()){            // loop through file lines

                lineFromFile = fileInput.nextLine();    // get next line from file

                // splits file line by space, into a String array
                String[] splitLine = lineFromFile.split(" ");

                /*

                * Each splitLine element used as a Process parameter.
                * Assumes process_scheduling_in.txt has 4 valid numbers
                * in order of priority, id, arrivalTime, and duration.

                Process p = new Process(Integer.valueOf(splitLine[0]),
                        Integer.valueOf(splitLine[1]),
                        Integer.valueOf(splitLine[2]),
                        Integer.valueOf(splitLine[3]));
                */

                Process p = new Process();
                p.setId(Integer.valueOf(splitLine[0]));
                p.setPriority(Integer.valueOf(splitLine[1]));
                p.setDuration(Integer.valueOf(splitLine[2]));
                p.setArrivalTime(Integer.valueOf(splitLine[3]));

                /*
                // test
                System.out.println("Following process input into D:");
                System.out.print("id: " + p.getId());
                System.out.print(", priority: " + p.getPriority());
                System.out.print(", duration: " + p.getDuration());
                System.out.println(", arrivalTime: " + p.getArrivalTime() + "\n");

                 */

                D.insert(p.getArrivalTime(), p);
                //processList.enqueue(p);     // add new Process to LinkedQueue
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
        //return processList;
        return D;
    }

    /*
    private static void printProcess(Process p){
         System.out.println("Process removed from queue is: id = " + p.getId() + ", at time ");
    }

     */


    public static void main(String[] args) {

        //LinkedQueue<Process> processQueue = readProcesses();    // unchecked assignment warning
        HeapPriorityQueue<Integer, Process> D = readProcesses();        // unchecked assignment warning
        Integer currentTime = 0;    // current time set to 0 at start
        Boolean running = false;    // T if process running, otherwise F
        HeapPriorityQueue<Integer, Process> Q = new HeapPriorityQueue();        // unchecked assignment warning
        Integer endTime = 0;
        Integer waitTime;
        Double totalWaitTime = 0.0;
        Process runningProcess = null;

        // start reading processQueue values into the heap priority queue
        while (!(D.isEmpty())){

            Process p = D.min().getValue();
            if (p.getArrivalTime() <= currentTime){
                Process removedProcess = D.removeMin().getValue();
                removedProcess.setEntryTime(currentTime);
                Q.insert(removedProcess.getPriority(),
                        removedProcess);                                                 // unchecked assignment warning
            }

            if (!(Q.isEmpty()) && !running){
                runningProcess = Q.removeMin().getValue();           // not sure if this has to be outside if statement

                // Get the process wait time and add it to totalWaitTime
                waitTime = currentTime - runningProcess.getEntryTime();
                totalWaitTime += waitTime;

                System.out.println("\nProcess removed from queue is: id = " + runningProcess.getId()
                        + ", at time " + currentTime + ", wait time = " + waitTime +
                        " Total wait time = " + totalWaitTime);
                System.out.println("Process id = " + runningProcess.getId());
                System.out.println("        Priority = " + runningProcess.getPriority());
                System.out.println("        Arrival = " + runningProcess.getArrivalTime());
                System.out.println("        Duration = " + runningProcess.getDuration());



                running = true;
                endTime = currentTime + runningProcess.getDuration();
            }

            currentTime++;

            if (running && currentTime.equals(endTime)){
                running = false;
                System.out.println("Process " + runningProcess.getId() +
                        " finished at time " + currentTime);
            }
        }

        while (!(Q.isEmpty())){

            if (!running){
                runningProcess = Q.removeMin().getValue();
                System.out.print("Id = " + runningProcess.getId() + ", ");
                System.out.print("priority = " + runningProcess.getPriority() + ", ");
                System.out.print("duration = " + runningProcess.getDuration() + ", ");
                System.out.println("arrival time = " + runningProcess.getArrivalTime());
                running = true;
                endTime = currentTime + runningProcess.getDuration();
            }

            currentTime++;

            if (running && currentTime.equals(endTime))
                running = false;

        }




        /*
        // test
        while (D.size() > 0) {
            Process p = D.removeMin().getValue();
            System.out.println("\n" + p.getPriority());
            System.out.println(p.getId());
            System.out.println(p.getArrivalTime());
            System.out.println(p.getDuration());
        }

         */
    }
}
