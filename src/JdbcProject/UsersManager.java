package JdbcProject;
import java.sql.*;
import java.util.*;

class Main{
	
	Scanner sc = new Scanner(System.in);
	String jdbcURL = "jdbc:mysql://localhost:3306/jdbcdb";
	String user = "root";
	String pass = "04Sep98";
	
	void insert() {				
		String username = sc.nextLine();
		String email = sc.nextLine();
		String fullname = sc.nextLine();
		String password = sc.nextLine();
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, user, pass);
			/*
			Type 1
			String query = "INSERT INTO users (username, email, fullname, password) VALUES('pradeepa', 'pradeepa@gmail.com', 'Pradeepa K', '123')";
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate(query);
			*/
			
			//Type 2
			String query = "INSERT INTO users (username, email, fullname, password) VALUES(?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, email);
			statement.setString(3, fullname);
			statement.setString(4, password);
			
			int rs = statement.executeUpdate();
			
			if(rs > 0) {
				System.out.println("New User successfully inserted.");
			}
			connection.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		System.out.println();
	}
	
	void retrive() {
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, user, pass);
			
			String query = "SELECT * FROM users";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				int userId = rs.getInt("user_id");
				String username = rs.getString("username");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				String password = rs.getString("password");
				
				System.out.println(userId + " : " + username + ", " + fullname + ", " + email + ", " + password);
			}			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		System.out.println();
	}
	
	void update() {
		try {
			String username = sc.nextLine();
			String email = sc.nextLine();
			String fullname = sc.nextLine();
			String password = sc.nextLine();
			
			Connection connection = DriverManager.getConnection(jdbcURL, user, pass);
			/*
			Type 1
			String query = "UPDATE users SET username='aaa' where password='444'";
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate(query);
			*/
			
			//Type 2
			String query = "UPDATE users SET username=?, email=?, fullname=?, password=? where username='Nethra'";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, email);
			statement.setString(3, fullname);
			statement.setString(4, password);
			
			int rs = statement.executeUpdate();
			if(rs > 0) {
				System.out.println("Update successfull!!.");
			}
			connection.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		System.out.println();
	}
	
	void delete() {
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, user, pass);
			String username = sc.nextLine();
			/*
			Type 1
			String query = "DELETE FROM users WHERE username='aaa'";
			Statement statement = connection.createStatement();
			int rs = statement.executeUpdate(query);
			*/
			//Type 2
			String query = "DELETE FROM users WHERE username=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			
			int rs = statement.executeUpdate();
			
			if(rs > 0) {
				System.out.println("Deleted successfully.");
			}
			connection.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		System.out.println();
	}
}

public class UsersManager {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Main m = new Main();
		int choice=0;	
		do {
			System.out.println("1.CREATE\n2.RETRIVE\n3.UPDATE\n4.DELETE\n5.EXIT\n");
			System.out.println("Enter your choice : ");
			choice = sc.nextInt();
			switch(choice) {
				case 1:
					m.insert();
					break;
				case 2:
					m.retrive();
					break;
				case 3:
					m.update();
					break;
				case 4:
					m.delete();
					break;
				case 5:
					System.out.println("Exit successfull!!!");
					break;
				default:
					System.out.println("Enter a valid option between 1 to 5");				
			}
		}
		while(choice!=5);		
	}
}
