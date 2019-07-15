package training;

import java.util.Comparator;

/**
 * Class containing auxiliary instruments to work with humans
 * 
 * @version 0.1 15.07.2019
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
	 * Abstract comparator that provides reverse functionality
	 * 
	 * @version 0.1 15.07.2019
	 * @author Oleg
	 */
	public static abstract class HumanComparator implements Comparator {
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
	public static Comparator getFirstNameComaparator() {
		return new HumanFirstNameComparator();
	}

	
	/**
	 * Gets first name comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator getFirstNameComaparator(boolean reverse) {
		return new HumanFirstNameComparator(reverse);
	}

	/**
	 * First name comparator
	 * 
	 * 0.1 15.07.2019
	 * @author Oleg
	 */
	private static class HumanFirstNameComparator extends HumanComparator {

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
		public int compare(Object o1, Object o2) {
			int result = NullChecker.check(o1, o2);

			if (result == NullChecker.NOT_NULL) {
				Human human1 = (Human) o1;
				Human human2 = (Human) o2;

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
	public static Comparator getLastNameComaparator() {
		return new HumanLastNameComparator();
	}

	/**
	 * Gets last name comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator getLastNameComaparator(boolean reverse) {
		return new HumanLastNameComparator(reverse);
	}

	/**
	 * Last name comparator
	 * 
	 * 0.1 15.07.2019
	 * @author Oleg
	 */
	private static class HumanLastNameComparator extends HumanComparator {

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
		public int compare(Object o1, Object o2) {
			int result = NullChecker.check(o1, o2);

			if (result == NullChecker.NOT_NULL) {
				Human human1 = (Human) o1;
				Human human2 = (Human) o2;

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
	public static Comparator getGenderComaparator() {
		return new HumanGenderComparator();
	}

	/**
	 * Gets gender comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator getGenderComaparator(boolean reverse) {
		return new HumanGenderComparator(reverse);
	}

	/**
	 * Gender comparator
	 * 
	 * 0.1 15.07.2019
	 * @author Oleg
	 */
	private static class HumanGenderComparator extends HumanComparator {

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
		public int compare(Object o1, Object o2) {
			int result = NullChecker.check(o1, o2);

			if (result == NullChecker.NOT_NULL) {
				Human human1 = (Human) o1;
				Human human2 = (Human) o2;

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
	public static Comparator getBirthdateComaparator() {
		return new HumanBirthdateComparator();
	}

	/**
	 * Gets birth date comparator accepting reverse parameter
	 * 
	 * @param reverse <code>boolean</code>
	 * @return <code>Comparator</code>
	 */
	public static Comparator getBirthdateComaparator(boolean reverse) {
		return new HumanBirthdateComparator(reverse);
	}

	/**
	 * Birth date comparator
	 * 
	 * 0.1 15.07.2019
	 * @author Oleg
	 */
	private static class HumanBirthdateComparator extends HumanComparator {

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
		public int compare(Object o1, Object o2) {
			int result = NullChecker.check(o1, o2);

			if (result == NullChecker.NOT_NULL) {
				Human human1 = (Human) o1;
				Human human2 = (Human) o2;

				result = human1.getBirthdate().compareTo(human2.getBirthdate());
			}

			return super.isReverse() ? result * -1 : result;
		}
	}
}