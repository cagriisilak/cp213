package cp213;

/**
 * @author Cagri Isilak 210764050
 * @version 2022-09-28
 */
public class Cipher {
    // Constants
    public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int ALPHA_LENGTH = ALPHA.length();

    /**
     * Encipher a string using a shift cipher. Each letter is replaced by a letter
     * 'n' letters to the right of the original. Thus for example, all shift values
     * evenly divisible by 26 (the length of the English alphabet) replace a letter
     * with itself. Non-letters are left unchanged.
     *
     * @param s string to encipher
     * @param n the number of letters to shift
     * @return the enciphered string in all upper-case
     */
    public static String shift(final String s, final int n) {

    	if (s == null) return null;
        final int shift = n % 26;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isLetter(c)) {
                c = (char) (c + shift);
                if (Character.isLowerCase(chars[i]) && c > 'z' || Character.isUpperCase(chars[i]) && c > 'Z') {
                    c = (char) (c - 26);
                }
                chars[i] = c;
            }
        }
        return new String(chars);
    }


    /**
     * Encipher a string using the letter positions in ciphertext. Each letter is
     * replaced by the letter in the same ordinal position in the ciphertext.
     * Non-letters are left unchanged. Ex:
     *
     * <pre>
    Alphabet:   ABCDEFGHIJKLMNOPQRSTUVWXYZ
    Ciphertext: AVIBROWNZCEFGHJKLMPQSTUXYD
     * </pre>
     *
     * A is replaced by A, B by V, C by I, D by B, E by R, and so on. Non-letters
     * are ignored.
     *
     * @param s          string to encipher
     * @param ciphertext ciphertext alphabet
     * @return the enciphered string in all upper-case
     */
    public static String substitute(final String s, final String ciphertext) {

    	if (s == null) return null;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isLetter(c)) {
                int index = (Character.isLowerCase(c)) ? c - 'a' : c - 'A';
                c = ciphertext.charAt(index);
                c = (Character.isLowerCase(chars[i])) ? Character.toLowerCase(c) : Character.toUpperCase(c);
                chars[i] = c;
            }
        }
        return new String(chars);
    }
    
}
