/*
 * Shovon Hossain Homework 4, Bankdata Bhola, 10/16/14
 * 
 *
 * DOCUMENTATION The purpose of this program is to allow users to maintain a
 * bank record for multiple people containing first and last names, phone
 * number, and account balance. The user will be able to create records and
 * maintain them by changing the first or last name, phone number, and
 * depositing or withdrawing money from the account. Each record will be
 * automatically ordered alphabetically by last name into a LinkedList
 * called recordList. The user will be able to create records as neccesary.
 * The newest record will be automatically set as the current record and the
 * current record is the only record the user may modify or delete. The
 * current record can be changed at anytime. This program contains one class
 * named Bankdata. This class is used to create bankdata objects which store
 * the users first and last name, phone number, and balance. This program
 * contains 13 methods: displayComandList, executeCommand, displayRecords,
 * deleteRecord, changeFirstName, changeLastName, addNewRecord
 * newReocrdIndex, changePhone, addDeposit, withdrawMoney,
 * changeCurrentRecord, printCurrentRecord. Each of these methods, excluding
 * displayComandList and executeCommand, uses one or both of the static
 * variables: Bankdata CurrentRecord / LinkedList recordList. The record
 * maintains order according to last name using the method newRecordIndex.he
 * newRecordIndex takes two parameters, an linked list and a Bankdata object
 * and it returns an int. The Bankdata object will be the record being
 * inserted, and the linked list will be recordList. It takes the last name
 * of the record being inserted and compares it to all the last names in
 * recordList using a for loop using comparedTo method. If the comparedTo
 * method returns a number less then 0 at any index, the record will be
 * inserted at that index, else the record will be inserted at the end. If
 * the compared value returns 0 at any point, the first names of that record
 * and the record to be inserted will be compared. If less then 0 is
 * returned the record will be inserted at that index, else the record will
 * be inserted at the index after. Upon execution, the displayCommands
 * method will run. The displayComandList method simply prints lines of
 * executable commands onto the screen, prompting the user to enter one of
 * them. It then takes the command as a string using scanner and uses it as
 * a parameter to call the executeCommand method. Every method in the
 * program calls on this method as their final commands. The displayCommand
 * method triggers the executeCommand method which takes a string parameter
 * from the displayCommandList method and contains a switch and case with
 * the parameter as the switch. Each case except case 10 executes another
 * different method. Case 10 quits the program. There is also a default case
 * which lets the user know if their command is invalid and executes
 * displayComandList. Depending on which case the switch matches a certain
 * method will be triggered. Each of the methods triggered by the cases will
 * call on the displayComandList method which will create and endless loop
 * of method execution until the user enters 10 to exit the program.
 * Following is a list of the methods capable of being triggered by the
 * cases and their discription. The displayRecords method takes the
 * recordList as its parameter uses a for loop to print out the full name
 * and phone number of every record in the list. The deleteRecord method
 * checks if currentRecord is empty, if it isnt, it will remove it from the
 * LinkedList and set currentRecord to null The changeFirstName checks if
 * currentRecord is empty, if it isnt, it asks the user for the new first
 * name and changes the first name of currentRecord to the users input then
 * displays the updated currentRecord. The changeLastName checks if current
 * record is empty if current record is not empty it asks for the new last
 * name. It then removes the current from recordList and then inserts the
 * upgraded currentRecord into the recordList according to the new last name
 * using the newRecordIndex method. The addNewRecord method ask the user for
 * the first name, last name, phone number and balance for the account they
 * wish to create. It will then put the phone number in ###-###-#### format
 * if it isnt already. It takes this information and creates a new Bankdata
 * object. It checks the size of recordList, if its 0, the new record will
 * be added to the front of the LikedList. Else it will be inserted
 * alphabetically according to last name using the newRecordIndex. The
 * changePhone method first checks if the current record is empty, if it
 * isnt, it will prompt user to enter new phone number. y and update
 * currentRecord with the new number. the addDeposit method first checks if
 * the current record is empty, if it isnt, it will ask the user how much
 * they want to deposit. It then takes that ammount and adds it to
 * currentRecords balance. the withdrawMoney method first checks if the
 * current record is empty, if it isnt, it will ask the user how much they
 * want to withdraw. It then takes that ammount and subtracts it from the
 * currentRecords balance. The changeCurrentMethod asks the user for the
 * first and last name of the account they want to make set currentRecord
 * to. It the searches through recordList and looks for a record with
 * matching first and last names, if found, that record will become the new
 * currentRecord, else it will display "No match". THe program contains an
 * additional helper method called printCurrentRecord. The
 * printCurrentRecord prints the first name last name and phone number of
 * the current record
 */

