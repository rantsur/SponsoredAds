package com.mabaya.sponsored.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Ran Tsur on 09-Jun-20.
 */
public class BidPerProduct implements Comparable<BidPerProduct>
{
	private Product product;
	private BigDecimal bid;

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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BidPerProduct that = (BidPerProduct) o;
		return Objects.equals(product, that.product) && Objects.equals(bid, that.bid);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(product, bid);
	}

	@Override
	public int compareTo(BidPerProduct bidPerProduct)
	{
		int compareResult = this.bid.compareTo(bidPerProduct.bid);
		if (compareResult == 0) {
			// same bid case, compare alphabetic order of product serial
			return this.product.getSerial().compareTo(bidPerProduct.product.getSerial());
		}
		return compareResult;
	}
}

