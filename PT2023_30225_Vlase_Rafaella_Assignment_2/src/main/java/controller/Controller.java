package controller;
import org.example.SimulationManager;
import view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Controller implements ActionListener
{
    View view;
    public Controller(View view)
    {
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String button = e.getActionCommand();

        if(Objects.equals(button, "Start"))
        {
            int noServers = 0;
            if (view.numberOfServersTextField != null)
            {
                noServers = Integer.parseInt(view.numberOfServersTextField.getText());
            }
            int noTasksInt = 0;
            int minArrivalTime = 0;
            int maxArrivalTime = 0;
            int minProcessingTime = 0;
            int maxProcessingTime = 0;
            int maxTime = 0;
            if(view.numberOfClientsTextField != null)
            {
                noTasksInt = Integer.parseInt(view.numberOfClientsTextField.getText());
            }
            if(view.minArrivalTimeTextField != null)
            {
                minArrivalTime = Integer.parseInt(view.minArrivalTimeTextField.getText());
            }
            if(view.maxArrivalTimeTextField != null)
            {
                maxArrivalTime = Integer.parseInt(view.maxArrivalTimeTextField.getText());
            }
            if(view.minProcessingTimeTextField != null)
            {
                minProcessingTime = Integer.parseInt(view.minProcessingTimeTextField.getText());
            }
            if(view.maxProcessingTimeTextField != null)
            {
                maxProcessingTime = Integer.parseInt(view.maxProcessingTimeTextField.getText());
            }
            if(view.timeLimitTextField != null)
            {
                maxTime = Integer.parseInt(view.timeLimitTextField.getText());
            }

            SimulationManager simulationManager = new SimulationManager(noServers, noTasksInt, minArrivalTime, maxArrivalTime, minProcessingTime, maxProcessingTime, maxTime, view);
            view.eventLogTextArea.setText(simulationManager.getCurrentLog());
            Thread thread = new Thread(simulationManager);
            thread.start();
        }
    }
}
