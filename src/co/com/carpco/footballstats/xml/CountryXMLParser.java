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
import co.com.carpco.footballstats.util.ImageUtil;

/**
 * @author Carlos Rodriguez
 */
public class CountryXMLParser extends XMLParser<Country> {
  
  private static final String ROOT_ELEMENT = "Countries";
  
  private static final String ENTRY_ELEMENT = "Country";
  
  private static final String ATTRIBUTE_ID =  "id";
  
  private static final String ATTRIBUTE_NAME =  "name";
  
  private static final String ATTRIBUTE_LANGUAGE = "language";
  
  private static final String ATTRIBUTE_FLAG = "flag";

  @Override
  public List<Country> parse(InputStream in) throws XmlPullParserException, IOException {
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
  protected List<Country> readFeed(XmlPullParser parser) throws XmlPullParserException,
      IOException {
    List<Country> entries = new ArrayList<Country>();

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
  protected Country readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
    int idTeam = 0;
    String name = null;
    String language = null;
    String flag = null;
    
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
        case ATTRIBUTE_LANGUAGE:
          language = (String) readValue(parser, ATTRIBUTE_LANGUAGE, STRING_VALUE);
          break;
        case ATTRIBUTE_FLAG:
          flag = (String) readValue(parser, ATTRIBUTE_FLAG, STRING_VALUE);
          break;
        default:
          skip(parser);
          break;
      }
    }
    return new Country(idTeam, name, ImageUtil.StringToBitMap(flag), language);
  }

}
