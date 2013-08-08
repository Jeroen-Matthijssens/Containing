package contains;

/**
 * class Simple
 * @author Jeroen Matthijssens
 * @email jeroen.matthijssens@gmail.com
 */
public class Simple implements ContainCounter {

	private char digit;

	public Simple (double digit) {
		this.digit = Double.toString (digit).toCharArray ()[0];
	}

	@Override
	public double containing (double upper) {
		double count = 0;
		for ( double i = 1; i <= upper; i++ ) {
			if ( numberContains (i) ) { count++; }
		}
		return count;
	}

	private boolean numberContains (double n) {
		String number = Double.toString (n);
		for ( char c : number.toCharArray () ) {
			if ( c == digit ) { return true; }
		}
		return false;
	}

}
