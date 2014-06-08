/**
 * 
 */
package co.com.carpco.footballstats.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;
import co.com.carpco.footballstats.entity.Country;
import co.com.carpco.footballstats.entity.Team;
import co.com.carpco.footballstats.util.ImageUtil;

/**
 * @author Carlos Rodriguez
 */
public class TeamXMLParser extends XMLParser<Team> {
  
  private static final String ROOT_ELEMENT = "Teams";
  
  private static final String ENTRY_ELEMENT = "Team";
  
  private static final String ATTRIBUTE_ID =  "id";
  
  private static final String ATTRIBUTE_NAME =  "name";
  
  private static final String ATTRIBUTE_NICKNAME = "nickname";
  
  private static final String ATTRIBUTE_FLAG = "flag";
  
  private static final String ATTRIBUTE_FOUNDATION =  "foundation";
  
  private static final String ATTRIBUTE_RANKING = "ranking";
  
  private static final String ATTRIBUTE_COACH = "coach";
  
  private static final String ATTRIBUTE_COUNTRY = "Country";
  
  private final XMLParser<Country> countryParser;
  
  public TeamXMLParser() {
    super();
    countryParser = new CountryXMLParser();
  }

  @Override
  public List<Team> parse(InputStream in) throws XmlPullParserException, IOException {
    try {
      XmlPullParser parser = Xml.newPullParser();
      parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
      parser.setInput(in, null);
      parser.nextTag();
      return readFeed(parser);
    } finally {
      in.close();
    }
  }

  @Override
  protected List<Team> readFeed(XmlPullParser parser) throws XmlPullParserException,
      IOException {
    List<Team> entries = new ArrayList<Team>();

    parser.require(XmlPullParser.START_TAG, NS, ROOT_ELEMENT);
    while (parser.next() != XmlPullParser.END_TAG) {
      
      if (parser.getEventType() != XmlPullParser.START_TAG) {
        continue;
      }
      
      String name = parser.getName();
      if (name.equals(ENTRY_ELEMENT)) {
        entries.add(readEntry(parser));
      } else {
        skip(parser);
      }
      
    }
    return entries;
  }

  @Override
  protected Team readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
    int idTeam = 0;
    String name = null;
    String nickname = null;
    String flag = null;
    int foundation = 0;
    int ranking = 0;
    String coach = null;
    Country country = null;
    
    while (parser.next() != XmlPullParser.END_TAG) {
      if (parser.getEventType() != XmlPullParser.START_TAG) {
        continue;
      }
      
      String attName = parser.getName();
      switch (attName) {
        case ATTRIBUTE_ID:
          idTeam = (int) readValue(parser, ATTRIBUTE_ID, INTEGER_VALUE);
          break;
        case ATTRIBUTE_NAME:
          name = (String) readValue(parser, ATTRIBUTE_NAME, STRING_VALUE);
          break;
        case ATTRIBUTE_NICKNAME:
          nickname = (String) readValue(parser, ATTRIBUTE_NICKNAME, STRING_VALUE);
          break;
        case ATTRIBUTE_FLAG:
          flag = (String) readValue(parser, ATTRIBUTE_FLAG, STRING_VALUE);
          break;
        case ATTRIBUTE_FOUNDATION:
          foundation = (Integer) readValue(parser, ATTRIBUTE_FOUNDATION, INTEGER_VALUE);
          break;
        case ATTRIBUTE_RANKING:
          ranking = (Integer) readValue(parser, ATTRIBUTE_RANKING, INTEGER_VALUE);
          break;
        case ATTRIBUTE_COACH:
          coach = (String) readValue(parser, ATTRIBUTE_COACH, STRING_VALUE);
          break;
        case ATTRIBUTE_COUNTRY:
          country = countryParser.readEntry(parser);
          break;
        default:
          skip(parser);
          break;
      }
    }
    return new Team(idTeam, name, nickname, ImageUtil.StringToBitMap(flag), country, foundation, ranking, coach);
  }

}
