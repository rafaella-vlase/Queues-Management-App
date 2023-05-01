package view;
import controller.Controller;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class View extends JFrame
{
    public JTextField numberOfServersTextField = new JTextField();
    public JTextField numberOfClientsTextField = new JTextField();
    public JTextField minArrivalTimeTextField = new JTextField();
    public JTextField maxArrivalTimeTextField = new JTextField();
    public JTextField minProcessingTimeTextField = new JTextField();
    public JTextField maxProcessingTimeTextField = new JTextField();
    public JTextField timeLimitTextField = new JTextField();
    public JPanel eventLogPanel;
    public JLabel eventLogLabel;
    public JTextArea eventLogTextArea;
    public JScrollPane eventLogScrollPane;
    public JPanel outputPanel;

    public View()
    {
        setTitle("Queue Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1300, 700);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2, 5, 5));
        inputPanel.setBackground(new Color(158, 129, 173));

        Border border = BorderFactory.createLineBorder(new Color(158, 129, 173));

        JLabel numberOfClientsLabel = new JLabel("Number of Clients:");
        numberOfClientsLabel.setForeground(new Color(229, 202, 225));
        numberOfClientsLabel.setFont(new Font("Consolas", Font.BOLD, 16));

        numberOfClientsTextField.setBackground(new Color(158, 129, 173));
        numberOfClientsTextField.setForeground(new Color(229, 202, 225));
        numberOfClientsTextField.setFont(new Font("Consolas", Font.BOLD, 16));

        numberOfClientsTextField.setBorder(border);
        inputPanel.add(numberOfClientsLabel);
        inputPanel.add(numberOfClientsTextField);

        JLabel numberOfServersLabel = new JLabel("Number of Queues:");
        numberOfServersLabel.setForeground(new Color(229, 202, 225));
        numberOfServersLabel.setFont(new Font("Consolas", Font.BOLD, 16));

        numberOfServersTextField.setBackground(new Color(158, 129, 173));
        numberOfServersTextField.setForeground(new Color(229, 202, 225));
        numberOfServersTextField.setFont(new Font("Consolas", Font.BOLD, 16));

        numberOfServersTextField.setBorder(border);
        inputPanel.add(numberOfServersLabel);
        inputPanel.add(numberOfServersTextField);

        JLabel timeLimitLabel = new JLabel("Time Limit:");
        timeLimitLabel.setForeground(new Color(229, 202, 225));
        timeLimitLabel.setFont(new Font("Consolas", Font.BOLD, 16));

        timeLimitTextField.setBackground(new Color(158, 129, 173));
        timeLimitTextField.setForeground(new Color(229, 202, 225));
        timeLimitTextField.setFont(new Font("Consolas", Font.BOLD, 16));

        timeLimitTextField.setBorder(border);
        inputPanel.add(timeLimitLabel);
        inputPanel.add(timeLimitTextField);

        JLabel minArrivalTimeLabel = new JLabel("Min Arrival Time:");
        minArrivalTimeLabel.setForeground(new Color(229, 202, 225));
        minArrivalTimeLabel.setFont(new Font("Consolas", Font.BOLD, 16));

        minArrivalTimeTextField.setBackground(new Color(158, 129, 173));
        minArrivalTimeTextField.setForeground(new Color(229, 202, 225));
        minArrivalTimeTextField.setFont(new Font("Consolas", Font.BOLD, 16));

        minArrivalTimeTextField.setBorder(border);
        inputPanel.add(minArrivalTimeLabel);
        inputPanel.add(minArrivalTimeTextField);

        JLabel maxArrivalTimeLabel = new JLabel("Max Arrival Time:");
        maxArrivalTimeLabel.setForeground(new Color(229, 202, 225));
        maxArrivalTimeLabel.setFont(new Font("Consolas", Font.BOLD, 16));

        maxArrivalTimeTextField.setBackground(new Color(158, 129, 173));
        maxArrivalTimeTextField.setForeground(new Color(229, 202, 225));
        maxArrivalTimeTextField.setFont(new Font("Consolas", Font.BOLD, 16));

        maxArrivalTimeTextField.setBorder(border);
        inputPanel.add(maxArrivalTimeLabel);
        inputPanel.add(maxArrivalTimeTextField);

        JLabel minProcessingTimeLabel = new JLabel("Min Service Time:");
        minProcessingTimeLabel.setForeground(new Color(229, 202, 225));
        minProcessingTimeLabel.setFont(new Font("Consolas", Font.BOLD, 16));

        minProcessingTimeTextField.setBackground(new Color(158, 129, 173));
        minProcessingTimeTextField.setForeground(new Color(229, 202, 225));
        minProcessingTimeTextField.setFont(new Font("Consolas", Font.BOLD, 16));

        minProcessingTimeTextField.setBorder(border);
        inputPanel.add(minProcessingTimeLabel);
        inputPanel.add(minProcessingTimeTextField);

        JLabel maxProcessingTimeLabel = new JLabel("Max Service Time:");
        maxProcessingTimeLabel.setForeground(new Color(229, 202, 225));
        maxProcessingTimeLabel.setFont(new Font("Consolas", Font.BOLD, 16));

        maxProcessingTimeTextField.setBackground(new Color(158, 129, 173));
        maxProcessingTimeTextField.setForeground(new Color(229, 202, 225));
        maxProcessingTimeTextField.setFont(new Font("Consolas", Font.BOLD, 16));

        maxProcessingTimeTextField.setBorder(border);
        inputPanel.add(maxProcessingTimeLabel);
        inputPanel.add(maxProcessingTimeTextField);


        contentPanel.add(inputPanel, BorderLayout.WEST);

        outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(1, 1));

        eventLogPanel = new JPanel();
        eventLogPanel.setLayout(new BoxLayout(eventLogPanel, BoxLayout.Y_AXIS));
        eventLogPanel.setBackground(new Color(229, 202, 225));
        eventLogLabel = new JLabel("Log Of Events");
        eventLogLabel.setBackground(new Color(158, 129, 173));
        eventLogLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventLogPanel.add(eventLogLabel);
        eventLogTextArea = new JTextArea();
        eventLogTextArea.setEditable(false);
        eventLogTextArea.setBackground(new Color(229, 202, 225));
        eventLogTextArea.setFont(new Font("Consolas", Font.BOLD, 16));
        eventLogPanel.add(eventLogTextArea);
        eventLogScrollPane = new JScrollPane(eventLogTextArea);
        eventLogPanel.add(eventLogScrollPane);
        eventLogPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        outputPanel.add(eventLogPanel);
        contentPanel.add(outputPanel);

        Controller controller = new Controller(this);

        JButton startButton = new JButton("Start");
        startButton.setActionCommand("Start");
        startButton.addActionListener(controller);
        startButton.setForeground(new Color(229, 202, 225));
        startButton.setBackground(new Color(158, 129, 173));
        startButton.setFont(new Font("Consolas", Font.BOLD, 16));
        startButton.setBorder(border);
        inputPanel.add(startButton);

        setContentPane(contentPanel);
        setVisible(true);
    }
}

