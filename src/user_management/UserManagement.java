package user_management;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import db_operations.DBUtils;

public class UserManagement {
	static ArrayList<User> al = new ArrayList<>();

	static {
		try {
			loadDataFromFileToArrayList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void userManagement() throws IOException {
//		loadDataFromFileToArrayList();
		Scanner scanner = new Scanner(System.in);
		boolean canIKeepRunningTheProgram = true;

		while (canIKeepRunningTheProgram == true) {

			System.out.println("****Welcome To User Management****");
			System.out.println("\n");
			System.out.println("What Do You Want To Do?");
			System.out.println("1.Add User");
			System.out.println("2.Edit User");
			System.out.println("3.Delete User");
			System.out.println("4.Search User");
			System.out.println("5.Quit");

			int optionSelectedByUser = scanner.nextInt();

			if (optionSelectedByUser == UserOptions.QUIT) {
				
				System.out.println("Program Closed!!!");
				canIKeepRunningTheProgram = false;

			} else if (optionSelectedByUser == UserOptions.ADD_USER) {

				addUser();
				System.out.println("\n");

			} else if (optionSelectedByUser == UserOptions.EDIT_USER) {

				System.out.println("Enter User Name To Edit:");
				scanner.nextLine();
				String en = scanner.nextLine();
				editUser(en);
				System.out.println("\n");

			} else if (optionSelectedByUser == UserOptions.SEARCH_USER) {

				System.out.println("Enter User Name to Search:");
				scanner.nextLine();
				String sn = scanner.nextLine();
				searchUser(sn);
				System.out.println("\n");

			} else if (optionSelectedByUser == UserOptions.DELETE_USER) {

				System.out.println("Enter User Name to Delete :");
				scanner.nextLine();
				String dn = scanner.nextLine();
				deleteUser(dn);
				System.out.println("\n");
			}
		}
	}

	public static void addUser() {

		Scanner scanner = new Scanner(System.in);
		User userObject = new User();

		System.out.print("User ID: ");
		userObject.userID = scanner.nextInt();

		System.out.print("User Name: ");
		userObject.userName = scanner.nextLine();

		System.out.print("Login Name: ");
		userObject.loginName = scanner.nextLine();

		System.out.print("Password: ");
		userObject.password = scanner.nextLine();

		System.out.print("Confirm Password: ");
		userObject.confirmPassword = scanner.nextLine();

		System.out.print("User Role: ");
		userObject.userRole = scanner.nextLine();

		String query = "INSERT INTO Users(userID , userName , loginName , pasword , userRole ) VALUES ( '"
				+ userObject.userID + "' , '" + userObject.userName + "' , '" + userObject.loginName + "' , '"
				+ userObject.password + "' , '" + userObject.userRole + "' );";
		DBUtils.executeQuery(query);
		System.out.println("User Added Successfully!!");

	}

	public static void editUser(String userName) {

		String Query = "select * from User where Username = '" + userName + "'";
		ResultSet rs = DBUtils.executeQueryGetResult(Query);
		try {

			if (rs.next()) {
				Scanner scanner = new Scanner(System.in);

				System.out.println("Editing User : " + userName);

				System.out.println("New User ID: ");
				int newUserID = scanner.nextInt();

				System.out.println("New User Name: ");
				String newUserName = scanner.nextLine();

				System.out.println("New Login Name: ");
				String newLoginName = scanner.nextLine();

				System.out.println("New Password: ");
				String newPassword = scanner.nextLine();

				System.out.println("New Confirm Password: ");
				String newConfirmPassword = scanner.nextLine();

				System.out.println("New User Role: ");
				String newUserRole = scanner.nextLine();

				String query = "UPDATE User SET Username = '" + newUserName + "', " + "LoginName = '" + newLoginName
						+ "', " + "Password = '" + newPassword + "', " + "ConfirmPassword = '" + newConfirmPassword
						+ "'," + "UserRole = '" + newUserRole + "'" + "WHERE Username = '" + newUserName + "'";

				DBUtils.executeQuery(query);
				System.out.println("User Information Updated");
			} else {
				System.out.println("User Not Found!!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static void deleteUser(String userName) {

		Iterator<User> itr = al.iterator();

		while (itr.hasNext()) {
			User user = itr.next();
			if (user.userName.equalsIgnoreCase(userName)) {
				itr.remove();
				System.out.println("User " + user.userName + " has been Deleted!!!");
				break;
			}
		}
		System.out.println("User Not Found!!!");
	}

	public static void searchUser(String userName) {

		for (User user : al) {
			if (user.userName.equalsIgnoreCase(userName)) {
				System.out.println("User ID: " + user.userID);
				System.out.println("User Name: " + user.userName);
				System.out.println("Login Name: " + user.loginName);
				System.out.println("Password: " + user.password);
				System.out.println("User Role: " + user.userRole);
				return;
			}
		}
		System.out.println("User not Found.");
	}

	public static void loadDataFromFileToArrayList() throws IOException {

		File file = new File("C:\\Users\\yash\\eclipse-workspace\\ShopManagement\\src\\user_management\\Users.csv");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = "";

		ResultSet rs = DBUtils.executeQueryGetResult("SELECT * FROM users");
		try {
			while (rs.next()) {
				User user = new User();

				user.userName = rs.getString(1);
				user.loginName = rs.getString(2);
				user.password = rs.getString(3);
				user.userRole = rs.getString(4);

				al.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * public static boolean validateUserAndPassword(String loginName1, String
	 * password1) {
	 * 
	 * Iterator<User> userIterator = al.iterator(); while (userIterator.hasNext()) {
	 * User user =userIterator.next(); if
	 * (user.loginName.equalsIgnoreCase(loginName1) &&
	 * user.password.equalsIgnoreCase(password1)) { return true; } } return false;
	 * 
	 * }
	 */
	public static boolean validateUserandPassword(String loginName, String password) {

		String query = "Select * from users where loginName='" + loginName + "' AND pasword = '" + password + "'";
		ResultSet rs = DBUtils.executeQueryGetResult(query);
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
