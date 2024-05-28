import java.util.*;
public class CarRental 
{ 
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        helper h=new helper();
        int ch=0;
        while(ch!=7)
        {
            System.out.println("\nWelcome to the CarRentals!!\n");        
            System.out.println("Choose a numeric value from below list");
		    System.out.println("---------------------------------------");
		    System.out.println("Enter 1 : Show All cars");
		    System.out.println("Enter 2 : Show All Available Cars");
		    System.out.println("Enter 3 : Show All Reserved Cars");
		    System.out.println("Enter 4 : To rent a Car");
		    System.out.println("Enter 5 : To return the Car");
		    System.out.println("Enter 6 : History");
		    System.out.println("Enter 7 : To Exit");
		    System.out.println("----------------------------------------");
            try
            {
                ch=Integer.parseInt(sc.nextLine());
                switch (ch) 
                {
                    case 1:
                        h.display();
                        break;
                    case 2:
                        h.display_ac();  
                        break;
                    case 3:
                        h.display_rc();
                        break;
                    case 4:
                        int op=0;
                        try
                        {
                            System.out.println("Enter 1:  If you are searching for a seater based car ");
                            System.out.println("Enter 2:  If you are searching for a model/company based car ");
                            op=Integer.parseInt(sc.nextLine());
                        }
                        catch(Exception e)
                        {
                            System.out.println("choose a valid option");
                        }
                        if(op==1)
                        {
                            try
                            {
                                System.out.println("4 or 5 or 7");
                                int k=Integer.parseInt(sc.nextLine());
                                if(k==4 || k==5 || k==7)
                                    h.display_sb(k);
                                else
                                {
                                    System.out.println("The "+k+" seater car is not avilable");
                                    break;
                                }
                            }
                            catch(Exception e)
                            {
                                System.out.println("Enter only from the above options given");
                            }
                        }
                        else if(op==2)  
                        {
                            try
                            {
                                System.out.println("Enter the model of the car you want:");
                                String mname=sc.nextLine();
                                h.display_mb(mname);
                            }
                            catch(Exception e)
                            {
                                System.out.println("make sure you enter the model name of a car existing in the record ");
                            }
                        }
                        else
                        {
                            System.out.println("choose only 1 or 2");
                            break;
                        }
                        System.out.println("Enter the model name of the car you want to rent : ");
                        String rname=sc.nextLine();
                        System.out.println("Enter your name: ");
                        String cust=sc.nextLine();
                        System.out.println("So do you want to Confirm the booking(yes/no): ");
                        String y=sc.nextLine();
                        try
                        {
                            if(y.equals("yes"))
                            {
                                h.reserveCar(rname,cust);
                            }
                        }
                        catch(Exception e)
                        {

                            System.out.println("Enter correct details please!\n");
                            //    System.out.println("Invalid: "+e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    case 5:
                        System.out.println("Please enter your name (as per the  car booking record): ");
                        try
                        {  
                            String customer=sc.nextLine();
                            h.returnCar(customer);      
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        break;
                    case 6:
                        h.display_rd();
                        break;
                    case 7:
					    System.exit(0);
                    default:
                        System.out.println("Enter a valid option!\n");    
                }   
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

