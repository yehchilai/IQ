/*
This question is from https://leetcode.com/problems/string-to-integer-atoi/
Difficulty: easy

Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.

Hint: at the bottom
////////////////////////////
update:
Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
Example 1:

Input: "42"
Output: 42
Example 2:

Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 42.
Example 3:

Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
Example 4:

Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical
             digit or a +/- sign. Therefore no valid conversion could be performed.
Example 5:

Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
             Thefore INT_MIN (−231) is returned.

*/

// 44 ms

public class Solution {
    public int myAtoi(String str) {
        // remove dot and consider the first character array only
        char[] charArr = str.split("\\.")[0].toCharArray();
        int start = 0;
        boolean negative = false;
        if(charArr.length == 0) return 0;
        // remove spaces untill see the first non-space character
        while(charArr[start] == ' '){
                if(start >= charArr.length - 1) break;
                start++;
            }

        // Check the number is positive or negative
        if(charArr[start] == '-'){
            start++;
            negative = true;
        }else if(charArr[start] == '+'){
            start++;
        }else{
            while(charArr[start] == ' '){
                if(start >= charArr.length - 1) break;
                start++;
            }
        }

        // arrange the array, and only consider the digits 0~9
        // if get a non-digit number, get rid of the rest of characters
        int end = charArr.length;
        for(int i = start; i < charArr.length ; i++ ){
            int current = (int)(charArr[i] - '0');
            if(current < 0 || current > 9){
                end = i;
            }
        }

        // check if the character array contains leading zeros, and remove them
        LinkedList<Character> charList = new LinkedList<>();
        int j = start;
        while(j < end){
            charList.add(charArr[j]);
            j++;
        }

        while(charList.peek() != null){
            if(charList.peek() == '0'){
                charList.poll();
            }else{
                break;
            }

        }

        char[] charArrClean = new char[charList.size()];
        for(int i = 0; i < charArrClean.length; i++){
            charArrClean[i] = charList.get(i);
        }

        // compute the updated character array to integer
        return getResult(charArrClean, 0, negative);
    }

    public int getResult(char[] charArr, int start, boolean negative){
        long result = 0;

        // check if the number larger or smaller the capability of Integer value.
        if(charArr.length > 10){
            result = Integer.MAX_VALUE;
            if(negative) result = Integer.MIN_VALUE;
            return (int)result;
        }


        for(int i = start ; i < charArr.length ; i++){
            int current = (int)(charArr[i] - '0');
            if(current < 0 || current > 9){
                int div = charArr.length -1 - i + 1;
                result = result/ (long)Math.pow(10, div);
                if(negative) result = 0 - result;
                if(result > Integer.MAX_VALUE) result = Integer.MAX_VALUE;
                if(result < Integer.MIN_VALUE) result = Integer.MIN_VALUE;
                return (int)result;
            }
            result = result + (long)Math.pow(10, charArr.length -1 - i)*(long)(current);
        }

        if(negative) result = 0 - result;

        if(result > Integer.MAX_VALUE) result = Integer.MAX_VALUE;
        if(result < Integer.MIN_VALUE) result = Integer.MIN_VALUE;

        return (int)result;
    }
}
// "      -11919730356x"
// "9223372036854775809"
// ""
// "+"
// "   -112u3"
// "   -1123u3761867"
// "     +004500"
// "     +   004500"
// "  -0012a42"
// "     "
// "8879362"
// "123f43"
// "+ 1"
// " 1 2 3"
// "    010"
// "1"
// "3.4"
/////////
// ""
// "+"
// "123f43"
// "+1"
// "    010"
// "1"
// "3.4"
// "3.6"
// "10"
// "-1"
// "2147483647"
// "-2147483648"
// "2147483648"
// "-2147483649"
// "9223372036854775807"
// "-9223372036854775808"
// "9223372036854775808"
// "-9223372036854775809"

/*
spoilers alert... click to show requirements for atoi.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.



*/

// https://leetcode.com/problems/string-to-integer-atoi/discuss/131755/Java-Solution-clear-and-easy-to-understand
class Solution {
    public int myAtoi(String str) {
        if (str.replace(" ", "").isEmpty()) return 0;
        boolean start = false;
        StringBuilder sb = new StringBuilder();
        char temp;
        for (int i=0; i<str.toCharArray().length; i++) {
            temp = str.charAt(i);
            if (!start) {
                if (temp==' ') {
                    continue;
                } else if ((temp-'0' > 9 || temp-'0' < 0) && (temp!='-' && temp!= '+')) {
                    return 0;
                } else {
                    sb.append(temp);
                    start = true;
                }
            } else {
                if (temp-'0' > 9 || temp-'0' < 0) break;
                sb.append(temp);
            }
        }

        int result;
        String num = sb.toString().trim();
        if (num.equals("+") || num.equals("-")) return 0;

        try {
            result = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            if (!num.contains("-")) return Integer.MAX_VALUE;
            else return Integer.MIN_VALUE;
        }

        return result;
    }
}