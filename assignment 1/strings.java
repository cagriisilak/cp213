package cp213;

/**
 * @author Cagri Isilak 210764050
 * @version 2022-09-23
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {

    	int x = string.length();
        for (int i = 0; i < x / 2; i++) {
            if (string.charAt(i) != string.charAt(x - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {

    	if (name.isEmpty()) {
            return false;
        }
        if (!Character.isLetter(name.charAt(0)) && name.charAt(0) != '_') {
            return false;
        }
        for (int i = 1; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {

    	if (word.length() == 0) {
            return "";
        }

        final String lowerWord = word.toLowerCase();
        final char firstChar = lowerWord.charAt(0);
        final boolean startsWithVowel = firstChar == 'a' || firstChar == 'e' || firstChar == 'i' || firstChar == 'o' || firstChar == 'u';

        if (startsWithVowel) {
            if (Character.isUpperCase(word.charAt(0))) {
                return Character.toUpperCase(firstChar) + "way";
            } else {
                return lowerWord + "way";
            }
        } else {
            int consonantEnd = 1;
            while (consonantEnd < lowerWord.length() && !startsWithVowel) {
                final char c = lowerWord.charAt(consonantEnd);
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    break;
                }
                consonantEnd++;
            }
            final String newWord = lowerWord.substring(consonantEnd) + lowerWord.substring(0, consonantEnd) + "ay";
            if (Character.isUpperCase(word.charAt(0))) {
                return Character.toUpperCase(newWord.charAt(0)) + newWord.substring(1);
            } else {
                return newWord;
            }
        }
    }
}
