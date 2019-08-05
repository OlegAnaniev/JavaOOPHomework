package training;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Scanner;

import training.Faculty.FacultyName;
import training.Human.Gender;
import training.HumanHelper.HumanComparator;
import training.Student.TeachingMethod;

/**
 * Class containing auxiliary instruments to work with students
 * 
 * @version 0.2 05.08.2019
 * @author Oleg
 */
public class StudentHelper {
	
	/**
	 * Private constructor to make instance creation impossible
	 */
	private StudentHelper() {
		super();
	}
	
	/**
	 * Reads new student from console
	 * 
	 * @return <code>Student</code>
	 */
	public static Student getStudentInput() {
		StudentConsoleReader scr = new StudentConsoleReader();
		return scr.getStudent();
	}

	/**
	 * Class reading new Student from console
	 * 
	 * @version 0.1 15.07.2019
	 * @author Oleg
	 */
	private static class StudentConsoleReader {
		private final static String INPUT_WELLCOME_MESSAGE = 
				"Enter data on new student:";
		private final static String INPUT_FIRSTNAME_MESSAGE = 
				"Input first name: ";
		private final static String INPUT_LASTNAME_MESSAGE = 
				"Input last name: ";
		private final static String INPUT_GENDER_MESSAGE = "Select gender:";
		private final static String INPUT_DAY_OF_BIRTH_MESSAGE = 
				"Input day of birth:";
		private final static String INPUT_MONTH_OF_BIRTH_MESSAGE = 
				"Input month of birth:";
		private final static String INPUT_YEAR_OF_BIRTH_MESSAGE = 
				"Input year of birth:";
		private final static String INPUT_FACULTY_MESSAGE = 
				"Input year of birth:";
		private final static String INPUT_TEACHING_METHOD_MESSAGE = 
				"Select teaching method:";
		private final static String INPUT_ID_MESSAGE = "Input id number: ";
		private final static String INPUT_RECORD_BOOK_MESSAGE = 
				"Input redord book number: ";
		private final static String CHOICE_MESSAGE = "Choice: ";
		Scanner scanner;

		/**
		 * Default constructor
		 */
		public StudentConsoleReader() {
			super();
			scanner = new Scanner(System.in);
		}

		/**
		 * Reads student from console
		 * 
		 * @return <code>Student</code>
		 */
		public Student getStudent() {
			System.out.println(INPUT_WELLCOME_MESSAGE);
			System.out.print(INPUT_FIRSTNAME_MESSAGE);
			String firstName = scanner.nextLine();

			System.out.print(INPUT_LASTNAME_MESSAGE);
			String lastName = scanner.nextLine();

			Gender gender = getGender();
			Calendar birthdate = getBirthdate();
			FacultyName facultyName = getFaculty();
			TeachingMethod teachingMethod = getTechingMethod();

			System.out.print(INPUT_ID_MESSAGE);
			String idNumber = scanner.nextLine();

			System.out.print(INPUT_RECORD_BOOK_MESSAGE);
			String recordBookNumber = scanner.nextLine();

			Student newStudent = new Student(firstName, lastName, gender, 
					birthdate, facultyName, teachingMethod, idNumber, 
					recordBookNumber);

			return newStudent;
		}

		/**
		 * Reads gender from console
		 * 
		 * @return <code>Human.Gender</code>
		 */
		private Gender getGender() {
			Gender[] genders = Gender.values();
			int choice;

			System.out.println(INPUT_GENDER_MESSAGE);
			for (int i = 0; i < genders.length; i++) {
				System.out.println(i + 1 + ". " + genders[i]);
			}

			choice = getIntConsoleInput(CHOICE_MESSAGE, 1, genders.length);
			return genders[choice - 1];
		}

