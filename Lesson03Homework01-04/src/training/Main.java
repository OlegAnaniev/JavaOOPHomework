package training;

import java.util.Arrays;
import java.util.Calendar;

import training.Faculty.FacultyName;
import training.Human.Gender;
import training.Student.TeachingMethod;
import training.exceptions.TooManyStudentsException;

public class Main {

	public static void main(String[] args) {
		Calendar birthdate = Calendar.getInstance();	
		birthdate.set(Calendar.DATE, 1);
		birthdate.set(Calendar.MONTH, 2);
		birthdate.set(Calendar.YEAR, 1955);
		
		Human human = new Human("Test", "Tester", Gender.MALE, birthdate);
		System.out.println("Human test output: " + human);
		
		Student student = new Student("Nelson", "James", Gender.MALE, 
				birthdate, FacultyName.BIOLOGY, TeachingMethod.IN_PERSON,
				"PS789654321", "123789");		
		System.out.println("Student test output: " + student);
		
		Student student2 = new Student("Howard", "Abrams", Gender.MALE, 16, 7,
				2001, FacultyName.MATH, TeachingMethod.LONG_DISTANCE,
				"TX741852963", "654789"); 		
		Student student3 = new Student("Eliza", "Graves", Gender.FEMALE, 5, 11,
				1995, FacultyName.ECONOMICS, TeachingMethod.ON_LINE,
				"NY159755456", "852456"); 
//		Student student4 = StudentHelper.getStudentInput();
		
		Group group = new Group("Lbxm-511");
		try {
			group.setStudent(student);
			group.setStudent(student2);
			group.setStudent(student3);		
//			group.setStudent(student4);
		} catch (TooManyStudentsException e) { 
			e.printStackTrace();
		}
		
		System.out.println("Group array test output:");
		System.out.println(Arrays.toString(group.getStudents()));
		System.out.println("Group test output:");
		System.out.println(group);		
		
		Student found = group.getStudent("James");
		System.out.println("Found student test output: " + found);
		
//		System.out.println(group.removeStudent(found));
//		System.out.println("Group array test output after removal:");
//		System.out.println(Arrays.toString(group.getStudents()));	
		
		System.out.println("FirstNameComaparator:");
		group.setComparator(StudentHelper.getFirstNameComaparator());
		System.out.println(group);
		
		System.out.println("FirstNameComaparator reverse:");
		group.setComparator(StudentHelper.getFirstNameComaparator(true));
		System.out.println(group);
		
		System.out.println("LastNameComaparator:");
		group.setComparator(StudentHelper.getLastNameComaparator());
		System.out.println(group);
		
		System.out.println("LastNameComaparator reverse:");
		group.setComparator(StudentHelper.getLastNameComaparator(true));
		System.out.println(group);
		
		System.out.println("GenderComaparator:");
		group.setComparator(StudentHelper.getGenderComaparator());
		System.out.println(group);
		
		System.out.println("GenderComaparator reverse:");
		group.setComparator(StudentHelper.getGenderComaparator(true));
		System.out.println(group);
		
		System.out.println("BirthdateComaparator:");
		group.setComparator(StudentHelper.getBirthdateComaparator());
		System.out.println(group);
		
		System.out.println("BirthdateComaparator reverse:");
		group.setComparator(StudentHelper.getBirthdateComaparator(true));
		System.out.println(group);
		
		System.out.println("FacultyComaparator:");
		group.setComparator(StudentHelper.getFacultyComaparator());
		System.out.println(group);
		
		System.out.println("FacultyComaparator reverse:");
		group.setComparator(StudentHelper.getFacultyComaparator(true));
		System.out.println(group);
		
		System.out.println("TeachingMethodComaparator:");
		group.setComparator(StudentHelper.getTeachingMethodComaparator());
		System.out.println(group);
		
		System.out.println("TeachingMethodComaparator reverse:");
		group.setComparator(StudentHelper.getTeachingMethodComaparator(true));
		System.out.println(group);
		
		System.out.println("GroupComaparator:");
		group.setComparator(StudentHelper.getGroupComaparator());
		System.out.println(group);
		
		System.out.println("GroupComaparator revrese:");
		group.setComparator(StudentHelper.getGroupComaparator(true));
		System.out.println(group);
		
		System.out.println("IdComaparator:");
		group.setComparator(StudentHelper.getIdComaparator());
		System.out.println(group);
		
		System.out.println("IdComaparator reverse:");
		group.setComparator(StudentHelper.getIdComaparator(true));
		System.out.println(group);
		
		
		System.out.println("RecordBookComaparator:");
		group.setComparator(StudentHelper.getRecordBookComaparator());
		System.out.println(group);
		
		System.out.println("RecordBookComaparator reverse:");
		group.setComparator(StudentHelper.getRecordBookComaparator(true));
		System.out.println(group);
		
		System.out.println("Military liable:");
		Student[] liable = group.getLiableStudents();
		System.out.println(Arrays.toString(liable));
	}
}