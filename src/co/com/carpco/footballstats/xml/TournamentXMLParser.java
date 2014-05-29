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
import co.com.carpco.footballstats.entity.Tournament;
import co.com.carpco.footballstats.entity.TournamentType;

/**
 * @author Carlos
 *
 */
public class TournamentXMLParser extends XMLParser<Tournament> {
  
  private static final String ROOT_ELEMENT = "Tournaments";
  
  private static final String ENTRY_ELEMENT = "Tournament";
  
  private static final String ATTRIBUTE_ID =  "id";
  
  private static final String ATTRIBUTE_NAME =  "name";
  
  private static final String ATTRIBUTE_FOUNDATION =  "foundation_year";

  @Override
  public List<Tournament> parse(InputStream in) throws XmlPullParserException, IOException {
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
  protected List<Tournament> readFeed(XmlPullParser parser) throws XmlPullParserException,
      IOException {
    List<Tournament> entries = new ArrayList<Tournament>();

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
  protected Tournament readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
    int idTournament = 0;
    String name = null;
    int foundation = 0;
    Country country = new Country("XXX", null, "XXX");
    TournamentType type = new TournamentType("Cup");
    
    while (parser.next() != XmlPullParser.END_TAG) {
      if (parser.getEventType() != XmlPullParser.START_TAG) {
        continue;
      }
      String attName = parser.getName();
      if (attName.equals(ATTRIBUTE_ID)) {
        idTournament = (int) readValue(parser, ATTRIBUTE_ID, INTEGER_VALUE);
      } else if (attName.equals(ATTRIBUTE_NAME)) {
        name = (String) readValue(parser, ATTRIBUTE_NAME, STRING_VALUE);
      } else if (attName.equals(ATTRIBUTE_FOUNDATION)) {
        foundation = (int) readValue(parser, ATTRIBUTE_FOUNDATION, INTEGER_VALUE);
      } else {
        skip(parser);
      }
    }
    return new Tournament(idTournament, name, foundation, country, type);
  }

}
