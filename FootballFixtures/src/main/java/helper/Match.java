package main.java.helper;

import java.util.Date;

public class Match 
{
	private String teamA;
	private String teamB;
	private Date matchDate;
	private String matchLocation;
	
	/**
	 * @param teamA
	 * @param teamB
	 * @param matchDate
	 * @param matchLocation
	 */
	public Match(String teamA, String teamB, Date matchDate, String matchLocation) {
		super();
		this.teamA = teamA;
		this.teamB = teamB;
		this.matchDate = matchDate;
		this.matchLocation = matchLocation;
	}

	/**
	 * @return the teamA
	 */
	public String getTeamA() {
		return teamA;
	}

	/**
	 * @param teamA the teamA to set
	 */
	public void setTeamA(String teamA) {
		this.teamA = teamA;
	}

	/**
	 * @return the teamB
	 */
	public String getTeamB() {
		return teamB;
	}

	/**
	 * @param teamB the teamB to set
	 */
	public void setTeamB(String teamB) {
		this.teamB = teamB;
	}

	/**
	 * @return the matchDate
	 */
	public Date getMatchDate() {
		return matchDate;
	}

	/**
	 * @param matchDate the matchDate to set
	 */
	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	/**
	 * @return the matchLocation
	 */
	public String getMatchLocation() {
		return matchLocation;
	}

	/**
	 * @param matchLocation the matchLocation to set
	 */
	public void setMatchLocation(String matchLocation) {
		this.matchLocation = matchLocation;
	}

	
	

}
