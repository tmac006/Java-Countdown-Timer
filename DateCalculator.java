import java.text.ParseException;

public class DateCalculator 
{

    public static long calculateTimeDifference(String inputDate) throws ParseException 
    {
        CountdownTimer countdownTimer = new CountdownTimer(inputDate);
        return countdownTimer.calculateTimeDifference();
    }

    public static long[] convertMilliseconds(long timeDifference) 
    {
        CountdownTimer countdownTimer = new CountdownTimer(""); 
        return countdownTimer.convertMilliseconds(timeDifference);
    }
}
