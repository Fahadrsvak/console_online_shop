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
import com.app.model.Order;
import com.app.model.Product;
import com.app.services.CartItemService;
import com.app.services.CustomerCreateService;
import com.app.services.CustomerLoginService;
import com.app.services.CustomerViewAllService;
import com.app.services.EmployeeLoginService;
import com.app.services.OrderService;
import com.app.services.ProductAddService;
import com.app.services.ProductDeleteService;
import com.app.services.ProductUpdateService;
import com.app.services.ProductViewService;
import com.app.services.impl.CartItemServiceImpl;
import com.app.services.impl.CustomerCreateServiceImpl;
import com.app.services.impl.CustomerLoginServiceImpl;
import com.app.services.impl.CustomerViewAllServiceImpl;
import com.app.services.impl.EmployeeLoginServiceImpl;
import com.app.services.impl.OrderServiceImpl;
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
		log.info("\n     Welcome to Online shoppy!\n");
		
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
				log.info("          Employee login\n"
						+ "        ------------------\n");
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
					log.info("\n----Welcome to ONLINE SHOPPY "+employee.getEmailId()+"----\n"
							+ "       ----------------------------------\n");
					
					log.info("1)add product");
					log.info("2)Update product details");
					log.info("3)Delete product");
					log.info("4)View all products");
					log.info("5)View all customers");
					log.info("6)View all orders");
					log.info("7)log out");
					log.info("8)goto ONLINE SHOP Home ");
					log.info("\nPlease enter your choice(1-8)");
					try {
						employeeChoice=Integer.parseInt(scanner.nextLine());
					} catch (NumberFormatException e) {
						log.info("Selected option is invalid");
						
					}
					switch(employeeChoice) {
						case 1:
							ProductAddService productAddService = new ProductAddServiceImpli();
							log.info("\n       Add new product\n"
									+ "      ------------------\n");
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
									log.info("---there are "+productList.size()+" different product available--- \n"
											+ "      ------printing the products-----\n"
											+ "      --------------------------------\n");
									
									log.info(" id      Products             price ");
									log.info("---------------------------------");
									productList.stream().forEach(x -> System.out.printf("%-5d%-6s %-12s %-9.1f  \n\n", x.getId(),
											x.getBrandName(),x.getName(),x.getPrice()) );
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
									log.info("---there are "+productList.size()+" different product available--- \n"
											+ "      ------printing the products-----\n"
											+ "      --------------------------------\n");
									
									log.info(" id      Products             price ");
									log.info("---------------------------------");
									productList.stream().forEach(x -> System.out.printf("%-5d%-6s %-12s %-9.1f  \n\n", x.getId(),
											x.getBrandName(),x.getName(),x.getPrice()) );
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
									log.info("---there are "+productList.size()+" different product available--- \n"
											+ "      ------printing the products-----\n"
											+ "      --------------------------------\n");
									
									log.info(" id      Products             price ");
									log.info("---------------------------------");
									productList.stream().forEach(x -> System.out.printf("%-5d%-6s %-12s %-9.1f  \n\n", x.getId(),
											x.getBrandName(),x.getName(),x.getPrice()) );
									log.info("\n");
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
							int searchChoice=0;
							do {
								log.info("1)Search customers using emailId");
								log.info("2)Search customers using id");
								log.info("3)Search customers using name");
								log.info("4)Search customers using orderId");
								log.info("5)Go Back");
								searchChoice=Integer.parseInt(scanner.nextLine());
								Customer customer =new Customer();
								switch(searchChoice) {
								
								case 1:
									log.info("enter the email id of customer");
									String tEmail=scanner.nextLine();
									try {
										customer =customerViewAllService.searchCustomerByEmail(tEmail);
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									}
									log.info(customer);
								break;
								case 2:
									log.info("enter the customer id");
									int id=Integer.parseInt(scanner.nextLine());
									try {
										customer =customerViewAllService.searchCustomerById(id);
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									}
									log.info(customer);
								break;
								case 3:
									log.info("enter the name ofcustomer");
									String tName=scanner.nextLine();
									try {
										customerList =customerViewAllService.searchCustomerByName(tName);
										for (Customer customer2 : customerList) {
											log.info(customer2);
										}
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									}
								break;
								case 4:
									log.info("enter the order id");
									int orderId=Integer.parseInt(scanner.nextLine());
									try {
										customer =customerViewAllService.searchCustomerByOrderId(orderId);
									} catch (BusinessException e) {
										log.warn(e.getMessage());
									}
									log.info(customer);
								break;
								case 5:
									log.info("going back....");
								break;
								}
							}
							while(searchChoice!=5);
						break;
						case 6:
							List<Order> orderList=new ArrayList<>();
							Order order=null;
							int orderChoice=0;
							OrderService orderService = new OrderServiceImpl();
							do {
							try {
								orderList=orderService.viewAllCustomerOrder();
								log.info("O.id      Pname         Qty   Tcost     Status   Cust.Id");
								orderList.stream().forEach(x -> System.out.printf("%-5d%-6s %-12s %-3d %-9.1f %-12s %-3d \n\n", x.getId(),
										x.getProduct().getBrandName(),x.getProduct().getName(),x.getpQuantity(),x.getOrderCost(),x.getStatus(),x.getCustomer().getId()) );
								log.info("1)to change status of recieved order");
								log.info("2)go back to customer home page");
								orderChoice=Integer.parseInt(scanner.nextLine());
								switch (orderChoice) {
								
								case 1:
									log.info("Enter the id of order to change status");
									int orderId=Integer.parseInt(scanner.nextLine());
									order=orderService.selectOrderEmployee(orderList, orderId);
									log.info(order);
									order=orderService.orderShipped(order);
									log.info("\norder status changed succesfully!\n");
									break;
								case 2:
									log.info("*Going Back to Customer home page*\n\n");
								}
							} catch (BusinessException e) {
								// TODO Auto-generated catch block
								log.warn(e.getMessage()); 
							}
							}
							while(orderChoice!=2);
							
						break;
						case 7:
							log.info("going back to employee login");
						break;	
						case 8:
							log.info("going back to mainlogin login");
							employeeChoice=7;
							employeelogin=0;
						break;
					}
					
					
				}
				while(employeeChoice!=7);
			}
			while(employeelogin!=0);
			
		break;	
		case 2:
			int accountChoice=0;
			do {
				log.info("\n        Customer login\n"
						+ "     -------------------\n");
				log.info("1)Already have account");
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
						log.info("\n      login to account\n"
								+ "    --------------------\n");
						try {
							log.info("Enter your registerd mail id");
							email=scanner.nextLine();
							log.info("Enter your password");
							pass=scanner.nextLine();
							customer =new Customer(email,pass);
							customer=customerLoginService.customerLogin(customer);
							
							if(customer.getId()>0) {
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
						CartItemService cartItemService=new CartItemServiceImpl();
						OrderService orderService = new OrderServiceImpl();
						CartItem cartItem=null;
						log.info("\n-------Welcome to ONLINE SHOP "+customer.getFirstName()+" "+customer.getLastName()+"--------\n"
								+ "        ---------------------------------" );
						do {
							int customerchoice=0;
							
							log.info("\n1)View all products");
							log.info("2)Add products to cart");
							log.info("3)open cart");
							log.info("4)view orders status");
							log.info("5)view profile");
							log.info("6)LOG OUT");
							log.info("7)goto ONLINE SHOPPY Home ");
							log.info("\nPlease enter your choice(1-4)");
							customerchoice=Integer.parseInt(scanner.nextLine());
							switch(customerchoice) {
								
							case 1:
								productViewService = new ProductViewServiceImpl();
								
								try {
									productList=productViewService.productView();
									if(productList!=null && productList.size()>0) {
										log.info("---there are "+productList.size()+" different product available--- \n"
												+ "      ------printing the products-----\n"
												+ "      --------------------------------\n");
										
										log.info(" id      Products         price ");
										log.info("---------------------------------");
										productList.stream().forEach(x -> System.out.printf("%-5d%-6s %-12s %-9.1f  \n\n", x.getId(),
												x.getBrandName(),x.getName(),x.getPrice()) );
										log.info("\n");
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
									log.info("enter the id of product to add inside cart");
									int productId=Integer.parseInt(scanner.nextLine());
									log.info("Enter the Qunatity of product");
									int productQ=Integer.parseInt(scanner.nextLine());
									Product pTemp=productViewService.selectProduct(productList, productId);
									cartItem=new CartItem(customer, pTemp, productQ);
									cartItem=cartItemService.addItem(cartItem);
									log.info(cartItem.getQuantity()+"Nos of "+ cartItem.getProduct().getName()+ "added to cart successfully!");
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
							break;
							case 3:
								List<CartItem> cartList= new ArrayList<>();
								
								log.info("\n       ---Items in cart---\n"
										+ "         -----------------");
								try {
									
									cartList =cartItemService.getAllCartItem(customer);
									log.info(" id      Item            price    Qty     Toalprice ");
									log.info("----------------------------------------------------");
									cartList.stream().forEach(x -> System.out.printf("%-5d%-6s %-12s %-8.1f %-3d %-9.1f \n\n", x.getProduct().getId(),
											x.getProduct().getBrandName(),x.getProduct().getName(),x.getProduct().getPrice(),
											x.getQuantity(),x.getLineTotal()) );
									Double totalCost=cartList.stream().mapToDouble(e->e.getLineTotal()).sum();
									log.info("----------------------------------------------------");
									log.info(" |	         Total cost :"+totalCost+"	        | ");
									log.info("-----------------------------------------------------");
									log.info("1)place order");
									log.info("2)go back to customer home");
									int cartChoice=Integer.parseInt(scanner.nextLine());
									switch(cartChoice) {
									case 1:
										if(orderService.orderPlaced(cartList)) {
											log.info("\nOrder placed successfully!!\n");
										}
									break;
									default:
										log.info("\ngoing back to customer home page\n");
						
									}
									
								} catch (BusinessException e) {
									log.warn(e.getMessage());
								}
								
							break;
							case 4:
								
								List<Order> orderList=new ArrayList<>();
								Order order=null;
								int orderChoice=0;
								do {
								try {
									orderList=orderService.viewAllOrder(customer);
									log.info("id      Pname        Qty   Tcost     Status");
									orderList.stream().forEach(x -> System.out.printf("%-3d%-6s %-12s-%-3d %-9.1f %-8s \n\n", x.getId(),
											x.getProduct().getBrandName(),x.getProduct().getName(),x.getpQuantity(),x.getOrderCost(),x.getStatus()) );
									log.info("1)to change status of recieved order");
									log.info("2)go back to customer home page");
									orderChoice=Integer.parseInt(scanner.nextLine());
									switch (orderChoice) {
									
									case 1:
										log.info("Enter the id of order to change status");
										int orderId=Integer.parseInt(scanner.nextLine());
										order=orderService.selectOrderCustomer(orderList, orderId);
										log.info(order);
										order=orderService.orderRecieved(order);
										log.info("\norder status changed succesfully!\n");
										break;
									case 2:
										log.info("Going Back to Customer home page");
									}
								} catch (BusinessException e) {
									// TODO Auto-generated catch block
									log.warn(e.getMessage()); 
								}
								}
								while(orderChoice!=2);
							case 5:
								log.info("\n          --Profile view-- \n"
										+ "            ----------------\n");
								log.info("Hello "+customer.getFirstName()+"!\n\n");
								log.info("first name: "+customer.getFirstName()+"\n");
								log.info("last name: "+customer.getLastName()+"\n");
								log.info("email Id: "+customer.getEmailId()+"\n");
								
								
							break;
							case 6:
								log.info("\ngoing Back...");
								customerlogIn--;
							break;
							case 7:
								log.info("\ngoing Back to ONLINE SHOPPY home...");
								customerlogIn--;
								accountLoginChoice=2;
								accountChoice=0;
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
						log.info("         ----------------------\n");
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
					break;
				case 3:
					
				}
			}
			while(accountChoice!=3);
		}
		
			

		}
		while(loginChoice!=3);
	}

}
