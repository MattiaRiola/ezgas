package it.polito.ezgas;






import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.polito.ezgas.service.impl.Haversine;

public class HaversineTest {
	private double lat1 = 45;
	private double lon1 = 50;
	private double lat2 = 45.1;
	private double lon2 = 50.1;
	private boolean debug = false;
	
	@Test
	public void testDistanceTest() {
			// The distance is multiplied by 1000 to see a better result (it shows  meters)
			double dist1_2;
			double hs_dist1_2;
			double err_outOfBoundaries = -1;
			dist1_2 = 13.615 *1000 ; 
			hs_dist1_2 = Math.round( ( Haversine.distance(lat1, lon1, lat2, lon2) * 1000 ));
			if(debug)
				System.out.println("Not calculated distance: " + dist1_2 + "\nCalculated Distance with Haversine: " + hs_dist1_2);
			assertEquals(dist1_2, hs_dist1_2,"Error: Haversine - wrong distance calculation");
			
			lat1 = 0;
			lon1 = 0;
			lat2 = 90;
			lon2 = 180;
			dist1_2 = 10007.543 *1000 ; 
			hs_dist1_2 = Math.round( ( Haversine.distance(lat1, lon1, lat2, lon2) * 1000 ));
			if(debug)
				System.out.println("Not calculated distance: " + dist1_2 + "\nCalculated Distance with Haversine: " + hs_dist1_2);
			assertEquals(dist1_2, hs_dist1_2,"Error: Haversine - wrong distance calculation");
			 
			lat1 = -25;
			lon1 = 30;
			lat2 = -26;
			lon2 = 35;
			dist1_2 = 513.947 *1000 ; 
			hs_dist1_2 = Math.round( ( Haversine.distance(lat1, lon1, lat2, lon2) * 1000 ));
			if(debug)
				System.out.println("Not calculated distance: " + dist1_2 + "\nCalculated Distance with Haversine: " + hs_dist1_2);
			assertEquals(dist1_2, hs_dist1_2,"Error: Haversine - wrong distance calculation");

			lat1 = -25.45;
			lon1 = -30;
			lat2 = -26.03;
			lon2 = -35;
			dist1_2 = 504.910 *1000 ; 
			hs_dist1_2 = Math.round( ( Haversine.distance(lat1, lon1, lat2, lon2) * 1000 ));
			if(debug)
				System.out.println("Not calculated distance: " + dist1_2 + "\nCalculated Distance with Haversine: " + hs_dist1_2);
			assertEquals(dist1_2, hs_dist1_2,"Error: Haversine - wrong distance calculation");
			
			
			lat1 = 380;
			lon1 = -390;
			lat2 = 380.02;
			lon2 = -391;
//			dist1_2 = 104.506*1000 ; 
			hs_dist1_2 = Math.round( ( Haversine.distance(lat1, lon1, lat2, lon2) ));
			if(debug)
				System.out.println("Error for the boundaries: " + err_outOfBoundaries + "\nReturn of the Haversine calculation: " + hs_dist1_2);
			assertEquals(err_outOfBoundaries, hs_dist1_2,"Error: Haversine - Boundaries check issue");
			
			lat1 = -500;
			lon1 = 800;
			lat2 = 600;
			lon2 = -900;
			
			hs_dist1_2 = Math.round( ( Haversine.distance(lat1, lon1, lat2, lon2) ));
			if(debug)
				System.out.println("Error for the boundaries: " + err_outOfBoundaries + "\nReturn of the Haversine calculation: " + hs_dist1_2);
			assertEquals(err_outOfBoundaries, hs_dist1_2,"Error: Haversine - Boundaries check issue");
			
			
			lat1 = -Double.MAX_VALUE;
			lon1 = -Double.MAX_VALUE;
			lat2 = Double.MAX_VALUE;
			lon2 = Double.MAX_VALUE;
			hs_dist1_2 = Math.round( ( Haversine.distance(lat1, lon1, lat2, lon2) ));
			if(debug)
				System.out.println("Error for the boundaries: " + err_outOfBoundaries + "\nReturn of the Haversine calculation: " + hs_dist1_2);
			assertEquals(err_outOfBoundaries, hs_dist1_2,"Error: Haversine - Boundaries check issue");
			
			
	}
	

	
	
	

}
