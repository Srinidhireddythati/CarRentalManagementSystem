//import java.io.Serializable;
import java.time.LocalDateTime;
public class Car{

    private String cname;
    private String model;
    private int year;
    private int rentCount;
    private int SeatingCapacity;
    private float rentPricePerDay;
    private float totalRentPrice;
    private String color;
    private String reservedBy;
    private boolean isAvailable;
    private LocalDateTime reservationDateTime; 
    private LocalDateTime returnTime; 
    private int CarCount;

    public int getCarCount() {
        return CarCount;
    }
    public void IncrementCarCount() {
        this.CarCount++;
    }


    Car(String name,String model,int year,String color,int SeatingCapacity,float RentPrice,boolean s,String reservedBy,int carCount){
        this.cname=name;
        this.model=model;
        this.reservedBy=reservedBy;
        this.SeatingCapacity = SeatingCapacity;
        this.rentPricePerDay = RentPrice;
        this.color=color;
        this.year=year;
        this.isAvailable=s;
        this.CarCount=carCount;

    }


    public LocalDateTime getReservationTime() {
        return reservationDateTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationDateTime = reservationTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public float getTotalRentPrice() {
        return totalRentPrice;
    }

    public void setTotalRentPrice(float r ) {
        this.totalRentPrice = r;
    }


    public String getName() {
        return cname;
    }

    public void setName(String name) {
        this.cname = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String brand) {
        this.model = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public int getRentCount() {
        return rentCount;
    }

    public void IncrementCount() {
        this.rentCount = rentCount+1;
    }

    public float getRentPricePerDay() {
        return rentPricePerDay;
    }

    public void setRentPricePerDay(float rentPricePerDay) {
        this.rentPricePerDay = rentPricePerDay;
    }

    public int getSeatingCapacity() {
        return SeatingCapacity;
    }

    public void setSeatingCapacity(int scp) {
        this.SeatingCapacity= scp;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCurrentUser() {
        return reservedBy;
    }

    public void setCurrentUser(String currentUser) {
        this.reservedBy = currentUser;
    }
    public void setIsAvailable(boolean isAvailable){
        this.isAvailable=isAvailable;
    }
     public boolean getisAvailable(){
        return isAvailable;
    }


    public String toString(){
        return "company= " + cname +"\nmodel= " + model+"\ncolor= " + color +"\nSeating capacity= " + SeatingCapacity + 
        "\nyear=" + year +
        "\nRentPricePerday=" + rentPricePerDay + 
        "\nisavaible=" + isAvailable +
        "\nno.of times rented=" + CarCount +
        "\nreservedBy= " + reservedBy+
        "\nreservedtime= " + reservationDateTime+"\n\n"; }

}









 // public void calculateBillAmount() {
    //     if (reservationTime != null && returnTime != null) {
    //         long diffInMillies = returnTime.getTime() - reservationTime.getTime();
    //         long totalRentalHours = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    //         float billAmount = RentPerHour * totalRentalHours;

    //         if (totalRentalHours > 24) {
    //             long additionalHours = totalRentalHours - 24;
    //             billAmount += additionalHours * 100;
    //         }

    //         this.numberOfDays = (int) Math.ceil(totalRentalHours / 24.0);
    //         this.totalRentPrice = billAmount;
    //     }
    // }