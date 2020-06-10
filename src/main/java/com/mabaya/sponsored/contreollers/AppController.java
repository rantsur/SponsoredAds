package com.mabaya.sponsored.contreollers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mabaya.sponsored.model.Campaign;
import com.mabaya.sponsored.model.Category;
import com.mabaya.sponsored.model.Product;
import com.mabaya.sponsored.services.BidServiceInf;
import com.mabaya.sponsored.services.CampaignServiceInf;
import com.mabaya.sponsored.services.ProductServiceInf;

@RestController
public class AppController
{
	final ProductServiceInf productService;
	final CampaignServiceInf campaignService;
	final BidServiceInf bidService;

	public AppController(ProductServiceInf productService, CampaignServiceInf campaignService, BidServiceInf bidService)
	{
		this.productService = productService;
		this.campaignService = campaignService;
		this.bidService = bidService;
	}

	@ResponseBody
	@RequestMapping("/newCampaign")
	public Campaign createCampaign(@RequestBody Campaign newCampaign)
	{
		productService.addProductsToCampaign(newCampaign);
		Campaign savedCampaign = campaignService.persistCampaign(newCampaign);
		bidService.process(savedCampaign);
		return savedCampaign;
	}

	@ResponseBody
	@RequestMapping("/getProduct")
	public Product getPromotedProduct(@RequestBody Category category)
	{
		return bidService.getRelevantProduct(category);
	}
}
