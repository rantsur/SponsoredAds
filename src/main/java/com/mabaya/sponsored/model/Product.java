package com.mabaya.sponsored.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by Ran Tsur on 09-Jun-20.
 */
@Entity
public class Product
{
	private String title;
	private BigDecimal price;
	@OneToOne
	private Category category;
	@Id
	private String serial;
	private String sellerId;

	public Product(String title, BigDecimal price, Category category, String serial, String sellerId)
	{
		this.title = title;
		this.price = price;
		this.category = category;
		this.serial = serial;
		this.sellerId = sellerId;
	}

	public Product(){}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory(Category category)
	{
		this.category = category;
	}

	public String getSerial()
	{
		return serial;
	}

	public void setSerial(String serial)
	{
		this.serial = serial;
	}

	public String getSellerId()
	{
		return sellerId;
	}

	public void setSellerId(String sellerId)
	{
		this.sellerId = sellerId;
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
		Product product = (Product) o;
		return Objects.equals(title, product.title) && Objects.equals(price, product.price) && Objects.equals(category, product.category) && Objects.equals(serial, product.serial)
				&& Objects.equals(sellerId, product.sellerId);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(title, price, category, serial, sellerId);
	}
}
