// Compilation (CryptoLibTest contains the main-method):
//   javac CryptoLibTest.java
// Running:
//   java CryptoLibTest
import java.lang.Math;
public class CryptoLib {

	/**
	 * Returns an array "result" with the values "result[0] = gcd",
	 * "result[1] = s" and "result[2] = t" such that "gcd" is the greatest
	 * common divisor of "a" and "b", and "gcd = a * s + b * t".
	 **/
	public static int[] EEA(int a, int b) {
		// Note: as you can see in the test suite,
		// your function should work for any (positive) value of a and b.
			int gcd = gcd(a, b);
			int s = -1;
			int t = -1;
			int s0 = 1;
			int s1 = 0;
			int t0 = 0;
			int t1 = 1;
			int[] result = new int[3];
			// Don't really know why, but when a == b s and t have the otherones value
			if(a == b){
				s0 = 0;
			 	s1 = 1;
				t0 = 1;
				t1 = 0;
			}
			while(b != 0){
				int r = a % b;
				int q = a / b;
				s = s0 - s1*q;
				t = t0 - t1*q;
				s0 = s1;
				s1 = s;
				t0 = t1;
				t1 = t;
				a = b;
				b = r;
			}
			result[0] = gcd;
			result[1] = s0;
			result[2] = t0;
			return result;
	}
	// helper function to calculate gcd
	public static int gcd(int a, int b){
		if(b==0){
			return a;
		}else{
			return gcd(b, a%b);
		}
	}
	/**
	 * Returns Euler's Totient for value "n".
	 **/
	public static int EulerPhi(int n) {
		return -1;
	}

	/**
	 * Returns the value "v" such that "n*v = 1 (mod m)". Returns 0 if the
	 * modular inverse does not exist.
	 **/
	public static int ModInv(int n, int m) {
		for(int v = 0; v<m; v++){
			if(((n*v)%m) == 1){
				return v;
			}
		}
		return 0;
	}

	/**
	 * Returns 0 if "n" is a Fermat Prime, otherwise it returns the lowest
	 * Fermat Witness. Tests values from 2 (inclusive) to "n/3" (exclusive).
	 **/
	public static int FermatPT(int n) {
		return -1;
	}

	/**
	 * Returns the probability that calling a perfect hash function with
	 * "n_samples" (uniformly distributed) will give one collision (i.e. that
	 * two samples result in the same hash) -- where "size" is the number of
	 * different output values the hash function can produce.
	 **/
	public static double HashCP(double n_samples, double size) {
		double result = 1 ;
		for(int i = 0; i <n_samples; i++){
			result = result*((size-i)/size);
		}
		return 1-result;
	}
}
