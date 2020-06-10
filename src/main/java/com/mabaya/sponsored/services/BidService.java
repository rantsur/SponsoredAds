package com.mabaya.sponsored.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.mabaya.sponsored.model.BidPerProduct;
import com.mabaya.sponsored.model.Campaign;
import com.mabaya.sponsored.model.Category;
import com.mabaya.sponsored.model.Product;

/**
 * Created by Ran Tsur on 10-Jun-20.
 */

@Service
public class BidService implements BidServiceInf
{
	private Campaign highestBidCampaign;
	private final Map<Category, TreeSet<BidPerProduct>> categoryToBidPerProductMap = new HashMap<>();

	@Override
	public void process(Campaign campaign)
	{
		List<Product> productList = campaign.getProductList();
		for (Product product : productList) {
			addToCacheMap(product.getCategory(), product, campaign.getBid());
		}
		searchForHighestBidCampaign(campaign);
	}

	private void searchForHighestBidCampaign(Campaign campaign)
	{
		if (!CollectionUtils.isEmpty(campaign.getProductList())) {
			if (ObjectUtils.isEmpty(highestBidCampaign)) {
				highestBidCampaign = campaign;
			}
			else if (campaign.getBid().compareTo(highestBidCampaign.getBid()) > 0) {
				highestBidCampaign = campaign;
			}
		}
	}

	/**
	 * Add product and bid to cache.
	 * if cache contains the given category, add bidPerProduct to the existing sorted set,
	 * else add new key to the cache (key = given category) and value is new sorted set contains the bidPerProduct
	 */
	public void addToCacheMap(Category category, Product product, BigDecimal bid)
	{
		BidPerProduct bidPerProduct = new BidPerProduct(product, bid);
		TreeSet<BidPerProduct> bidPerProductList = categoryToBidPerProductMap.computeIfAbsent(category, (key) -> new TreeSet<>(BidPerProduct::compareTo));
		bidPerProductList.add(bidPerProduct);
	}

	/**
	 * @param category of the requested product
	 * @return product in the given category with the highest bid
	 * or product of campaign with the highest bid (in case of no products in the given category)
	 */
	@Override
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

	/**
	 * @return arbitrary product from the campaign with the highest bid
	 */
	public Product getHighestBidProduct()
	{
		return highestBidCampaign.getProductList().get(0);
	}
}
