package user_management;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import db_operations.DBUtils;

public class UserManagement {

	public static void userManagement() throws IOException {

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

				String query = "UPDATE Users SET userID= '" + newUserID + "', Username = '" + newUserName + "', "
						+ "LoginName = '" + newLoginName + "', " + "Password = '" + newPassword + "', "
						+ "ConfirmPassword = '" + newConfirmPassword + "'," + "UserRole = '" + newUserRole + "'"
						+ "WHERE Username = '" + newUserName + "'";

				DBUtils.executeQuery(query);
				System.out.println("User Information Updated");
				return;
			}
		} catch (SQLException e) {
			System.out.println("User Not Found");
		}
		System.out.println("User Not Found");
	}

	public static void deleteUser(String userName) {
		String query = "Delete from Users where userName = '" + userName + "'";
		DBUtils.executeQuery(query);
		System.out.println("User Deleted Successfully");

	}

	public static void searchUser(String userName) {
		String query = "Select * from Users where userName = '" + userName + "' ";

		ResultSet rs = DBUtils.executeQueryGetResult(query);
		try {
			while (rs.next()) {
				if (rs.getString(userName).equalsIgnoreCase(userName)) {
					System.out.println("User Name: " + rs.getString("userName"));
					System.out.println("Login Name: " + rs.getString("loginName"));
					System.out.println("Password: " + rs.getString("password"));
					System.out.println("User Role: " + rs.getString("userRole"));
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("User Not Found");
		}
	}

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
