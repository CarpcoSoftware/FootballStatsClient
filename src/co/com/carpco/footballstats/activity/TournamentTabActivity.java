/**
 * 
 */
package co.com.carpco.footballstats.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import co.com.carpco.footballstats.R;
import co.com.carpco.footballstats.entity.Team;
import co.com.carpco.footballstats.xml.TeamXMLParser;
import co.com.carpco.footballstats.xml.XMLParser;

/**
 * @author Carlos Rodriguez
 */
@SuppressWarnings("deprecation")
public class TournamentTabActivity extends TabActivity {

  protected static List<Team> teamList;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tournament_tab);

    Resources ressources = getResources();
    TabHost tabHost = getTabHost();

    Bundle b = getIntent().getExtras();
    b.putSerializable("tournament", b.getSerializable("tournament"));

    // Info tab
    Intent intentInfo = new Intent().setClass(this, TournamentActivity.class);
    intentInfo.putExtras(b);
    TabSpec tabSpecInfo =
        tabHost
            .newTabSpec(getResources().getString(R.string.info))
            .setIndicator(getResources().getString(R.string.info),
                ressources.getDrawable(R.drawable.ic_launcher)).setContent(intentInfo);

    // Groups tab
    Intent intentGroups = new Intent().setClass(this, GroupsActivity.class);
    intentInfo.putExtras(b);
    TabSpec tabSpecGroups =
        tabHost
            .newTabSpec(getResources().getString(R.string.groups))
            .setIndicator(getResources().getString(R.string.groups),
                ressources.getDrawable(R.drawable.ic_launcher)).setContent(intentGroups);

    // Teams tab
    Intent intentTeams = new Intent().setClass(this, TeamListActivity.class);
    intentInfo.putExtras(b);
    TabSpec tabSpecTeams =
        tabHost
            .newTabSpec(getResources().getString(R.string.teams))
            .setIndicator(getResources().getString(R.string.teams),
                ressources.getDrawable(R.drawable.ic_launcher)).setContent(intentTeams);

    // Schedule tab
    Intent intentSchedule = new Intent().setClass(this, ScheduleActivity.class);
    intentInfo.putExtras(b);
    TabSpec tabSpecSchedule =
        tabHost
            .newTabSpec(getResources().getString(R.string.schedule))
            .setIndicator(getResources().getString(R.string.schedule),
                ressources.getDrawable(R.drawable.ic_launcher)).setContent(intentSchedule);

    tabHost.setOnTabChangedListener(new OnTabChangeListener() {
      public void onTabChanged(String arg0) {
        loadPage();
      }
    });

    // add all tabs
    tabHost.addTab(tabSpecInfo);
    tabHost.addTab(tabSpecGroups);
    tabHost.addTab(tabSpecTeams);
    tabHost.addTab(tabSpecSchedule);

    // set Windows tab as default (zero based)
    tabHost.setCurrentTab(0);
  }

  private void loadPage() {
    if (teamList == null || teamList.size() == 0) {
      TournamentTabActivity.this.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          DownloadXmlTask task = new DownloadXmlTask();
          task.execute("http://192.168.1.3:1512/footballstats/team/all");
        }
      });
    }
  }

  // Implementation of AsyncTask used to download XML feed from stackoverflow.com.
  private class DownloadXmlTask extends AsyncTask<String, Void, List<Team>> {

    @Override
    protected List<Team> doInBackground(final String... urls) {
      List<Team> team = null;

      try {
        team = loadXmlFromNetwork(urls[0]);
      } catch (XmlPullParserException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }

      return team;
    }

    @Override
    protected void onPostExecute(List<Team> result) {
      super.onPostExecute(result);
      teamList = result;
    }
  }

  // Uploads XML from stackoverflow.com, parses it, and combines it with
  // HTML markup. Returns HTML string.
  private List<Team> loadXmlFromNetwork(String urlString) throws XmlPullParserException,
      IOException {
    InputStream stream = null;
    XMLParser<Team> xmlParser = new TeamXMLParser();
    List<Team> entries = null;

    try {
      stream = downloadUrl(urlString);
      entries = xmlParser.parse(stream);
    } finally {
      if (stream != null) {
        stream.close();
      }
    }

    return entries;
  }

  // Given a string representation of a URL, sets up a connection and gets
  // an input stream.
  private InputStream downloadUrl(String urlString) throws IOException {
    URL url = new URL(urlString);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setReadTimeout(10000 /* milliseconds */);
    conn.setConnectTimeout(15000 /* milliseconds */);
    conn.setRequestMethod("GET");
    conn.setDoInput(true);
    // Starts the query
    conn.connect();
    InputStream stream = conn.getInputStream();
    return stream;
  }

}
