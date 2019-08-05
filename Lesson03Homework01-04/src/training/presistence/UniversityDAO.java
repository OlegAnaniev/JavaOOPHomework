package training.presistence;

import training.Group;
import training.Student;

/**
 * University data saver/loader interface
 * 
 * @version 0.1 18.07.2019
 * @author Oleg
 */
public interface UniversityDAO {
	/**
	 * Gets group from storage by id
	 * 
	 * @param id <code>int</code>
	 * @return <code>Group</code>
	 */
	public Group getGroup(int id);
	
	/**
	 * Inserts group into storage
	 * 
	 * @param group <code>Group</code>
	 * @return <code>int</code> id
	 */
	public int insertGroup(Group group);
	
	/**
	 * Updates existing group in storage
	 * 
	 * @param group <code>Group</code>
	 * @return <code>int</code> id
	 */
	public int updateGroup(Group group);
	
	/**
	 * Deletes group from storage
	 * 
	 * @param group <code>Group</code>
	 * @return <code>boolean</code> true if deletion successful and false 
	 * otherwise
	 */
	public boolean deleteGroup(Group group);
	
	/**
	 * Gets student from storage by id
	 * 
	 * @param id <code>int</code>
	 * @return <code>Student</code>
	 */
	public Student getStudent(int id);
	
	/**
	 * Inserts student into storage
	 * 
	 * @param student <code>Student</code>
	 * @return <code>int</code> id
	 */
	public int insertStudent(Student student);
	
	/**
	 * Updates existing student in storage
	 * 
	 * @param student <code>Student</code>
	 * @return <code>int</code> id
	 */
	public int updateStudent(Student student);
	
	/**
	 * Deletes student from storage
	 * 
	 * @param student <code>Student</code>
	 * @return <code>boolean</code> true if deletion successful and false 
	 * otherwise
	 */
	public boolean deleteStudent(Student student);
}