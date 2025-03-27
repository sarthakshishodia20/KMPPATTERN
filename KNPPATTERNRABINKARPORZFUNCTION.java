public class KMPWithBruteForceLPS {
    // Brute-force method to compute LPS array
    static int[] computeLPSBruteForce(String pattern) {
        int M = pattern.length();
        int[] lps = new int[M];

        for (int i = 0; i < M; i++) {
            for (int j = i; j > 0; j--) {
                if (pattern.substring(0, j).equals(pattern.substring(i - j + 1, i + 1))) {
                    lps[i] = j;
                    break;
                }
            }
        }
        return lps;
    }

    // KMP algorithm for pattern searching
    static void KMPSearch(String text, String pattern) {
        int N = text.length();
        int M = pattern.length();
        int[] lps = computeLPSBruteForce(pattern); // Use brute-force LPS

        int i = 0, j = 0; // i for text, j for pattern
        while (i < N) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            if (j == M) { // Found a match
                System.out.println("Pattern found at index " + (i - j));
                j = lps[j - 1]; // Use LPS array to avoid unnecessary comparisons
            } else if (i < N && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1]; // Move to previous LPS position
                } else {
                    i++; // Move text index forward if no LPS available
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "ABABABABDABABD";
        String pattern = "ABABD";

        System.out.println("LPS Array: ");
        int[] lps = computeLPSBruteForce(pattern);
        for (int value : lps) {
            System.out.print(value + " ");
        }
        System.out.println("\n\nKMP Pattern Search:");
        KMPSearch(text, pattern);
    }
}
