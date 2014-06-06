/**
 * 
 */
package co.com.carpco.footballstats.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import co.com.carpco.footballstats.R;
import co.com.carpco.footballstats.entity.Tournament;

/**
 * @author Carlos Rodriguez
 */
@SuppressWarnings("deprecation")
public class TournamentTabActivity extends TabActivity {

  protected static Tournament tournament;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tournament_tab);
    
    Bundle b = getIntent().getExtras();
    tournament = (Tournament) b.getSerializable("tournament");

    Resources ressources = getResources();
    TabHost tabHost = getTabHost();

    // Info tab
    Intent intentInfo = new Intent().setClass(this, TournamentActivity.class);
    TabSpec tabSpecInfo =
        tabHost
            .newTabSpec(getResources().getString(R.string.info))
            .setIndicator(getResources().getString(R.string.info),
                ressources.getDrawable(R.drawable.ic_launcher)).setContent(intentInfo);

    // Groups tab
    Intent intentGroups = new Intent().setClass(this, GroupsActivity.class);
    TabSpec tabSpecGroups =
        tabHost
            .newTabSpec(getResources().getString(R.string.groups))
            .setIndicator(getResources().getString(R.string.groups),
                ressources.getDrawable(R.drawable.ic_launcher)).setContent(intentGroups);

    // Teams tab
    Intent intentTeams = new Intent().setClass(this, TeamActivity.class);
    TabSpec tabSpecTeams =
        tabHost
            .newTabSpec(getResources().getString(R.string.teams))
            .setIndicator(getResources().getString(R.string.teams),
                ressources.getDrawable(R.drawable.ic_launcher)).setContent(intentTeams);

    // Schedule tab
    Intent intentSchedule = new Intent().setClass(this, ScheduleActivity.class);
    TabSpec tabSpecSchedule =
        tabHost
            .newTabSpec(getResources().getString(R.string.schedule))
            .setIndicator(getResources().getString(R.string.schedule),
                ressources.getDrawable(R.drawable.ic_launcher)).setContent(intentSchedule);

    // add all tabs
    tabHost.addTab(tabSpecInfo);
    tabHost.addTab(tabSpecGroups);
    tabHost.addTab(tabSpecTeams);
    tabHost.addTab(tabSpecSchedule);

    // set Windows tab as default (zero based)
    tabHost.setCurrentTab(0);
  }
}
