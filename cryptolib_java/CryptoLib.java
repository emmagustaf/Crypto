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
	/*// helper function to calculate gcd
	public static int gcd(int a, int b){
		if(b==0){
			return a;
		}else{
			return gcd(b, a%b);
		}
	}*/
	/**
	 * Returns Euler's Totient for value "n".
	 **/
	public static int EulerPhi(int n) {
		int count = 1;
		if(n<=0)
			count = 0;
		if(n==1 || n==2)
			count = 1;
		if(isPrime(n) == true && n>0)
			count = n-1;
		else {
			for(int i=2; i<n; i++) {
				if (gcd(n,i)==1) {//coprime
				    count++;
				}
			}
		}
		return count;
	}

	public static boolean isPrime(int n) {
		boolean prime = true;
		if(n%2 == 0)
		   prime = false;
	    else {
		   for(int i=3; i*i<n; i++) {
              if(n%i == 0)
              	prime = false;
		   }
		}
		return prime;
	}

	public static int gcd(int a, int b) {
		int temp;
        if(a<b) {
           temp=a;
           a=b;
           b=temp;
        }
        if(a%b==0) {
         return(b);
        }
        return(gcd(a%b,b));
	}

	/**
	 * Returns the value "v" such that "n*v = 1 (mod m)". Returns 0 if the
	 * modular inverse does not exist.
	 **/
	public static int ModInv(int n, int m) {
		for(int v = 0; v<m; v++){
			int tmp = (n*v)%m;
			if(n<0){
				tmp+=m;
			}
			if(tmp == 1){
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
		for(int i=1; i<n/3; i++) {
           if(remainder(i, n)!=1)
           	return i;//return the lowest fermit witness
		}
		return 0;//when remainder is 1
	}

	public static int remainder(int x, int y) {
		int result = 1;
		for(int i=0; i<y-1; i++) {//to find remainder of i^(n-1)
          result *= x;
          if(result > y)
           result %= y;
		}
		return (result % y);
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