		/**
		 * Reads birth date from console
		 * 
		 * @return <code>Calendar</code>
		 */
		private Calendar getBirthdate() {
			int birthDay = getIntConsoleInput(INPUT_DAY_OF_BIRTH_MESSAGE, 1, 
					31);
			int birthMonth = getIntConsoleInput(INPUT_MONTH_OF_BIRTH_MESSAGE, 
					1, 12);
			int birthYear = getIntConsoleInput(INPUT_YEAR_OF_BIRTH_MESSAGE, 
					1900, getMaxBirthYear());

			Calendar birthdate = Calendar.getInstance();
			birthdate.set(Calendar.DAY_OF_MONTH, birthDay);
			birthdate.set(Calendar.MONTH, birthMonth - 1);
			birthdate.set(Calendar.YEAR, birthYear);

			return birthdate;
		}

		/**
		 * Reads faculty from console
		 * 
		 * @return <code>Faculty.FacultyName</code>
		 */
		private FacultyName getFaculty() {
			FacultyName[] faculties = Faculty.FacultyName.values();
			int choice;

			System.out.println(INPUT_FACULTY_MESSAGE);
			for (int i = 0; i < faculties.length; i++) {
				System.out.println(i + 1 + ". " + faculties[i]);
			}

			choice = getIntConsoleInput(CHOICE_MESSAGE, 1, faculties.length);

			return faculties[choice - 1];
		}

		/**
		 * Reads teaching method from console
		 * 
		 * @return <code>Student.TeachingMethod</code>
		 */
		private TeachingMethod getTechingMethod() {
			TeachingMethod[] teachingMethods = TeachingMethod.values();
			int choice;

			System.out.println(INPUT_TEACHING_METHOD_MESSAGE);
			for (int i = 0; i < teachingMethods.length; i++) {
				System.out.println(i + 1 + ". " + teachingMethods[i]);
			}

			choice = getIntConsoleInput(CHOICE_MESSAGE, 1, 
					teachingMethods.length);

			return teachingMethods[choice - 1];
		}

		/**
		 * Reads a given range int from console
		 * 
		 * @param message <code>String</code>
		 * @param from <code>int</code>
		 * @param to <code>int</code>
		 * @return <code>int</code>
		 */
		private int getIntConsoleInput(String message, int from, int to) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			String userInput;
			int result;

			do {
				System.out.print(message);
				userInput = scanner.nextLine();

				try {
					result = Integer.parseInt(userInput);
				} catch (NumberFormatException e) {
					result = from - 1;
				}
			} while (result < from || result > to);

			return result;
		}

