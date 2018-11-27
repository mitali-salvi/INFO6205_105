/**
 * 
 */
package main.java.helper;

/**
 * @author mitalisalvi
 *
 */
public class VenueDistance 
{
	private String startLocation;
	private String endLocation;
	private double distanceBetweenVenues;
	
	/**
	 * @param startLocation
	 * @param endLocation
	 * @param distanceBetweenVenues
	 */
	public VenueDistance(String startLocation, String endLocation, double distanceBetweenVenues) {
		super();
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.distanceBetweenVenues = distanceBetweenVenues;
	}

	/**
	 * @return the startLocation
	 */
	public String getStartLocation() {
		return startLocation;
	}

	/**
	 * @param startLocation the startLocation to set
	 */
	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	/**
	 * @return the endLocation
	 */
	public String getEndLocation() {
		return endLocation;
	}

	/**
	 * @param endLocation the endLocation to set
	 */
	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}

	/**
	 * @return the distanceBetweenVenues
	 */
	public double getDistanceBetweenVenues() {
		return distanceBetweenVenues;
	}

	/**
	 * @param distanceBetweenVenues the distanceBetweenVenues to set
	 */
	public void setDistanceBetweenVenues(double distanceBetweenVenues) {
		this.distanceBetweenVenues = distanceBetweenVenues;
	}
	
	
	
	

}
