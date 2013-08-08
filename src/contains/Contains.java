package contains;


/**
 * class Contains
 * @author Jeroen Matthijssens
 * @email jeroen.matthijssens@gmail.com
 */
public class Contains {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		ContainCounter s = new Simple (3);
		ContainCounter b = new Better (3);
		ContainCounter bb = new BetterR (3);
		ContainCounter bR = new BetterRR (3);

		long lower = Long.MAX_VALUE - 10000000;
		long upper = Long.MAX_VALUE;
		
		// test (s, bR, 0, 5000);
		// test (s, bb, 0, 5000);
		test (bb, bR, upper, lower);
		// time (bb, lower, upper);
		// time (bR, lower, upper);
		System.out.println(bR);
		System.out.println(bR.containing (Long.MAX_VALUE));
	}
	
	private static void time (ContainCounter c, long lower, long upper) {
		final long startTime = System.currentTimeMillis();
		
		for (long i = lower; i < upper; i++) { c.containing (i); }

		final long stopTime = System.currentTimeMillis();
		System.out.println (stopTime - startTime);
	}

	private static void test (ContainCounter first, ContainCounter second, long lower
			, long upper) {
		
		System.out.println ("---");

		for (long i = lower; i < upper; i++) {
			if ( first.containing (i) != second.containing (i) ) {
				System.out.println (i);
				System.out.println ("first " + first.containing (i));
				System.out.println ("second " + second.containing (i));
				System.out.println ();
			}
		}

		System.out.println ("---");
	}
}
