package za.ac.sun.cs.hons.minke.gui.graph;

import java.util.HashSet;

import org.achartengine.GraphicalView;

import za.ac.sun.cs.hons.minke.R;
import za.ac.sun.cs.hons.minke.gui.utils.DialogUtils;
import za.ac.sun.cs.hons.minke.tasks.ChartTask;
import za.ac.sun.cs.hons.minke.utils.BrowseUtils;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class ChartActivity extends SherlockActivity {

	private TimelineChart chart;
	private GraphicalView view;
	private RelativeLayout holder;
	private ChartTask curTask;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_graph);
		holder = (RelativeLayout) findViewById(R.id.graph_holder);
		if(getLastNonConfigurationInstance() != null){
			curTask = (ChartTask) getLastNonConfigurationInstance();
			curTask.attach(this);
		}else	if (BrowseUtils.getBranchProducts().size() != 0) {
			buildChart();
		}

	}
	@Override
	public Object onRetainNonConfigurationInstance(){
		curTask.detach();
		return curTask;
		
	}
	private void buildChart() {
		chart = new TimelineChart(this);
		curTask = new ChartTask(chart);
		curTask.execute();
	}

	public void showChart() {
		view = chart.getChart();
		holder.addView(view);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.menu_graph, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.menu_item_refresh:
			reset();
			return true;
		case R.id.menu_item_add:
			showEditDlg(true, chart.getRemoved(), getString(R.string.add));
			return true;
		case R.id.menu_item_remove:
			showEditDlg(false, chart.getAdded(), getString(R.string.remove));
			return true;
		case android.R.id.home:
			onBackPressed();
			return true;
		}
		return false;
	}

	private void showEditDlg(final boolean add, HashSet<String> items,
			String btn) {
		final String[] choices = items.toArray(new String[items.size()]);
		final HashSet<String> changed = new HashSet<String>();
		AlertDialog.Builder dlg = DialogUtils.getChartDialog(this);
		if (items.size() == 0) {
			dlg.setMessage(getString(R.string.no_change));
		} else {
			dlg.setMultiChoiceItems(choices, null,
					new DialogInterface.OnMultiChoiceClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which,
								boolean isChecked) {
							if (isChecked) {
								changed.add(choices[which]);
							} else {
								changed.remove(choices[which]);
							}
						}
					});
		}
		dlg.setPositiveButton(btn, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				editItems(changed, add);
				dialog.cancel();
			}
		});
		dlg.show();
	}

	private void reset() {
		HashSet<String> itr = new HashSet<String>();
		itr.addAll(chart.getRemoved());
		for (String s : itr) {
			chart.addSeries(s);
		}
		view.repaint();
	}

	protected void editItems(HashSet<String> changed, boolean add) {
		if (changed.size() == 0) {
			return;
		}
		for (String s : changed) {
			if (add) {
				chart.addSeries(s);
			} else {
				chart.removeSeries(s);
			}
		}
		view.repaint();
	}

}
