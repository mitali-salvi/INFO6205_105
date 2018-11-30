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
	private double fitness =0.0;
	private HashMap<Team, Integer> matchesPlayed;
	private HashMap<String, Integer> matchesLocation;
	private Match[] matches =new Match[((FootballData.getTeams().size()) * (FootballData.getTeams().size()-1))/2 * Constants.NUMBER_OF_ROUNDS];
	
	public Chromosome ()
	{
		generateChromosome();
		matchesPlayed = new HashMap<Team, Integer>();
		matchesLocation = new HashMap<String, Integer>();
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
             System.out.println("Match:::"+matches[i]);
        }
	 }
	
	 public int size() 
	 {
		    return matches.length;
	 }

	public void calculateFitness(){
		
		int n = size();
//		int totalConflicts =0;
		
		//Constraints for particular match
		int conflicts = 0;
		
		for(int i = 0; i < n; i++)
		{
			Match matchSchedule = matches[i];
			System.out.println("Checking for:::"+matchSchedule);
			
           
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

//			//Constraints for schedule
//			int conflicts = 0;
			
			//1. Team will not play with itself
			if(matchSchedule.getTeamA() == matchSchedule.getTeamB())
			{
				System.out.println("Team will not play with itself");
				conflicts++;
			}
			
//			//2. Team will play with each other team exactly 2 times
//			Iterator<Entry<Team, Integer>> matchPlayedEntries = matchesPlayed.entrySet().iterator();
//			while(matchPlayedEntries.hasNext()){
//				Entry<Team, Integer> entry = matchPlayedEntries.next();
//				
//				if(entry.getValue() != (2 * FootballData.getTeams().size() - 2))
//				{
//					System.out.println("Team will play with each other team exactly 2 times");
//					conflicts++;
//				}
//			}
			
//			//3. One match should be played once in home ground and once in opponent ground
//			Iterator<Entry<String, Integer>> homeGroundMatchesEntries = matchesLocation.entrySet().iterator();
//			while(homeGroundMatchesEntries.hasNext()){
//				Entry<String, Integer> entry = homeGroundMatchesEntries.next();
//				
//				if(entry.getValue() != FootballData.getTeams().size() - 1)
//				{
//					System.out.println("One match should be played once in home ground and once in opponent ground");
//					conflicts++;
//				}
//			}
			
			//4. Two matches cannot take place on the same day and same location
			for (int j=i+1; j<n && i<n-1 ; j++)
			{
				if (matchSchedule.getMatchDate().compareTo(matches[j].getMatchDate())==0)
				{
					if (matchSchedule.getMatchLocation().equalsIgnoreCase(matches[j].getMatchLocation()))
					{
						System.out.println("Two matches cannot take place on the same day and same location");
						conflicts++;
					}
				}
			}
			

			System.out.println("Done for the current match--------");
		}
		
		//2. Team will play with each other team exactly 2 times
		Iterator<Entry<Team, Integer>> matchPlayedEntries = matchesPlayed.entrySet().iterator();
		while(matchPlayedEntries.hasNext()){
			Entry<Team, Integer> entry = matchPlayedEntries.next();
			
			if(entry.getValue() != (2 * FootballData.getTeams().size() - 2))
			{
				System.out.println("Team will play with each other team exactly 2 times");
				conflicts++;
			}
		}
		
		//3. One match should be played once in home ground and once in opponent ground
		Iterator<Entry<String, Integer>> homeGroundMatchesEntries = matchesLocation.entrySet().iterator();
		while(homeGroundMatchesEntries.hasNext()){
			Entry<String, Integer> entry = homeGroundMatchesEntries.next();
			
			if(entry.getValue() != FootballData.getTeams().size() - 1)
			{
				System.out.println("One match should be played once in home ground and once in opponent ground");
				conflicts++;
			}
		}
		
		System.out.println("Total Conflicts::"+conflicts);
		setFitness(1/(double)(1+ conflicts));
	}

	/**
	 * @return the fitness
	 */
	public double getFitness() {
		return fitness;
	}

	/**
	 * @param fitness the fitness to set
	 */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
}
