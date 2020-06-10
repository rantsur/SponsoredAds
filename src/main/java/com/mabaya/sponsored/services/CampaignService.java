package com.mabaya.sponsored.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mabaya.sponsored.model.Campaign;

/**
 * Created by Ran Tsur on 10-Jun-20.
 */
@Service
public class CampaignService implements CampaignServiceInf
{
	List<Campaign> savedCampaigns = new ArrayList<>();
	@Override
	public Campaign persistCampaign(Campaign campaign)
	{
		savedCampaigns.add(campaign);
		return campaign;
	}

	@Override
	public List<Campaign> getSavedCampaigns()
	{
		return savedCampaigns;
	}
}
