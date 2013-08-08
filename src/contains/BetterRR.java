package contains;

import java.util.Arrays;

/**
 * class BetterRR
 * @author Jeroen Matthijssens
 * @email jeroen.matthijssens@gmail.com
 */
public class BetterRR implements ContainCounter {

	private int d;
	private long [] powTen = new long [25];

	public BetterRR (double digit) {
		this.d = (int) digit;
	}

	@Override
	public double containing (double n) {
		return (double) arbitrary ((long) n);
	}

	private long arbitrary (long n) {
		// We don't want the actual log, we want the log of the highest power of 10 lower
		// than this number (or the length of the number); casting to an int drops the
		// decimal part which accomplishes this.
		int log = (int) Math.log10 (n);
		long number = n;
		long result = 0;

		for ( int i = log; i >= 0; i-- ) {
			int first = getDigitAt (number, i);
			long remaining = getPartAfter (number, i);
			long lower = powerOfTen (i);

			// add 1 for the number itself followed by all zeros. This number is not
			// counted by just adding the last part.
			if ( first == d ) { return result + first * lower + remaining + 1; }
			
			if ( first < d ) { result += first * lower; }
			else { result += (first-1) * lower + (long) Math.pow (10, i); }
			
			number = remaining;
		}

		return result;
	}

	// This function could be writen recursively to fill in all lower values in the array
	// but if the array is not large enough, the recursion itself will kill performance.
	private long powerOfTen (int power) {
		if ( power < 1 ) { return 0; }
		if ( power < powTen.length && powTen[power] != 0 ) { return powTen [power]; }

		long result = 1;
		long currentPow = 10;
		for ( int i = 1; i < power; i++ ) {
			result = 9 * result + currentPow;
			currentPow = currentPow * 10;
		}

		if ( power < powTen.length ) { powTen[power] = result; }
		return result;
	}

	private int getDigitAt (long n, int index) {
		return (int) (n / Math.pow (10, index)) % 10;
	}

	private long getPartAfter (long n, int index) {
		return (long) (n % Math.pow (10, index));
	}

	@Override
	public String toString () {
		return Arrays.toString (powTen);
	}
}
