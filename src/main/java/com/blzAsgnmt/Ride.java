package com.blzAsgnmt;

public class Ride {
	
	public double distance;
	public int time;

	public Ride(double distance, int time) 
	{
		this.distance = distance;
		this.time = time;
	}

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance) 
    {
        this.distance = distance;
    }

    public double getTime() 
    {
        return time;
    }

    public void setTime(int time) 
    {
        this.time = time;
    }
}
