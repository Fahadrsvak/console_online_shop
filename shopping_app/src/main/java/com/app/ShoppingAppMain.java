package com.app;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;

import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.model.Employee;
import com.app.model.Product;
import com.app.services.CustomerCreateService;
import com.app.services.CustomerLoginService;
import com.app.services.EmployeeLoginService;
import com.app.services.ProductAddService;
import com.app.services.impl.CustomerCreateServiceImpl;
import com.app.services.impl.CustomerLoginServiceImpl;
import com.app.services.impl.EmployeeLoginServiceImpl;
import com.app.services.impl.ProductAddServiceImpli;

public class ShoppingAppMain {
	
	
	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		log.info("\n*******************************************");
		log.info("\n  ----------  ONLINE SHOPPY  -----------");
		log.info("\n*******************************************");
		log.info("\n Welcome to Online shoppy!");
		int loginChoice=0;
		do {
		log.info("1)Login as an employee");
		log.info("2)Login as an customer");
		log.info("3)EXIT");
		log.info("\nPlease enter your choice(1-3)");
		try {
			loginChoice=Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			log.info("Selected option is invalid");
			
		}
		switch(loginChoice) {
		case 1:int employeelogin=1;
			Employee employee = null ;
			do {
				EmployeeLoginService employeeloginService =new EmployeeLoginServiceImpl();
				log.info("          Employee login");
				String email;
				String pass;
				try {
					log.info("Enter your registerd mail id");
					email=scanner.nextLine();
					log.info("Enter your password");
					pass=scanner.nextLine();
					employee =new Employee(email, pass);
					employee =employeeloginService.employeeLogin(employee);
					if(employee!=null) {
	//					log.info("Employee logged in with below details");
	//					log.info(employee);
						employeelogin++;
					}
					else {
						log.info("Employee login mail and password not correct");
					}
				}
				catch (BusinessException e) {
					log.info(e.getMessage());
				}
				int employeeChoice=0;
				do {
					log.info("Welcome to ONLINE SHOP "+employee.getEmailId());
					log.info("1)add product");
					log.info("\nPlease enter your choice(1-3)");
					try {
						employeeChoice=Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
						log.info("Selected option is invalid");
						
					}
					switch(employeeChoice) {
						case 1:
							ProductAddService productAddService = new ProductAddServiceImpli();

							String name;
							double price;
							String brandName;
							try {
								log.info("Enter name of the product");
								name=scanner.nextLine();
								log.info("Enter price");
								price=Double.parseDouble(scanner.nextLine());
								log.info("Enter the brand name of product");
								brandName=scanner.nextLine();
								Product product = new Product(name, price, brandName);
								product = productAddService.productAdd(product);
								
								log.info("product added with details below");
								log.info(product);
								
								}catch (Exception e) {
									log.info(e.getMessage());
								}
							
						break;
						
						case 2:
							
					}
					
					
				}
				while(employeelogin!=1);
			}
			while(employeelogin!=0);
			
			
		case 2:
			int accountChoice=0;
			do {
				log.info("          Customer login");
				log.info("\n1)Already have account");
				log.info("2)New user-Create account");
				log.info("3)Go Back");
				log.info("\nPlease select an option");
				try {
					accountChoice=Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException e) {
					log.info("Selected option is invalid");
					
				}
				switch(accountChoice) {
				case 1:
					int accountLoginChoice=0;
					int customerlogIn=0;
					do {
						CustomerLoginService customerLoginService = new CustomerLoginServiceImpl();
						String email;
						String pass;
						log.info("          login to account");
						try {
						log.info("Enter your registerd mail id");
						email=scanner.nextLine();
						log.info("Enter your password");
						pass=scanner.nextLine();
						Customer customer =new Customer(email,pass);
						customer=customerLoginService.customerLogin(customer);
						
						if(customer.getId()>0) {
						log.info("User logined with below details");
						log.info(customer);
						customerlogIn=1;
						}
						else {
							log.info("email id and password not matching");
						}
						}
						catch (BusinessException e) {
							log.info(e.getMessage());
						}
						do {
							
						}
						while(customerlogIn!=2);
					}
					while(accountLoginChoice!=2);
					break;
				case 2:
					do {
						CustomerCreateService customerCreateService=new CustomerCreateServiceImpl();
						String fName;
						String lName;
						String email;
						String pass;
						String pass1;
						log.info("          Create a new account");
						try {
						log.info("Enter your first name");
						fName=scanner.nextLine();
						log.info("Enter your last name");
						lName=scanner.nextLine();
						log.info("Enter your mail id");
						email=scanner.nextLine();
						log.info("Enter your password");
						pass=scanner.nextLine();
	//					log.info("Enter your password again to confirm");
	//					pass1=scanner.nextLine();
						Customer customer =new Customer(fName,lName,email,pass);
						customer =customerCreateService.customerCreate(customer);
						log.info("User created with details below");
						accountChoice=0;
						log.info(customer);
						
						}catch (Exception e) {
							log.info(e.getMessage());
						}
					}while(accountChoice!=0);
				case 3:
					break;
				}
			}
			while(accountChoice!=3);
		}
		
			

		}
		while(loginChoice!=3);
	}

}
