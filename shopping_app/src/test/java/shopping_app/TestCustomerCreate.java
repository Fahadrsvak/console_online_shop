package shopping_app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.services.CustomerCreateService;
import com.app.services.impl.CustomerCreateServiceImpl;

class TestCustomerCreate {

	@Test
	void testCustomerCreate() throws BusinessException {
		Customer customer=new Customer("Jaango","aji","jaango@gmail.com","Jaango@123");
		customer.setId(7);
		CustomerCreateService customerCreateservice = new CustomerCreateServiceImpl();
		assertEquals(customer, customerCreateservice.customerCreate(new Customer("Jaango","aji","jaango@gmail.com","Jaango@123")));
	}

}
