/*
This question is from https://leetcode.com/problems/reverse-words-in-a-string/
Difficulty: medium


Given an input string, reverse the string word by word.



Example 1:

Input: "the sky is blue"
Output: "blue is sky the"
Example 2:

Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:

Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.


Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.


Follow up:

For C programmers, try to solve it in-place in O(1) extra space.
*/

// T:O(N), S:O(N), 5 ms
class Solution {
    public String reverseWords(String s) {
        LinkedList<String> list = new LinkedList();

        StringBuilder sb = new StringBuilder();

        int len = s.length();

        for(int i = len - 1; i >= 0; i--){
            char c = s.charAt(i);

            if(c == ' '){
                if(sb.length() > 0) list.add(sb.reverse().toString());
                sb = new StringBuilder();
                continue;
            }

            sb.append(c);
        }

        if(sb.length() > 0) list.add(sb.reverse().toString());

        return String.join(" ",list.toArray(new String[list.size()]));
    }
}

// T:O(N), S:O(1), 30 ms
class Solution{
    public String reverseWords(String s) {
        Scanner parts = new Scanner(s);

        String result = "";

        while(parts.hasNext()){
            result = parts.next() + " " + result;
        }

        return result.trim();
    }
}