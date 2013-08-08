package contains;

/**
 * class Better
 * @author Jeroen Matthijssens
 * @email jeroen.matthijssens@gmail.com
 */
public class BetterR implements ContainCounter {

	private int d;

	public BetterR (double digit) {
		this.d = (int) digit;
	}

	@Override
	public double containing (double n) {
		// return powerOfTen (Math.log10 (n));
		return arbitrary (n);
	}

	private double arbitrary (double n) {

		if ( n <= 10 && n < d ) { return 0; }
		else if ( n <= 10 ) { return 1; }

		// We don't want the actual log, we want the log of the highest power of 10 lower
		// than this number; casting to an int drops the decimal part which accomplishes
		// this.
		int log = (int) Math.log10 (n);
		double lower = powerOfTen (log - 1);
		int first = getDigitAt (n, log);

		// add 1 for the number itself followed by all zeros. This is not counted by just
		// adding the last part.
		if ( first == d ) { return first * lower + getPartAfter (n, log) + 1; }

		double result;
		if ( first < d ) { result = first * lower; }
		else { result = (first-1) * lower + Math.pow (10, log); }

		return result + arbitrary (getPartAfter (n, log));
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

	private int getDigitAt (double n, int index) {
		return (int) (n / Math.pow (10, index)) % 10;
	}

	private double getPartAfter (double n, int index) {
		return (n % Math.pow (10, index));
	}

}
