package za.ac.sun.cs.hons.minke.gui.browse;

import za.ac.sun.cs.hons.minke.R;
import za.ac.sun.cs.hons.minke.gui.utils.ItemListAdapter;
import za.ac.sun.cs.hons.minke.utils.EntityUtils;
import za.ac.sun.cs.hons.minke.utils.SearchUtils;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;


public class LocationSearchFragment  extends SherlockFragment {

	private AutoCompleteTextView locationBox;
	private ArrayAdapter<Object> locationAdapter;
	private ItemListAdapter<Object> locationListAdapter;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_location_search, container, false);
		initBoxes(v);
		initLists(v);
		return v;
	}
	@Override
	public void onResume() {
		super.onResume();
		SearchUtils.getAddedLocations().clear();
		locationListAdapter.notifyDataSetChanged();
	}


	private void initBoxes(View v) {
		locationBox = (AutoCompleteTextView) v.findViewById(R.id.text_location);
		locationAdapter = new ArrayAdapter<Object>(this.getActivity(),
				R.layout.listitem_default, EntityUtils.getLocations());
		locationBox.setAdapter(locationAdapter);
		locationBox.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				addItem( locationAdapter.getItem(position));
			}

		});

	}

	private void initLists(View v) {
		locationListAdapter = new ItemListAdapter<Object>(this.getActivity(),
				SearchUtils.getAddedLocations()){
					@Override
					public void removeFromSearch(Object loc) {
						SearchUtils.removeLocation(loc);
						notifyDataSetChanged();
					}
			
		};
		ListView locationList = (ListView) v.findViewById(R.id.location_list);
		locationList.setAdapter(locationListAdapter);
	}

	protected void addItem(Object location) {
		if (location != null && !SearchUtils.getAddedLocations().contains(location)) {
			SearchUtils.addLocation(location);
			locationListAdapter.notifyDataSetChanged();
		}
		else{
			Toast msg = Toast.makeText(this.getActivity(), getString(R.string.str_added),
					Toast.LENGTH_LONG);
			msg.setGravity(Gravity.CENTER_VERTICAL,
					Gravity.CENTER_HORIZONTAL, 0);
			msg.show();
		}
		locationBox.setText("");

	}

	
}
