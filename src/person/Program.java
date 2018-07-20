package person;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Program {
	
	static Connection conn;
	static Scanner sc = new Scanner(System.in);
	static Random rng = new Random();
	static boolean exit = false;
	
	public static void main(String[] args) throws SQLException, IOException {
		
		String dbUrl = "jdbc:mysql://localhost:3306/person?useSSL=false";
		String username = "root";
		String password = "Silver";
		
		conn = DriverManager.getConnection(dbUrl, username, password);
		
		System.out.println("Connected? " + !conn.isClosed());
		
		createHuman();
		createCity();
		createCountry();
		
		fillCountyAndCity();
		
		for (int i;;){
			
			showMenu();
			
			i = sc.nextInt();
			switch(i) {
			case 1:
				addCountry();
				break;
			case 2:
				addCity();
				break;
			case 3:
				addHuman();
				break;
			case 4:
				showInformationHuman();
				break;
			case 5:
				showInformationHumanById();
				break;
			case 6:
				showInformationCityById();
				break;
			case 7:
				showInformationCountyById();
				break;
			case 8:
				showPersonByCityId();
				break;
			case 9:
				showCityByCountryId();
				break;
			case 10:
				randomHumans();
				break;
			case 11:
				updateHuman();
				break;
			case 12:
				exit = true;
				break;
			default:
				System.out.println("Error: There is no such option. Try again.");
			};
			if (exit == true) break;
		}
		
		conn.close();
	}
	
	public static void createHuman() throws SQLException {
		
		String dropQuery = "DROP TABLE IF EXISTS human;";
		String query = "CREATE TABLE human("
				+ "id INT KEY AUTO_INCREMENT,"
				+ "first_name VARCHAR(30),"
				+ "last_name VARCHAR(30),"
				+ "age INT,"
				+ "hobby VARCHAR(50),"
				+ "city_id INT"
				+ ");";
		Statement stmt = conn.createStatement();
		stmt.execute(dropQuery);
		stmt.execute(query);
		
		stmt.close();
		System.out.println("Table 'Human' created");
	}
	
	public static void createCity() throws SQLException {
		
		String dropQuery = "DROP TABLE IF EXISTS city;";
		String query = "CREATE TABLE city("
				+ "id INT KEY AUTO_INCREMENT,"
				+ "city_name VARCHAR(30),"
				+ "country_id INT"
				+ ");";
		
		Statement stmt = conn.createStatement();
		stmt.execute(dropQuery);
		stmt.execute(query);
				
		stmt.close();
		System.out.println("Table 'City' created");
	}
	
	public static void createCountry() throws SQLException {
		
		String dropQuery = "DROP TABLE IF EXISTS country;";
		String query = "CREATE TABLE country("
				+ "id INT KEY AUTO_INCREMENT,"
				+ "country_name VARCHAR(30)"
				+ ");";
		
		Statement stmt = conn.createStatement();
		stmt.execute(dropQuery);
		stmt.execute(query);
				
		stmt.close();
		System.out.println("Table 'Country' created");
	}
	
	public static void alterTable() throws SQLException {
		
		String foreignKeyQuery = "ALTER TABLE human ADD FOREIGN KEY (city_id) REFERENCES city(id);"
				+ "ALTER TABLE city ADD FOREIGN KEY (country_id) REFERENCES country(id);";
		Statement stmt = conn.createStatement();
		stmt.execute(foreignKeyQuery);
		
		stmt.close();
		System.out.println("Foreign keys created");
	}
	
	public static void showMenu() {
		
		System.out.println("Menu of 'Person' DataBase\n"
				+ "1. Add country to DB\n"
				+ "2. Add city to DB\n"
				+ "3. Add human to DB\n"
				+ "4. Show list of all humans in BD\n"
				+ "5. Show information about human\n"
				+ "6. Show information about city\n"
				+ "7. Show information about country\n"
				+ "8. Show information about all humans from city\n"
				+ "9. Show information about all cities in country\n"
				+ "10. Fill database with with random persons\n"
				+ "11. Update information about person\n"
				+ "12. Exit");
		
	}
	
	public static void addCountry() throws SQLException {
		
		System.out.println("Enter county name");
		fillTableCountry(sc.next());
		
	}
	
	public static void addCity() throws SQLException {
		
		System.out.println("Enter city name");
		String name = sc.next();
		System.out.println("Enter country id");
		fillTableCity(name, sc.nextInt());
	} 
	
	public static void addHuman() throws SQLException {
		
		System.out.println("Enter person fist name");
		String name = sc.next();
		System.out.println("Enter person last name");
		String lastName = sc.next();
		System.out.println("Enter person age");
		int age = sc.nextInt();
		System.out.println("Enter person hobby");
		String hobby = sc.next();
		fillTableHuman(name, lastName, age, hobby, sc.nextInt());
		
	}
	
	public static void fillTableCountry(String name) throws SQLException {
		
		String query = "INSERT INTO country(country_name) VALUES(?)";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, name);
		pstmt.executeUpdate();
		
		pstmt.close();
		System.out.println(name + " added to table 'Country'");
		
	}
	
	public static void fillTableCity(String name, int id) throws SQLException{
		
		String query = "INSERT INTO city (city_name, country_id) VALUES (?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, name);
		pstmt.setInt(2, id);
		pstmt.executeUpdate();
		
		pstmt.close();
		System.out.println(name +" added to table 'City'");
	}
	
	public static void fillTableHuman(String firstName, String lastName, int age, String hobby, int id) throws SQLException {
		
		String query = "INSERT INTO human (first_name, last_name, age, hobby, city_id) VALUES (?, ?, ?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, firstName);
		pstmt.setString(2, lastName);
		pstmt.setInt(3, age);
		pstmt.setString(4, hobby);
		pstmt.setInt(5, id);
		pstmt.executeUpdate();
		
		pstmt.close();
		System.out.println(firstName + " " + lastName + " added to table 'Human'");
	}
	
	public static void fillCountyAndCity() throws SQLException{
		for (int i = 0; i < RandomNames.getCountryNamesLength(); i++) {
			fillTableCountry(RandomNames.getCountryName(i));
		}
		for (int i = 0; i < RandomNames.getCityNamesLength(); i++) {
			fillTableCity(RandomNames.getCityName(i), i/2 + 1);
		}
	}
	
	public static void showInformationHuman() throws SQLException, IOException {
		
		System.out.println("Enter id:");
		String query = "SELECT h.id, h.first_name, h.last_name, h.age, h.hobby, ct.city_name, c.country_name FROM human h JOIN city ct ON h.city_id = ct.id "
				+ "JOIN country c ON ct.country_id = c.id;";
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet res = pstmt.executeQuery();
		while (res.next()) {
			System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3) + " " + res.getInt(4) + " " + res.getString(5) + " " + res.getString(6) + " " + res.getString(7));
		}
		pstmt.close();
		
		System.out.println("Enter to return to menu");
		int pause = System.in.read();
		
	}
	
	public static void showInformationHumanById() throws SQLException, IOException {
		
		System.out.println("Enter id:");
		String query = "SELECT h.id, h.first_name, h.last_name, h.age, h.hobby, ct.city_name, c.country_name FROM human h JOIN city ct ON h.city_id = ct.id "
				+ "JOIN country c ON ct.country_id = c.id WHERE h.id = " + sc.next() + ";";
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet res = pstmt.executeQuery();
		while (res.next()) {
			System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3) + " " + res.getInt(4) + " " + res.getString(5) + " " + res.getString(6) + " " + res.getString(7));
		}
		pstmt.close();
		
		System.out.println("Enter to return to menu");
		int pause = System.in.read();
		
	}
		
	public static void showInformationCityById() throws SQLException, IOException {
		
		System.out.println("Enter id:");
		String query = "SELECT ct.id, ct.city_name, c.country_name FROM city ct JOIN country c ON ct.country_id = c.id WHERE ct.id = " + sc.next() + ";";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet res = pstmt.executeQuery();
		while (res.next()) {
			System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3));
		}
		pstmt.close();
		
		System.out.println("Enter to return to menu");
		int pause = System.in.read();
		
	}
	
	public static void showInformationCountyById() throws SQLException, IOException {
		
		System.out.println("Enter id:");
		String query = "SELECT * FROM country WHERE id = " + sc.next() + ";";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet res = pstmt.executeQuery();
		while (res.next()) {
			System.out.println(res.getInt(1) + " " + res.getString(2));
		}
		pstmt.close();
		
		System.out.println("Enter to return to menu");
		int pause = System.in.read();
		
	}
	
	public static void showPersonByCityId() throws SQLException, IOException {
		
		System.out.println("Enter id:");
		String query = "SELECT h.id, h.first_name, h.last_name, h.age, h.hobby, ct.city_name, c.country_name FROM human h JOIN city ct ON h.city_id = ct.id "
				+ "JOIN country c ON ct.country_id = c.id WHERE ct.id = " + sc.next() + ";";
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet res = pstmt.executeQuery();
		while (res.next()) {
			System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3) + " " + res.getInt(4) + " " + res.getString(5) + " " + res.getString(6) + " " + res.getString(7));
		}
		pstmt.close();
		
		System.out.println("Enter to return to menu");
		int pause = System.in.read();
	}
	
	public static void showCityByCountryId() throws SQLException, IOException {
		
		System.out.println("Enter id:");
		String query = "SELECT ct.id, ct.city_name, c.country_name FROM city ct JOIN country c ON ct.country_id = c.id WHERE c.id = " + sc.next() + ";";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet res = pstmt.executeQuery();
		while (res.next()) {
			System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3));
		}
		pstmt.close();
		
		System.out.println("Enter to return to menu");
		int pause = System.in.read();
		
	}
	
	public static void randomHumans() throws SQLException {
		
		for (int i = 0; i <= 50; i++) {
			fillTableHuman(RandomNames.getHumanFirstName(rng.nextInt(RandomNames.getHumanFirstNamesLength())), RandomNames.getHumanLastName(rng.nextInt(RandomNames.getHumanLastNamesLength())),
					rng.nextInt(100), RandomNames.getHumanHobbies(rng.nextInt(RandomNames.getHumanHobbiesLength())), rng.nextInt(RandomNames.getCityNamesLength()));
		}
	}
	
	public static void updateHuman() throws SQLException {
		
		System.out.println("Enter person id");
		int id = sc.nextInt();
		boolean exitToMenu = false;
		for (int j; ;) {
			System.out.println("Choose what to change\n"
					+ "1. First name\n"
					+ "2. Last name\n"
					+ "3. Age\n"
					+ "4. Hobby\n"
					+ "5. City\n"
					+ "0. Return to main menu");
			
			switch (j = sc.nextInt()) {
			case 0: 
				exitToMenu = true;
				break;
			case 1:
				updateFirstName(id);
				break;
			case 2:
				updateLastName(id);
				break;
			case 3:
				updateAge(id);
				break;
			case 4:
				updateHobby(id);
				break;
			case 5:
				updateCity(id);
				break;
			default:
				System.out.println("Error: There is no such option. Try again");
			}
			
			if(exitToMenu == true) break;
			
		}
	}
	
	public static void updateFirstName(int id) throws SQLException {
		
		System.out.println("Enter first name");
		String query = "UPDATE human SET first_name = '" + sc.next() + "' WHERE id = " + id + ";";
		Statement stmt = conn.createStatement();
		stmt.execute(query);
		
		stmt.close();
		System.out.println("First name changed");
		
	}
	
	public static void updateLastName(int id) throws SQLException {
		
		System.out.println("Enter last name");
		String query = "UPDATE human SET last_name = '" + sc.next() + "' WHERE id = " + id + ";";
		Statement stmt = conn.createStatement();
		stmt.execute(query);
		
		stmt.close();
		System.out.println("Last name changed");
		
	}
	
	public static void updateAge(int id) throws SQLException {
		
		System.out.println("Enter age");
		String query = "UPDATE human SET age = '" + sc.next() + "' WHERE id = " + id + ";";
		Statement stmt = conn.createStatement();
		stmt.execute(query);
		
		stmt.close();
		System.out.println("Age changed");
		
	}
	
	public static void updateHobby(int id) throws SQLException {
		
		System.out.println("Enter hobby");
		String query = "UPDATE human SET hobby = '" + sc.next() + "' WHERE id = " + id + ";";
		Statement stmt = conn.createStatement();
		stmt.execute(query);
		
		stmt.close();
		System.out.println("Hobby changed");
		
	}
	
	public static void updateCity(int id) throws SQLException {
		
		System.out.println("Enter city id");
		String query = "UPDATE human SET city_id = '" + sc.next() + "' WHERE id = " + id + ";";
		Statement stmt = conn.createStatement();
		stmt.execute(query);
		
		stmt.close();
		System.out.println("City changed");
		
	}
}