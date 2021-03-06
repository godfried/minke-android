package za.ac.sun.cs.hons.minke.utils;

import za.ac.sun.cs.hons.minke.entities.location.City;
import za.ac.sun.cs.hons.minke.entities.location.Province;
import za.ac.sun.cs.hons.minke.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.entities.product.Brand;
import za.ac.sun.cs.hons.minke.entities.product.Category;
import za.ac.sun.cs.hons.minke.entities.product.Product;
import za.ac.sun.cs.hons.minke.entities.store.Store;

public class ScanUtils {
	private static Product product;
	private static BranchProduct branchProduct;
	private static long barCode;
	public static Brand brand;
	public static Category category;
	public static double size;
	public static int price, measurePos;
	public static String categoryName, brandName, productName, cityName, branchName, storeName;
	public static Province province;
	public static City city;
	public  static Store store;
	

	public static Product getProduct() {
		return product;
	}

	public static void setProduct(Product product) {
		ScanUtils.product = product;
	}

	public static BranchProduct getBranchProduct() {
		return branchProduct;
	}

	public static void setBranchProduct(BranchProduct branchProduct) {
		ScanUtils.branchProduct = branchProduct;
		if (branchProduct != null) {
			BrowseUtils.setBranchProducts(EntityUtils.retrieveBranchProducts(
					branchProduct.getProductId(), branchProduct));
		}
	}

	public static void setBarCode(long _barCode) {
		barCode = _barCode;
	}

	public static long getBarCode() {
		return barCode;
	}

	public static void clearFields() {
		brand = null;
		category = null;
		city = null;
		store = null;
		province = null;
		categoryName = brandName = productName = cityName = branchName = storeName = null;
		size = price = measurePos  = -1;
	}

}
