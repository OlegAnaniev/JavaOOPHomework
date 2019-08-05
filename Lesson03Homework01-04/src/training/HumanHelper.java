package training;

import java.util.Comparator;

/**
 * Class containing auxiliary instruments to work with humans
 * 
 * @version 0.2 05.08.2019
 * @author Oleg
 */
public class HumanHelper {
	
	/**
	 * Private constructor to make instance creation impossible
	 */
	private HumanHelper() {
		super();
	}

	/**
	 * Abstract human comparator that provides reverse functionality
	 * 
	 * @version 0.2 05.08.2019
	 * @author Oleg
	 */
	public static abstract class HumanComparator<T extends Human> implements Comparator<T> {
		private boolean reverse;

		/**
		 * Default constructor
		 */
		public HumanComparator() {
			super();
		}

		/**
		 * Constructor accepting reverse value
		 * 
		 * @param reverse <code>boolean</code>
		 */
		public HumanComparator(boolean reverse) {
			super();
			this.reverse = reverse;
		}

		/**
		 * Gets reverse value
		 * 
		 * @return
		 */
		public boolean isReverse() {
			return reverse;
		}

		/**
		 * Sets reverse value
		 * 
		 * @param reverse
		 */
		public void setReverse(boolean reverse) {
			this.reverse = reverse;
		}
	}

	/**
	 * Gets first name comparator
	 * 
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Human> getFirstNameComaparator() {
		return new HumanFirstNameComparator();
	}

	
	/**
	 * Gets first name comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Human> getFirstNameComaparator(boolean reverse) {
		return new HumanFirstNameComparator(reverse);
	}

	/**
	 * First name comparator
	 * 
	 * 0.2 05.08.2019
	 * @author Oleg
	 */
	private static class HumanFirstNameComparator extends HumanComparator<Human> {

		/**
		 * Default constructor
		 */
		public HumanFirstNameComparator() {
			super();
		}

		/**
		 * Constructor accepting reverse value
		 * 
		 * @param reverse <code>boolean</code>
		 */
		public HumanFirstNameComparator(boolean reverse) {
			super(reverse);
		}

		@Override
		public int compare(Human human1, Human human2) {
			int result = NullChecker.check(human1, human2);

			if (result == NullChecker.NOT_NULL) {
				result = human1.getFirstName().compareToIgnoreCase(human2.getFirstName());
			}

			return super.isReverse() ? result * -1 : result;
		}
	}

	/**
	 * Gets last name comparator
	 * 
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Human> getLastNameComaparator() {
		return new HumanLastNameComparator();
	}

	/**
	 * Gets last name comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Human> getLastNameComaparator(boolean reverse) {
		return new HumanLastNameComparator(reverse);
	}

	/**
	 * Last name comparator
	 * 
	 * 0.1 05.08.2019
	 * @author Oleg
	 */
	private static class HumanLastNameComparator extends HumanComparator<Human> {

		/**
		 * Default constructor
		 */
		public HumanLastNameComparator() {
			super();
		}

		/**
		 * Constructor accepting reverse value
		 * 
		 * @param reverse <code>boolean</code>
		 */
		public HumanLastNameComparator(boolean reverse) {
			super(reverse);
		}

		@Override
		public int compare(Human human1, Human human2) {
			int result = NullChecker.check(human1, human2);

			if (result == NullChecker.NOT_NULL) {
				result = human1.getLastName().compareToIgnoreCase(human2.getLastName());
			}

			return super.isReverse() ? result * -1 : result;
		}
	}

	/**
	 * Gets gender comparator
	 * 
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Human> getGenderComaparator() {
		return new HumanGenderComparator();
	}

	/**
	 * Gets gender comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Human> getGenderComaparator(boolean reverse) {
		return new HumanGenderComparator(reverse);
	}

	/**
	 * Gender comparator
	 * 
	 * 0.2 05.08.2019
	 * @author Oleg
	 */
	private static class HumanGenderComparator extends HumanComparator<Human> {

		/**
		 * Default constructor
		 */
		public HumanGenderComparator() {
			super();
		}

		/**
		 * Constructor accepting reverse value
		 * 
		 * @param reverse <code>boolean</code>
		 */
		public HumanGenderComparator(boolean reverse) {
			super(reverse);
		}

		@Override
		public int compare(Human human1, Human human2) {
			int result = NullChecker.check(human1, human2);

			if (result == NullChecker.NOT_NULL) {
				result = human1.getGender().compareTo(human2.getGender());
			}

			return super.isReverse() ? result * -1 : result;
		}
	}

	/**
	 * Gets birth date comparator
	 * 
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Human> getBirthdateComaparator() {
		return new HumanBirthdateComparator();
	}

	/**
	 * Gets birth date comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator<Human> getBirthdateComaparator(boolean reverse) {
		return new HumanBirthdateComparator(reverse);
	}

	/**
	 * Birth date comparator
	 * 
	 * 0.2 05.08.2019
	 * @author Oleg
	 */
	private static class HumanBirthdateComparator extends HumanComparator<Human> {

		/**
		 * Default constructor
		 */
		public HumanBirthdateComparator() {
			super();
		}

		/**
		 * Constructor accepting reverse value
		 * 
		 * @param reverse <code>boolean</code>
		 */
		public HumanBirthdateComparator(boolean reverse) {
			super(reverse);
		}

		@Override
		public int compare(Human human1, Human human2) {
			int result = NullChecker.check(human1, human2);

			if (result == NullChecker.NOT_NULL) {
				result = human1.getBirthdate().compareTo(human2.getBirthdate());
			}

			return super.isReverse() ? result * -1 : result;
		}
	}
}