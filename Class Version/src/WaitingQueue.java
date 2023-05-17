//References : | Telusko YouTube | Queue Implementation using Java / Circular Array Complete playlist of Data Structure Using Java
// P1:https://youtu.be/PvDoT79oHTs  P2:https://youtu.be/8K1rt6v5mJ4    P3:https://youtu.be/23DjZA7AuOY

public class WaitingQueue {
    private Passenger[] waitingList = new Passenger[7];     // Array declaration
    private int size;
    private int front;
    private int rear;


    public void enQueue(Passenger passenger) {      // To insert passengers to the queue
        if (!isFull()) {
            waitingList[rear] = passenger;
            rear = (rear + 1) % waitingList.length;
            size = size + 1;
        }else
            System.out.println("Queue is full");
    }

    public Passenger deQueue() {    // To remove passengers from the queue (front will increase by +1)
        Passenger passenger = waitingList[front];
        if (!isEmpty()) {
            front = (front + 1) % waitingList.length;
            size = size - 1;
        }else
            System.out.println("Waiting Queue is Empty");
        return passenger;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {   // Check the list is empty or not
        return getSize() == 0;
    }

    public boolean isFull() {    // Check the list is full or not
        return getSize() == waitingList.length;
    }

    public Passenger getFrontObj() {     // Getter to get front object in the list
        return waitingList[front];
    }

    public void show() {
        System.out.print("Waiting Queue : ");
        for (int i = 0; i < size; i++){
            System.out.print(waitingList[(front+i) % waitingList.length] + " ");
        }
        System.out.println();
    }

    public Passenger[] getWaitingQ() {     // Getter for the ArrayList
        return waitingList;
    }

}