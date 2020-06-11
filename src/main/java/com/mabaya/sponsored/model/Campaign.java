package com.mabaya.sponsored.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by Ran Tsur on 09-Jun-20.
 */
@Entity
public class Campaign
{
	public enum Status{
		ACTIVE, DELETED
	}

	@NotNull
	@NotEmpty(message = "Please provide a name")
	private String name;
	private Status status;
	@NotNull(message = "Please provide a positive bid")
	@Min(0)
	private BigDecimal bid;
	@NotNull()
	@NotEmpty(message = "Please provide a seller ID")
	@Id
	private String sellerId;
	@OneToMany
	private List<Product> productList;

	protected Campaign(){
		this.status = Status.ACTIVE;
		this.productList = new ArrayList<>();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public BigDecimal getBid()
	{
		return bid;
	}

	public void setBid(BigDecimal bid)
	{
		this.bid = bid;
	}

	public String getSellerId()
	{
		return sellerId;
	}

	public void setSellerId(String sellerId)
	{
		this.sellerId = sellerId;
	}

	public List<Product> getProductList()
	{
		return productList;
	}

	public void setProductList(List<Product> productList)
	{
		this.productList = productList;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Campaign campaign = (Campaign) o;
		return Objects.equals(name, campaign.name) && status == campaign.status && Objects.equals(bid, campaign.bid) && Objects.equals(sellerId, campaign.sellerId) && Objects
				.equals(productList, campaign.productList);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(name, status, bid, sellerId, productList);
	}
}
