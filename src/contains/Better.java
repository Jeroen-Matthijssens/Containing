package contains;

/**
 * class Better
 * @author Jeroen Matthijssens
 * @email jeroen.matthijssens@gmail.com
 */
public class Better implements ContainCounter {

	private int d;

	public Better (double digit) {
		this.d = (int) digit;
	}

	@Override
	public double containing (double n) {
		// return powerOfTen (Math.log10 (n));
		return arbitrary (n);
	}

	private double arbitrary (double n) {

		if ( n <= 10 && n < d) { return 0; }
		else if ( n <= 10 ) { return 1; }

		double result;
		int first = getFirstDigit (n);
		// We don't want the actual log, we want the log of the highest power of 10 lower
		// than this number; casting to an int drops the decimal part which accomplishes
		// this.
		double log = (int) Math.log10 (n);
		double lower = powerOfTen (log - 1);

		/* System.out.println ("log and lower: " + log + " " + lower); */

		if ( first < d ) { result = first * lower; }
		else if ( first == d ) { result = first * lower + getLast (n) + 1; }
		else { result = (first-1) * lower + Math.pow (10, log); }

		/* System.out.println ("result for " + n + " " + result); */

		if ( first != d ) { result += arbitrary (getLast (n)); }

		/* System.out.println ("result for " + n + " " + result); */

		return result;
	}

	private double powerOfTen (double power) {
		double result = 1;
		double currentPow = 10;
		for ( int i = 0; i < power; i++ ) {
			result = 9 * result + currentPow;
			currentPow = currentPow * 10;
		}
		return result;
	}

	// these are helper functions that are horribly implemented.
	private int getFirstDigit (double n) {
		return Integer.parseInt (Double.toString (n).substring(0, 1));
	}

	private double getLast (double n) {
		return Double.parseDouble (Double.toString (n).substring (1));
	}

}