import java.util.LinkedList;
import java.util.Scanner;

public class Bankdata {
	String firstName, lastName, phoneNumber;
	int balance;
	static Bankdata currentRecord;
	static LinkedList<Bankdata> recordList = new LinkedList<Bankdata>();

	public Bankdata(String firstName, String lastName, String phoneNumber,
			int balance) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.balance = balance;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	static Scanner UI = new Scanner(System.in);

	public static void main(String[] args) {

		displayComandList();

	}

	/*
	 * The displayComandList method simply prints lines of executable commands
	 * onto the screen, prompting the user to enter one of them. It then takes
	 * the command as a string using scanner and uses it as a parameter to call
	 * the executeCommand method. Every method in the program calls on this
	 * method as their final commands.
	 */
	public static void displayComandList() {
		String cmnd;
		System.out
				.println("\n1 Show all records.\n"
						+ "2 Delete the current record.\n"
						+ "3 Change the first name in the current record.\n"
						+ "4 Change the last name in the current record.\n"
						+ "5 Add a new record\n"
						+ "6 Change the phone number in the current record.\n"
						+ "7 Add a deposit to the current balance in the current record.\n"
						+ "8 Make a withdrawl from the current record if there is sufficient funds.\n"
						+ "9 Select a record from the record list to become the current record.\n"
						+ "10 Quit.\n" + "Enter command from list above\n");
		cmnd = UI.next();

		executeCommand(cmnd);

	}

	/*
	 * the executeCommand method takes a string parameter from the
	 * displayCommandList method and contains a switch and case with the
	 * parameter as the switch. Each case except case 10 executes another
	 * different method. Case 10 quits the program. There is also a default case
	 * which lets the user know if their command is invalid and executes
	 * displayComandList.
	 */
	public static void executeCommand(String cmnd) {
		switch (cmnd) {
		case "1":
			displayRecords();
			break;
		case "2":
			deleteRecord();
			break;
		case "3":
			changeFirstName();
			break;
		case "4":
			changeLastName();
			break;
		case "5":
			addNewRecord();
			break;
		case "6":
			changePhone();
			break;
		case "7":
			addDeposit();
			break;
		case "8":
			withdrawMoney();
			break;
		case "9":
			changeCurrentRecord();
			break;
		case "10":
			System.exit(0);
			break;
		default:
			System.out.println("Invalid command\n");
			displayComandList();
			break;
		}

	}

	/*
	 * The displayRecords method takes the recordList as its parameter uses a
	 * for loop to print out the full name and phone number of every record in
	 * the list.
	 */
	public static void displayRecords() {
		System.out.println("Name            Phone #            Balance");
		Bankdata record;
		for (int i = 0; i < recordList.size(); i++) {
			record = recordList.get(i);
			System.out.println(record.getFirstName() + " "
					+ record.getLastName() + "    " + record.getPhoneNumber()
					+ "    " + record.getBalance());
		}
		displayComandList();
	}

	/*
	 * The deleteRecord method checks if currentRecord is empty, if it isnt, it
	 * will remove it from the LinkedList and set currentRecord to null
	 */
	public static void deleteRecord() {
		if (currentRecord == null) {
			System.out.println("No current record");
		} else {
			recordList.remove(currentRecord);
			currentRecord = null;
			System.out.println("Current record: null");
		}
		displayComandList();
	}

