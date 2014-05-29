package co.com.carpco.footballstats.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.TextView;
import co.com.carpco.footballstats.R;
import co.com.carpco.footballstats.R.string;
import co.com.carpco.footballstats.entity.Tournament;

public class TournamentActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tournament);
    
    Bundle b = getIntent().getExtras();
    Tournament tournament = (Tournament) b.getSerializable("tournament");
    
    if (tournament != null) {
      TextView txtTournament = (TextView) findViewById(R.id.tvTournament);
      TextView txtFoundation = (TextView) findViewById(R.id.tvFoundation);
      txtTournament.setText(tournament.getName());
      txtFoundation.setText(getResources().getString(string.foundation_year).concat(String.valueOf(tournament.getFoundationYear())));
    }
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.tournament, menu);
    return true;
  }

}
