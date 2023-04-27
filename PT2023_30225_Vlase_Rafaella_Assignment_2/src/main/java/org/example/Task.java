package org.example;

public class Task implements Comparable<Task>
{
    private int arrivalTime;
    int serviceTime;
    private int ID;

    public Task(int ID, int serviceTime, int arrivalTime)
    {
        this.ID = 0;
        this.arrivalTime = 0;
        this.serviceTime = 0;
    }

    public int getArrivalTime()
    {
        return arrivalTime;
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
