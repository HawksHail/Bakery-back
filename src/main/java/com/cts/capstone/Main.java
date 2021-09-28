package com.cts.capstone;

import com.cts.capstone.model.*;
import com.cts.capstone.service.DbService;
import com.cts.capstone.service.DbServiceDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				SpringApplication.run(Main.class, args);

		DbServiceDao serviceDao = context.getBean(DbServiceDao.class);
		testDB(serviceDao);
	}

	private static void testDB(DbService service) {
		Category category = new Category(12345, "test category", "test description");

		System.out.println("\nInsert: " + service.createCategory(category));
		System.out.println(service.getCategory(category.getCategoryId()));

		category.setCategoryName("new test name");
		category.setDescription("new test description");
		System.out.println("Update: " + service.updateCategory(category));
		System.out.println(service.getCategory(category.getCategoryId()));


		Customer customer = new Customer("test9", "test company", "test contact", "test street", "test city", "test state");

		System.out.println("\nInsert: " + service.createCustomer(customer));
		System.out.println(service.getCustomer(customer.getCustomerId()));

		customer.setCompanyName("new test company");
		customer.setContactName("new test contact");
		customer.setStreet("new test street");
		customer.setCity("new test ity");
		customer.setState("new test state");
		System.out.println("Update: " + service.updateCustomer(customer));
		System.out.println(service.getCustomer(customer.getCustomerId()));


		Supplier supplier = new Supplier(12345, "test company", "test contact");

		System.out.println("\nInsert: " + service.createSupplier(supplier));
		System.out.println(service.getSupplier(supplier.getSupplierId()));

		supplier.setCompanyName("new name");
		supplier.setContactName("new contact");
		System.out.println("Update: " + service.updateSupplier(supplier));
		System.out.println(service.getSupplier(supplier.getSupplierId()));


		Product product = new Product(12345, "test product", 12345, 12345, BigDecimal.TEN);

		System.out.println("\nInsert: " + service.createProduct(product));
		System.out.println(service.getProduct(product.getProductId()));

		product.setProductName("new name");
		product.setSupplierId(2);
		product.setCategoryId(2);
		product.setUnitPrice(BigDecimal.ONE);
		System.out.println("Update: " + service.updateProduct(product));
		System.out.println(service.getProduct(product.getProductId()));

		Order order = new Order(12345, "test9", LocalDate.now());

		System.out.println("\nInsert: " + service.createOrder(order));
		System.out.println(service.getOrder(order.getOrderId()));

		order.setOrderDate(LocalDate.now().minusDays(4));
		System.out.println("Update: " + service.updateOrder(order));
		System.out.println(service.getOrder(order.getOrderId()));


		OrderDetails orderDetails = new OrderDetails(12345, 12345, 2);

		System.out.println("\nInsert: " + service.createOrderDetails(orderDetails));
		System.out.println(service.getOrderDetails(orderDetails.getOrderId()));

		orderDetails.setQuantity(90);
		System.out.println("Update: " + service.updateOrderDetails(orderDetails));
		System.out.println(service.getOrderDetails(orderDetails.getOrderId()));


		System.out.println("\n\nDelete order details: " + service.deleteOrderDetails(orderDetails.getOrderId()));

		System.out.println("Delete order: " + service.deleteOrder(order.getOrderId()));

		System.out.println("Delete customer: " + service.deleteCustomer(customer.getCustomerId()));

		System.out.println("Delete product: " + service.deleteProduct(product.getProductId()));

		System.out.println("Delete category: " + service.deleteCategory(category.getCategoryId()));

		System.out.println("Delete supplier: " + service.deleteSupplier(supplier.getSupplierId()));
	}
}
