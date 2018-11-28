/**
 * 
 */
package main.java.fixtures;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import main.java.helper.VenueDistance;

/**
 * @author aditi
 *
 */
public class Configuration {
	
	
	public static void initializeData()
	{
		addTeamNames();
		addLocations();
		addDates();
		addDistanceBetweenVenues();
	}
	
	private static void addDistanceBetweenVenues() 
	{
		ArrayList<String> teams = (ArrayList<String>) FootballData.getTeams();
	    for (int i = 0; i < teams.size(); i++) 
        {
            for (int j = i + 1; j < teams.size(); j++)
            {
            	FootballData.distanceBetweenVenues.add(new VenueDistance(teams.get(i), teams.get(j), 
            			ThreadLocalRandom.current().nextInt(100, 250 + 1)));
            }
        }
	}

	private static void addDates() {
		
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH).parse("2018-08-10 17:00");

	        int numberOfMatches = FootballData.teams.size() * FootballData.teams.size() - 1;
	        
	        for(int i = 0; i < numberOfMatches; i++){
	        	FootballData.dates.add(date);
	        	// Convert Date to a Calendar
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        
		        // mutate the value
		        cal.add(Calendar.DATE, 2);

		        // convert back to Date
		        date = cal.getTime();	
	        }
	        
		} catch (ParseException e) {
			e.printStackTrace();
		}

        
	}

	public static void addTeamNames(){
		FootballData.teams.add("Manchester City");
		FootballData.teams.add("Liverpool");
		FootballData.teams.add("Tottenham Hotspur");
		FootballData.teams.add("Chelsea");
		FootballData.teams.add("Arsenal");
		FootballData.teams.add("Everton");
		FootballData.teams.add("Manchester United");
		FootballData.teams.add("Watford");
	}
	
	public static void addLocations(){
		FootballData.locations.add("Etihad stadium");
		FootballData.locations.add("Anfield");
		FootballData.locations.add("Wembley stadium");
		FootballData.locations.add("Stamford stadium");
		FootballData.locations.add("Emirates stadium");
		FootballData.locations.add("Goodison park");
		FootballData.locations.add("Old Trafford");
		FootballData.locations.add("Vicarage Road");
	}
	
	
}
