import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CountdownTimer 
{

    private String targetDate;

    public CountdownTimer(String targetDate) 
    {
        this.targetDate = targetDate;
    }

    public long calculateTimeDifference() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date futureDate = dateFormat.parse(targetDate);
        Date currentDate = new Date();
        return futureDate.getTime() - currentDate.getTime();
    }

    public long[] convertMilliseconds(long timeDifference) 
    {
        long days = timeDifference / (1000 * 60 * 60 * 24);
        long hours = (timeDifference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (timeDifference % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (timeDifference % (1000 * 60)) / 1000;

        return new long[]{days, hours, minutes, seconds};
    }
}
