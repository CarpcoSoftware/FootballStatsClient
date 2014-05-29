/**
 * 
 */
package co.com.carpco.footballstats.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;
import co.com.carpco.footballstats.entity.Country;
import co.com.carpco.footballstats.entity.Team;
import co.com.carpco.footballstats.util.ImageUtil;

/**
 * @author Carlos
 *
 */
public class TeamXMLParser extends XMLParser<Team> {
  
  private static final String ROOT_ELEMENT = "Teams";
  
  private static final String ENTRY_ELEMENT = "Team";
  
  private static final String ATTRIBUTE_ID =  "id";
  
  private static final String ATTRIBUTE_NAME =  "name";
  
  private static final String ATTRIBUTE_NICKNAME = "nickname";
  
  private static final String ATTRIBUTE_FLAG = "flag";
  
  private static final String ATTRIBUTE_FOUNDATION =  "foundation";

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
    Long foundation = 0L;
    Country country = new Country("XXX", null, "XXX");
    
    while (parser.next() != XmlPullParser.END_TAG) {
      if (parser.getEventType() != XmlPullParser.START_TAG) {
        continue;
      }
      String attName = parser.getName();
      if (attName.equals(ATTRIBUTE_ID)) {
        idTeam = (int) readValue(parser, ATTRIBUTE_ID, INTEGER_VALUE);
      } else if (attName.equals(ATTRIBUTE_NAME)) {
        name = (String) readValue(parser, ATTRIBUTE_NAME, STRING_VALUE);
      }  else if (attName.equals(ATTRIBUTE_NICKNAME)) {
        nickname = (String) readValue(parser, ATTRIBUTE_NICKNAME, STRING_VALUE);
      } else if (attName.equals(ATTRIBUTE_FLAG)) {
        flag = (String) readValue(parser, ATTRIBUTE_FLAG, STRING_VALUE);
      } else if (attName.equals(ATTRIBUTE_FOUNDATION)) {
        foundation = (Long) readValue(parser, ATTRIBUTE_FOUNDATION, LONG_VALUE);
      } else {
        skip(parser);
      }
    }
    return new Team(idTeam, name, nickname, ImageUtil.StringToBitMap(flag), country, new DateTime(foundation));
  }

}
