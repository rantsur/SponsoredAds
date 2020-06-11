package com.mabaya.sponsored;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.mabaya.sponsored.dao.ProductDao;
import com.mabaya.sponsored.dao.ProductDaoInf;
import com.mabaya.sponsored.model.Campaign;
import com.mabaya.sponsored.model.Category;
import com.mabaya.sponsored.model.Product;
import com.mabaya.sponsored.services.BidService;
import com.mabaya.sponsored.services.BidServiceInf;
import com.mabaya.sponsored.services.ProductService;
import com.mabaya.sponsored.services.ProductServiceInf;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SponsoredApplicationTests
{
	private ProductDaoInf productDaoInf;
	private ProductServiceInf productService;
	private BidServiceInf bidServiceInf;

	public SponsoredApplicationTests()
	{
	}

	@Before
	public void setUp()
	{
		ProductDao productDao = new ProductDao();
		productDao.init();
		this.productDaoInf = productDao;
		this.productService = new ProductService(productDaoInf);
		this.bidServiceInf = new BidService();
	}

	@Test
	public void productsSize()
	{
		assertThat(productDaoInf.getProductList().size()).isEqualTo(9);
	}

	@Test
	public void createCampaign()
	{
		BigDecimal campaignBid = new BigDecimal(BigInteger.TEN);
		String campaignName = "testCampaign";
		String sellerId = "sellerId-ABC";
		Campaign campaign = new Campaign(campaignName, campaignBid, sellerId);
		productService.addProductsToCampaign(campaign);

		assertThat(campaign.getProductList().size()).isEqualTo(3);
		assertThat(campaign.getBid()).isEqualTo(campaignBid);
		assertThat(campaign.getSellerId()).isEqualTo(sellerId);
		assertThat(campaign.getName()).isEqualTo(campaignName);
	}

	/**
	 * This test exam the correctness of the retrieval product.
	 * firstCampaign has higher bid than secondCampaign
	 * if secondCampaign has products with the given category we expect to get one of his products
	 * otherwise, return one of the products of the firstCampaign with the higher bid
	 */
	@Test
	public void getHighestBidProduct()
	{
		Campaign firstCampaign = createFirstCampaign();
		Campaign secondCampaign = createSecondCampaign();

		Set<Category> categoriesOfSecondCampaign = getCategoriesFromCampaigns(secondCampaign.getProductList());
		Category testCategory = new Category();
		testCategory.setName("sport");

		Product relevantProduct = bidServiceInf.getRelevantProduct(testCategory);
		if (categoriesOfSecondCampaign.contains(testCategory)) {
			// return product with the given category
			assertThat(relevantProduct.getSellerId()).isEqualTo(secondCampaign.getSellerId());
		}
		else {
			// return product with the highest bid
			assertThat(relevantProduct.getSellerId()).isEqualTo(firstCampaign.getSellerId());
		}
	}

	private Campaign createSecondCampaign()
	{
		return createCampaignProcess("secondTestCampaign", BigInteger.ONE, "sellerId-XYZ");
	}

	private Campaign createFirstCampaign()
	{
		return createCampaignProcess("testCampaign", BigInteger.TEN, "sellerId-ABC");
	}

	private Campaign createCampaignProcess(String campaignName, BigInteger bigInteger, String sellerId)
	{
		Campaign newCampaign = new Campaign(campaignName, new BigDecimal(bigInteger), sellerId);
		productService.addProductsToCampaign(newCampaign);
		bidServiceInf.process(newCampaign);
		return newCampaign;
	}

	/**
	 * Category name correctness
	 */
	@Test
	public void categoryName()
	{
		Campaign campaign = createFirstCampaign();
		Set<Category> categoriesFromCampaign = getCategoriesFromCampaigns(campaign.getProductList());
		Set<Category> allCategories = getAllCategories();
		assertThat(allCategories.containsAll(categoriesFromCampaign));
	}

	private Set<Category> getCategoriesFromCampaigns(List<Product> productList)
	{
		Set<Category> categoriesFromExistingProducts = new HashSet<>();
			for (Product product : productList) {
				categoriesFromExistingProducts.add(product.getCategory());
			}
		return categoriesFromExistingProducts;
	}

	private Set<Category> getAllCategories()
	{
		Set<Category> allCategoriesOptions = new HashSet<>();
		Category category = new Category();
		category.setName("gambling");
		allCategoriesOptions.add(category);
		category.setName("healthcare");
		allCategoriesOptions.add(category);
		category.setName("food");
		allCategoriesOptions.add(category);
		category.setName("travel");
		allCategoriesOptions.add(category);
		category.setName("sport");
		allCategoriesOptions.add(category);
		return allCategoriesOptions;
	}

}
