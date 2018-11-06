
package telcatalog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


//@author Aris Alexopoulos


public class MyCatalog {
	static String[] telCatalogNames = new String[20];
	static String[] telCatalogTels = new String[20];
	static Scanner scanner = new Scanner(System.in);
	
	// Default constructor
	public MyCatalog() {}
	

	public static void main(String[] args) {
		
		//  1. Read the file telcatalog.txt
		readFile("telcatalog.txt");
		
		System.out.println("Please select from bellow the action you want.");
		System.out.println("Please type \n1 for Search entry, \n2 for New entry, \n3 for Update an entry, \n4 for Delete an entry.");
		int userInput = scanner.nextInt();
		
		if (userInput == 1) {
			System.out.println("Please type name if you want to search the array based on name \nOr tel if you want to search the array based on telephone");
			String searchInput = scanner.next();
			if (searchInput.equalsIgnoreCase("name")) {
				//  4. Create the method SearchArray(String name) that searches the array based on name
				System.out.println(searchArray("william"));
			} else if (searchInput.equalsIgnoreCase("tel")) {
				//  5. Create the method SearchArray(String tel) that searches the array based on tel
				System.out.println(searchArray("23456"));
			}
		} else if (userInput == 2) {
			//  6. Create the method CreateEntry(String name, String tel) that inserts a new name,tel entry
			createEntry("MPAMPIS", "7654578098655");
		} else if (userInput == 3) {
			System.out.println("Please type name if you want to update the array based on name \nOr tel if you want to update the array based on telephone");
			String updateInput = scanner.next();
			if (updateInput.equalsIgnoreCase("name")) {
				//  7. Create the method UpdateEntry(String name) that returns true if the method did the update
				System.out.println(updateNameEntry("robert"));
			} else if (updateInput.equalsIgnoreCase("tel")) {
			//  8. Create the method UpdateEntry(String tel) that returns true if the method did the update
				System.out.println(updateTelEntry("56789"));
			}
		} else if (userInput == 4) {
			System.out.println("Please type name if you want to delete the entry based on name \nOr tel if you want to delete the array based on telephone");
			String deleteInput = scanner.next();
			if (deleteInput.equalsIgnoreCase("name")) {
				//  9. Create the method DeleteEntry(String name) that returns true if the method did the delete
				System.out.println(deleteTelEntry("james"));
			} else if (deleteInput.equalsIgnoreCase("tel")) {
				// 10. Create the method DeleteEntry(String tel) that returns true if the method did the delete
				System.out.println(deleteTelEntry("12345"));
			}
		} else {
			System.out.println("You've typed wrong number, please try again.");
		}
		
		printEntries();
		scanner.close();
	}

	public static void readFile(String fileName) {
		try {
            File f = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String readLine = "";
            int index = 0;
            while ((readLine = reader.readLine()) != null) {
                telCatalogNames[index] = readLine.substring(0, readLine.indexOf(","));
                telCatalogTels[index] = readLine.substring(readLine.indexOf(",") + 1, readLine.length());
                index++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	static void printEntries() {		
		for(int i = 0; i < telCatalogNames.length; i++) {
			System.out.println(telCatalogNames[i] + " - " + telCatalogTels[i]);
		}
	}
	
	public static String searchArray(String input) {
		int position = -1;
		String answer = null;
		for (int i = 0; i < telCatalogNames.length; i++) {
			if (input.equalsIgnoreCase(telCatalogNames[i]) || input.equalsIgnoreCase(telCatalogTels[i])) {
				position = i;
				answer = ("Founded at position " + position);
				break;
			}
			else {
				answer = "Sorry your search " + "\"" + input + "\"" + " does not match with any of the entries.";
			}
		}
		return (answer);
	}
	
	public static void createEntry(String name, String phone) {
		
		for (int i = 0; i < telCatalogNames.length; i++) {
			if (telCatalogNames[i] == null) {
				telCatalogNames[i] = name;
				telCatalogTels[i] = phone;
				break;
			}
		}
		
		try {
		String fileName = new String("telcatalog.txt");
		BufferedWriter writer;
		BufferedReader reader;
		FileWriter file = new FileWriter(fileName, true);
		reader = new BufferedReader(new FileReader(fileName));
		writer = new BufferedWriter(file);
		writer.newLine();
		writer.write(name);
		writer.write(",");
		writer.write(phone);
		
		reader.close();
		writer.close();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static boolean updateNameEntry(String input) {
		boolean bool = false;
		for (int i = 0; i < telCatalogNames.length; i++) {
			if (input.equalsIgnoreCase(telCatalogNames[i])){
				System.out.println("Please type the new name");
				String newName = scanner.next();
				telCatalogNames[i] = newName;
				bool = true;
			}
		}
		return bool;
	}
	
	public static boolean updateTelEntry(String input) {
		boolean bool = false;
		for (int i = 0; i < telCatalogTels.length; i++) {
			if (input.equalsIgnoreCase(telCatalogTels[i])){
				System.out.println("Please type the new phone");
				String newPhone = scanner.next();
				telCatalogTels[i] = newPhone;
				bool = true;
			}
		}
		return bool;
	}
	
	public static boolean deleteTelEntry(String input) {
		boolean answer = false;
		String temp;
		String temp2;
		for (int i = 0; i < telCatalogNames.length; i++) {
			if (input.equalsIgnoreCase(telCatalogNames[i]) || input.equalsIgnoreCase(telCatalogTels[i])) {
				telCatalogNames[i] = null;
				telCatalogTels[i] = null;
				temp = telCatalogNames[i];
				temp2 = telCatalogTels[i];
				
				for (int j = telCatalogNames.length - 1; j >= 0; j--) {
					if (!(telCatalogNames[j] == (null))) {
						telCatalogNames[i] = telCatalogNames[j];
						telCatalogTels[i] = telCatalogTels[j];
						telCatalogNames[j] = temp;
						telCatalogTels[j] = temp2;
						break;
					}
				}
				answer = true;
			}
		}
		return answer;
	}
}