	/*
	 * The changeFirstName checks if currentRecord is empty, if it isnt, it asks
	 * the user for the new first name and changes the first name of
	 * currentRecord to the users input then displays the updated currentRecord.
	 */
	public static void changeFirstName() {
		if (currentRecord == null) {
			System.out.println("No current record");
		} else {
			System.out.println("Enter new first name for current record: ");
			String newName = UI.next();
			currentRecord.setFirstName(newName);
		}
		printCurrentRecord();
		displayComandList();

	}

	/*
	 * The changeLastName checks if current record is empty if current record is
	 * not empty it asks for the new last name. It then removes the current from
	 * recordList and then inserts the upgraded currentRecord into the
	 * recordList according to the new last name using the newRecordIndex
	 * method.
	 */
	public static void changeLastName() {
		if (currentRecord == null) {
			System.out.println("No current record");
		} else {
			System.out.println("Enter new last name for current record: ");
			String newName = UI.next();
			Bankdata updatedRecord;
			int oldRecord = recordList.indexOf(currentRecord);
			currentRecord.setLastName(newName);
			updatedRecord = currentRecord;
			recordList.remove(oldRecord);
			recordList.add(newRecordIndex(recordList, currentRecord),
					updatedRecord);
		}
		printCurrentRecord();
		displayComandList();

	}

	/*
	 * The addNewRecord method ask the user for the first name, last name, phone
	 * number and balance for the account they wish to create. It will then put
	 * the phone number in ###-###-#### format if it isnt already. It takes this
	 * information and creates a new Bankdata object. It checks the size of
	 * recordList, if its 0, the new record will be added to the front of the
	 * LikedList. Else it will be inserted alphabetically according to last name
	 * using the newRecordIndex.
	 */
	public static void addNewRecord() {
		String firstName, lastName, phoneNumber;
		int balance;
		System.out.println("Enter first name.");
		firstName = UI.next();
		System.out.println("Enter last name");
		lastName = UI.next();
		System.out.println("Enter phone number");
		String tempPhoneNumber = UI.next();
		if (tempPhoneNumber.charAt(3) != '-') {
			phoneNumber = tempPhoneNumber.substring(0, 3) + "-"
					+ tempPhoneNumber.substring(3, 6) + "-"
					+ tempPhoneNumber.substring(6);
		} else {
			phoneNumber = tempPhoneNumber;
		}
		System.out.println("Enter balance");
		balance = UI.nextInt();
		currentRecord = new Bankdata(firstName, lastName, phoneNumber, balance);
		if (recordList.size() > 0) {
			recordList.add(newRecordIndex(recordList, currentRecord),
					currentRecord);
		} else {
			recordList.addFirst(currentRecord);
		}
		printCurrentRecord();
		displayComandList();
	}

	/*
	 * The newRecordIndex takes two parameters, an linked list and a Bankdata
	 * object and it returns an int. The Bankdata object will be the record
	 * being inserted, and the linked list will be recordList. It takes the last
	 * name of the record being inserted and compares it to all the last names
	 * in recordList using a for loop using comparedTo method. If the comparedTo
	 * method returns a number less then 0 at any index, the record will be
	 * inserted at that index, else the record will be inserted at the end. If
	 * the compared value returns 0 at any point, the first names of that record
	 * and the record to be inserted will be compared. If less then 0 is
	 * returned the record will be inserted at that index, else the record will
	 * be inserted at the index after.
	 */
	public static int newRecordIndex(LinkedList<Bankdata> recordList,
			Bankdata record) {
		String lastNameInList;
		String firstNameInList;
		String currentFirstName = record.getFirstName();
		String currentLastName = record.getLastName();
		int comparedValue;

		for (int i = 0; i < recordList.size(); i++) {
			lastNameInList = recordList.get(i).getLastName();
			comparedValue = currentLastName.compareToIgnoreCase(lastNameInList);
			if (comparedValue < 0) {
				return i;
			} else if (comparedValue == 0) {
				firstNameInList = recordList.get(i).getFirstName();
				comparedValue = currentFirstName
						.compareToIgnoreCase(firstNameInList);
				if (comparedValue < 0) {
					return i;
				} else {
					return (i + 1);
				}
			}
		}
		return recordList.size();

	}

