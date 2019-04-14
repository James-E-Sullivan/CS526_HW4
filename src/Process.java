public class Process<E> {



    private Integer pr;              // priority of the process
    private Integer id;              // process id
    private Integer arrivalTime;     // the time when the process arrives in the system
    private Integer duration;        // execution of the process takes this amount of time


    public Process(){}              //default constructor

    // alternate constructor
    public Process(Integer p, Integer i, Integer a, Integer d){
        pr = p;
        id = i;
        arrivalTime = a;
        duration = d;
    }

    // getter methods for process variables
    public Integer getPriority(){return pr;}
    public Integer getId(){return id;}
    public Integer getArrivalTime(){return arrivalTime;}
    public Integer getDuration(){return duration;}


    /*
    // setter methods for process variables
    public void setPriority(Integer p){pr = p;}
    public void setId(Integer i){id = i;}
    public void setArrivalTime(Integer a){arrivalTime = a;}
    public void setDuration(Integer d){duration = d;}
     */


}
