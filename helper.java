import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class helper {
    Car c;
    String name;
    int n;
    static LocalDateTime reservationTime;
    LocalDateTime date;
    Scanner sc=new Scanner(System.in);
    public static List<Car>readCarsFromCSV() {
        List<Car> ca = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("carDetails.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String cname=data[0];
                String model = data[1];
                int year = Integer.parseInt(data[2]);
                String color = data[3];
                int seatingCapacity = Integer.parseInt(data[4]);
                Float rentPerDay = Float.parseFloat(data[5]);
                boolean isAvailable= Boolean.parseBoolean(data[6]);
                String reservedBy=data[7];
                LocalDateTime reservationTime = null;
                String reservationTimeStr = data[8];
                int carCount=Integer.parseInt(data[9]);
                if (!"null".equals(reservationTimeStr)) {
                 reservationTime = LocalDateTime.parse(reservationTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                }
                Car car = new Car(cname,model, year, color, seatingCapacity,rentPerDay,isAvailable,reservedBy,carCount);
                car.setReservationTime(reservationTime);
                ca.add(car);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ca;
    }
   public static void writeCarsToCSV(List<Car> cars) {

        try {
           BufferedWriter bw = new BufferedWriter(new FileWriter("carDetails.csv"));
            for (Car car : cars) {
                String line = car.getName() + "," + car.getModel() + "," + car.getYear() + "," + car.getColor() + ","
                + car.getSeatingCapacity() + "," + car.getRentPricePerDay() + "," + car.getisAvailable() + ","
                + car.getCurrentUser()+","+car.getReservationTime()+","+car.getCarCount();
                // System.out.println(line);
        bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static List<RentalDetail> readRentalDetails() {
        List<RentalDetail> rentalDetails = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("crzz.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String customerName = data[0];
                String carName = data[1];
                String carModel = data[2];
                int year = Integer.parseInt(data[3]);
                float rentPricePerDay = Float.parseFloat(data[4]);
                float totalPrice = Float.parseFloat(data[5]);
                float additionalCharges = Float.parseFloat(data[6]);
                LocalDateTime rentalTime = LocalDateTime.parse(data[7], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                LocalDateTime actualReturnTime = LocalDateTime.parse(data[8], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

                RentalDetail rentalDetail = new RentalDetail(customerName, carName, carModel, year, rentPricePerDay, totalPrice, additionalCharges, rentalTime, actualReturnTime);
                rentalDetails.add(rentalDetail);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

        return rentalDetails;
    }
    void display_rd(){
        System.out.println("The cars that have been on rent so far:");
        List<RentalDetail> rDetails=readRentalDetails();
        for(RentalDetail r:rDetails){
            System.out.println(r);
        }
    }
    void display(){
        System.out.println("All cars:");
        List<Car>c=readCarsFromCSV();
        for(Car x:c){
                System.out.println(x);
        }
    }
    void display_sb(int s ){
        System.out.println("Details of all the "+s+" seater cars:");
         List<Car>c=readCarsFromCSV();
            for(Car x:c){
                if(x.getSeatingCapacity()==s){
                    System.out.println(x);
                }
        }
    }
    void display_mb(String m){
        System.out.println("Details of the car you are searching for:");
         List<Car>c=readCarsFromCSV();
            for(Car x:c){
                if(x.getModel().equals(m)){
                    System.out.println(x);
                }
        }

    } 
    void display_rc(){

        System.out.println("All Reserved cars:");
         List<Car>c=readCarsFromCSV();
            for(Car x:c){
                if(!x.getisAvailable()){
                    System.out.println(x);
                }
        }
    }
    void display_ac(){ int k=0;
        System.out.println("All Available Cars:");               
         List<Car>c=readCarsFromCSV();
        for(Car x:c){
                if(x.getisAvailable()){
                    System.out.println(x);
                    k++;
                }
        } if(k==0)
        System.out.println("null");
    }

    void reserveCar(String mo_name,String cust_name){
        List<Car>l=readCarsFromCSV();
        for(Car x:l){
            if(x.getModel().equals(mo_name)){
            this.c=x;
            if (!c.getisAvailable()) {
                System.out.println("Sorry, the car is already reserved.");
                return;
            }
            c.IncrementCarCount();
            c.setIsAvailable(false);
            c.setCurrentUser(cust_name);
            System.out.println("Enter the date and time to pickup in the format 'yyyy-MM-dd HH:mm': ");
            String inputDate = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(inputDate, formatter);
            c.setReservationTime(date);
            LocalDateTime returnTime = date.plusHours(24);
            c.setReturnTime(returnTime);
            System.out.println("your Booking is confirmed!");
            writeCarsToCSV(l);                           
            return;
            }
        }
        System.out.println("Booking was not possible!");


    }
    void returnCar(String cust){
        List<Car>l=readCarsFromCSV();
        float additionalCharges=0;
        for(Car x:l){
            if(x.getCurrentUser().equals(cust)){
            this.c=x;
            LocalDateTime actualReturnTime = LocalDateTime.now();
            final long hoursDelay = ChronoUnit.HOURS.between(c.getReservationTime(),actualReturnTime)-24;
            System.out.print("the car is returned!\n\n");
            System.out.println("Pricing Details:");
            if (hoursDelay > 0) {
                additionalCharges = 100* hoursDelay;
                System.out.println("Additional Charges due to delay: " + additionalCharges);
            }
            c.setIsAvailable(true);
            c.setCurrentUser(null);
            float totalRentPrice = c.getRentPricePerDay() + additionalCharges;
             try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("crzz.csv", true));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            String rentalDetail = cust + "," + c.getName() + "," + c.getModel() + "," + c.getYear() + "," +
                    c.getRentPricePerDay() + "," + totalRentPrice + "," + additionalCharges + "," +
                    c.getReservationTime().format(formatter) + "," + LocalDateTime.now().format(formatter);
            bw.write(rentalDetail);
            bw.write(System.lineSeparator()); 
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            c.setTotalRentPrice(totalRentPrice);
            System.out.println();
            System.out.println("Rent:       "+c.getRentPricePerDay());
            System.out.println("Additional: "+ additionalCharges);
            System.out.println("----------------------");
            System.out.println("TotalPrice: " +c.getTotalRentPrice()); 
            c.setReservationTime(null);
            c.setReturnTime(null);
            writeCarsToCSV(l);
            return;
            }
        }
        if(this.c==null){
            System.out.println("No customer found ");
        }
        writeCarsToCSV(l);

    }
}