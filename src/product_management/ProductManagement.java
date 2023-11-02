package product_management;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ProductManagement {
	static ArrayList<Product> al = new ArrayList<Product>();

	public static void productManagement() throws IOException {
		loadProductFromFile();
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
				File file = new File(
						"C:\\Users\\yash\\eclipse-workspace\\ShopManagement\\src\\product_management\\Products.csv");
				FileWriter fw = new FileWriter(file, false);
				BufferedWriter bw = new BufferedWriter(fw);

				for (Product product : al) {
					String productLine = String.join(",", product.getProductName(), product.getProductID(),
							product.getAvailableQuantity(), product.getProductCategory());
					bw.write(productLine + "\n");
				}
				bw.close();
				fw.close();
				file = null;
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
		al.add(productObject);
		System.out.println("Product Added Successfully!!");

	}

	public static void editProduct(String productName) {

		for (Product product : al) {

			if (product.productName.equalsIgnoreCase(productName)) {
				Scanner scanner = new Scanner(System.in);

				System.out.println("Editing Product : " + product.productName);

				System.out.println("New Product Name: ");
				product.productName = scanner.nextLine();

				System.out.println("New Product ID: ");
				product.productID = scanner.nextLine();

				System.out.println("New Quantity: ");
				product.availableQuantity = scanner.nextLine();

				System.out.println("New Product Price: ");
				product.productPrice = scanner.nextLine();

				System.out.println("New Product Category: ");
				product.productCategory = scanner.nextLine();

				System.out.println("Product Information Updated");
				return;

			}
		}
		System.out.println("Product Not Found!!!");
	}

	public static void deleteProduct(String productName) {
		Iterator<Product> itr = al.iterator();

		while (itr.hasNext()) {
			Product product = itr.next();
			if (product.productName.equalsIgnoreCase(productName)) {
				itr.remove();
				System.out.println("Product " + product.productName + " has been Deleted!!!");
				break;
			}
		}
		System.out.println("Product Not Found!!!");
	}

	public static void searchProduct(String productName) {
		for (Product product : al) {
			if (product.productName.equalsIgnoreCase(productName)) {
				System.out.println("Product Name: " + product.productName);
				System.out.println("Product ID: " + product.productID);
				System.out.println("Quantity: " + product.availableQuantity);
				System.out.println("Product Category: " + product.productCategory);
				return;
			}
		}
		System.out.println("Product not Found.");
	}

	public static void loadProductFromFile() throws IOException {
		File file = new File(
				"C:\\Users\\yash\\eclipse-workspace\\ShopManagement\\src\\product_management\\Products.csv");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = "";

		while ((line = br.readLine()) != null) {
			String[] productDetails = line.split(",");
			if (productDetails.length == 4) {
				Product product = new Product();
				product.setProductName(productDetails[0]);
				product.setProductID(productDetails[1]);
				product.setAvailableQuantity(productDetails[2]);
				product.setProductCategory(productDetails[3]);
				al.add(product);
			}

		}
		br.close();
		fr.close();
		file = null;
	}
	

}
