package main.java.helper;

import java.util.Date;

public class Match 
{
	private Team teamA;
	private Team teamB;
	private Date matchDate;
	private String matchLocation;
	
	/**
	 * @param teamA
	 * @param teamB
	 * @param matchDate
	 * @param matchLocation
	 */
	public Match(Team teamA, Team teamB, Date matchDate, String matchLocation) {
		super();
		this.teamA = teamA;
		this.teamB = teamB;
		this.matchDate = matchDate;
		this.matchLocation = matchLocation;
	}

	/**
	 * @return the teamA
	 */
	public Team getTeamA() {
		return teamA;
	}

	/**
	 * @param teamA the teamA to set
	 */
	public void setTeamA(Team teamA) {
		this.teamA = teamA;
	}

	/**
	 * @return the teamB
	 */
	public Team getTeamB() {
		return teamB;
	}

	/**
	 * @param teamB the teamB to set
	 */
	public void setTeamB(Team teamB) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Match [teamA=" + teamA.toString() + ", teamB=" + teamB.toString() + ", matchDate=" + matchDate + ", matchLocation="
				+ matchLocation + "]";
	}
	
	
	
	
	
	
	


	
	

}
