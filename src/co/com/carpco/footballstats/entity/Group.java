/**
 * 
 */
package co.com.carpco.footballstats.entity;

/**
 * @author Carlos Rodriguez
 */
public class Group {
  
  private int idGroup;
  
  private String name;
  
  public Group(String name) {
    super();
    this.name = name;
  }
  
  public Group(int idGroup, String name) {
    super();
    this.idGroup = idGroup;
    this.name = name;
  }

  /**
   * @return the idGroup
   */
  public int getIdGroup() {
    return idGroup;
  }

  /**
   * @param idGroup the idGroup to set
   */
  public void setIdGroup(int idGroup) {
    this.idGroup = idGroup;
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
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Group [idGroup=" + idGroup + ", name=" + name + "]";
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + idGroup;
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
    if (!(obj instanceof Group))
      return false;
    Group other = (Group) obj;
    if (idGroup != other.idGroup)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

}
