import java.text.ParseException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main 
{

    public static void main(String[] args) 
    {
        try 
        {
            // Enter some stuff
            System.out.println("Enter the future date (yyyy-MM-dd HH:mm:ss): ");
            String inputDate = new Scanner(System.in).nextLine();

            while (true) 
            {
                // Difference Calculation
                long timeDifference = DateCalculator.calculateTimeDifference(inputDate);

                if (timeDifference <= 0) 
                {
                    System.out.println("The specified time has passed.");
                    break;
                }

                // Converion Code
                long[] timeComponents = DateCalculator.convertMilliseconds(timeDifference);

                // You can see the countdown now!
                System.out.println("Time left until " + inputDate + ":");
                System.out.println(timeComponents[0] + " days, " + timeComponents[1] + " hours, " + timeComponents[2] + " minutes, " + timeComponents[3] + " seconds");

                // Updates the countdown, Duh
                TimeUnit.SECONDS.sleep(1);
            }

        } 
        catch (ParseException | InterruptedException e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
