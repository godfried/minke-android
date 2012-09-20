package za.ac.sun.cs.hons.minke.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import za.ac.sun.cs.hons.minke.entities.IsEntity;
import za.ac.sun.cs.hons.minke.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.entities.store.Branch;

public class RPCUtils {
	private static String URL_BASE = "http://10.0.2.2:5000";// "http://backend.shop-minke.appspot.com"

	private static String REQUEST_BASE = "/entityRequestServlet?type=";

	@SuppressWarnings("unchecked")
	public static void retrieveLocations() {
		String suffix = "_locations";
		String url = URL_BASE + REQUEST_BASE + suffix;
		String response = HTTPUtils.doGetWithResponse(url);
		if (response.equals("failed")) {
			EntityUtils.setLocations(new ArrayList<IsEntity>());
		} else {
			try {
				EntityUtils.setLocations(ObjectParsers.parseResponse(response,
						suffix));
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("unchecked")
	public static void retrieveBrands() {
		String suffix = "_brands";
		String url = URL_BASE + REQUEST_BASE + suffix;
		String response = HTTPUtils.doGetWithResponse(url);
		if (response.equals("failed")) {
			EntityUtils.setBrands(new ArrayList<IsEntity>());
		} else {
			try {
				EntityUtils.setBrands(ObjectParsers.parseResponse(response,
						suffix));
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("unchecked")
	public static void retrieveCategories() {
		String suffix = "_categories";
		String url = URL_BASE + REQUEST_BASE + suffix;
		String response = HTTPUtils.doGetWithResponse(url);
		if (response.equals("failed")) {
			EntityUtils.setLocations(new ArrayList<IsEntity>());
		} else {
			try {
				EntityUtils.setCategories(ObjectParsers.parseResponse(response,
						suffix));
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void retrieveProducts() {
		String suffix = "_products";
		String url = URL_BASE + REQUEST_BASE + suffix;
		String response = HTTPUtils.doGetWithResponse(url);
		if (response.equals("failed")) {
			EntityUtils.setLocations(new ArrayList<IsEntity>());
		} else {
			try {
				EntityUtils.setProducts(ObjectParsers.parseResponse(response,
						suffix));
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("unchecked")
	private static void retrieveBranchProductsC() {
		String suffix = "locationcategory_branchproducts";
		String url = URL_BASE + REQUEST_BASE + suffix;
		String response = HTTPUtils.doGetWithResponse(url,
				SearchUtils.getAddedCities(), SearchUtils.getAddedProvinces(),
				SearchUtils.getAddedCountries(),
				SearchUtils.getAddedCategories(false));
		if (response.equals("failed")) {
			return;
		}
		try {
			List<IsEntity> searched = ObjectParsers.parseResponse(response,
					suffix);
			SearchUtils.setSearched(searched);
			EntityUtils.setBranchProducts(searched);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();

		}
	}

	@SuppressWarnings("unchecked")
	private static void retrieveBranchProductsP() {
		String suffix = "locationproduct_branchproducts";
		String url = URL_BASE + REQUEST_BASE + suffix;
		String response = HTTPUtils.doGetWithResponse(url,
				SearchUtils.getAddedCities(), SearchUtils.getAddedProvinces(),
				SearchUtils.getAddedCountries(),
				SearchUtils.getAddedProducts(false));
		if (response.equals("failed")) {
			return;
		}
		try {
			List<IsEntity> searched = ObjectParsers.parseResponse(response,
					suffix);
			SearchUtils.setSearched(searched);
			EntityUtils.setBranchProducts(searched);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();

		}
	}

	public static void retrieveBranchProducts(boolean productsActive) {
		if (productsActive) {
			retrieveBranchProductsP();
		} else {
			retrieveBranchProductsC();

		}
	}

	@SuppressWarnings("unchecked")
	public static void retrieveBranches(List<IsEntity> products) {
		String suffix = "product_branches";
		String url = URL_BASE + REQUEST_BASE + suffix;
		String response = HTTPUtils.doGetWithResponse(url, products);
		if (response.equals("failed")) {
			return;
		}
		try {
			EntityUtils.setBranches(ObjectParsers.parseResponse(response,
					suffix));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void retrieveBranches(double latitude, double longitude) {
		String suffix = "coords_branches";
		String url = URL_BASE + REQUEST_BASE + suffix;
		String response = HTTPUtils.doGetWithResponse(url, latitude, longitude);
		if (response.equals("failed")) {
			return;
		}
		try {
			EntityUtils.setBranches(ObjectParsers.parseResponse(response,
					suffix));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void getBranchProduct(long code, Branch branch) {
		String suffix = "branch_branchproduct";
		String url = URL_BASE + REQUEST_BASE + suffix;
		List<IsEntity> holder = Arrays.asList(new IsEntity[] { branch });
		String response = HTTPUtils.doGetWithResponse(url, code, holder);
		if (response.equals("failed")) {
			return;
		}
		try {
			List<IsEntity> scanned = ObjectParsers.parseResponse(response,
					suffix);
			if (scanned != null && scanned.size() > 0) {
				EntityUtils.setScanned(scanned.get(0));
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}

	public static void retrieveBranchProducts(Branch branch) {
		String suffix = "branch_branchproducts";
		String url = URL_BASE + REQUEST_BASE + suffix;
		List<IsEntity> holder = Arrays.asList(new IsEntity[] { branch });
		@SuppressWarnings("unchecked")
		String response = HTTPUtils.doGetWithResponse(url, holder);
		if (response.equals("failed")) {
			return;
		}
		try {
			EntityUtils.setBranchProducts(ObjectParsers.parseResponse(response,
					suffix));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void addBranchProduct(BranchProduct bp, long barcode,
			long branchcode) {
		String suffix = "branchproduct_branchproduct";
		String url = URL_BASE + REQUEST_BASE + suffix;
		String response = HTTPUtils.doPostWithResponse(url, bp, barcode,
				branchcode);
		if (response.equals("failed")) {
			return;
		}
		try {
			List<IsEntity> holder = ObjectParsers.parseResponse(response,
					suffix);
			EntityUtils.setBranchProducts(holder);
			BrowseUtils.setBranchProducts(holder);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void updateBranchProduct(BranchProduct bp) {
		String suffix = "price_branchproduct";
		String url = URL_BASE + REQUEST_BASE + suffix;
		String response = HTTPUtils.doPostWithResponse(url, bp.getID(),
				bp.getPrice());
		if (response.equals("failed")) {
			return;
		}
		try {
			List<IsEntity> holder = ObjectParsers.parseResponse(response,
					suffix);
			EntityUtils.setBranchProducts(holder);
			BrowseUtils.setBranchProducts(holder);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();

		}
	}

	public static void addBranch(Branch branch, String province, String country) {
		String suffix = "branch_branch";
		String url = URL_BASE + REQUEST_BASE + suffix;
		String response = HTTPUtils.doPostWithResponse(url, branch, province,
				country);
		if (response.equals("failed")) {
			return;
		}
		try {
			List<IsEntity> holder = ObjectParsers.parseResponse(response,
					suffix);
			EntityUtils.setUserBranch((Branch) holder.get(0));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

}
