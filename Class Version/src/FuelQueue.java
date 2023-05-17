import java.util.ArrayList;

public class FuelQueue {
    private ArrayList<Passenger> Queue = new ArrayList<>();     // ArrayList declaration

    private int pump;
    private int income = 0;

    //Constructor method
    public FuelQueue(int pump){
        this.pump = pump;
    }

    public void setAddCustomer (Passenger passenger){      // Setter for adding passenger to the Queue
        Queue.add(passenger);
    }

    public void setRemoveCustomer (int position){      // Setter for remove specific passenger from Queue
        Queue.remove(position);
    }

    public ArrayList<Passenger> getQueue() {     // Getter for the ArrayList
        return Queue;
    }

    public int getCustomerLength() {     // Getter for the customer array length
        return Queue.size();
    }

    public void setInncome(int pumpIncome){
        income = income + pumpIncome;
    }

    public Passenger getCustomer(int position){
        return Queue.get(position);
    }

    public int getInncome(){
        return income;
    }

}
