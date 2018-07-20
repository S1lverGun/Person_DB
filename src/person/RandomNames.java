package person;

public class RandomNames {

	private static String[] countryNames = {"Ukraine", "Russia", "Poland", "Japan", "United Kindom", "German", "France", "USA", "Italia", "Canada"};
	
	private static String[] cityNames = {"Lviv", "Kiev", "Moscov", "St.Peterburg", "Warsaw", "Kracow", "Tokio", "Kioto", "London", "York", "Berlin", "Hamburg", "Paris", "Lion", "New York", "Washington", "Rome", "Milan", "Ottawa", "Toronto"};
	
	private static String [] humanFirstNames = {"Jacinto", "Lemuel", "Ramona", "Carmella", "Karl", "Tiana", "Lashaun", "Stella", "Rossie", "Julius", "Regena", "Carole", "Delila", "Shaun", "Florence", "Gerardo", "Justin", 
		"Werner", "Clinton", "Lee", "Gil", "Sierra", "Philip", "Mack", "Jayne", "Eric", "Imogene", "Loris", "Kylie", "Eugenio", "Keiko", "Tawny", "Sally", "Claribel", "Rayna", "Jenise", "Karine", "Ami", "Vergie",
		"Bobbie", "Blaine", "Shakira", "Beverlee", "Loralee", "Julene", "Garnett", "Giselle", "Ashanti", "Twana", "Treasa"};
	
	private static String[] humanLastNames = {"Steffens", "Middlebrook", "Dufresne", "Dodge", "Colwell", "Hagwood", "Lamore", "Nilles", "Leming", "Labrum", "Parmenter", "Hilgendorf", "Schreiner", "Uyehara", "Szewczyk",
		"Mattix", "Bundy", "Hall", "Mcdermott", "Archuleta", "Westberry", "Rogalski", "Lininger", "Knepper", "Sereno", "Seabrook", "Feldstein", "Palacio", "Nesby", "Hardnett", "Titus", "Klima", "Printup", "Goggin", "Disher",
		"King", "Agbayani", "Effinger", "Lindstrom", "Luken", "Merkel", "Knipp", "Jaynes", "Cowens", "Polston", "Macias", "Wnuk", "Chupp", "Kessler", "Thieme"};
	
	private static String[] humanHobbies = {"Reading", "Watching TV", "Family Time", "Going to Movies", "Fishing", "Computer", "Gardening", "Renting Movies", "Walking", "Exercise", "Listening to Music", "Entertaining", "Hunting",
		"Team Sports", "Shopping", "Traveling", "Sleeping", "Socializing", "Sewing", "Golf", "Church Activities", "Relaxing", "Playing Music", "Housework", "Crafts", "Watching Sports", "Bicycling", "Playing Cards", "Hiking",
		"Cooking", "Eating Out", "Dating Online", "Swimming", "Camping", "Skiing", "Working on Cars", "Writing", "Boating", "Motorcycling", "Animal Care", "Bowling", "Painting", "Running", "Dancing", "Horseback Riding", "Tennis",
		"Theater", "Billiards", "Beach", "Volunteer Work"};
	
	public static String getCountryName(int i) {
		return countryNames[i];
	}
	
	public static String getCityName(int i) {
		return cityNames[i];
	}
	
	public static String getHumanFirstName(int i) {
		return humanFirstNames[i];
	}
	
	public static String getHumanLastName(int i) {
		return humanLastNames[i];
	}
	
	public static String getHumanHobbies(int i) {
		return humanHobbies[i];
	}
	
	public static int getCountryNamesLength() {
		return countryNames.length;
	}
	
	public static int getCityNamesLength() {
		return cityNames.length;
	}
	
	public static int getHumanFirstNamesLength() {
		return humanFirstNames.length;
	}
	
	public static int getHumanLastNamesLength() {
		return humanLastNames.length;
	}
	
	public static int getHumanHobbiesLength() {
		return humanHobbies.length;
	}
}
