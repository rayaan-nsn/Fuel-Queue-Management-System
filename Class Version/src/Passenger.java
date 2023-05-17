public class Passenger {

    private String firstName;
    private String secondName;
    private String vehicleNo;
    private int requiredFuel;


    //Passenger constructor with  firstName, lastName, vehicleNo and requiredFuel
    Passenger(String firstName, String secondName, String vehicleNo, int requiredFuel) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.vehicleNo = vehicleNo;
        this.requiredFuel = requiredFuel;
    }

    //setters are to update the current value of the attribute
    //getters are to get the value of the attribute

    public void setFirstName(String firstName) {     // Setter for the firstName
        this.firstName = firstName;
    }

    public String getFirstName() {      // Getter for the firstName
        return firstName;
    }

    public void setSecondName(String secondName) {   // Setter for the secondName
        this.secondName = secondName;
    }

    public String getSecondName() {      // Getter for the secondName
        return secondName;
    }

    public void setVehicleNo(String vehicleNo) {    // Setter for the vehicleNo
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleNo() {      // Getter for the vehicleNo
        return vehicleNo;
    }

    public void setRequiredFuel(int requiredFuel) {     // Setter for the requiredFuel
        this.requiredFuel = requiredFuel;
    }

    public int getRequiredFuel() {       // Getter for the requiredFuel
        return requiredFuel;
    }

    public String getFullname(){
        return getFirstName() + " " + getSecondName();
    }

}
