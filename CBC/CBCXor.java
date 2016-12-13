import java.io.BufferedReader;
import java.io.FileReader;

import javax.xml.bind.DatatypeConverter;

public class CBCXor {

	public static void main(String[] args) {
		String filename = "input.txt";
		byte[] first_block = null;
		byte[] encrypted = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			first_block = br.readLine().getBytes();
			encrypted = DatatypeConverter.parseHexBinary(br.readLine());
			br.close();
		} catch (Exception err) {
			System.err.println("Error handling file.");
			err.printStackTrace();
			System.exit(1);
		}
		String m = recoverMessage(first_block, encrypted);
		System.out.println("Recovered message: " + m);
	}

	/**
	 * Recover the encrypted message (CBC encrypted with XOR, block size = 12).
	 *
	 * @param first_block
	 *            We know that this is the value of the first block of plain
	 *            text.
	 * @param encrypted
	 *            The encrypted text, of the form IV | C0 | C1 | ... where each
	 *            block is 12 bytes long.
	 */
	private static String recoverMessage(byte[] first_block, byte[] encrypted) {
		byte [] iv = new byte[12];
		byte[] decrypted = new byte [encrypted.length-1];
		byte[] msg1 = new byte [12];
		byte[] msg2 = new byte [12];
		byte[] k = new byte[12];
		for(int i = 0; i<iv.length; i++){ // IV
				iv[i] = encrypted[i];
				k[i] = (byte)(iv[i]^encrypted[i+12]^first_block[i]);
				msg1[i] = (byte)(k[i]^first_block[i]^iv[i]);

		}
		int j = 0;
		for(int i=24; i<encrypted.length-3; i++){ // Added "-3" because of the padding
			decrypted[i-24] = (byte)(k[j]^msg1[j]^encrypted[i]);
			msg2[j] = encrypted[i]; // save previous C in msg2
			if(j==11){
				j=0;
				msg1=msg2;
			}else{
				j++;
			}
		}
		return new String(decrypted);
	}
}
