package com.blzAsgnmt;

public class CabInvoiceGenerator {
	
	private static final int COST_PER_MINUTE = 1;
	private static final double MINIMUM_COST_PER_KM = 10.0;
	private static final double MIN_FARE = 5;
	private static final int COST_PER_MINUTE_PREMIUM_RIDES = 2;
	private static final double MINIMUM_COST_PER_KM_PREMIUM_RIDES = 15.0;
	private static final double MIN_FARE_PREMIUM_RIDES = 200;
	private RideRepository rideRepository;
	private String type;
	
	public CabInvoiceGenerator()
	{
		this.rideRepository = new RideRepository();
	}
	
	public double calculateFare(double distance, int time)
	{
		double totalFare = (distance * MINIMUM_COST_PER_KM) + (time * COST_PER_MINUTE);
		return Math.max(totalFare, MIN_FARE);
//		if(totalFare <= MIN_FARE)
//		{
//			return MIN_FARE;
//		}
//		return totalFare;
		
	}
	
//	public InvoiceSummary calculateFare(Ride[] rides)
//	{
//		double totalFare =0;
//		for(Ride ride : rides)
//		{
//			totalFare = totalFare + this.calculateFare(ride.distance, ride.time);
//		}
//		return totalFare;
//	}
	
	public InvoiceSummary calculateFare(Ride[] rides)
	{
		double totalFare =0;
		for(Ride ride : rides)
		{
			totalFare = totalFare + this.calculateFare(ride.distance, ride.time);
		}
		return new InvoiceSummary(rides.length, totalFare);
	}
	
	public InvoiceSummary calculateFare(Ride[] rides, String type)
	{
		double totalFare =0;
		this.setType(type);
		for(Ride ride : rides)
		{
			totalFare = totalFare + this.calculateFare(ride.distance, ride.time, type);
		}
		return new InvoiceSummary(rides.length, totalFare);
	}
	
	private double calculateFare(double distance, int time, String type)
	{
		this.setType(type);
		if(type == "Normal")
		{
			double totalFare = (distance * MINIMUM_COST_PER_KM) + (time * COST_PER_MINUTE);
			return Math.max(totalFare, MIN_FARE);
		}
		else
		{
			double totalFare = (distance * MINIMUM_COST_PER_KM_PREMIUM_RIDES) + (time * COST_PER_MINUTE_PREMIUM_RIDES);
			return Math.max(totalFare, MIN_FARE_PREMIUM_RIDES);
		}
	}

	public void addRides(String userId, Ride[] rides) 
	{
        rideRepository.addRide(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) 
    {
        return this.calculateFare( rideRepository.getRides(userId));
    }

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}
}

