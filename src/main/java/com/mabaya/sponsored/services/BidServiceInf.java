package com.mabaya.sponsored.services;

import com.mabaya.sponsored.model.Campaign;
import com.mabaya.sponsored.model.Category;
import com.mabaya.sponsored.model.Product;

public interface BidServiceInf
{
	void process(Campaign savedCampaign);

	Product getRelevantProduct(Category category);
}
