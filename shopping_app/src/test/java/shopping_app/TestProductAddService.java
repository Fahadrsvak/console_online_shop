package shopping_app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.dao.ProductAddDAO;
import com.app.dao.impl.ProductAddDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Product;

class TestProductAddService {

	@Test
	void testproductAdd1() throws BusinessException {
		Product product = new Product("A53",13999.00 , "OPPO");
		ProductAddDAO productAddDAO =new ProductAddDAOImpl();
		assertEquals(product, productAddDAO.productAdd(new Product("A53",13999.00 , "OPPO")),"invalid");
	}
	@Test
	void testproductAdd2() throws BusinessException {
		Product product = new Product("Mi 10I",21999.00 , "Redmi");
		ProductAddDAO productAddDAO =new ProductAddDAOImpl();
		assertEquals(product, productAddDAO.productAdd(new Product("Mi 10I",21999.00 , "Redmi")),"invalid");
	}
	

}
