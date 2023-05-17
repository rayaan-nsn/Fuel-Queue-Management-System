import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    static int totalFuel = 6600;            // Total remaining fuel stock
    static int pricePerLiter = 430;         // Price of a fuel liter
    static int customersPerQ = 6;           // Number of customers that can be hold in a queue
    static int noOfPumps = 5;               // Number of pumps in the Fuel center


    static FuelQueue[] allQueues = new FuelQueue[5];
    static WaitingQueue waitingQ = new WaitingQueue();      // Waiting Queue Array


    public static void main(String[] args) {
        createQueue();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                _______________________________________________________________
                            !!!Fuel Management System!!!!!
                                              
                 Select your option below,
                     100 or VFQ:  View all Fuel Queues
                     101 or VEQ:  View all Empty Queues
                     102 or ACQ:  Add customer to a Queue
                     103 or RCQ:  Remove a customer from a specific location
                     104 or PCQ:  Remove a served customer
                     105 or VCS:  View Customers Sorted in alphabetical order
                     106 or SPD:  Store Program Data into file
                     107 or LPD:  Load Program Data from file
                     108 or STK:  View Remaining Fuel Stock
                     109 or AFS:  Add Fuel Stock
                     110 or IFQ:  Print the income of Fuel queue.
                     999 or EXT:  Exit the Program
                _______________________________________________________________""");

        while (true) {
            System.out.print("Enter option : ");
            String choice = scanner.next();

            switch (choice) {
                case "100", "VFQ", "vfq" -> allFuelQueue();
                case "101", "VEQ", "veq" -> emptyQueue();
                case "102", "ACQ", "acq" -> addCustomer();
                case "103", "RCQ", "rcq" -> removeSpecific();
                case "104", "PCQ", "pcq" -> removeServed();
                case "105", "VCS", "vcs" -> alphabeticSort();
                case "106", "SPD", "spd" -> storeData();
                case "107", "LPD", "lpd" -> loadData();
                case "108", "STK", "stk" -> System.out.println("Remaining Fuel Stock is " + totalFuel + " liters");
                case "109", "AFS", "afs" -> addStock();
                case "110", "IFQ", "ifq" -> printFuelQIncome();
                case "999", "EXT", "ext" -> System.exit(0);
                default -> System.out.println("Invalid Key");
            }
        }
    }


    static void createQueue() {
        FuelQueue Queue1 = new FuelQueue(1);
        FuelQueue Queue2 = new FuelQueue(2);
        FuelQueue Queue3 = new FuelQueue(3);
        FuelQueue Queue4 = new FuelQueue(4);
        FuelQueue Queue5 = new FuelQueue(5);

        allQueues[0] = Queue1;
        allQueues[1] = Queue2;
        allQueues[2] = Queue3;
        allQueues[3] = Queue4;
        allQueues[4] = Queue5;
    }


    static void allFuelQueue() {
        for (int i = 0; i < noOfPumps; i++) {
            System.out.print("Pump " + (i + 1) + " Queue List = ");
            ArrayList<Passenger> Passen = allQueues[i].getQueue();
            for (Passenger p : Passen) {
                System.out.print(p.getFullname() + ", ");
            }
            System.out.println();
        }
        if (!waitingQ.isEmpty()) {
            System.out.print("Waiting Queue List = ");
            Passenger[] waiting = waitingQ.getWaitingQ();
            for (Passenger qp : waiting) {
                System.out.print(qp.getFullname() + ", ");
            }
        }else {
            System.out.println("Waiting list is empty");
        }
        System.out.println();
    }   //View all Fuel Queues

    static void emptyQueue() {
        for (int i = 0; i < noOfPumps; i++){
            if (allQueues[i].getCustomerLength() == 0) {
                System.out.println("Pump " + (i + 1) + " is empty");      // i+1 = Pump No to begin with 1 instead of 0
            }else {
                System.out.println("Pump " + (i + 1) + " is not empty");
            }
        }
    }   //View all Empty Queues

    static void addCustomer() {
        Scanner scan = new Scanner(System.in);
        String YesNo;
        boolean opt = true;
        try {
            do {
                if (opt) {
                    System.out.print("Add first name : ");
                    String firstName = scan.next();
                    System.out.print("Add last name : ");
                    String lastName = scan.next();
                    System.out.print("Add vehicle number : ");
                    String vehicleNo = scan.next();
                    System.out.print("Required fuel amount : ");
                    int fuelAmount = scan.nextInt();

                    Passenger passenger = new Passenger(firstName, lastName, vehicleNo, fuelAmount);

                    if (!(allQueues[1].getCustomerLength() == customersPerQ)) {     // This will check last pump's last customer. This will run till it fills.
                        for (int i = 0; i < customersPerQ; i++) {
                            if (allQueues[0].getCustomerLength() == i) {
                                allQueues[0].setAddCustomer(passenger);
                                System.out.println(firstName + " " + lastName + " added to Pump 1 queue successfully");
                                totalFuel = totalFuel - passenger.getRequiredFuel();
                                break;
                            }
                            if (allQueues[1].getCustomerLength() == i) {
                                allQueues[1].setAddCustomer(passenger);
                                System.out.println(firstName + " " + lastName + " added to Pump 2 queue successfully");
                                totalFuel = totalFuel - passenger.getRequiredFuel();
                                break;
                            }
                            if (allQueues[2].getCustomerLength() == i) {
                                allQueues[2].setAddCustomer(passenger);
                                System.out.println(firstName + " " + lastName + " added to Pump 3 queue successfully");
                                totalFuel = totalFuel - passenger.getRequiredFuel();
                                break;
                            }
                            if (allQueues[3].getCustomerLength() == i) {
                                allQueues[3].setAddCustomer(passenger);
                                System.out.println(firstName + " " + lastName + " added to Pump 4 queue successfully");
                                totalFuel = totalFuel - passenger.getRequiredFuel();
                                break;
                            }
                            if (allQueues[4].getCustomerLength() == i) {
                                allQueues[4].setAddCustomer(passenger);
                                System.out.println(firstName + " " + lastName + " added to Pump 5 queue successfully");
                                totalFuel = totalFuel - passenger.getRequiredFuel();
                                break;
                            }
                        }
                    } else {        // Runs If the last pump's last customer filled
                        if (!waitingQ.isFull()) {   // To check space availability in waiting queue
                            waitingQ.enQueue(passenger);
                            System.out.println(firstName+" "+lastName+" added to the waiting queue");
                            waitingQ.show();
                        } else {
                            System.out.println("Waiting Queue is Full");
                        }
                    }if (totalFuel<=500){
                        System.out.println(" !!!WARNING!!! Fuel Stock is less than 500 liters (Remaining Stock : "+totalFuel+")");
                    }
                    System.out.print("Add another customer? (y for Yes | AnyKey for No) : ");
                    YesNo = scan.next();
                    if (!YesNo.equalsIgnoreCase("y")) {
                        opt = false;
                    }
                } else {
                    break;
                }
            } while (true);
        } catch (InputMismatchException e) {
            System.out.println("Ender a valid fuel amount ");
        }
        scan.nextLine();
    }   //Add customer to a Queue


    static void removeSpecific() {
        try {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("Select the Pump (1-5) : ");
                int pumpNum = scan.nextInt();

                if (pumpNum > noOfPumps) {
                    System.out.println("Invalid Pump");
                } else {
                    Scanner pos = new Scanner(System.in);
                    System.out.print("Enter the Queue Position Number (1-6) : ");
                    int position = pos.nextInt();
                    if (position > customersPerQ) {
                        System.out.println("Invalid position");
                    } else {
                        allQueues[pumpNum - 1].setRemoveCustomer(position - 1);
                        if (!waitingQ.isEmpty()) {
                            for (int i = 0; i < noOfPumps; i++) {
                                if (allQueues[i].getCustomerLength() < customersPerQ) {
                                    allQueues[i].setAddCustomer(waitingQ.getFrontObj());
                                    waitingQ.deQueue();
                                    break;
                                }
                            }
                        }
                        System.out.println("Pump " + pumpNum + ", queue member " + position + " is Successfully Removed");
                    }
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid, Enter a number (1-5)");
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Customer not available in the Pump");
        }
    }   //Remove a customer from a specific location


    static void removeServed() {
        try {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("Select the Pump (1-5) to remove the Served Customer : ");
                int pumpNum = scan.nextInt();
                if (pumpNum > noOfPumps) {
                    System.out.println("Invalid Pump");
                } else {
                    int customerIncome = pricePerLiter * allQueues[pumpNum - 1].getCustomer(0).getRequiredFuel();
                    allQueues[pumpNum - 1].setRemoveCustomer(0);      //First customer in the list will be removed
                    allQueues[pumpNum - 1].setInncome(customerIncome);
                    if (!waitingQ.isEmpty()) {
                        for (int i = 0; i < noOfPumps; i++) {
                            if (allQueues[i].getCustomerLength() < customersPerQ) {
                                allQueues[i].setAddCustomer(waitingQ.getFrontObj());
                                waitingQ.deQueue();
                                break;
                            }
                        }
                        System.out.println("Pump " + pumpNum + " customer is Served Successfully");
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No customers available in the Pump ");
            }
        }catch (InputMismatchException e) {
            System.out.println("Invalid, Enter a number (1-5)");
        }
    }   //Remove a served customer


      static void alphabeticSort() {
          for (int i=0; i<noOfPumps; i++){
              ArrayList<Passenger> Passen = allQueues[i].getQueue();  //copy of Queue
              ArrayList<String> customerNames = new ArrayList<>();
              for (Passenger p : Passen) {
                  customerNames.add(p.getFullname());
              }
              customerNames.sort(String.CASE_INSENSITIVE_ORDER);
              System.out.println("Pump "+ (i+1) + " queue in alphabetical order = " + customerNames);
          }
      }   //View Customers Sorted in alphabetic order



      static void storeData() {    // Java Create and Write To Files (https://www.w3schools.com/java/java_files_create.asp)
          try {
              FileWriter myWriter = new FileWriter("Main.txt");
              myWriter.write("\n_______________________________________________________________ \n");
              myWriter.write("Remaining Fuel Balance is "+totalFuel+" liters"+"\n"+"\n");
              for (int i = 0; i < noOfPumps; i++) {
                  myWriter.write("Pump " + (i + 1) + " Queue List = ");
                  ArrayList<Passenger> Passen = allQueues[i].getQueue();  //copy of Queue
                  for (Passenger p : Passen) {
                      myWriter.write(p.getFirstName() +" "+ p.getSecondName() + ", ");
                  }
                  myWriter.write("\n");
              }
              myWriter.write("_______________________________________________________________ \n \n");
              for (int i = 0; i < noOfPumps; i++) {
                  ArrayList<Passenger> Passen = allQueues[i].getQueue();  //copy of Queue
                  for (Passenger p : Passen) {
                      myWriter.write("<-- Details of " + p.getFirstName() +" "+ p.getSecondName() + " --> \n");
                      myWriter.write("First Name : " + p.getFirstName() +"\n" +
                              "Second Name : " + p.getSecondName() + "\n" +
                              "Vehicle Number : " + p.getVehicleNo() + "\n" +
                              "Required Fuel Amount : " + p.getRequiredFuel() + "\n");
                      myWriter.write("\n");
                  }
              }
              myWriter.write("\n_______________________________________________________________ \n");
              myWriter.close();
              System.out.println("Successfully wrote to the file");
          } catch (IOException e) {
              System.out.println("An error occurred");
              e.printStackTrace();
          }
      }   //Store Program Data into file


    static void loadData() {    //Java Read Files (https://www.w3schools.com/java/java_files_read.asp)
        try {
            File myObj = new File("Main.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }  //Load Program Data from file


    static void addStock() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter adding Stock : ");
            int addFuel = scan.nextInt();
            totalFuel = totalFuel + addFuel;
            System.out.println(addFuel + " liters added to the Stock");
        }catch (InputMismatchException e){
            System.out.println("Invalid input, add only numbers");
        }
    }   //Add Fuel Stock


    static void printFuelQIncome() {
        for (int i = 0; i < noOfPumps; i++) {
            System.out.println("Pump " + (i + 1) + " income : Rs." + allQueues[i].getInncome());
        }
    }  // Print the income of Fuel queues


}
