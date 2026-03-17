
/**
 * Uses a stack to handle operator precedence by evaluating * and / immediately, while deferring + and -.
 * Maintains a running number and applies the previous operator when a new operator or end is encountered.
 * Time: O(n) single pass | Space: O(n) for stack
 */
class BasicCalculatorII {
    public int calculate(String s) {

        char operator = '+'; 
        Stack<Integer> stk = new Stack<>(); 
        int result = 0;
        int currNum = 0;

        for(int i = 0; i < s.length() ; i++) {
            char c = s.charAt(i); 

            if(Character.isDigit(c)) {
                // Build multi-digit number
                currNum = currNum * 10 + c - '0';
            }

            // Trigger evaluation when we hit an operator OR end of string
            if((!Character.isDigit(c) && !Character.isWhitespace(c)) || i == s.length() - 1) {

                // Key idea:
                // '+' and '-' are deferred (pushed to stack),
                // '*' and '/' are resolved immediately with previous number
                if(operator == '+') {
                    stk.push(currNum);
                }

                if(operator == '-') {
                    stk.push(-currNum);
                }

                if(operator == '*') {
                    stk.push(stk.pop() * currNum);
                }

                if(operator == '/') {
                    // Java integer division truncates toward zero (important edge case)
                    stk.push(stk.pop() / currNum);
                }

                // Update operator AFTER processing current number
                operator = c;
                currNum = 0; // Reset for next number
            }
        }

        // Final result is sum of all processed values (only + and - remain)
        while(!stk.isEmpty()) {
            result += stk.pop();
        }

        return result;
    }
}