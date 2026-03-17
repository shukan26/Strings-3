/**
 * Recursively breaks the number into chunks (billion, million, thousand, hundred) and converts each part.
 * Key idea: divide the number into smaller subproblems and reuse the same helper for each segment.
 * Time Complexity: O(log n) | Space Complexity: O(log n) due to recursion depth
 */
public class IntegerToEnglishWords {

    List<String> tens = Arrays.asList("", "Ten","Twenty", "Thirty", "Forty","Fifty","Sixty", "Seventy", "Eighty", "Ninety"); 
    List<String> elevens = Arrays.asList("", "Eleven","Twelve", "Thirteen", "Fourteen","Fifteen","Sixteen", "Seventeen", "Eighteen", "Nineteen");
    List<String> ones = Arrays.asList("", "One", "Two", "Three", "Four", "Five", "Six",
        "Seven", "Eight", "Nine");

    public String numberToWords(int num) {
        if (num == 0) return "Zero"; // important edge case
        return helper(num);
    }

    private String helper(int num) {
        String result = "";

        if (num >= 1000000000) {
            // Break into billions + remainder
            result = helper(num / 1000000000) + " Billion " + helper(num % 1000000000);
        } else if (num >= 1000000) {
            // Same pattern reused for each magnitude → core recursive insight
            result = helper(num / 1000000) + " Million " + helper(num % 1000000);
        } else if (num >= 1000) {
            result = helper(num / 1000) + " Thousand " + helper(num % 1000);
        } else if (num >= 100) {
            result = helper(num / 100) + " Hundred " + helper(num % 100);
        } else if (num >= 11 && num <= 19) {
            // Special handling since teens don't follow standard pattern
            result = elevens.get(num - 10);
        } else if (num >= 10) {
            // Combine tens place with recursive handling of ones
            result = tens.get(num / 10) + " " + helper(num % 10);
        } else {
            result = ones.get(num);
        }

        // Trim ensures no trailing/extra spaces from recursive concatenation
        return result.trim();
    }
}