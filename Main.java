import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        try
        {
            // Enter the future date
            System.out.println("Enter the future date (yyyy-MM-dd HH:mm:ss): ");
            String inputDate = new Scanner(System.in).nextLine();

            JFrame frame = new JFrame("Countdown Timer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            frame.getContentPane().add(panel);
            placeComponents(panel, inputDate);

            frame.setSize(400, 200);
            frame.setVisible(true);

            while (true)
            {
                // Difference Calculation
                long timeDifference = DateCalculator.calculateTimeDifference(inputDate);

                if (timeDifference <= 0)
                {
                    System.out.println("The specified time has passed.");
                    break;
                }

                // Conversion Code
                long[] timeComponents = DateCalculator.convertMilliseconds(timeDifference);

                // Update the GUI
                updateGUI(panel, timeComponents);

                // Updates the countdown
                Thread.sleep(1000);
            }
        }
        catch (ParseException | InterruptedException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void placeComponents(JPanel panel, String inputDate)
    {
        panel.setLayout(new BorderLayout());

        JLabel countdownLabel = new JLabel("Countdown Timer", SwingConstants.CENTER);
        countdownLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(countdownLabel, BorderLayout.NORTH);

        JLabel dateLabel = new JLabel("Time Until " + inputDate, SwingConstants.CENTER);
        countdownLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(dateLabel, BorderLayout.CENTER);

        JLabel timeLabel = new JLabel("", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 60));
        panel.add(timeLabel, BorderLayout.SOUTH);
    }

    private static void updateGUI(JPanel panel, long[] timeComponents)
    {
        JLabel timeLabel = (JLabel) panel.getComponent(2); // Assumes the time label is the third component

        String formattedTime = String.format("%d days, %d hours, %d minutes, %d seconds",
                timeComponents[0], timeComponents[1], timeComponents[2], timeComponents[3]);

        timeLabel.setText(formattedTime);
    }
}
