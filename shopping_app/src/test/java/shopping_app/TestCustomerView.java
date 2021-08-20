/**
 * 
 */
package shopping_app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.dao.CustomerVIewAllDAO;
import com.app.dao.impl.CustomerViewAllDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;

/**
 * @author PC
 *
 */
class TestCustomerView {

	@Test
	void testSearchCustomerByEmail()  throws BusinessException{
		Customer customer=new Customer(1,"Fahad R","S","fahadrs1999@gmail.com");
		CustomerVIewAllDAO customerViewAllDAO=new CustomerViewAllDAOImpl();
		assertEquals(customer,customerViewAllDAO.searchCustomerByEmail("fahadrs1999@gmail.com"),"invliad input");
	}
	@Test
	void testSearchCustomerById()  throws BusinessException{
		Customer customer=new Customer(1,"Fahad R","S","fahadrs1999@gmail.com");
		CustomerVIewAllDAO customerViewAllDAO=new CustomerViewAllDAOImpl();
		assertEquals(customer,customerViewAllDAO.searchCustomerById(1),"invliad input");
	}
	@Test
	void testSearchCustomerByOrderId()  throws BusinessException{
		Customer customer=new Customer(1,"Fahad R","S","fahadrs1999@gmail.com");
		CustomerVIewAllDAO customerViewAllDAO=new CustomerViewAllDAOImpl();
		assertEquals(customer,customerViewAllDAO.searchCustomerByOrderId(11),"invliad input");
	}
	@Test
	void testSearchCustomerByOrderId2()  throws BusinessException{
		Customer customer=new Customer(1,"Fahad R","S","fahadrs1999@gmail.com");
		CustomerVIewAllDAO customerViewAllDAO=new CustomerViewAllDAOImpl();
		assertEquals(customer,customerViewAllDAO.searchCustomerByOrderId(9),"invliad input");
	}
	@Test
	void testSearchCustomerByOrderId3()  throws BusinessException{
		Customer customer=new Customer(1,"Fahad R","S","fahadrs1999@gmail.com");
		CustomerVIewAllDAO customerViewAllDAO=new CustomerViewAllDAOImpl();
		assertEquals(customer,customerViewAllDAO.searchCustomerByOrderId(8),"invliad input");
	}
	@Test
	void testSearchCustomerByOrderId4()  throws BusinessException{
		Customer customer=new Customer(1,"Fahad R","S","fahadrs1999@gmail.com");
		CustomerVIewAllDAO customerViewAllDAO=new CustomerViewAllDAOImpl();
		assertEquals(customer,customerViewAllDAO.searchCustomerByOrderId(12),"invliad input");
	}

}
