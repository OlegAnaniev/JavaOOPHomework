package training.presistence;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import training.Faculty;
import training.Group;
import training.Human;
import training.Student;
import training.exceptions.TooManyStudentsException;

/**
 * University xml file saver/loader
 * 
 * @version 0.1 05.08.2019
 * @author Oleg
 */
public class XMLFactory implements UniversityDAO {
	private static final String BASE_STORAGE_PATH = "datastorage" 
			+ File.separator + "xml";
	private static final String GROUP_STORAGE_PATH = "groups";
	private static final String STUDENT_STORAGE_PATH = "students";
	private static final String FILE_EXTENSION = ".xml";

	private File groupStorage;
	private File studentStorage;
	
	/**
	 * Default constructor
	 */
	public XMLFactory() {
		super();
		groupStorage = new File(BASE_STORAGE_PATH + File.separator 
				+ GROUP_STORAGE_PATH);
		studentStorage = new File(BASE_STORAGE_PATH + File.separator 
				+ STUDENT_STORAGE_PATH);		
	}

	@Override
	public Group getGroup(int id) {
		FileFactoryHelper.validateFolder(groupStorage);

		File file = FileFactoryHelper.getFile(groupStorage, id);
		
		try {
			Document document = getDocument(file);			
			NodeList nodes = document.getDocumentElement().getChildNodes();			
			String groupName = ((Element) nodes.item(1)).getTextContent();			
			Group group = new Group(id, groupName);			
			NodeList studentNodes = nodes.item(2).getChildNodes();			
			Element element;
			
			for (int i = 0; i < studentNodes.getLength(); i++) {
				element = (Element) studentNodes.item(i);
				group.setStudent(getStudentFromElement(element));
				
			}
			
			return group;
			
		} catch (DOMException | TooManyStudentsException | ParseException e) {
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

		Document document = getDocument();
		Element root = document.createElement("group");
		
		Element id = document.createElement("id");
		id.setTextContent(Integer.toString(group.getId()));
		root.appendChild(id);
		
		Element name = document.createElement("name");
		name.setTextContent(group.getName());
		root.appendChild(name);
				
		Element students = document.createElement("students");
		root.appendChild(students);
		
		for (Student student : group.getStudents()) {
			if (student != null) {
				students.appendChild(getStudentElement(student, document));
			}				
		}

		document.appendChild(root);
		
		File file = FileFactoryHelper.getFile(group, groupStorage, 
				FILE_EXTENSION);
		saveToFile(document, file);
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
		Document document = getDocument(file);
		NodeList node = document.getDocumentElement().getChildNodes();	
		Student student = null;
		
		try {
			student = getStudentFromElement((Element) node);
		} catch (DOMException | ParseException e) {
			e.printStackTrace();
		}
		
		return student;
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
		
		Document document = getDocument();		
		document.appendChild(getStudentElement(student, document));
		
		saveToFile(document, file);

		return student.getId();
	}

	@Override
	public boolean deleteStudent(Student student) {
		FileFactoryHelper.validateFolder(studentStorage);

		File file = FileFactoryHelper.getFile(studentStorage, student.getId());
		return file.delete();
	}
	
	/**
	 * Gets element from student 
	 * 
	 * @param student <code>Student</code>
	 * @param document <code>Document</code>
	 * @return <code>Element</code>
	 */
	private Element getStudentElement(Student student, Document document) {
		Element element = document.createElement("student");
		
		Element id = document.createElement("id");
		id.setTextContent(Integer.toString(student.getId()));
		element.appendChild(id);
		
		Element firstName = document.createElement("firstname");
		firstName.setTextContent(student.getFirstName());
		element.appendChild(firstName);
		
		Element lastName = document.createElement("lastname");
		lastName.setTextContent(student.getLastName());
		element.appendChild(lastName);
		
		Element gender = document.createElement("gender");
		gender.setTextContent(student.getGender().toString());
		element.appendChild(gender);
		
		Element birthdate = document.createElement("birthdate");
		birthdate.setTextContent(FileFactoryHelper.dateFormat.format(
				student.getBirthdate().getTime()));
		element.appendChild(birthdate);
		
		Element faculty = document.createElement("faculty");
		faculty.setTextContent(student.getFacultyName().toString());
		element.appendChild(faculty);
		
		Element teachingMethod = document.createElement("teachingmethod");
		teachingMethod.setTextContent(student.getTeachingMethod().toString());
		element.appendChild(teachingMethod);
		
		Element groupName = document.createElement("group");
		groupName.setTextContent(student.getGroupName());
		element.appendChild(groupName);
		
		Element idNumber = document.createElement("idnumber");
		idNumber.setTextContent(student.getIdNumber());
		element.appendChild(idNumber);
		
		Element recordBookNumber = document.createElement("recordbook");
		recordBookNumber.setTextContent(student.getRecordBookNumber());		
		element.appendChild(recordBookNumber);
		
		return element;
	}
	
	/**
	 * Gets student from element
	 * 
	 * @param element <code>Element</code>
	 * @return <code>Student</code>
	 * @throws DOMException
	 * @throws ParseException
	 */
	private Student getStudentFromElement(Element element) throws DOMException, ParseException {
		int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
		String firstName = element.getElementsByTagName("firstname").item(0).getTextContent();
		String lastName = element.getElementsByTagName("lastname").item(0).getTextContent();
		Human.Gender gender = Human.Gender.valueOf(
				element.getElementsByTagName("gender").item(0).getTextContent());
		Date date = FileFactoryHelper.dateFormat.parse(
				element.getElementsByTagName("birthdate").item(0).getTextContent());
		Calendar birthdate = Calendar.getInstance();
		birthdate.setTime(date);
		Faculty.FacultyName facultyName = Faculty.FacultyName.valueOf(
				element.getElementsByTagName("faculty").item(0).getTextContent());
		
		Student.TeachingMethod teachingMethod = Student.TeachingMethod.valueOf(
				element.getElementsByTagName("teachingmethod").item(0).getTextContent());
		String idNumber = element.getElementsByTagName("idnumber").item(0).getTextContent();
		String recordBookNumber = element.getElementsByTagName("recordbook").item(0).getTextContent();
		
		return new Student(id, firstName, lastName, gender, 
				birthdate, facultyName, teachingMethod, idNumber, 
				recordBookNumber);
	}
	
	/**
	 * Gets document builder
	 * 
	 * @return <code>DocumentBuilder</code>
	 */
	private DocumentBuilder getBuilder() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			return builder;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Gets document
	 * 
	 * @return <code>Document</code>
	 */
	private Document getDocument() {
		DocumentBuilder builder = getBuilder();
		Document document = null;
		
		if (builder != null) {
			document = builder.newDocument();
		}
		
		return document;
	}
	
	/**
	 * Gets document from file
	 * 
	 * @param file <code>File</code>
	 * @return <code>Document</code>
	 */
	private Document getDocument(File file) {
		DocumentBuilder builder = getBuilder();
		Document document = null;
		
		if (builder != null) {
			try {
				document = builder.parse(file);
			} catch (SAXException | IOException e) {
				e.printStackTrace();
			}
		}
		
		return document;
	}
	
	/**
	 * Saves document to file
	 * 
	 * @param document <code>Document</code>
	 * @param file <code>File</code>
	 */
	private void saveToFile(Document document, File file) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(file);
			
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}		
	}
}