package com.mabaya.sponsored.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.mabaya.sponsored.model.Category;
import com.mabaya.sponsored.model.Product;

/**
 * Created by Ran Tsur on 09-Jun-20.
 */
@Repository
public class ProductDao
{
	final static String SPORT = "sport";
	final static String TRAVEL = "travel";
	final static String HEALTHCARE = "healthcare";
	final static String FOOD = "food";
	final static String GAMBLING = "gambling";
	final static int CATEGORY_OPTIONS = 5;
	List<Product> productList = new ArrayList<>();

	@PostConstruct
	public void init()
	{
		Product p1 = new Product("product-1", new BigDecimal(BigInteger.ONE), getRandomCategory(), "serial-1", "sellerId-ABC");
		Product p2 = new Product("product-2", new BigDecimal(BigInteger.ONE), getRandomCategory(), "serial-2", "sellerId-ABC");
		Product p3 = new Product("product-3", new BigDecimal(BigInteger.ONE), getRandomCategory(), "serial-3", "sellerId-ABC");
		productList.add(p1);
		productList.add(p2);
		productList.add(p3);

		Product p4 = new Product("product-4", new BigDecimal("3"), getRandomCategory(), "serial-4", "sellerId-LMN");
		Product p5 = new Product("product-5", new BigDecimal("3"), getRandomCategory(), "serial-5", "sellerId-LMN");
		Product p6 = new Product("product-6", new BigDecimal("3"), getRandomCategory(), "serial-6", "sellerId-LMN");
		productList.add(p4);
		productList.add(p5);
		productList.add(p6);

		Product p7 = new Product("product-7", new BigDecimal(BigInteger.TEN), getRandomCategory(), "serial-7", "sellerId-XYZ");
		Product p8 = new Product("product-8", new BigDecimal(BigInteger.TEN), getRandomCategory(), "serial-8", "sellerId-XYZ");
		Product p9 = new Product("product-9", new BigDecimal(BigInteger.TEN), getRandomCategory(), "serial-9", "sellerId-XYZ");
		productList.add(p7);
		productList.add(p8);
		productList.add(p9);
	}

	private static Category getRandomCategory()
	{
		int randomNumberInRange = getRandomNumberInRange();
		Category ans = new Category();
		switch (randomNumberInRange) {
			case 1:
				ans.setName(FOOD);
				break;
			case 2:
				ans.setName(GAMBLING);
				break;
			case 3:
				ans.setName(TRAVEL);
				break;
			case 4:
				ans.setName(HEALTHCARE);
				break;
			default:
				ans.setName(SPORT);
		}
		return ans;
	}

	private static int getRandomNumberInRange()
	{
		int min = 1;
		Random r = new Random();
		return r.nextInt((CATEGORY_OPTIONS - min) + 1) + min;
	}

	// todo - note about
	public List<Product> getProductList(){
		return productList;
	}
}
