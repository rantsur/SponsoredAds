package com.mabaya.sponsored.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Ran Tsur on 09-Jun-20.
 */
@Entity
public class Category
{
	@Id
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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
		Category category = (Category) o;
		return Objects.equals(name, category.name);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(name);
	}
}
