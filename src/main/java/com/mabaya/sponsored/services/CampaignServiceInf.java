package com.mabaya.sponsored.services;

import java.util.List;

import com.mabaya.sponsored.model.Campaign;

public interface CampaignServiceInf
{
	Campaign persistCampaign(Campaign campaign);

	List<Campaign> getSavedCampaigns();
}
