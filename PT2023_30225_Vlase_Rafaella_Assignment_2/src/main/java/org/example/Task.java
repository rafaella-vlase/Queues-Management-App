package org.example;

public class Task implements Comparable<Task>
{
    private int arrivalTime;
    int serviceTime;
    private int ID;

    public Task()
    {
        ID = 0;
        arrivalTime = 0;
        serviceTime = 0;
    }

    @Override
    public int compareTo(Task task)
    {
        return arrivalTime - task.serviceTime;
    }

    @Override
    public String toString()
    {
        return "(" + ID + ", " + arrivalTime + ", " + serviceTime + ")";
    }
}
