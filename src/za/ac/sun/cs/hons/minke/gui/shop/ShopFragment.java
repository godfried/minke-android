package za.ac.sun.cs.hons.minke.gui.shop;

import za.ac.sun.cs.hons.minke.R;
import za.ac.sun.cs.hons.minke.entities.product.Product;
import za.ac.sun.cs.hons.minke.gui.utils.ItemListAdapter;
import za.ac.sun.cs.hons.minke.utils.EntityUtils;
import za.ac.sun.cs.hons.minke.utils.ShopUtils;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;

public class ShopFragment extends SherlockFragment {
	private ArrayAdapter<Product> productAdapter;
	AutoCompleteTextView shopping;
	ItemListAdapter<Product> shoplistAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_shop, container, false);
		initGUI(v);
		return v;
	}

	private void initGUI(View v) {
		productAdapter = new ArrayAdapter<Product>(getActivity(),
				R.layout.listitem_default, EntityUtils.getProducts());
		shopping = (AutoCompleteTextView) v
				.findViewById(R.id.text_shops);
		shopping.setAdapter(productAdapter);
		shopping.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				addItem((Product) productAdapter.getItem(position));
			}

		});
		shoplistAdapter = new ItemListAdapter<Product>(getActivity(),
				ShopUtils.getAddedProducts(true));
		ListView shoplist = (ListView) v.findViewById(R.id.list_shopping);
		shoplist.setAdapter(shoplistAdapter);
		shoplist.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ShopUtils.removeProduct(position);
				shoplistAdapter.notifyDataSetChanged();

			}
		});

	}

	private void addItem(Product item) {
		if (item != null) {
			ShopUtils.addProduct(item);
			shoplistAdapter.notifyDataSetChanged();
		}
		shopping.setText("");
	}
}