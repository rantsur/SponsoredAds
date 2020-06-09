package com.mabaya.sponsored;

import java.math.BigDecimal;

/**
 * Created by Ran Tsur on 09-Jun-20.
 */
public class BidPerProduct
{
	Product product;
	BigDecimal bid;

	public BidPerProduct(Product product, BigDecimal bid)
	{
		this.product = product;
		this.bid = bid;
	}

	public Product getProduct()
	{
		return product;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}

	public BigDecimal getBid()
	{
		return bid;
	}

	public void setBid(BigDecimal bid)
	{
		this.bid = bid;
	}
}

