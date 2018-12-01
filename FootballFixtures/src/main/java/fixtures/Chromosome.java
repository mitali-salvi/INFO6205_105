/**
 * 
 */
package main.java.fixtures;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

import main.java.helper.Match;
import main.java.helper.Team;

/**
 * @author mitalisalvi
 *
 */
public class Chromosome 
{
	private double fitness;
	
	private HashMap<Team, Integer> matchesPlayed;
	
	private HashMap<String, Integer> matchesLocation;
	
	private Match[] matches =new Match[((FootballData.getTeams().size()) * (FootballData.getTeams().size()-1))];
	
	public Chromosome ()
	{
		generateChromosome();
		matchesPlayed = new HashMap<>();
		matchesLocation = new HashMap<>();
	}
	
	private void generateChromosome() 
	{
		
		Random random = new Random();
	    for (int i = 0; i < size(); i++) 
        {
             matches[i] = new Match(FootballData.getTeams().get(random.nextInt(FootballData.getTeams().size())),
                		FootballData.getTeams().get(random.nextInt(FootballData.getTeams().size())),
                		FootballData.getDates().get(random.nextInt(FootballData.getDates().size())), 
                		FootballData.getLocations().get(random.nextInt(FootballData.getLocations().size()))) ;
        }
	 }
	
	 public int size() 
	 {
		    return matches.length;
	 }
	 
	 
	 
	 public Match[] getMatch()
	 {
		 return matches;
	 }
	 
	 public void  SetMatch(Match [] matches)
	 {  System.arraycopy(matches, 0, matches, 0, this.size());
		 
	 }
	 
	 
	 
	 

	public void getFittnessFactor(){
		
		int n = matches.length;
		
		for(int i = 0; i < n; i++){
			Match matchSchedule = matches[i];
           
            //Calculate total matches played by each team
            
            Team team = matchSchedule.getTeamA();
			if(matchesPlayed.containsKey(team)){
				matchesPlayed.put(team, matchesPlayed.get(team) + 1);
			}else{
				matchesPlayed.put(team, 1);
			}
			
			team = matchSchedule.getTeamB();
			if(matchesPlayed.containsKey(team)){
				matchesPlayed.put(team, matchesPlayed.get(team) + 1);
			}else{
				matchesPlayed.put(team, 1);
			}
			
			//calculate total matches played by each location
			
			String location = matchSchedule.getMatchLocation();
			if(matchesLocation.containsKey(location)){
				matchesLocation.put(location, matchesLocation.get(location) + 1);
			}else {
				matchesLocation.put(location, 1);
			}

			//Constraints for schedule
			int conflicts = 0;
			//1. Team will not play with itself
			if(matchSchedule.getTeamA() == matchSchedule.getTeamB()){
				conflicts++;
			}
			
			//2. Team will play with each other team exactly 2 times
			Iterator<Entry<Team, Integer>> matchPlayedEntries = matchesPlayed.entrySet().iterator();
			while(matchPlayedEntries.hasNext()){
				Entry<Team, Integer> entry = matchPlayedEntries.next();
				
				if(entry.getValue() != (2 * FootballData.getTeams().size() - 2)){
					conflicts++;
				}
			}
			
			//3. One match should be played once in home ground and once in home ground of opponent team
			Iterator<Entry<String, Integer>> homeGroundMatchesEntries = matchesLocation.entrySet().iterator();
			while(homeGroundMatchesEntries.hasNext()){
				Entry<String, Integer> entry = homeGroundMatchesEntries.next();
				
				if(entry.getValue() != FootballData.getTeams().size() - 1){
					conflicts++;
				}
			}
		}
	}
}
