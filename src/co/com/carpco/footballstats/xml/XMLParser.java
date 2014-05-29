/**
 * 
 */
package co.com.carpco.footballstats.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 * @author Carlos Rodriguez
 */
public abstract class XMLParser <T> {
  
  protected static final String NS = null;
  
  protected static final int STRING_VALUE = 0;
  
  protected static final int INTEGER_VALUE = 1;
  
  protected static final int DOUBLE_VALUE = 2;
  
  protected static final int LONG_VALUE = 3;

  public abstract List<T> parse(InputStream in) throws XmlPullParserException, IOException;

  protected abstract List<T> readFeed(XmlPullParser parser) throws XmlPullParserException,
      IOException;

  /**
   * Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
   * to their respective &quot;read&quot; methods for processing. Otherwise, skips the tag.
   * 
   * @param parser
   * @return
   * @throws XmlPullParserException
   * @throws IOException
   */
  protected abstract T readEntry(XmlPullParser parser) throws XmlPullParserException,
      IOException;

  /**
   * For the tags title and summary, extracts their text values.
   * 
   * @param parser
   * @return
   * @throws IOException
   * @throws XmlPullParserException
   */
  protected String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
    String result = "";
    if (parser.next() == XmlPullParser.TEXT) {
      result = parser.getText();
      parser.nextTag();
    }
    return result;
  }
  
  protected Object readValue(XmlPullParser parser, String attributeName, int valueType) throws IOException, XmlPullParserException {
    parser.require(XmlPullParser.START_TAG, NS, attributeName);
    String value = readText(parser);
    parser.require(XmlPullParser.END_TAG, NS, attributeName);
    
    switch (valueType) {
      case INTEGER_VALUE:
        return Integer.parseInt(value);
      case DOUBLE_VALUE:
        return Double.parseDouble(value);
      case LONG_VALUE:
        return Long.parseLong(value);
      default:
        return value;
    }
  }

  /**
   * Skips tags the parser isn't interested in. Uses depth to handle nested tags. i.e., if the next
   * tag after a START_TAG isn't a matching END_TAG, it keeps going until it finds the matching
   * END_TAG (as indicated by the value of "depth" being 0).
   */
  protected void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
    if (parser.getEventType() != XmlPullParser.START_TAG) {
      throw new IllegalStateException();
    }
    int depth = 1;
    while (depth != 0) {
      switch (parser.next()) {
        case XmlPullParser.END_TAG:
          depth--;
          break;
        case XmlPullParser.START_TAG:
          depth++;
          break;
      }
    }
  }
}
