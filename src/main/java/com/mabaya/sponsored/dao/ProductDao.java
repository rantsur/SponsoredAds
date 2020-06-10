package com.mabaya.sponsored.dao;

import java.math.BigDecimal;
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
public class ProductDao implements ProductDaoInf
{
	final static String SPORT = "sport";
	final static String TRAVEL = "travel";
	final static String HEALTHCARE = "healthcare";
	final static String FOOD = "food";
	final static String GAMBLING = "gambling";
	final static String PRODUCT = "product-";
	final static String SERIAL = "serial-";
	final static String SELLER_ID = "sellerId-";
	final static int CATEGORY_OPTIONS = 5;
	List<Product> productList = new ArrayList<>();

	@PostConstruct
	public void init()
	{
		for (int i = 0; i < 9; i++) {
			Product p = createNewProduct(i + 1, getPriceForProduct(i), getSellerSuffix(i));
			productList.add(p);
		}
	}

	private BigDecimal getPriceForProduct(int i)
	{
		if (i < 3) {
			return new BigDecimal("10");
		}
		else if (i < 6) {
			return new BigDecimal("9");
		}
		else {
			return new BigDecimal("8");
		}
	}

	private String getSellerSuffix(int i)
	{
		if (i < 3) {
			return "ABC";
		}
		else if (i < 6) {
			return "LMN";
		}
		else {
			return "XYZ";
		}
	}

	private Product createNewProduct(int suffix, BigDecimal price, String sellerSuffix)
	{
		return new Product(PRODUCT.concat(String.valueOf(suffix)), price, getRandomCategory(), SERIAL.concat(String.valueOf(suffix)), SELLER_ID.concat(sellerSuffix));
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

	@Override
	public List<Product> getProductList(){
		return productList;
	}
}
