package org.example;

public class Task implements Comparable<Task>
{
    int ID;
    int arrivalTime;
    int processingTime;

    Task()
    {
        ID = 0;
        arrivalTime = 0;
        processingTime = 0;
    }

    @Override
    public int compareTo(Task task)
    {
        return arrivalTime - task.arrivalTime;
    }

    @Override
    public String toString()
    {
        return "(" + ID + ", " + arrivalTime + ", " + processingTime + ")";
    }
}
