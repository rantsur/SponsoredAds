package com.mabaya.sponsored.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mabaya.sponsored.dao.ProductDaoInf;
import com.mabaya.sponsored.model.Campaign;
import com.mabaya.sponsored.model.Product;

/**
 * Created by Ran Tsur on 09-Jun-20.
 */
@Service
public class ProductService implements ProductServiceInf
{
	ProductDaoInf productDao;

	public ProductService(ProductDaoInf productDao)
	{
		this.productDao = productDao;
	}

	private Campaign initCampaignProducts(Campaign campaign){
		// get available products for seller campaign
		List<Product> productsForSeller = productDao.getProductList().stream().filter(product -> product.getSellerId().equals(campaign.getSellerId())).collect(Collectors.toList());
		for (Product product : productsForSeller) {
			campaign.getProductList().add(product);
		}
		return campaign;
	}

	@Override
	public Campaign addProductsToCampaign(Campaign campaign)
	{
		return initCampaignProducts(campaign);
	}

}
