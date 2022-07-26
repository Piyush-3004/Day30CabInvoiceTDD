package com.blzAsgnmt;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class InvoiceServiceTest {

	CabInvoiceGenerator cig = null;
		
	@Before
	public void serUp() throws Exception 
	{
		cig = new CabInvoiceGenerator();
	}
	@Test
	public void givenDistanceAndTime_ShouldReturnTotalFare()
	{
		double distance = 2.0;
		int time = 5;
		double fare = cig.calculateFare(distance, time);
		Assert.assertEquals(25, fare, 0.0);
	}
	
	@Test
	public void givenLessDistanceOrTime_ShouldReturnMinimumFare()
	{
		double distance = 0.1;
		int time = 1;
		double fare = cig.calculateFare(distance, time);
		Assert.assertEquals(5, fare, 0.0);
	}

//		@Test
//		public void givenMultipleRides_ShouldReturnTotalFare()
//		{
//			Ride[] rides = { new Ride(2.0, 5),
//							 new Ride(0.1, 1) };
//			double fare = cig.calculateFare(rides);
//			Assert.assertEquals(30, fare, 0.0);
//		}
		
	@Test
	public void givenMultipleRides_ShouldReturnInvoiceSummary()
	{
		Ride[] rides = { new Ride(2.0, 5),
						 new Ride(0.1, 1) };
		InvoiceSummary summary = cig.calculateFare(rides);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
		Assert.assertEquals(expectedInvoiceSummary, summary);
	}
		
	@Test
    public void givenUserId_shouldReturnInvoiceSummary() 
	{
        String userId = "arafathbaig1997@gmail.com";
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1)};
        cig.addRides(userId, rides);
        InvoiceSummary summary = cig.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = cig.getInvoiceSummary(userId);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }
	
	@Test
    public void givenUserIdAndRiderType_shouldReturnInvoiceSummary() 
	{
        String userId = "arafathbaig1997@gmail.com";
        String type = "Normal";
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1)};
        cig.addRides(userId, rides);
        InvoiceSummary summary = cig.calculateFare(rides, type);
        InvoiceSummary expectedInvoiceSummary = cig.getInvoiceSummary(userId);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }
		
	}