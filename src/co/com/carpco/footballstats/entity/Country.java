/**
 * 
 */
package co.com.carpco.footballstats.entity;

import java.io.Serializable;

import android.graphics.Bitmap;
import co.com.carpco.footballstats.util.SerialBitmap;

/**
 * Country entity
 * 
 * @author Carlos Rodriguez
 * @version 1.0
 * @since 5/17/2014
 */
public class Country implements Serializable {

  private static final long serialVersionUID = -4511704857831222151L;

  private int idCountry;

  private String name;

  private SerialBitmap flag;

  private String language;
  
  public Country(String name, Bitmap flag, String language) {
    super();
    this.name = name;
    this.flag = new SerialBitmap(flag);
    this.language = language;
  }

  public Country(int idCountry, String name, Bitmap flag, String language) {
    super();
    this.idCountry = idCountry;
    this.name = name;
    this.flag = new SerialBitmap(flag);
    this.language = language;
  }

  /**
   * @return the idCountry
   */
  public int getIdCountry() {
    return idCountry;
  }

  /**
   * @param idCountry the idCountry to set
   */
  public void setIdCountry(int idCountry) {
    this.idCountry = idCountry;
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
   * @return the flag
   */
  public SerialBitmap getFlag() {
    return flag;
  }

  /**
   * @param flag the flag to set
   */
  public void setFlag(Bitmap flag) {
    this.flag = new SerialBitmap(flag);
  }

  /**
   * @return the language
   */
  public String getLanguage() {
    return language;
  }

  /**
   * @param language the language to set
   */
  public void setLanguage(String language) {
    this.language = language;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((flag == null) ? 0 : flag.hashCode());
    result = prime * result + idCountry;
    result = prime * result + ((language == null) ? 0 : language.hashCode());
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
    if (!(obj instanceof Country))
      return false;
    Country other = (Country) obj;
    if (flag == null) {
      if (other.flag != null)
        return false;
    } else if (!flag.equals(other.flag))
      return false;
    if (idCountry != other.idCountry)
      return false;
    if (language == null) {
      if (other.language != null)
        return false;
    } else if (!language.equals(other.language))
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
    return "Country [idCountry=" + idCountry + ", name=" + name + ", flag=" + flag + ", language="
        + language + "]";
  }

}
