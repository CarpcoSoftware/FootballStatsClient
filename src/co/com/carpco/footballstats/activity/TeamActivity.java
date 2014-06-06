package co.com.carpco.footballstats.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import co.com.carpco.footballstats.R;
import co.com.carpco.footballstats.entity.Team;
import co.com.carpco.footballstats.entity.Tournament;

public class TeamActivity extends Activity {

  private static List<Team> teamList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_team);
    
    Tournament tournament = TournamentTabActivity.tournament;
    
    if (tournament != null) {
      ImageView imgTournament = (ImageView) findViewById(R.id.imgTeamTournametFlag);
      ImageView imgCountry = (ImageView) findViewById(R.id.imgTeamCountryFlag);
      imgTournament.setImageBitmap(tournament.getFlag().getBitmap());
      imgCountry.setImageBitmap(tournament.getCountry().getFlag().getBitmap());
    }
    
    teamList = new ArrayList<>(TournamentTabActivity.tournament.getTeamSet());
    if (teamList != null) {
      TeamAdapter adapter =
          new TeamAdapter(TeamActivity.this, TournamentTabActivity.tournament.getTeamSet());
      ListView list = (ListView) findViewById(R.id.lstTeam);
      list.setAdapter(adapter);
      list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          Toast.makeText(TeamActivity.this, "You Clicked at " + teamList.get(position).getName(),
              Toast.LENGTH_SHORT).show();
        }
      });
    }
  }
  
  class TeamAdapter extends ArrayAdapter<Team> {

    private final Activity context;
    private final List<Team> teamList;

    public TeamAdapter(Activity context, Set<Team> teamSet) {
      super(context, R.layout.activity_team_list, new ArrayList<>(teamSet));
      this.context = context;
      this.teamList = new ArrayList<>(teamSet);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
      LayoutInflater inflater = context.getLayoutInflater();
      View rowView = inflater.inflate(R.layout.activity_team_list, null, true);
      ImageView imageView1 = (ImageView) rowView.findViewById(R.id.imgTeamListFlag1);
      ImageView imageView2 = (ImageView) rowView.findViewById(R.id.imgTeamListFlag2);
      TextView txtTitle = (TextView) rowView.findViewById(R.id.txtTeamListName);
      txtTitle.setText(teamList.get(position).getName());
      imageView1.setImageBitmap(teamList.get(position).getFlag().getBitmap());
      imageView2.setImageBitmap(teamList.get(position).getCountry().getFlag().getBitmap());
      return rowView;
    }
  }
}