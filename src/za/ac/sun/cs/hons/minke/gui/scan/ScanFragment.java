package za.ac.sun.cs.hons.minke.gui.scan;

import za.ac.sun.cs.hons.minke.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

public class ScanFragment extends SherlockFragment  {

	 @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
             Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.fragment_scan, container, false);
         return v;
     }
}
