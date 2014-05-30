package co.com.carpco.footballstats.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import co.com.carpco.footballstats.R;
import co.com.carpco.footballstats.entity.Team;

public class TeamListActivity extends ListActivity {
  
  private static List<Team> teamList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setListAdapter(new EfficientAdapter(this));
  }

  private static class EfficientAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    public EfficientAdapter(Context context) {
      mInflater = LayoutInflater.from(context);
      teamList = new ArrayList<>(TournamentTabActivity.tournament.getTeamSet());
    }

    /**
     * The number of items in the list is determined by the number of speeches in our array.
     * 
     * @see android.widget.ListAdapter#getCount()
     */
    public int getCount() {
      int count = 0;
      if (teamList != null && teamList.size() > 0) {
        count = teamList.size();
      }
      return count;
    }

    /**
     * Since the data comes from an array, just returning the index is sufficent to get at the data.
     * If we were using a more complex data structure, we would return whatever object represents
     * one row in the list.
     * 
     * @see android.widget.ListAdapter#getItem(int)
     */
    public Object getItem(int position) {
      return position;
    }

    /**
     * Use the array index as a unique id.
     * 
     * @see android.widget.ListAdapter#getItemId(int)
     */
    public long getItemId(int position) {
      return position;
    }

    /**
     * Make a view to hold each row.
     * 
     * @see android.widget.ListAdapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder holder;

      if (convertView == null) {
        convertView = mInflater.inflate(R.layout.activity_team, null);

        holder = new ViewHolder();
        holder.text = (TextView) convertView.findViewById(R.id.text);
        holder.icon1 = (ImageView) convertView.findViewById(R.id.imgIcon1);

        convertView.setTag(holder);
      } else {
        holder = (ViewHolder) convertView.getTag();
      }

      holder.text.setText(teamList.get(position).getName());
      holder.icon1.setImageBitmap(teamList.get(position).getFlag().getBitmap());

      return convertView;
    }

    static class ViewHolder {
      TextView text;
      ImageView icon1;
    }
  }
}
