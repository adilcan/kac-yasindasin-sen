

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class calculateCurrentDate {
	public static int getNumberOfDaysInMonth(int m, int y) {
		if(m == 4 || m == 6 || m == 9 || m == 11) {
			return 30;
		}
		else if(m == 2) {
			if(y % 4 == 0) {
				return 29;
			}
			return 28;
		}
		return 31;
	}
	public static void main(String args[]) {

		Scanner scanner = new Scanner(System.in);
		
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);

		String enteredYear = "";
		String enteredMonth = "";
		String enteredDay = "";
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		System.out.println("Today is " + year + "/" + month + "/" + day);

		int inputYear = 0;
		int inputMonth = 0;
		int inputDay = 0;

		int numberOfDaysInInputMonth = 0;
		int numberOfDays = 0;

		System.out.println("Enter the birth year (4 digits)");
		while(true) {
			enteredYear = scanner.nextLine();
			if(enteredYear.length() == 4) {
				inputYear = Integer.parseInt(enteredYear);
				if (inputYear > 0 && inputYear <= year) {
					break;
				}
			}
			System.out.println("Invalid year. Please enter the year between 0-" + year);
		}
		
		System.out.println("Enter the birth month (2 digits)");
		while(true) {
			enteredMonth = scanner.nextLine();
			if(enteredMonth.length() == 2) {
				inputMonth = Integer.parseInt(enteredMonth);
				if(inputMonth > 0 && inputMonth <= 12) {
					if(inputYear == year) {
						if(inputMonth <= month) {
							break;
						}
						else {
							System.out.println("Invalid month. Please enter the month between 1-" + month);
							continue;
						}
					}
					else {
						break;
					}
				}
			}
			System.out.println("Invalid month. Please enter the month between 1-12");
		}

		numberOfDaysInInputMonth = getNumberOfDaysInMonth(inputMonth, inputYear);
		
		System.out.println("Enter the birth day (2 digits)");
		while(true) {
			enteredDay = scanner.nextLine();
			if(enteredDay.length() == 2) {
				inputDay = Integer.parseInt(enteredDay);
				if(inputDay > 0 && inputDay <= numberOfDaysInInputMonth) {
					if(inputYear == year) {
						if(inputMonth == month) {
							if(inputDay <= day) {
								break;
							}
							else {
								System.out.println("Invalid day. Please enter the day between 1-" + day);
								continue;
							}
						}
						else {
							break;
						}
					}
					else {
						break;
					}
				}
			}
			System.out.println("Invalid day. Please enter the day between 1-" + numberOfDaysInInputMonth);
		}
		
		System.out.println("Your birthday is " + inputYear + "/" + inputMonth + "/" + inputDay);

		int monthOffset = 0;
		int yearOffset = 0;
		int numberOfDaysInMonth = 0;

		if(inputYear == year) {
			if(inputMonth == month) {
				if(inputDay == day) {
					numberOfDays = 0;
				}
				else {
					numberOfDays = day - inputDay;
				}
			}
			else {
				monthOffset = 0;
				while(inputMonth + monthOffset < month) {
					numberOfDaysInMonth = getNumberOfDaysInMonth(inputMonth + monthOffset, inputYear);
					if(monthOffset == 0) {
						numberOfDays += numberOfDaysInMonth - inputDay;
					}
					else {
						numberOfDays += numberOfDaysInMonth;
					}
					monthOffset += 1;
				}
				numberOfDays += day;
			}
		}
		else {
			monthOffset = 0;
			while(inputMonth + monthOffset <= 12) {
				numberOfDaysInMonth = getNumberOfDaysInMonth(inputMonth + monthOffset, inputYear);
				if(monthOffset == 0) {
					numberOfDays += numberOfDaysInMonth - inputDay;
				}
				else {
					numberOfDays += numberOfDaysInMonth;
				}
				monthOffset += 1;
			}

			yearOffset = 0;
			while(inputYear + yearOffset < year) {
				if((inputYear + yearOffset) % 4 == 0) {
					numberOfDays += 366;
				}
				else {
					numberOfDays += 365;
				}
				yearOffset += 1;
			}

			monthOffset = 0;
			while(monthOffset < month) {
				numberOfDaysInMonth = getNumberOfDaysInMonth(monthOffset, inputYear);
				numberOfDays += numberOfDaysInMonth;
				monthOffset += 1;
			}
			numberOfDays += day;
		}

		System.out.println("You have lived " + numberOfDays + " days");
	}
}
