/**
 * 
 */
package main.java.fixtures;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.helper.VenueDistance;

/**
 * @author mitalisalvi
 *
 */
public class FootballData 
{

	public static List<String> teams = new ArrayList<String>();
	public static List<String> locations = new ArrayList<String>();
	public static List<Date> dates = new ArrayList<Date>();
	public static List<VenueDistance> distanceBetweenVenues = new ArrayList<VenueDistance>();
	
	/**
	 * @return the teams
	 */
	public static List<String> getTeams() {
		return teams;
	}
	/**
	 * @return the locations
	 */
	public static List<String> getLocations() {
		return locations;
	}
	/**
	 * @return the dates
	 */
	public static List<Date> getDates() {
		return dates;
	}
	/**
	 * @return the distanceBetweenVenues
	 */
	public static List<VenueDistance> getDistanceBetweenVenues() {
		return distanceBetweenVenues;
	}
	
	
	



}
