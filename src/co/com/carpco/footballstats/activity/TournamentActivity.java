package co.com.carpco.footballstats.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import co.com.carpco.footballstats.R;
import co.com.carpco.footballstats.R.string;
import co.com.carpco.footballstats.entity.Tournament;

public class TournamentActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tournament);
    
    Tournament tournament = TournamentTabActivity.tournament;
    
    if (tournament != null) {
      TextView txtTournament = (TextView) findViewById(R.id.txtTournamentName);
      TextView txtFoundation = (TextView) findViewById(R.id.txtTournamentFoundation);
      ImageView imgTournament = (ImageView) findViewById(R.id.imgTournametFlag);
      ImageView imgCountry = (ImageView) findViewById(R.id.imgCountryFlag);
      txtTournament.setText(tournament.getName());
      txtFoundation.setText(getResources().getString(string.foundation_year).concat(String.valueOf(tournament.getFoundationYear())));
      imgTournament.setImageBitmap(tournament.getFlag().getBitmap());
      imgCountry.setImageBitmap(tournament.getCountry().getFlag().getBitmap());
    }
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.tournament, menu);
    return true;
  }

}
