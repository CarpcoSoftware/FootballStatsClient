/**
 * 
 */
package co.com.carpco.footballstats.entity;

import java.io.Serializable;

/**
 * Tournament type entity
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 5/17/2014
 */
public class TournamentType implements Serializable {
  
  private static final long serialVersionUID = 1406756557107146512L;

  private int idTournamentType;
  
  private String name;
  
  public TournamentType(String name) {
    super();
    this.name = name;
  }
  
  public TournamentType(int idTournamentType, String name) {
    super();
    this.idTournamentType = idTournamentType;
    this.name = name;
  }

  /**
   * @return the idTournamentType
   */
  public int getIdTournamentType() {
    return idTournamentType;
  }

  /**
   * @param idTournamentType the idTournamentType to set
   */
  public void setIdTournamentType(int idTournamentType) {
    this.idTournamentType = idTournamentType;
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

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + idTournamentType;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
    if (!(obj instanceof TournamentType))
      return false;
    TournamentType other = (TournamentType) obj;
    if (idTournamentType != other.idTournamentType)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "TournamentType [idTournamentType=" + idTournamentType + ", name=" + name + "]";
  }

}
