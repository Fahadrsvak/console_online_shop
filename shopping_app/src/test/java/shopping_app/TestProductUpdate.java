package shopping_app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.dao.ProductUpdateDAO;
import com.app.dao.impl.ProductUpdateDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Product;

class TestProductUpdate {

	@Test
	void testProductUpdate() throws BusinessException {
		Product product = new Product(2,"A53",13999.00 , "Oppo");
		ProductUpdateDAO productUpdateDAO =new ProductUpdateDAOImpl();
		assertEquals(product, productUpdateDAO.productUpdate(new Product(2,"A53",13999.00 , "Oppo")));
	}
}
