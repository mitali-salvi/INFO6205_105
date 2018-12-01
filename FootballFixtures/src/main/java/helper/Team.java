/**
 * 
 */
package main.java.helper;

/**
 * @author mitalisalvi
 *
 */
public class Team 
{
	private String teamName = "";
	private String homeStadium = "";
	
	
	/**
	 * @param teamName
	 * @param homeStadium
	 */
	public Team(String teamName, String homeStadium) {
		super();
		this.teamName = teamName;
		this.homeStadium = homeStadium;
	}
	
	
	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}
	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	/**
	 * @return the homeStadium
	 */
	public String getHomeStadium() {
		return homeStadium;
	}
	/**
	 * @param homeStadium the homeStadium to set
	 */
	public void setHomeStadium(String homeStadium) {
		this.homeStadium = homeStadium;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Team [teamName=" + teamName + ", homeStadium=" + homeStadium + "]";
	}
	

	
	
	
	
	

}
