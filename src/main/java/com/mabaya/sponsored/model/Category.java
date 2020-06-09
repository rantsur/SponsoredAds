package com.mabaya.sponsored.model;

import java.util.Objects;

/**
 * Created by Ran Tsur on 09-Jun-20.
 */
public class Category
{
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
