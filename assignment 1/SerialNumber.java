package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Cagri Isilak 210764050
 * @version 2022-09-23
 */
public class SerialNumber {

    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(final String str) {

    	for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(final String sn) {

    	if (sn.length() != 11) return false;
        if (!sn.startsWith("SN/")) return false;
        if (!Character.isDigit(sn.charAt(3)) || !Character.isDigit(sn.charAt(4)) || !Character.isDigit(sn.charAt(5)) || !Character.isDigit(sn.charAt(6))) return false;
        if (sn.charAt(7) != '-') return false;
        if (!Character.isDigit(sn.charAt(8)) || !Character.isDigit(sn.charAt(9)) || !Character.isDigit(sn.charAt(10))) return false;
        return true;
    }

    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {

    	while (fileIn.hasNextLine()) {
            String line = fileIn.nextLine();
            if (validSn(line)) {
                goodSns.println(line);
            } else {
                badSns.println(line);
            }
        }
    
	return;
    }

}
