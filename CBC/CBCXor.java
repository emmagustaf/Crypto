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
		//MemoryStream mStream = new MemoryStream(encrypted);
		byte[] decrypt = null;
		//decrypt = mstream.toByteArray(encrypted);
		System.out.println("\n In the function");
        System.out.println("print first byte of encrypted message " +encrypted[0]);
        System.out.println("print first byte of encrypted message " +(encrypted[1]^encrypted[1]));
        byte[] msg1 = new byte[108];
        byte[] msg2 = new byte[108];
        byte[] pmsg2 = new byte[108];
        byte[] pmsg1 = new byte[108];
        //long myArray[] = new long[9];
        //myArray[0] = (long)encrypted[1];
        for(int i=0; i<108; i++) {
        	pmsg2[i] = (byte)i;
        }
        //encrypting pmsg2 considering same IV and key
        for(int i=0; i<108; i++) {
            if(i<12) {//keeping the first block that is IV same so (K + IV) remains same as given encrypted message
                msg2[i] = encrypted[i];
                //System.out.println(" "+msg2[i]);
            }
            else {//computing next byte from previous byte
                msg2[i] = (byte)((int)msg2[i-12]^(int)pmsg2[i]);
            }
        	
        }
        //reconstructing the message 1 by XORing both the cipher text
        for(int i=11; i<108; i++) {
        	msg1[i] = (byte)((int)msg2[i]^(int)encrypted[i]);
            pmsg1[i] = (byte)((int)msg1[i]^(int)pmsg2[i]);
            //System.out.println(pmsg1[i]);
           
        }
        

 
		return new String(encrypted);
	}
}