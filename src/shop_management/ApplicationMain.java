package shop_management;

import java.io.IOException;
import java.util.Scanner;

import product_management.ProductManagement;
import user_management.UserManagement;

public class ApplicationMain {

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);

		System.out.println("***Welcome To Shop Management System****");
		System.out.println("\n");

		System.out.println("Enter Login Name: ");
		String loginName = scanner.nextLine();
		System.out.println("Enter Password: ");
		String password = scanner.nextLine();

		if (!UserManagement.validateUserandPassword(loginName, password)) {
			System.out.println("Login Failed !!! Closing Application!");
			return;
		}

		boolean canIKeepRunningTheProgram = true;

		while (canIKeepRunningTheProgram == true) {

			System.out.println("What You Want To Do?");

			System.out.println("1. User Management");
			System.out.println("2. Product Management");
			System.out.println("3. Quit");

			int userChoice = scanner.nextInt();

			if (userChoice == 1) {
				UserManagement.userManagement();
			} else if (userChoice == 2) {
				ProductManagement.productManagement();
			} else if (userChoice == 3) {
				System.out.println("Thank You For Choosing Our Service!!!!");
				break;
			}
		}
	}
}
