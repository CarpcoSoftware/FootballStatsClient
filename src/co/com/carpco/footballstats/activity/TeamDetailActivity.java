/**
 * 
 */
package co.com.carpco.footballstats.activity;

import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import co.com.carpco.footballstats.R;
import co.com.carpco.footballstats.entity.Team;

/**
 * @author Carlos Rodriguez
 */
public class TeamDetailActivity extends ActionBarActivity implements OnClickListener {
  
  private Team team;
  
  private List<Team> teamList;
  
  private int currentPosition;
  
  @SuppressWarnings("unchecked")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_team_detail);
    
    Bundle b = getIntent().getExtras();
    teamList = (List<Team>) b.getSerializable("teamList");
    currentPosition = b.getInt("currentPosition");
    
    if (teamList != null && teamList.size() > 0) {
      this.moveTo(currentPosition);
      ImageButton btnPrevious = (ImageButton) findViewById(R.id.imgTeamDetailPrevious);
      ImageButton btnNext = (ImageButton) findViewById(R.id.imgTeamDetailNext);
      btnPrevious.setOnClickListener(this);
      btnNext.setOnClickListener(this);
    }
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.imgTeamDetailPrevious:
        if (currentPosition > 0) {
          this.moveTo(currentPosition - 1);
        } else {
          this.moveTo(teamList.size() - 1);
        }
        break;
      case R.id.imgTeamDetailNext:
        if (currentPosition < teamList.size() - 1) {
          this.moveTo(currentPosition + 1);
        } else {
          this.moveTo(0);
        }
        break;
    }
  }
  
  private void moveTo(int position) {
    team = teamList.get(position);
    currentPosition = position;
    
    ImageView imgTeam = (ImageView) findViewById(R.id.imgTeamDetailFlag);
    ImageView imgCountry = (ImageView) findViewById(R.id.imgTeamDetailCountryFlag);
    TextView txtTeamName = (TextView) findViewById(R.id.txtTeamDetailName);
    TextView txtTeamCapital = (TextView) findViewById(R.id.txtTeamDetailCapital);
    TextView txtTeamLanguage = (TextView) findViewById(R.id.txtTeamDetailLanguage);
    TextView txtTeamNickname = (TextView) findViewById(R.id.txtTeamDetailNickname);
    TextView txtTeamFoundation = (TextView) findViewById(R.id.txtTeamDetailFoundation);
    TextView txtTeamRanking = (TextView) findViewById(R.id.txtTeamDetailRanking);
    TextView txtTeamCoach = (TextView) findViewById(R.id.txtTeamDetailCoach);
    
    imgTeam.setImageBitmap(team.getFlag().getBitmap());
    imgCountry.setImageBitmap(team.getCountry().getFlag().getBitmap());
    txtTeamName.setText(team.getName());
    
    txtTeamCapital.setText(team.getCountry().getCapital());
    txtTeamNickname.setText(team.getNickname());
    txtTeamLanguage.setText(team.getCountry().getLanguage());
    txtTeamFoundation.setText(String.valueOf(team.getFoundation()));
    txtTeamRanking.setText(String.valueOf(team.getRanking()));
    txtTeamCoach.setText(team.getCoach());
  }

}