	/*
	 * The changePhone method first checks if the current record is empty, if it
	 * isnt, it will prompt user to enter new phone number. y and update
	 * currentRecord with the new number.
	 */
	public static void changePhone() {
		if (currentRecord == null) {
			System.out.println("No current record");
		} else {
			System.out.println("Enter new number for current record: ");
			String tempPhoneNumber = UI.next();
			String phoneNumber;
			if (tempPhoneNumber.charAt(3) != '-') {
				phoneNumber = tempPhoneNumber.substring(0, 3) + "-"
						+ tempPhoneNumber.substring(3, 6) + "-"
						+ tempPhoneNumber.substring(6);
			} else {
				phoneNumber = tempPhoneNumber;
			}
			currentRecord.setPhoneNumber(phoneNumber);
		}
		printCurrentRecord();
		displayComandList();
	}

	/*
	 * the addDeposit method first checks if the current record is empty, if it
	 * isnt, it will ask the user how much they want to deposit. It then takes
	 * that ammount and adds it to currentRecords balance
	 */
	public static void addDeposit() {
		if (currentRecord == null) {
			System.out.println("No current record");
		} else {
			System.out.println("Enter amount to deposit to current record");
			int deposit = UI.nextInt();
			int currentBalance = currentRecord.getBalance();
			currentRecord.setBalance(currentBalance + deposit);
		}
		displayComandList();
	}

	/*
	 * the withdrawMoney method first checks if the current record is empty, if
	 * it isnt, it will ask the user how much they want to withdraw. It then
	 * takes that ammount and subtracts it from the currentRecords balance.
	 */
	public static void withdrawMoney() {
		if (currentRecord == null) {
			System.out.println("No current record");
		} else {
			System.out
					.println("Enter amount to withdraw from current record: ");
			int withdrawl = UI.nextInt();
			int balance = currentRecord.getBalance();
			if (withdrawl > balance) {
				System.out.println("Insufficient funds");
			} else {
				currentRecord.setBalance(balance - withdrawl);
			}
		}
		displayComandList();
	}

	/*
	 * The changeCurrentMethod asks the user for the first and last name of the
	 * account they want to make set currentRecord to. It the searches through
	 * recordList and looks for a record with matching first and last names, if
	 * found, that record will become the new currentRecord, else it will
	 * display "No match"
	 */
	public static void changeCurrentRecord() {
		String firstName, lastName, firstNameInList, lastNameInList;
		Bankdata record = null;
		System.out.print("Enter first name: ");
		firstName = UI.next();
		System.out.print("Enter last name: ");
		lastName = UI.next();

		for (int i = 0; i < recordList.size(); i++) {
			record = recordList.get(i);
			firstNameInList = record.getFirstName();
			lastNameInList = record.getLastName();
			if (firstName.equalsIgnoreCase(firstNameInList)
					&& lastName.equalsIgnoreCase(lastNameInList)) {
				currentRecord = record;
				break;
			}
		}
		if (currentRecord != record) {
			System.out.println("No match");
		} else {
			printCurrentRecord();
		}
		displayComandList();
	}

	/*
	 * The printCurrentRecord prints the first name last name and phone number
	 * of the current record
	 */
	public static void printCurrentRecord() {
		System.out.println("Current record: " + currentRecord.getFirstName()
				+ " " + currentRecord.getLastName() + "    "
				+ currentRecord.getPhoneNumber());
	}

}
