package training;

import java.util.List;

/**
 * Military manager interface
 * 
 * @version 0.2 05.08.2019
 * @author Oleg
 */
public interface MilitaryManager {
	/**
	 * Gets list of military liable students
	 * 
	 * @return <code>Student[]</code>
	 */
	public List<Student> getLiableStudents();
}