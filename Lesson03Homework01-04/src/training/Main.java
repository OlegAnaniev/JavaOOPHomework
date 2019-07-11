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
		System.out.println(human);
		
		Student student = new Student("Nelson", "James", Gender.MALE, 
				birthdate, FacultyName.BIOLOGY, TeachingMethod.IN_PERSON,
				"Az214", "PS789654321", "123789");
		
		System.out.println(student);
		
		Student student2 = new Student("Howard", "Abrams", Gender.MALE, 1, 6,
				1971, FacultyName.MATH, TeachingMethod.LONG_DISTANCE, "Ppc951",
				"TX741852963", "654789"); 		
		Student student3 = new Student("Eliza", "Graves", Gender.FEMALE, 5, 11,
				1995, FacultyName.ECONOMICS, TeachingMethod.ON_LINE, "Zxc258",
				"NY159755456", "852456"); 
		
		Group group = new Group("Lbxm-511");
		try {
			group.setStudent(student);
			group.setStudent(student2);
			group.setStudent(student3);			
		} catch (TooManyStudentsException e) { 
			e.printStackTrace();
		}
		
		System.out.println(Arrays.toString(group.getStudents()));
		System.out.println(group);		
		
		Student found = group.getStudent("James");
		System.out.println(found);
		
		System.out.println(group.removeStudent(found));
		System.out.println(Arrays.toString(group.getStudents()));
		
	}
}