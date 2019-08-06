package training;

import java.util.Calendar;
import java.util.List;

import training.Faculty.FacultyName;
import training.Human.Gender;
import training.Student.TeachingMethod;
import training.exceptions.TooManyStudentsException;
import training.presistence.DAOFactory;
import training.presistence.UniversityDAO;

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
		
		System.out.println("Group list test output:");
		System.out.println(group.getStudents());
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
		List<Student> liable = group.getLiableStudents();
		System.out.println(liable);
		
		System.out.println("Filesystem storage:");
		UniversityDAO storage = DAOFactory.getFactory(DAOFactory.Type.FILESYSTEM);
//		storage.insertGroup(group);
		Group loadedGroup = storage.getGroup(1);		
		System.out.println(loadedGroup);
		storage.insertGroup(loadedGroup);
//		storage.deleteGroup(loadedGroup);
		
		System.out.println("Serialization storage:");
		storage = DAOFactory.getFactory(DAOFactory.Type.SERIALIZATION);
		storage.insertGroup(loadedGroup);
		loadedGroup = storage.getGroup(1);
		System.out.println(loadedGroup);
		storage.insertGroup(loadedGroup);
//		storage.deleteGroup(loadedGroup);
		
		for (Student item : loadedGroup.getStudents()) {
			if (item != null) {
				storage.insertStudent(item);
			}			
		}
		
		Student loadedStudent = storage.getStudent(2);
		System.out.println(loadedStudent);
//		storage.deleteStudent(loadedStudent);
		
		System.out.println("XML storage:");
		storage = DAOFactory.getFactory(DAOFactory.Type.XML);
		storage.insertGroup(loadedGroup);
		loadedGroup = storage.getGroup(1);
		System.out.println(loadedGroup);
		
		for (Student item : loadedGroup.getStudents()) {
			if (item != null) {
				storage.insertStudent(item);
			}			
		}
		
		loadedStudent = storage.getStudent(2);
		System.out.println(loadedStudent);
//		storage.deleteStudent(loadedStudent);
		
		System.out.println("JAXB storage:");
		storage = DAOFactory.getFactory(DAOFactory.Type.JAXB);
		storage.insertGroup(loadedGroup);
		loadedGroup = storage.getGroup(1);
		System.out.println(loadedGroup);
		
		for (Student item : loadedGroup.getStudents()) {
			if (item != null) {
				storage.insertStudent(item);
			}			
		}
		
		loadedStudent = storage.getStudent(2);
		System.out.println(loadedStudent);
		
		System.out.println("GSON storage:");
		storage = DAOFactory.getFactory(DAOFactory.Type.GSON);
		storage.insertGroup(loadedGroup);
		loadedGroup = storage.getGroup(1);
		System.out.println(loadedGroup);
		
		for (Student item : loadedGroup.getStudents()) {
			if (item != null) {
				storage.insertStudent(item);
			}			
		}
		
		loadedStudent = storage.getStudent(2);
		System.out.println(loadedStudent);
		
		List<Student> students = group.getStudents('g');
		System.out.println(students);
		
	}
}