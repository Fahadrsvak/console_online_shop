package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;
import com.app.exception.BusinessException;
import com.app.model.CartItem;
import com.app.model.Customer;
import com.app.model.Employee;
import com.app.model.Product;
import com.app.services.CartItemService;
import com.app.services.CustomerCreateService;
import com.app.services.CustomerLoginService;
import com.app.services.CustomerViewAllService;
import com.app.services.EmployeeLoginService;
import com.app.services.ProductAddService;
import com.app.services.ProductDeleteService;
import com.app.services.ProductUpdateService;
import com.app.services.ProductViewService;
import com.app.services.impl.CartItemServiceImpl;
import com.app.services.impl.CustomerCreateServiceImpl;
import com.app.services.impl.CustomerLoginServiceImpl;
import com.app.services.impl.CustomerViewAllServiceImpl;
import com.app.services.impl.EmployeeLoginServiceImpl;
import com.app.services.impl.ProductAddServiceImpli;
import com.app.services.impl.ProductDeleteServiceImpl;
import com.app.services.impl.ProductUpdateServiceImpl;
import com.app.services.impl.ProductViewServiceImpl;

public class ShoppingAppMain {
	
	
	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int loginChoice=0;
		do {
		log.info("\n*******************************************");
		log.info("\n  ----------  ONLINE SHOPPY  -----------");
		log.info("\n*******************************************");
		log.info("\n Welcome to Online shoppy!");
		
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
						continue;
					}
				}
				catch (BusinessException e) {
					log.info(e.getMessage());
					continue;
				}
				int employeeChoice=0;
				do {
					log.info("Welcome to ONLINE SHOP "+employee.getEmailId());
					
					log.info("1)add product");
					log.info("2)Update product details");
					log.info("3)Delete product");
					log.info("4)View all products");
					log.info("5)View all customers");
					log.info("6)log out");
					log.info("7)goto ONLINE SHOP Home ");
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
							ProductUpdateService productUpdateService = new ProductUpdateServiceImpl();
							ProductViewService productViewService = new ProductViewServiceImpl();
							List<Product> productList= null;
							try {
								productList=productViewService.productView();
								if(productList!=null && productList.size()>0) {
									log.info("Total there are "+productList.size()+" number of products \nprinting the products");
									for(Product product:productList) {
										log.info(product);
										
									}
								}
							} catch (BusinessException e) {
								log.warn(e.getMessage());
							}
							try {
							log.info("Enter the Product id to edit details ");
							int productId=Integer.parseInt(scanner.nextLine());
							Product pTemp=productViewService.selectProduct(productList, productId);
							log.info("Selected product is ");
							log.info(pTemp);
//						
							log.info("Enter name of the product");
							name=scanner.nextLine();
							log.info("Enter price");
							price=Double.parseDouble(scanner.nextLine());
							log.info("Enter the brand name of product");
							brandName=scanner.nextLine();
							Product product = new Product(pTemp.getId(),name, price, brandName);
							product= productUpdateService.productUpdate(product);
							log.info("Producted Updated successfully!");
							log.info(product);
							} catch (BusinessException e) {
								log.info(e.getMessage());
							}
						break;	
						case 3:
							ProductDeleteService productDeleteService = new ProductDeleteServiceImpl();
							productViewService = new ProductViewServiceImpl();
							productList= null;
							try {
								productList=productViewService.productView();
								if(productList!=null && productList.size()>0) {
									log.info("Total there are "+productList.size()+" number of products \nprinting the products");
									for(Product product:productList) {
										log.info(product);
										
									}
								}
							} catch (BusinessException e) {
								log.warn(e.getMessage());
							}
							try {
							log.info("Enter the Id of the product to delete");
							int productId=Integer.parseInt(scanner.nextLine());
							log.info("Deletng the  product below");
							Product pTemp=productViewService.selectProduct(productList, productId);
							
							if(productDeleteService.productDelete(pTemp)) {
								log.info("Product Deleted Successfully");
							}
							else {
								log.info("Product Deletion failed database id mismatch");
							}
							} catch ( BusinessException e) {
								log.info(e.getMessage());
							}
						break;
						case 4:
							productViewService = new ProductViewServiceImpl();
							productList= null;
							try {
								productList=productViewService.productView();
								if(productList!=null && productList.size()>0) {
									log.info("Total there are "+productList.size()+" number of products \nprinting the products");
									for(Product product:productList) {
										log.info(product);
										
									}
								}
							} catch (BusinessException e) {
								log.warn(e.getMessage());
							}
						break;
						case 5:
							CustomerViewAllService customerViewAllService = new CustomerViewAllServiceImpl();
							List<Customer> customerList = new ArrayList<>();
							try {
								customerList=customerViewAllService.viewAllCustomer();
								if(customerList!=null && customerList.size()>0) {
									log.info("Total there are "+customerList.size()+" number of  customers using ONLINE SHOPPY");
									for(Customer customer:customerList) {
										log.info(customer.formatView());
										
									}
								}
							} catch (BusinessException e) {
								log.warn(e.getMessage());
							}
						break;
						case 6:
							log.info("going back to employee login");
						break;	
						case 7:
							log.info("going back to mainlogin login");
							employeeChoice=6;
							employeelogin=0;
						break;
					}
					
					
				}
				while(employeeChoice!=6);
			}
			while(employeelogin!=0);
			
		break;	
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
					Customer customer=null;
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
							customer =new Customer(email,pass);
							customer=customerLoginService.customerLogin(customer);
							
							if(customer.getId()>0) {
							log.info("User logined with below details");
							log.info(customer);
							customerlogIn++;
							}
							else {
								log.info("wrong email id and password. try again!");
								continue;
							}
						}
						catch (BusinessException e) {
							log.info(e.getMessage());
							continue;
						}

						List<Product> productList=null;
						ProductViewService productViewService =null;
						CartItemService cartItemService =null;
						CartItem cartItem=null;
						do {
							int customerchoice=0;
							log.info("Welcome to ONLINE SHOP "+customer.getFirstName()+" "+customer.getLastName() );
							log.info("1)View all products");
							log.info("2)Add products to cart");
							log.info("\nPlease enter your choice(1-3)");
							customerchoice=Integer.parseInt(scanner.nextLine());
							switch(customerchoice) {
								
							case 1:
								productViewService = new ProductViewServiceImpl();
								
								try {
									productList=productViewService.productView();
									if(productList!=null && productList.size()>0) {
										log.info("Total there are "+productList.size()+" number of products \nprinting the products");
										for(Product product:productList) {
											log.info(product);
											
										}
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
							break;
							case 2:
								cartItemService=new CartItemServiceImpl();
								productViewService = new ProductViewServiceImpl();
								
								try {
									productList=productViewService.productView();
									if(productList!=null && productList.size()>0) {
										log.info("Total there are "+productList.size()+" number of products \nprinting the products");
										for(Product product:productList) {
											log.info(product);
											
										}
									}
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								log.info("enter the id of product to add inside cart");
								int productId=Integer.parseInt(scanner.nextLine());
								log.info("Enter the Qunatity of product");
								int productQ=Integer.parseInt(scanner.nextLine());
								
//								log.info("Deletng the  product below");
//								Product pTemp=null;
//								for (Product product : productList) {
//									if(productId==)
//								}
							break;
						
							}
						}
						while(customerlogIn!=0);
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
					//	String pass1;
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
						customer =new Customer(fName,lName,email,pass);
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
