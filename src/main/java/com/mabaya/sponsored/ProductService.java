package com.mabaya.sponsored;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.mabaya.sponsored.dao.ProductDao;
import com.mabaya.sponsored.model.BidPerProduct;
import com.mabaya.sponsored.model.Campaign;
import com.mabaya.sponsored.model.Category;
import com.mabaya.sponsored.model.Product;

/**
 * Created by Ran Tsur on 09-Jun-20.
 */
@Service
public class ProductService
{
	Map<Category, TreeSet<BidPerProduct>> categoryToBidPerProductMap = new HashMap<>();
	Campaign highestBidCampaign;
	ProductDao productDao;

	public ProductService(ProductDao productDao)
	{
		this.productDao = productDao;
	}

	public void addToMap(Category category, Product product, BigDecimal bid)
	{
		BidPerProduct bidPerProduct = new BidPerProduct(product, bid);
		TreeSet<BidPerProduct> bidPerProductList = categoryToBidPerProductMap.computeIfAbsent(category, (key) -> new TreeSet<>(BidPerProduct::compareTo));
		bidPerProductList.add(bidPerProduct);
	}

	public void addProductsToCampaign(Campaign campaign)
	{
		List<Product> productsForSeller = productDao.getProductList().stream().filter(product -> product.getSellerId().equals(campaign.getSellerId())).collect(Collectors.toList());
		for (Product product : productsForSeller) {
			addToMap(product.getCategory(), product, campaign.getBid());
			campaign.getProductList().add(product);
		}

		if (!CollectionUtils.isEmpty(productsForSeller)) {
			if (ObjectUtils.isEmpty(highestBidCampaign)) {
				highestBidCampaign = campaign;
			}
			else if (campaign.getBid().compareTo(highestBidCampaign.getBid()) > 0) {
				highestBidCampaign = campaign;
			}
		}
	}

	public Product getHighestBidProduct()
	{
		return highestBidCampaign.getProductList().get(0);
	}

	public Product getRelevantProduct(Category category)
	{
		TreeSet<BidPerProduct> bidPerProducts = categoryToBidPerProductMap.get(category);
		if (!ObjectUtils.isEmpty(bidPerProducts)) {
			return bidPerProducts.last().getProduct();
		}
		else {
			return getHighestBidProduct();
		}
	}
}
