
import net.datastructures.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProcessScheduling {

     private static HeapPriorityQueue<Integer, Process> readProcesses(){
        HeapPriorityQueue<Integer, Process> D = new HeapPriorityQueue<>();
        String lineFromFile;

        try{
            FileInputStream processFile = new FileInputStream(
                    "resources/process_scheduling_in.txt");
            Scanner fileInput = new Scanner(processFile);

            while (fileInput.hasNextLine()){            // loop through file lines
                lineFromFile = fileInput.nextLine();    // get next line from file

                // splits file line by space, into a String array
                String[] splitLine = lineFromFile.split(" ");

                Process p = new Process();
                p.setId(Integer.valueOf(splitLine[0]));
                p.setPriority(Integer.valueOf(splitLine[1]));
                p.setDuration(Integer.valueOf(splitLine[2]));
                p.setArrivalTime(Integer.valueOf(splitLine[3]));

                D.insert(p.getArrivalTime(), p);
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


    private static void printProcess(Process p, Integer currentTime, Integer waitTime, Double totalTime){
         System.out.println("\nProcess removed from queue is: id = " + p.getId()
                 + ", at time " + currentTime + ", wait time = " + waitTime +
                 " Total wait time = " + totalTime);
         System.out.println("Process id = " + p.getId());
         System.out.println("        Priority = " + p.getPriority());
         System.out.println("        Arrival = " + p.getArrivalTime());
         System.out.println("        Duration = " + p.getDuration());
    }


    public static void main(String[] args) {

        HeapPriorityQueue<Integer, Process> D = readProcesses();
        Integer currentTime = 0;    // current time set to 0 at start
        Boolean running = false;    // T if process running, otherwise F
        HeapPriorityQueue<Integer, Process> Q = new HeapPriorityQueue<>();
        Integer endTime = 0;
        Integer waitTime;
        Double totalWaitTime = 0.0;
        Process runningProcess = null;
        Integer executionCount = 0;

        // start reading processQueue values into the heap priority queue
        while (!(D.isEmpty())){

            Process p = D.min().getValue();                         // process w/ min arrivalTime
            if (p.getArrivalTime() <= currentTime){
                Process removedProcess = D.removeMin().getValue();  // process w/ min arrivalTime removed from D
                removedProcess.setEntryTime(currentTime);
                Q.insert(removedProcess.getPriority(),
                        removedProcess);
            }

            if (!(Q.isEmpty()) && !running){
                runningProcess = Q.removeMin().getValue();

                // Get the process wait time and add it to totalWaitTime
                waitTime = currentTime - runningProcess.getEntryTime();
                totalWaitTime += waitTime;
                printProcess(runningProcess, currentTime, waitTime, totalWaitTime);
                running = true;
                endTime = currentTime + runningProcess.getDuration();
            }

            currentTime++;

            if (running && currentTime.equals(endTime)){
                running = false;
                System.out.println("Process " + runningProcess.getId() +
                        " finished at time " + currentTime);
                executionCount++;
            }
        }

        System.out.println("\nD becomes empty at time " + (currentTime - 1) + "\n");

        while (!(Q.isEmpty())){

            if (!running){
                runningProcess = Q.removeMin().getValue();
                waitTime = currentTime - runningProcess.getEntryTime();
                totalWaitTime += waitTime;
                printProcess(runningProcess, currentTime, waitTime, totalWaitTime);

                running = true;
                endTime = currentTime + runningProcess.getDuration();
            }

            currentTime++;

            if (running && currentTime.equals(endTime)){
                running = false;
                System.out.println("Process " + runningProcess.getId() +
                        " finished at time " + currentTime);
                executionCount++;
            }
        }

        while (running){
            if (currentTime.equals(endTime)){
                running = false;
                System.out.println("Process " + runningProcess.getId() +
                        " finished at time " + currentTime);
                executionCount++;
            }
            currentTime++;
        }

        System.out.println("\nTotal wait time = " + totalWaitTime);
        System.out.println("Average wait time = " + (totalWaitTime / executionCount));

    }
}
