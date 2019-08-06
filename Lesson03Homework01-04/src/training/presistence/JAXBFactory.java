package training.presistence;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import training.Group;
import training.Student;

/**
 * University JAXB xml file saver/loader
 * 
 * @version 0.2 05.08.2019
 * @author Oleg
 */
public class JAXBFactory implements UniversityDAO {
	private static final String BASE_STORAGE_PATH = "datastorage" 
		+ File.separator + "jaxb";
	private static final String GROUP_STORAGE_PATH = "groups";
	private static final String STUDENT_STORAGE_PATH = "students";
	private static final String FILE_EXTENSION = ".xml";

	private File groupStorage;
	private File studentStorage;

	/**
	 * Default constructor
	 */
	public JAXBFactory() {
		super();
		studentStorage = new File(BASE_STORAGE_PATH + File.separator 
				+ STUDENT_STORAGE_PATH);
		groupStorage = new File(BASE_STORAGE_PATH + File.separator 
				+ GROUP_STORAGE_PATH);
	}

	@Override
	public Group getGroup(int id) {
		FileFactoryHelper.validateFolder(groupStorage);

		File file = FileFactoryHelper.getFile(groupStorage, id);

		try {			
			return (Group) loadFromXML(file, Group.class);			
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int insertGroup(Group group) {
		FileFactoryHelper.validateFolder(groupStorage);

		if (group.getId() == 0) {
			group.setId(FileFactoryHelper.getNextId(groupStorage));
		}

		return updateGroup(group);
	}

	@Override
	public int updateGroup(Group group) {
		FileFactoryHelper.validateFolder(groupStorage);

		if (group.getId() == 0) {
			throw new IllegalArgumentException(
					FileFactoryHelper.NON_EXISTANT_GROUP_MESSAGE);
		}

		File file = FileFactoryHelper.getFile(group, groupStorage, 
				FILE_EXTENSION);

		try {
			saveToFile(group, file);
		} catch (JAXBException e) {
			e.printStackTrace();
			return -1;
		}

		return group.getId();
	}

	@Override
	public boolean deleteGroup(Group group) {
		FileFactoryHelper.validateFolder(groupStorage);

		File file = FileFactoryHelper.getFile(groupStorage, group.getId());

		return file.delete();
	}

	@Override
	public Student getStudent(int id) {
		FileFactoryHelper.validateFolder(studentStorage);

		File file = FileFactoryHelper.getFile(studentStorage, id);

		try {
			Student student = (Student) loadFromXML(file, 
					Student.class);			
			
			return student;
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int insertStudent(Student student) {
		FileFactoryHelper.validateFolder(studentStorage);

		if (student.getId() == 0) {
			student.setId(FileFactoryHelper.getNextId(studentStorage));
		}

		return updateStudent(student);
	}

	@Override
	public int updateStudent(Student student) {
		FileFactoryHelper.validateFolder(studentStorage);

		if (student.getId() == 0) {
			throw new IllegalArgumentException(
					FileFactoryHelper.NON_EXISTANT_STUDENT_MESSAGE);
		}

		File file = FileFactoryHelper.getFile(student, studentStorage, 
				FILE_EXTENSION);

		try {
			saveToFile(student, file);
		} catch (JAXBException e) {
			e.printStackTrace();
			return -1;
		}

		return student.getId();
	}

	@Override
	public boolean deleteStudent(Student student) {
		FileFactoryHelper.validateFolder(studentStorage);

		File studentFile = FileFactoryHelper.getFile(studentStorage, student.getId());
		return studentFile.delete();
	}

	/**
	 * Saves entity to file
	 * 
	 * @param object <code>Object</code>
	 * @param file <code>File</code>
	 * @throws JAXBException
	 */
	private void saveToFile(Object object, File file) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(object.getClass());
		Marshaller marshaller = context.createMarshaller();

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(object, file);
	}

	/**
	 * Loads entity from file
	 * 
	 * @param file <code>File</code>
	 * @param cls <code>Class&lt;?&gt;</code>
	 * @return <code>Object</code>
	 * @throws JAXBException
	 */
	private Object loadFromXML(File file, Class<?> cls) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(cls);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		return unmarshaller.unmarshal(file);
	}
	
	/**
	 * Class providing date format adapter
	 * 
	 * @version 0.1 05.08.2019
	 * @author Oleg
	 */
	public static class BirthdateFormatter extends 
		XmlAdapter<String, Calendar> {

		@Override
		public Calendar unmarshal(String v) throws Exception {
			Date date = null;
			
			date = FileFactoryHelper.dateFormat.parse(v);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date.getTime());
			
			return calendar;
		}

		@Override
		public String marshal(Calendar v) throws Exception {
			Date date = v.getTime();
			return FileFactoryHelper.dateFormat.format(date);
		}		
	}
}