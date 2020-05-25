package it.polito.ezgas.service.impl;

/**
 * Jason Winn
 * http://jasonwinn.org
 * Created July 10, 2013
 *
 * Description: Small class that provides approximate distance between
 * two points using the Haversine formula.
 *
 * Call in a static context:
 * Haversine.distance(47.6788206, -122.3271205,
 *                    47.6788206, -122.5271205)
 * --> 14.973190481586224 [km]
 *
 */

public class Haversine {
    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM
    /**
     * The coordinates must be <360 and >-360
     * @param startLat the latitude of the starting point
     * @param startLong the longitude of the starting point
     * @param endLat the latitude of the ending point
     * @param endLong the longitude of the ending point
     * @return the distance in km, -1 if coordinates are out of boundaries
     */
    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) {
    	boolean debug = false;
    	if		(	   startLat > 360  || startLat < -360 
    				|| startLong > 360 || startLong < -360
    				|| endLat > 360    || endLat < -360
    				|| endLong >360    || endLong < -360	) {
    		if(debug)
    			System.err.println("Coordinates out of range (use coordinates >-360 and <360");
    		return -1;
    	}
        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- d
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}