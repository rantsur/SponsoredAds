package com.mabaya.sponsored;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mabaya.sponsored.model.Campaign;
import com.mabaya.sponsored.model.Category;
import com.mabaya.sponsored.model.Product;

@RestController
public class AppController
{
	final ProductService businessLogic;

	public AppController(ProductService businessLogic)
	{
		this.businessLogic = businessLogic;
	}

	@ResponseBody
	@RequestMapping("/newCampaign")
	public Campaign createCampaign(@RequestBody Campaign newCampaign)
	{
		businessLogic.addProductsToCampaign(newCampaign);
		return newCampaign;
	}

	@ResponseBody
	@RequestMapping("/getProduct")
	public Product getPromotedProduct(@RequestBody Category category)
	{
		return businessLogic.getRelevantProduct(category);
	}
}