		/**
		 * Calculates max acceptable birth year
		 * 
		 * @return <code>int</code>
		 */
		private int getMaxBirthYear() {
			Calendar calendar = Calendar.getInstance();
			return calendar.get(Calendar.YEAR);
		}
	}
	
	/**
	 * Class adapting human comparators for students
	 * 
	 * @version 0.1 05.08.2019
	 * @author Oleg
	 */
	private static class HumanComparatorAdapter implements 
		Comparator<Student> {
		
		public enum Type {
			FIRST_NAME, LAST_NAME, GENDER, BIRTHDATE
		}		
		private Comparator<Human> comparator;

		/**
		 * Default constructor
		 */
		@SuppressWarnings("unused")
		public HumanComparatorAdapter() {
			super();
		}

		/**
		 * Constructor accepting human comparator
		 * 
		 * @param comparator <code>HumanComparator&lt;Human&gt;</code>
		 */
		@SuppressWarnings("unused")
		public HumanComparatorAdapter(HumanComparator<Human> comparator) {
			super();
			this.comparator = comparator;
		}
		
		/**
		 * Constructor accepting comparator type
		 * 
		 * @param type <code>Type</code>
		 */
		public HumanComparatorAdapter(Type type) {
			this(type, false);
		}
		
		/**
		 * Constructor accepting comparator type and reverse value
		 * 
		 * @param type <code>Type</code>
		 * @param reverse <code>boolean</code>
		 */
		public HumanComparatorAdapter(Type type, boolean reverse) {
			switch (type) {
			case FIRST_NAME:
				comparator = HumanHelper.getFirstNameComaparator(reverse);
				break;
			case LAST_NAME:
				comparator = HumanHelper.getLastNameComaparator(reverse);
				break;
			case GENDER:
				comparator = HumanHelper.getGenderComaparator(reverse);
				break;
			case BIRTHDATE:
				comparator = HumanHelper.getBirthdateComaparator(reverse);
			default:
				break;
			}
		}

		@Override
		public int compare(Student student1, Student student2) {
			return comparator.compare(student1, student2);
		}		
	}
	
	/**
	 * Gets first name comparator
	 * 
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Student> getFirstNameComaparator() {
		return new HumanComparatorAdapter(
				HumanComparatorAdapter.Type.FIRST_NAME);
	}

	/**
	 * Gets first name comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Student> getFirstNameComaparator(
			boolean reverse) {
		return new HumanComparatorAdapter(
				HumanComparatorAdapter.Type.FIRST_NAME, reverse);
	}
	
	
	/**
	 * Gets last name comparator
	 * 
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Student> getLastNameComaparator() {
		return new HumanComparatorAdapter(
				HumanComparatorAdapter.Type.LAST_NAME);
	}
	
	/**
	 * Gets last name comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Student> getLastNameComaparator(boolean reverse) {
		return new HumanComparatorAdapter(
				HumanComparatorAdapter.Type.LAST_NAME, reverse);
	}
	
	/**
	 * Gets gender comparator
	 * 
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Student> getGenderComaparator() {
		return new HumanComparatorAdapter(HumanComparatorAdapter.Type.GENDER);
	}
	
	/**
	 * Gets gender comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Student> getGenderComaparator(boolean reverse) {
		return new HumanComparatorAdapter(
				HumanComparatorAdapter.Type.GENDER, reverse);
	}
	
	/**
	 * Gets birth date comparator
	 * 
	 * @return <code>Comparator</code>
	 */	
	public static Comparator<Student> getBirthdateComaparator() {
		return new HumanComparatorAdapter(
				HumanComparatorAdapter.Type.BIRTHDATE);
	}

	/**
	 * Gets birth date comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Student> getBirthdateComaparator(
			boolean reverse) {
		return new HumanComparatorAdapter(
				HumanComparatorAdapter.Type.BIRTHDATE, reverse);
	}
	
	/**
	 * Gets faculty comparator
	 * 
	 * @return <code>Comparator</code>
	 */		
	public static Comparator<Student> getFacultyComaparator() {
		return new StudentFacultyComparator();
	}
	
	/**
	 * Gets faculty comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Student> getFacultyComaparator(boolean reverse) {
		return new StudentFacultyComparator(reverse);
	}
	
	/**
	 * Faculty comparator
	 * 
	 * 0.2 05.08.2019
	 * @author Oleg
	 */
	private static class StudentFacultyComparator extends 
		HumanComparator<Student> {	
		
		/**
		 * Default constructor
		 */
		public StudentFacultyComparator() {
			super();
		}

		/**
		 * Constructor accepting reverse value
		 * 
		 * @param reverse <code>boolean</code>
		 */
		public StudentFacultyComparator(boolean reverse) {
			super(reverse);
		}

		@Override
		public int compare(Student student1, Student student2) {		
			int result = NullChecker.check(student1, student2);

			if (result == NullChecker.NOT_NULL) {				
				result = student1.getFacultyName().compareTo(
						student2.getFacultyName());
			}		
			
			return super.isReverse() ? result * -1 : result;
		}
	}
	
	/**
	 * Gets teaching method comparator
	 * 
	 * @return <code>Comparator</code>
	 */	
	public static Comparator<Student> getTeachingMethodComaparator() {
		return new StudenteachingMethodComparator();
	}
	
	/**
	 * Gets teaching method comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Student> getTeachingMethodComaparator(
			boolean reverse) {
		return new StudenteachingMethodComparator(reverse);
	}
	
	/**
	 * Teaching method comparator
	 * 
	 * 0.2 05.08.2019
	 * @author Oleg
	 */
	private static class StudenteachingMethodComparator 
		extends HumanComparator<Student> {	
		
		/**
		 * Default constructor
		 */
		public StudenteachingMethodComparator() {
			super();
		}

		/**
		 * Constructor accepting reverse value
		 * 
		 * @param reverse <code>boolean</code>
		 */
		public StudenteachingMethodComparator(boolean reverse) {
			super(reverse);
		}

		@Override
		public int compare(Student student1, Student student2) {		
			int result = NullChecker.check(student1, student2);

			if (result == NullChecker.NOT_NULL) {				
				result = student1.getTeachingMethod().compareTo(
						student2.getTeachingMethod());
			}		
			
			return super.isReverse() ? result * -1 : result;
		}
	}
	
	/**
	 * Gets group comparator
	 * 
	 * @return <code>Comparator</code>
	 */	
	public static Comparator<Student> getGroupComaparator() {
		return new StudentGroupComparator();
	}
	
	/**
	 * Gets group comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Student> getGroupComaparator(boolean reverse) {
		return new StudentGroupComparator(reverse);
	}
	
	/**
	 * Group comparator
	 * 
	 * 0.2 05.08.2019
	 * @author Oleg
	 */
	private static class StudentGroupComparator extends 
		HumanComparator<Student> {	
		
		/**
		 * Default constructor
		 */
		public StudentGroupComparator() {
			super();
		}

		/**
		 * Constructor accepting reverse value
		 * 
		 * @param reverse <code>boolean</code>
		 */
		public StudentGroupComparator(boolean reverse) {
			super(reverse);
		}

		@Override
		public int compare(Student student1, Student student2) {		
			int result = NullChecker.check(student1, student2);

			if (result == NullChecker.NOT_NULL) {				
				result = student1.getGroupName().compareToIgnoreCase(
						student2.getGroupName());
			}		
			
			return super.isReverse() ? result * -1 : result;
		}
	}
	
	/**
	 * Gets id comparator
	 * 
	 * @return <code>Comparator</code>
	 */	
	public static Comparator<Student> getIdComaparator() {
		return new StudentIdComparator();
	}
	
	/**
	 * Gets id comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Student> getIdComaparator(boolean reverse) {
		return new StudentIdComparator(reverse);
	}
	
	/**
	 * ID comparator
	 * 
	 * 0.2 05.08.2019
	 * @author Oleg
	 */
	private static class StudentIdComparator extends HumanComparator<Student> {	
		
		/**
		 * Default constructor
		 */
		public StudentIdComparator() {
			super();
		}

		/**
		 * Constructor accepting reverse value
		 * 
		 * @param reverse <code>boolean</code>
		 */
		public StudentIdComparator(boolean reverse) {
			super(reverse);
		}

		@Override
		public int compare(Student student1, Student student2) {		
			int result = NullChecker.check(student1, student2);

			if (result == NullChecker.NOT_NULL) {				
				result = student1.getIdNumber().compareToIgnoreCase(
						student2.getIdNumber());
			}		
			
			return super.isReverse() ? result * -1 : result;
		}
	}
	
	/**
	 * Gets record book comparator
	 * 
	 * @return <code>Comparator</code>
	 */	
	public static Comparator<Student> getRecordBookComaparator() {
		return new StudentRecordBookComparator();
	}
	
	/**
	 * Gets record book comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Student> getRecordBookComaparator(
			boolean reverse) {
		return new StudentRecordBookComparator(reverse);
	}
	
	/**
	 * Record book comparator
	 * 
	 * 0.2 05.08.2019
	 * @author Oleg
	 */
	private static class StudentRecordBookComparator extends 
		HumanComparator<Student> {	
		
		/**
		 * Default constructor
		 */
		public StudentRecordBookComparator() {
			super();
		}

		/**
		 * Constructor accepting reverse value
		 * 
		 * @param reverse <code>boolean</code>
		 */
		public StudentRecordBookComparator(boolean reverse) {
			super(reverse);
		}

		@Override
		public int compare(Student student1, Student student2) {		
			int result = NullChecker.check(student1, student2);

			if (result == NullChecker.NOT_NULL) {				
				result = student1.getRecordBookNumber().compareToIgnoreCase(
						student2.getRecordBookNumber());
			}		
			
			return super.isReverse() ? result * -1 : result;
		}
	}
}