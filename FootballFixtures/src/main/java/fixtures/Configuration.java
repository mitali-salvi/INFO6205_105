/**
 * 
 */
package main.java.fixtures;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import main.java.helper.VenueDistance;

/**
 * @author aditi
 *
 */
public class Configuration {
	
	private List<String> teams = new ArrayList<String>();
	private List<String> locations = new ArrayList<String>();
	private List<Date> dates = new ArrayList<Date>();
	private List<VenueDistance> distanceBetweenVenues = new ArrayList<VenueDistance>();
	
	public void initializeData(){
		addTeamNames();
		addLocations();
		addDates();
		addDistanceBetweenVenues();
	}
	
	private void addDistanceBetweenVenues() {
		
		
	}

	private void addDates() {
		
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH).parse("2018-08-10 17:00");

	        int numberOfMatches = teams.size() * teams.size() - 1;
	        
	        for(int i = 0; i < numberOfMatches; i++){
	        	dates.add(date);
	        	// Convert Date to a Calendar
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        
		        // mutate the value
		        cal.add(Calendar.DATE, 2);

		        // convert back to Date
		        date = cal.getTime();	
	        }
	        
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
	}

	public void addTeamNames(){
		teams.add("Manchester City");
		teams.add("Liverpool");
		teams.add("Tottenham Hotspur");
		teams.add("Chelsea");
		teams.add("Arsenal");
		teams.add("Everton");
		teams.add("Manchester United");
		teams.add("Watford");
	}
	
	public void addLocations(){
		locations.add("Etihad stadium");
		locations.add("Anfield");
		locations.add("Wembley stadium");
		locations.add("Stamford stadium");
		locations.add("Emirates stadium");
		locations.add("Goodison park");
		locations.add("Old Trafford");
		locations.add("Vicarage Road");
	}
}
