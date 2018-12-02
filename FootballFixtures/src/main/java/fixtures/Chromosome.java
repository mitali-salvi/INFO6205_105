/**
 * 
 */
package main.java.fixtures;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import main.java.helper.Match;
import main.java.helper.Team;

/**
 * @author mitalisalvi
 *
 */
public class Chromosome implements Comparable<Chromosome>
{
	private double fitness =0.0;
	private Match[] matches;
	
	public Chromosome ()
	{
		matches = new Match[((FootballData.getTeams().size()) * (FootballData.getTeams().size()-1))/2 * Constants.NUMBER_OF_ROUNDS];
		generateChromosome();
		calculateFitness();
	}
	
	public Chromosome (Match[] matches)
	{
		setMatches(matches);
		calculateFitness();
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
             //System.out.println("-"+matches[i]);
             //System.out.println();
        }
	 }
	
	 public int size() 
	 {
		    return matches.length;
	 }

	public void calculateFitness()
	{
		int conflicts = 0;
		HashMap<Team, Integer> matchesPlayed = new HashMap<Team, Integer>();
		
		//each team will play NumberOfRounds against each team
	    HashMap<String, ArrayList<Team>> hashMapOfTeamAndOpponentCount = new HashMap<String, ArrayList<Team>>();
	    for (Team t :FootballData.getTeams())
	    {
	    	hashMapOfTeamAndOpponentCount.put(t.getTeamName(), new ArrayList<Team>());
	    }
//	    Java 8
//	    Map<String, List<Student>> studlistGrouped = studlist.stream().collect(Collectors.groupingBy(w -> w.stud_location));
	    
	    
	    //location hashMap - each location (home ground) will have list of all team which have played there
	    HashMap <String , HashMap<Team, Integer>> locationCount = new HashMap<String , HashMap<Team, Integer>>();
	    for (String loc:FootballData.getLocations())
	    {
	    	locationCount.put(loc, new HashMap<Team,Integer>());
	    }
	    
	    
	    //one team cant play 2 matches on the same day
	    HashMap<Team, ArrayList<Date>> sameDateChecker = new HashMap<Team, ArrayList<Date>>();
	    for (Team t:FootballData.getTeams())
	    	sameDateChecker.put(t, new ArrayList<Date>());
	    
	    //iterating through each gene
		for (int i=0; i<size(); i++)
		{
			Match matchSchedule = matches[i];
			
			//Calculate total matches played by each team
            Team teamA = matchSchedule.getTeamA();
			if(matchesPlayed.containsKey(teamA))
			{
				matchesPlayed.put(teamA, matchesPlayed.get(teamA) + 1);
			}
			else{
				matchesPlayed.put(teamA, 1);
			}
			
			Team teamB = matchSchedule.getTeamB();
			if(matchesPlayed.containsKey(teamB))
			{
				matchesPlayed.put(teamB, matchesPlayed.get(teamB) + 1);
			}
			else{
				matchesPlayed.put(teamB, 1);
			}
						
			//storing opponents of each team
			for (Entry<String, ArrayList<Team>> entry : hashMapOfTeamAndOpponentCount.entrySet())
		    {
		        String team = entry.getKey();
		        if (team.equals(teamA.getTeamName()))
		        {
		        	ArrayList<Team> al = hashMapOfTeamAndOpponentCount.get(team);
		        	al.add(teamB);
		        }
		        
		        if (team.equals(teamB.getTeamName()) && !(teamA.equals(teamB))   )
		        {
		        	ArrayList<Team> al = hashMapOfTeamAndOpponentCount.get(team);
		        	al.add(teamA);
		        }
		    }
			
			
			//1. Team will not play with itself
			if(teamA == teamB)
			{
				//System.out.println("Team will not play with itself");
				conflicts++;
			}
			
			//4. Two matches cannot take place on the same day and same location
			for (int j=i+1; j< size() && i< size()-1 ; j++)
			{
				if (matchSchedule.getMatchDate().compareTo(matches[j].getMatchDate())==0)
				{
					if (matchSchedule.getMatchLocation().equalsIgnoreCase(matches[j].getMatchLocation()))
					{
						//System.out.println("Two matches cannot take place on the same day and same location");
						conflicts++;
					}
				}
			}
			
			//each team will play one match at home and one match at opponent ground
			//equivalent to each location will have 2 home ground matches and each other team will play one match each at that ground
			//here we assume 2 rounds
			String currentLocation = matchSchedule.getMatchLocation();
			
			HashMap<Team,Integer> locHashMap = locationCount.get(currentLocation);
			//add to locHashMap if location is either teams home ground. If not then increase conflicts
			if (currentLocation.equals(teamA.getHomeStadium()) || currentLocation.equals(teamB.getHomeStadium()))
			{
				if (locHashMap.containsKey(teamA))
					locHashMap.put(teamA, locHashMap.get(teamA) + 1);
				else
					locHashMap.put(teamA, 1);
				
				if (locHashMap.containsKey(teamB) && !(teamA.equals(teamB))  )
					locHashMap.put(teamB, locHashMap.get(teamB) + 1);
				else
					locHashMap.put(teamB, 1);
			}
			else
			{
				//System.out.println("location doesnt match either team home stadiums so increase conflicts");
				conflicts++;
			}
			
			ArrayList<Date> dateForTeamA = sameDateChecker.get(teamA);
			dateForTeamA.add(matchSchedule.getMatchDate());
			sameDateChecker.put(teamA, dateForTeamA);
			
			if (teamA != teamB)
			{
				ArrayList<Date> dateForTeamB = sameDateChecker.get(teamB);
				dateForTeamB.add(matchSchedule.getMatchDate());
				sameDateChecker.put(teamB, dateForTeamB);
			}
			
			
		}
		
		for (Entry<Team, ArrayList<Date>> entry : sameDateChecker.entrySet())
		{
			//System.out.println("Team::"+entry.getKey().getTeamName());
//			for (Date d:entry.getValue())
//			{
//				System.out.println(d);
//				
//			}
			//System.out.println();
			boolean flag = containsDuplicates(entry.getValue());
			if (flag)
			{
				//System.out.println("more than 1 motch occurs on the same day for team::"+entry.getKey().getTeamName());
				conflicts++;
			}

		}
		
		for (Entry<String, HashMap<Team, Integer>> entry : locationCount.entrySet())
		{
			String currentLoc = entry.getKey();
			//System.out.println("currentLoc:"+currentLoc);
			HashMap<Team,Integer> locHashMap = locationCount.get(currentLoc);
			//System.out.println(locHashMap);
			//System.out.println();
			if (locHashMap.size() != FootballData.getTeams().size())
			{
				//System.out.println("Each team has not played at this location");
				conflicts++;
			}
			for (Entry<Team ,Integer> temp : locHashMap.entrySet())
			{
				if (temp.getKey().getHomeStadium().equals(currentLoc))
				{
					//System.out.println("home ground for::"+temp.getKey().getTeamName());
					if (temp.getValue() != Constants.NUMBER_OF_ROUNDS)
					{
						//System.out.println("Home team has not played [numberOfRounds] games here");
						conflicts++;
					}	
				}
				else
				{
					//System.out.println("Opponent ground");
					if (temp.getValue() != Constants.NUMBER_OF_ROUNDS/2)
					{
						//System.out.println(temp.getKey().getTeamName()+" has played only "+temp.getValue() +" matches @ "+ currentLoc);
						conflicts++;
					}
				}
			}
		}
		
	    //Each team will play - (TotalMatches - NumberOfRounds) matches
	    for (Entry<Team, Integer> entry : matchesPlayed.entrySet())
	    {
	        int numberOfMatches = entry.getValue();
	        if (numberOfMatches != size() - Constants.NUMBER_OF_ROUNDS )
	        {
	        	//System.out.println(entry.getKey().getTeamName()+" is NOT playing - [TotalMatches - NumberOfRounds]");
	        	conflicts++;
	        }
	    }
	    
	    
	    //System.out.println();
	    //each team will play 2 (number of rounds) against every other team
	    for (Entry<String, ArrayList<Team>> entry : hashMapOfTeamAndOpponentCount.entrySet())
	    {
	    	ArrayList<Team> temp = entry.getValue();
	    	String team = entry.getKey();
	    	HashMap <String, Integer> countOfMatchesPlayed = new HashMap<>();
	    	for (Team t :temp)
	    	{
		    	if(countOfMatchesPlayed.containsKey(t.getTeamName()))
				{
		    		countOfMatchesPlayed.put(t.getTeamName(), countOfMatchesPlayed.get(t.getTeamName()) + 1);
				}
				else{
					countOfMatchesPlayed.put(t.getTeamName(), 1);
				}
	    	}
	    	//System.out.println("Executing for:::"+team);
	    	//System.out.println("countOfMatchesPlayed::"+countOfMatchesPlayed);
	    	for (Entry<String, Integer> single : countOfMatchesPlayed.entrySet())
	    	{
	    		String opponentTeam = single.getKey();
	    		int numberOfMatchesBetween = single.getValue();
	    		if (team.equals(opponentTeam))
	    		{
	    			//System.out.println("Already taken care of in loop so ignore");
	    		}
	    		else
	    		{
	    			if (numberOfMatchesBetween != Constants.NUMBER_OF_ROUNDS)
	    			{
	    				//System.out.println("Two matches are not played between:"+team+" and "+opponentTeam);
	    				conflicts++;
	    			}
	    		}
	    	}
	    	
	    	//checking if this team has played matches against EACH other team in the league
	    	HashMap <String, Integer> copyOfCountOfMatchesPlayed = new HashMap<>(countOfMatchesPlayed);
	    	int counterOfOpponentTeam = 0;
	    	for (Entry<String, Integer> single : copyOfCountOfMatchesPlayed.entrySet())
	    	{
	    		String opponentTeam = single.getKey();
	    		if (!team.equals(opponentTeam))
	    		{
	    			counterOfOpponentTeam ++;
	    		}
	    	}
	    	if (counterOfOpponentTeam != FootballData.getTeams().size() -1)
	    	{
	    		//System.out.println("After removing own entry count is less");
	    		conflicts++;
	    	}
	    }
	    
	    //System.out.println("Total fitness for this chromosome::"+1/(double)(1+conflicts));
	    setFitness(1/(double)(1+conflicts));

		
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
	
	
    public ArrayList<Team> removeDuplicates(ArrayList<Team> list) 
    { 
        Set<Team> hs = new HashSet<Team>();
        hs.addAll(list);
        list.clear();
        list.addAll( hs);
        return list;
    }
    
    public boolean containsDuplicates (ArrayList<Date> list)
    {
    	Set<Date> set = new HashSet<Date>(list);
    	boolean flag = false;
    	if(set.size() < list.size())
    	{
    	    /* There are duplicates */
    		flag = true;
    	}
    	return flag;
    }

	/**
	 * @return the matches
	 */
	public Match[] getMatches() {
		return matches;
	}

	/**
	 * @param matches the matches to set
	 */
	public void setMatches(Match[] matches) {
		this.matches = matches;
	}

	@Override
	public int compareTo(Chromosome o) {
		return Double.compare(o.getFitness(), this.getFitness());
	}

}
