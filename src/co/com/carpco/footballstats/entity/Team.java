/**
 * 
 */
package co.com.carpco.footballstats.entity;

import java.io.Serializable;

import org.joda.time.DateTime;

import android.graphics.Bitmap;

/**
 * Team entity
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 5/17/2014
 */
public class Team implements Serializable {

  private static final long serialVersionUID = 8289150558810874165L;

  private int idTeam;
  
  private String name;
  
  private String nickname;
  
  private Bitmap flag;
  
  private Country country;
  
  private DateTime foundation;
  
  public Team(String name, String nickname, Bitmap flag, Country country, DateTime foundation) {
    super();
    this.name = name;
    this.nickname = nickname;
    this.flag = flag;
    this.country = country;
    this.foundation = foundation;
  }
  
  public Team(int idTeam, String name, String nickname, Bitmap flag, Country country, DateTime foundation) {
    super();
    this.idTeam = idTeam;
    this.name = name;
    this.nickname = nickname;
    this.flag = flag;
    this.country = country;
    this.foundation = foundation;
  }

  /**
   * @return the idTeam
   */
  public int getIdTeam() {
    return idTeam;
  }

  /**
   * @param idTeam the idTeam to set
   */
  public void setIdTeam(int idTeam) {
    this.idTeam = idTeam;
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
   * @return the nickname
   */
  public String getNickname() {
    return nickname;
  }

  /**
   * @param nickname the nickname to set
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  /**
   * @return the flag
   */
  public Bitmap getFlag() {
    return flag;
  }

  /**
   * @param flag the flag to set
   */
  public void setFlag(Bitmap flag) {
    this.flag = flag;
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
   * @return the foundation
   */
  public DateTime getFoundation() {
    return foundation;
  }

  /**
   * @param foundation the foundation to set
   */
  public void setFoundation(DateTime foundation) {
    this.foundation = foundation;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((country == null) ? 0 : country.hashCode());
    result = prime * result + ((flag == null) ? 0 : flag.hashCode());
    result = prime * result + idTeam;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
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
    if (!(obj instanceof Team))
      return false;
    Team other = (Team) obj;
    if (country == null) {
      if (other.country != null)
        return false;
    } else if (!country.equals(other.country))
      return false;
    if (flag == null) {
      if (other.flag != null)
        return false;
    } else if (!flag.equals(other.flag))
      return false;
    if (idTeam != other.idTeam)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (nickname == null) {
      if (other.nickname != null)
        return false;
    } else if (!nickname.equals(other.nickname))
      return false;
    return true;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Team [idTeam=" + idTeam + ", name=" + name + ", nickname=" + nickname + ", flag="
        + flag + ", country=" + country + "]";
  }
}
