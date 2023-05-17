import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ArraysVersion {
    static int TotFuel = 6600;
    static int PerCustomer = 10;

    static String[] Pump1 = new String[6];
    static String[] Pump2 = new String[6];
    static String[] Pump3 = new String[6];


    public static void main(String[] args) {

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
                     999 or EXT:  Exit the Program
                _______________________________________________________________""");

        while (true) {
            System.out.print("Enter option : ");
            String choice = scanner.next();

            if (choice.equals("100") || choice.equalsIgnoreCase("VFQ")) {  //Ignoring lower & upper case differences (w3schools)
                AllFuelQueue();
            } else if (choice.equals("101") || choice.equalsIgnoreCase("VEQ")) {
                EmptyQueue();
            } else if (choice.equals("102") || choice.equalsIgnoreCase("ACQ")) {
                AddCustomer();
            } else if (choice.equals("103") || choice.equalsIgnoreCase("RCQ")) {
                RemoveSpecific();
            } else if (choice.equals("104") || choice.equals("PCQ")) {
                RemoveServed();
            } else if (choice.equals("105") || choice.equalsIgnoreCase("VCS")) {
                AlphabeticSort();
            } else if (choice.equals("106") || choice.equalsIgnoreCase("SPD")) {
                StoreData();
            } else if (choice.equals("107") || choice.equalsIgnoreCase("LPD")) {
                LoadData();
            } else if (choice.equals("108") || choice.equalsIgnoreCase("STK")) {
                System.out.println("Remaining Fuel Stock is " + TotFuel + " liters");
            } else if (choice.equals("109") || choice.equals("AFS")) {
                AddStock();
            } else if (choice.equals("999") || choice.equalsIgnoreCase("EXT")) {
                System.exit(0);
            } else {
                System.out.println("Invalid Key");
            }
            }
        }

    static void AllFuelQueue() {
        System.out.println("Pump 1 Queue List = " + Arrays.toString(Pump1));
        System.out.println("Pump 2 Queue List = " + Arrays.toString(Pump2));
        System.out.println("Pump 3 Queue List = " + Arrays.toString(Pump3));
    }   //View all Fuel Queues

    static void EmptyQueue() {
        if (Pump1[0] == null) {
            System.out.println("Pump 1 is Empty");
        }if (Pump2[0] == null) {
            System.out.println("Pump 2 is Empty");
        }if (Pump3[0] == null) {
            System.out.println("Pump 3 is Empty");
        }else
            System.out.println("No Empty Queue Available");
    }   //View all Empty Queues

    static void AddCustomer() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Select the Pump : ");
        String PumpNum = scan.next();
        String YesNo;
        boolean opt = true;
        try {
            do {
                if (opt) {  //(opt == true)
                    if (PumpNum.equals("1")) {
                        System.out.print("Add Customer : ");
                        String CustName = scan.next();
                        for (int j = 0; j < 6; j++) {
                            if (Pump1[j] == null) {
                                Pump1[j] = CustName;
                                TotFuel = TotFuel-PerCustomer;
                                break;
                            }
                        }
                    }if (PumpNum.equals("2")) {
                        System.out.print("Add Customer : ");
                        String CustName = scan.next();
                        for (int j = 0; j < 6; j++) {
                            if (Pump2[j] == null) {
                                Pump2[j] = CustName;
                                TotFuel = TotFuel-PerCustomer;
                                break;
                            }
                        }
                    }if (PumpNum.equals("3")) {
                        System.out.print("Add Customer : ");
                        String CustName = scan.next();
                        for (int j = 0; j < 6; j++) {
                            if (Pump3[j] == null) {
                                Pump3[j] = CustName;
                                TotFuel = TotFuel-PerCustomer;
                                break;
                            }
                        }
                    }if (TotFuel<=500){
                        System.out.println(" !!!WARNING!!! Fuel Stock is less than 500 liters ");
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
            System.out.println("Ender valid num");
        }
        scan.nextLine();
    }   //Add customer to a Queue

    static void RemoveSpecific(){       //Remove Array Element in Java(https://www.youtube.com/watch?v=r1L_71N-5rs)
        Scanner scan = new Scanner(System.in);
        System.out.print("Select the Pump : ");
        String PumpNum = scan.next();
        System.out.print("Select the target : ");
        int target = scan.nextInt();
        int logicalsize = 6;
        if (PumpNum.equals("1")) {
            for (int i = target; i < logicalsize - 1; i++) {
                Pump1[i] = Pump1[i + 1];
            }
            Pump1[logicalsize - 1] = null;
            logicalsize--;
            TotFuel=TotFuel+PerCustomer;
            System.out.println("Customer Removed");
        }
        if (PumpNum.equals("2")) {
            for (int i = target; i < logicalsize - 1; i++) {
                Pump2[i] = Pump2[i + 1];}
            Pump2[logicalsize - 1] = null;
            logicalsize--;
            TotFuel=TotFuel+PerCustomer;
            System.out.println("Customer Removed");
        }
        if (PumpNum.equals("3")) {
            for (int i = target; i < logicalsize - 1; i++) {
                Pump3[i] = Pump3[i + 1];}
            Pump3[logicalsize - 1] = null;
            logicalsize--;
            TotFuel=TotFuel+PerCustomer;
            System.out.println("Customer Removed");
        }if (!(PumpNum.equals("1") || PumpNum.equals("2") || PumpNum.equals("3"))) {
            System.out.println("Invalid Pump Number");
        }
    }   //Remove a customer from a specific location

    static void RemoveServed(){     //Remove Array Element in Java(https://www.youtube.com/watch?v=r1L_71N-5rs)
        int logicalsize = 6;
        Scanner scan = new Scanner(System.in);
        System.out.print("Select the Pump Number to remove the Served Customer : ");  //First customer in the list will be removed
        String PumpNum = scan.next();
        if (PumpNum.equals("1")) {
            for (int i = 0; i < logicalsize - 1; i++) {
                Pump1[i] = Pump1[i + 1];}
            Pump1[logicalsize - 1] = null;
            logicalsize--;
            System.out.println("Served Customer Removed");
        }
        if (PumpNum.equals("2")) {
            for (int i = 0; i < logicalsize - 1; i++) {
                Pump2[i] = Pump2[i + 1];}
            Pump2[logicalsize - 1] = null;
            logicalsize--;
            System.out.println("Served Customer Removed");
        }
        if (PumpNum.equals("3")) {
            for (int i = 0; i < logicalsize - 1; i++) {
                Pump3[i] = Pump3[i + 1];}
            Pump3[logicalsize - 1] = null;
            logicalsize--;
            System.out.println("Served Customer Removed");
        }
    }   //Remove a served customer

    static void SortName(String[] arr, int n) {   //https://www.geeksforgeeks.org/sorting-strings-using-bubble-sort-2/
        String temp;
        for (int j = 0; j < n - 1; j++) {   // Sorting strings using bubble sort
            for (int i = j + 1; i < n; i++)
                if (!(arr[j] == null || arr[i] == null)) {
                    if (arr[j].compareToIgnoreCase(arr[i]) > 0) {
                        temp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = temp;
                    }
                }
        }
    }   //Belongs to AlphabeticSort
    static void AlphabeticSort(){   //https://www.geeksforgeeks.org/sorting-strings-using-bubble-sort-2/
        Scanner scan = new Scanner(System.in);
        System.out.print("Select the Pump Number to sort in Alphabetically : ");
        String PumpNum = scan.next();
        if (PumpNum.equals("1")) {
            String[] Pump1copy = Arrays.copyOf(Pump1,Pump1.length);     //https://www.youtube.com/watch?v=ZJFSr-qjjts(How to copy arrays using Arrays.copyOf() method in java?)
            int n = Pump1copy.length;
            SortName(Pump1copy, n);
            System.out.println("Pump 1 Alphabetic list = " + Arrays.toString(Pump1copy));
        }if (PumpNum.equals("2")) {
            String[] Pump2copy = Arrays.copyOf(Pump2,Pump2.length);
            int n = Pump2copy.length;
            SortName(Pump2copy, n);
            System.out.println("Pump 2 Alphabetic list = " + Arrays.toString(Pump2copy));
        }if (PumpNum.equals("3")) {
            String[] Pump3copy = Arrays.copyOf(Pump3,Pump3.length);
            int n = Pump3copy.length;
            SortName(Pump3copy, n);
            System.out.println("Pump 3 Alphabetic list = " + Arrays.toString(Pump3copy));
        }
    }   //View Customers Sorted in alphabetical order

    static void StoreData(){    //https://www.w3schools.com/java/java_files_create.asp
        try {
            FileWriter myWriter = new FileWriter("FuelQueue.txt");
            myWriter.write("Remaining Fuel Balance is "+TotFuel+" liters"+"\n"+"\n"+
                    "Pump 1 Queue List = "+Arrays.toString(Pump1)+"\n"+
                    "Pump 2 Queue List = "+Arrays.toString(Pump2)+"\n"+
                    "Pump 3 Queue List = "+Arrays.toString(Pump3)+"\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file");
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }   //Store Program Data into file

    static void LoadData(){    //https://www.w3schools.com/java/java_files_read.asp
        try {
            File myObj = new File("FuelQueue.txt");
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
    }   //Load Program Data from file

    static void AddStock() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter adding Stock : ");
        int AddFuel = scan.nextInt();
        TotFuel = TotFuel + AddFuel;
        System.out.println(AddFuel + " liters added to the Stock");
    }   //Add Fuel Stock
}
