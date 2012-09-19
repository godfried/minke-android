package za.ac.sun.cs.hons.minke.utils;

import za.ac.sun.cs.hons.minke.HomeActivity;
import za.ac.sun.cs.hons.minke.gui.browse.BrowseActivity;
import za.ac.sun.cs.hons.minke.gui.browse.LocationSearchActivity;
import za.ac.sun.cs.hons.minke.gui.browse.ProductSearchActivity;
import za.ac.sun.cs.hons.minke.gui.create.NewProductActivity;
import za.ac.sun.cs.hons.minke.gui.graph.GraphActivity;
import za.ac.sun.cs.hons.minke.gui.maps.DirectionsActivity;
import za.ac.sun.cs.hons.minke.gui.shop.ShopActivity;
import za.ac.sun.cs.hons.minke.gui.shop.StoreActivity;
import android.content.Context;
import android.content.Intent;

public class IntentUtils {
	public static Intent createShareIntent() {
		final Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "Shared from minke.");
		return Intent.createChooser(intent, "Share");
	}

	public static Intent getHomeIntent(Context context) {
		Intent home = new Intent(context, HomeActivity.class);
		home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return home;
	}

	public static Intent getShopIntent(Context context) {
		Intent shop = new Intent(context, ShopActivity.class);
		shop.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return shop;
	}

	public static Intent getBrowseIntent(Context context) {
		Intent browse = new Intent(context, BrowseActivity.class);
		browse.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return browse;
	}

	public static Intent getNewProductIntent(Context context) {
		Intent scan = new Intent(context, NewProductActivity.class);
		scan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return scan;
	}

	public static Intent getLocationSearchIntent(Context context) {
		Intent locationSearch = new Intent(context, LocationSearchActivity.class);
		locationSearch.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return locationSearch;
	}

	public static Intent getProductSearchIntent(Context context) {
		Intent productSearch = new Intent(context, ProductSearchActivity.class);
		productSearch.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return productSearch;
	}

	public static Intent getGraphIntent(Context context) {
		Intent graph = new Intent(context, GraphActivity.class);
		graph.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return graph;
	}

	public static Intent getDirectionsIntent(Context context) {
		Intent directions = new Intent(context, DirectionsActivity.class);
		directions.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return directions;
	}

	public static Intent getStoreIntent(Context context) {
		Intent store = new Intent(context, StoreActivity.class);
		store.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return store;
	}

}
