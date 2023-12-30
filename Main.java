import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Countdown Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        placeComponents(panel);

        frame.setSize(400, 200);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel countdownLabel = new JLabel("Countdown Timer", SwingConstants.CENTER);
        countdownLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(countdownLabel, gbc);

        JPanel inputPanel = new JPanel();
        JTextField dateTextField = new JTextField("yyyy-MM-dd HH:mm:ss");
        JButton startButton = new JButton("Start Countdown");
        //This is a comment
        AtomicReference<String> inputDateRef = new AtomicReference<>();

        startButton.addActionListener(e -> {
            String inputDate = dateTextField.getText();
            inputDateRef.set(inputDate);
            startCountdown(panel, inputDateRef);
            inputPanel.setVisible(false);
        });

        inputPanel.add(dateTextField);
        inputPanel.add(startButton);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(inputPanel, gbc);

        JLabel timeUntilLabel = new JLabel("", SwingConstants.CENTER);
        timeUntilLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(timeUntilLabel, gbc);
    }

    private static void startCountdown(JPanel panel, AtomicReference<String> inputDateRef) {
        new Thread(() -> {
            try {
                while (true) {
                    String inputDate = inputDateRef.get();
                    long timeDifference = DateCalculator.calculateTimeDifference(inputDate);

                    if (timeDifference <= 0) {
                        System.out.println("The specified time has passed.");
                        break;
                    }

                    long[] timeComponents = DateCalculator.convertMilliseconds(timeDifference);

                    updateGUI(panel, inputDate, timeComponents);

                    Thread.sleep(1000);
                }
            } catch (ParseException | InterruptedException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }).start();
    }

    private static void updateGUI(JPanel panel, String inputDate, long[] timeComponents) {
        JLabel timeLabel = findTimeLabel(panel);

        if (timeLabel != null) {
            String formattedTime = String.format("Time Until %s - %d days, %d hours, %d minutes, %d seconds",
                    inputDate, timeComponents[0], timeComponents[1], timeComponents[2], timeComponents[3]);

            SwingUtilities.invokeLater(() -> {
                timeLabel.setText(formattedTime);
                panel.revalidate();
            });
        }
    }

    private static JLabel findTimeLabel(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                return (JLabel) component;
            }
        }
        return null;
    }
}
