package product_management;

import java.io.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import db_operations.DBUtils;

public class ProductManagement {

	public static void productManagement() throws IOException {
		Scanner scanner = new Scanner(System.in);
		boolean canIKeepRunningTheProgram = true;
		while (canIKeepRunningTheProgram == true) {
			System.out.print("*****WELCOME TO PRODUCT MANAGEMENT ****");
			System.out.print("\n");
			System.out.println("What You Want To Do?");
			System.out.println("1.Add Product");
			System.out.println("2.Edit Product");
			System.out.println("3.Delete Product");
			System.out.println("4.Search Product");
			System.out.println("5.Quit");

			int productChoice = scanner.nextInt();

			if (productChoice == ProductOptions.QUIT) {
				System.out.println("Program Closed!!!!!");
				canIKeepRunningTheProgram = false;
			} else if (productChoice == ProductOptions.ADD_PRODUCT) {
				addProduct();
				System.out.println("\n");
			} else if (productChoice == ProductOptions.EDIT_PRODUCT) {
				System.out.println("Enter ProductName to Edit: ");
				scanner.nextLine();
				String editName = scanner.nextLine();
				editProduct(editName);
				System.out.println("\n");
			} else if (productChoice == ProductOptions.DELETE_PRODUCT) {
				System.out.println("Enter Productname to Delete: ");
				scanner.nextLine();
				String deleteName = scanner.nextLine();
				deleteProduct(deleteName);
				System.out.println("\n");
			} else if (productChoice == ProductOptions.SEARCH_PRODUCT) {
				System.out.println("Enter Product Name to Search: ");
				scanner.nextLine();
				String sn = scanner.nextLine();
				searchProduct(sn);
				System.out.println("\n");
			}

		}
	}

	public static void addProduct() {
		Scanner scanner = new Scanner(System.in);
		Product productObject = new Product();

		System.out.print("Product Name: ");
		productObject.productName = scanner.nextLine();

		System.out.print("Product ID: ");
		productObject.productID = scanner.nextLine();

		System.out.print("Quantity: ");
		productObject.availableQuantity = scanner.nextLine();

		System.out.print("Product Price: ");
		productObject.productPrice = scanner.nextLine();

		System.out.print("Product Category: ");
		productObject.productCategory = scanner.nextLine();

		System.out.println("Product Added Successfully!!");
		String query = "INSERT INTO product(productID , productName , availableQuantity , productPrice , productCategory ) VALUES ( '"
				+ productObject.productID + "' , '" + productObject.productName + "' , '"
				+ productObject.availableQuantity + "' , '" + productObject.productPrice + "' , '"
				+ productObject.productCategory + "' );";
		DBUtils.executeQuery(query);
		System.out.println("Product Added Successfully!!");
	}

	public static void editProduct(String productName) {
		Product product = new Product();

		if (product.productName.equalsIgnoreCase(productName)) {
			Scanner scanner = new Scanner(System.in);

			System.out.println("Editing Product : " + product.productName);

			System.out.println("New Product Name: ");
			String newProductName = scanner.nextLine();

			System.out.println("New Product ID: ");
			String newProductID = scanner.nextLine();

			System.out.println("New Quantity: ");
			String newAvailableQuantity = scanner.nextLine();

			System.out.println("New Product Price: ");
			String newProductPrice = scanner.nextLine();

			System.out.println("New Product Category: ");
			String newProductCategory = scanner.nextLine();
			String query = "Update into product Set productID='" + newProductID + "',productName='" + newProductName
					+ "',availableQuantity = '" + newAvailableQuantity + "',productPrice='" + newProductPrice
					+ "',productCategory='" + newProductCategory + "'";
			DBUtils.executeQuery(query);
			System.out.println("Product Information Updated");
			return;
		}
		System.out.println("Product Not Found!!!");
	}

	public static void deleteProduct(String productName) {
		String query = "DElETE FROM product WHERE productName='" + productName + "'";
		DBUtils.executeQuery(query);
		System.out.println("User Deleted Successfully!!!");
		System.out.println("\n");

	}

	public static void searchProduct(String productName) {
		String query = "Select * from product where productName = '" + productName + "' ";
		ResultSet rs = DBUtils.executeQueryGetResult(query);
		try {
			while (rs.next()) {
				if (rs.getString("productName").equalsIgnoreCase(productName)) {
					System.out.println("Product Name: " + rs.getString("productName"));
					System.out.println("Product ID: " + rs.getString("productID"));
					System.out.println("Quantity: " + rs.getString("availableQuantity"));
					System.out.println("Product Category: " + rs.getString("productCategory"));
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("Product not Found.");
		}
	}
}
