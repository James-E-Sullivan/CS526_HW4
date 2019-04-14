/**
 * Process class provides a base class for processes with a priority,
 * an id, an arrival time, and a duration.
 */
public class Process {

    private Integer pr;              // priority of the process
    private Integer id;              // process id
    private Integer arrivalTime;     // the time when the process arrives in the system
    private Integer duration;        // execution of the process takes this amount of time
    private Integer entryTime;       // time of entry into HeapPriorityQueue

    public Process(){}              //default constructor

    // alternate constructor
    public Process(Integer i, Integer p, Integer d, Integer a){
        id = i;
        pr = p;
        duration = d;
        arrivalTime = a;
    }

    // getter methods for process variables
    public Integer getPriority(){return pr;}
    public Integer getId(){return id;}
    public Integer getArrivalTime(){return arrivalTime;}
    public Integer getDuration(){return duration;}
    public Integer getEntryTime(){return entryTime;}

    // setter methods for process variables
    public void setPriority(Integer p){pr = p;}
    public void setId(Integer i){id = i;}
    public void setArrivalTime(Integer a){arrivalTime = a;}
    public void setDuration(Integer d){duration = d;}
    public void setEntryTime(Integer e){entryTime = e;}

}
