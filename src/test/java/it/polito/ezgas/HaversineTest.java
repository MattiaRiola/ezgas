package it.polito.ezgas;






import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.polito.ezgas.service.impl.Haversine;

public class HaversineTest {
	private double lat1 = 45;
	private double lon1 = 50;
	private double lat2 = 45.1;
	private double lon2 = 50.1;
	private boolean debug = true;
	
	@Test
	public void testDistanceTest() {
			// The distance is multiplied by 1000 to see a better result (it shows  meters)
			double dist1_2 = 13.615 *1000 ; 
			double hs_dist1_2 = Math.round( ( Haversine.distance(lat1, lon1, lat2, lon2) * 1000 ));
			if(debug)
				System.out.println("Not calculated distance: " + dist1_2 + "\nCalculated Distance with Haversine: " + hs_dist1_2);
			assertEquals(dist1_2, hs_dist1_2);
			
			lat1 = 0;
			lon1 = 0;
			lat2 = 90;
			lon2 = 180;
			dist1_2 = 10007.543 *1000 ; 
			hs_dist1_2 = Math.round( ( Haversine.distance(lat1, lon1, lat2, lon2) * 1000 ));
			if(debug)
				System.out.println("Not calculated distance: " + dist1_2 + "\nCalculated Distance with Haversine: " + hs_dist1_2);
			assertEquals(dist1_2, hs_dist1_2);
			 
			lat1 = -25;
			lon1 = 30;
			lat2 = -26;
			lon2 = 35;
			dist1_2 = 513.947 *1000 ; 
			hs_dist1_2 = Math.round( ( Haversine.distance(lat1, lon1, lat2, lon2) * 1000 ));
			if(debug)
				System.out.println("Not calculated distance: " + dist1_2 + "\nCalculated Distance with Haversine: " + hs_dist1_2);
			assertEquals(dist1_2, hs_dist1_2);

			lat1 = -25.45;
			lon1 = -30;
			lat2 = -26.03;
			lon2 = -35;
			dist1_2 = 504.910 *1000 ; 
			hs_dist1_2 = Math.round( ( Haversine.distance(lat1, lon1, lat2, lon2) * 1000 ));
			if(debug)
				System.out.println("Not calculated distance: " + dist1_2 + "\nCalculated Distance with Haversine: " + hs_dist1_2);
			assertEquals(dist1_2, hs_dist1_2);
			
			
			
	}
	

	
	
	

}
