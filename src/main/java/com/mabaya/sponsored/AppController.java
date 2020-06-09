package com.mabaya.sponsored;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController
{
	@ResponseBody @RequestMapping("/newCampaign")
	public Campaign createCampaign(@RequestBody Campaign campaign){
		return new Campaign(campaign.getName(), campaign.bid, campaign.sellerId);
	}

	@ResponseBody @RequestMapping("/getProduct")
	public Product getPromotedProduct(@RequestBody String category){
		return null;
	}
}
