/**
 * 
 */
package co.com.carpco.footballstats.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Tournament entity
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 5/17/2014
 */
public class Tournament implements Serializable {
  
  private static final long serialVersionUID = -2980913382084736480L;

  private int idTournament;
  
  private String name;
  
  private int foundationYear;
  
  private Country country;
  
  private TournamentType tournamentType;
  
  private Set<Team> teamSet;
  
  public Tournament(String name, int foundationYear, Country country, TournamentType tournamentType) {
    super();
    this.name = name;
    this.foundationYear = foundationYear;
    this.country = country;
    this.tournamentType = tournamentType;
  }
  
  public Tournament(int idTournament, String name, int foundationYear, Country country, TournamentType tournamentType) {
    super();
    this.idTournament = idTournament;
    this.name = name;
    this.foundationYear = foundationYear;
    this.country = country;
    this.tournamentType = tournamentType;
  }

  /**
   * @return the idTournament
   */
  public int getIdTournament() {
    return idTournament;
  }

  /**
   * @param idTournament the idTournament to set
   */
  public void setIdTournament(int idTournament) {
    this.idTournament = idTournament;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the foundationYear
   */
  public int getFoundationYear() {
    return foundationYear;
  }

  /**
   * @param foundationYear the foundationYear to set
   */
  public void setFoundationYear(int foundationYear) {
    this.foundationYear = foundationYear;
  }

  /**
   * @return the country
   */
  public Country getCountry() {
    return country;
  }

  /**
   * @param country the country to set
   */
  public void setCountry(Country country) {
    this.country = country;
  }

  /**
   * @return the tournamentType
   */
  public TournamentType getTournamentType() {
    return tournamentType;
  }

  /**
   * @param tournamentType the tournamentType to set
   */
  public void setTournamentType(TournamentType tournamentType) {
    this.tournamentType = tournamentType;
  }

  /**
   * @return the teamSet
   */
  public Set<Team> getTeamSet() {
    return teamSet;
  }

  /**
   * @param teamSet the teamSet to set
   */
  public void setTeamSet(Set<Team> teamSet) {
    this.teamSet = teamSet;
  }
  
  /**
   * @param team the team to be added
   */
  public void addTeamToSet(Team team) {
    if (teamSet == null) {
      teamSet = new HashSet<>();
    }
    this.teamSet.add(team);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((country == null) ? 0 : country.hashCode());
    result = prime * result + foundationYear;
    result = prime * result + idTournament;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((teamSet == null) ? 0 : teamSet.hashCode());
    result = prime * result + ((tournamentType == null) ? 0 : tournamentType.hashCode());
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof Tournament))
      return false;
    Tournament other = (Tournament) obj;
    if (country == null) {
      if (other.country != null)
        return false;
    } else if (!country.equals(other.country))
      return false;
    if (foundationYear != other.foundationYear)
      return false;
    if (idTournament != other.idTournament)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (teamSet == null) {
      if (other.teamSet != null)
        return false;
    } else if (!teamSet.equals(other.teamSet))
      return false;
    if (tournamentType == null) {
      if (other.tournamentType != null)
        return false;
    } else if (!tournamentType.equals(other.tournamentType))
      return false;
    return true;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Tournament [idTournament=" + idTournament + ", name=" + name + ", foundationYear="
        + foundationYear + ", country=" + country + ", tournamentType=" + tournamentType
        + ", teamSet=" + teamSet + "]";
  }

}
