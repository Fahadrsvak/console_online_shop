package shopping_app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.dao.ProductDeleteDAO;
import com.app.dao.impl.ProductDeleteDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Product;

class TestProductDeleteDAO {

	@Test
	void testProductDelete() throws BusinessException {
		//Product product = new Product(,"A53",13999.00 , "OPPO");
		ProductDeleteDAO productDeleteDAO=new ProductDeleteDAOImpl();
		assertEquals(true, productDeleteDAO.productDelete(new Product(9,"A53",13999.00 , "OPPO")),"invalid");
	}

}
