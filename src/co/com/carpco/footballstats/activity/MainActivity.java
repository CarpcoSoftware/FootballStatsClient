package co.com.carpco.footballstats.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import co.com.carpco.footballstats.R;
import co.com.carpco.footballstats.entity.Tournament;
import co.com.carpco.footballstats.xml.TournamentXMLParser;
import co.com.carpco.footballstats.xml.XMLParser;

public class MainActivity extends ActionBarActivity implements OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button btn = (Button) findViewById(R.id.button1);
    btn.setOnClickListener(this);
  }

  public void onClick(View view) {
    // detect the view that was "clicked"
    switch (view.getId()) {
      case R.id.button1:
        this.loadPage();
        break;
    }
  }

  private void loadPage() {
    MainActivity.this.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        DownloadXmlTask task = new DownloadXmlTask();
        task.execute("http://192.168.1.3:1512/footballstats/tournament/all");
      }
    });

  }

  // Implementation of AsyncTask used to download XML feed from stackoverflow.com.
  private class DownloadXmlTask extends AsyncTask<String, Void, Tournament> {

    @Override
    protected Tournament doInBackground(final String... urls) {
      Tournament tournament = null;
      
      try {
        tournament = loadXmlFromNetwork(urls[0]);
      } catch (XmlPullParserException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }

      return tournament;
    }

    @Override
    protected void onPostExecute(Tournament result) {
      super.onPostExecute(result);
      Intent intent = new Intent(MainActivity.this, TournamentTabActivity.class);
      Bundle b = new Bundle();
      b.putSerializable("tournament", result);
      intent.putExtras(b);
      startActivity(intent);
      finish();
    }
  }

  // Uploads XML from stackoverflow.com, parses it, and combines it with
  // HTML markup. Returns HTML string.
  private Tournament loadXmlFromNetwork(String urlString) throws XmlPullParserException,
      IOException {
    InputStream stream = null;
    XMLParser<Tournament> xmlParser = new TournamentXMLParser();
    List<Tournament> entries = null;

    try {
      stream = downloadUrl(urlString);
      entries = xmlParser.parse(stream);
      // Makes sure that the InputStream is closed after the app is
      // finished using it.
    } finally {
      if (stream != null) {
        stream.close();
      }
    }

    return entries.get(0);
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
