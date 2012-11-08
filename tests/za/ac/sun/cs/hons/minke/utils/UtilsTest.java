package za.ac.sun.cs.hons.minke.utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import za.ac.sun.cs.hons.minke.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.utils.constants.TIME;

public class UtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void calcTotalTest() {
		ArrayList<BranchProduct> bps = new ArrayList<BranchProduct>();
		double total = 0, price = 0;
		int qty = 1;
		for(int i = 0 ; i < 10 ; i++){
			price = (double)((int)(Math.random()*1000*100)/100);
			qty = (int)((Math.random()+1)*50);
			BranchProduct bp = new BranchProduct();
			DatePrice dp = new DatePrice();
			dp.setPrice((int)(price*100));
			bp.setQuantity(qty);
			bp.setDatePrice(dp);
			total += qty*price;
			bps.add(bp);
		}
		bps.add(new BranchProduct());
		bps.get(0).getDatePrice().setPrice(-1);
		bps.get(1).setQuantity(-1);
		assertEquals(total, ShopUtils.getTotal(bps),0.0001);
		assertEquals(0, ShopUtils.getTotal(null),0.0001);
		assertEquals(0, ShopUtils.getTotal(new ArrayList<BranchProduct>()),0.0001);

	}
	
	@Test
	public void filterTest(){
		ArrayList<BranchProduct> bps = new ArrayList<BranchProduct>();
		ArrayList<BranchProduct> latest = new ArrayList<BranchProduct>();
		getLists(bps, latest,20);
		assertEquals(latest, EntityUtils.filterLatest(bps));
		assertEquals(new ArrayList<BranchProduct>(), EntityUtils.filterLatest(new ArrayList<BranchProduct>()));
		assertEquals(null, EntityUtils.filterLatest(null));
		bps = new ArrayList<BranchProduct>();
		latest = new ArrayList<BranchProduct>();
		getLists(bps, latest,5);
		assertEquals(latest, EntityUtils.filterLatest(bps));
		getLists(bps, latest,1);
		assertEquals(latest, EntityUtils.filterLatest(bps));
		

	}
	
	private void getLists(ArrayList<BranchProduct> all, ArrayList<BranchProduct> latest, int n){
		if(n < 0){
			n = -n;
		}
		int len = Math.min(10, n);
		for(int i = 0 ; i < n  ; i++){
			BranchProduct bp = new BranchProduct();
			DatePrice dp = new DatePrice();
			dp.setDate(new Date(new Date().getTime()-TIME.DAY*i));
			bp.setDatePrice(dp);
			all.add(bp);
			if(i < len){
				latest.add(bp);
			}
		}
		Collections.shuffle(all);
		Collections.shuffle(latest);
		Collections.sort(latest);
	}

}