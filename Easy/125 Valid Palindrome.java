/*
This question is from https://leetcode.com/problems/valid-palindrome/
Difficulty: easy

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome. 

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.

Time Complexity: O()
*/
public class Solution {
    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^A-Za-z0-9]",""); // use replaceAll not replace
        s = s.toLowerCase();
        // int len = s.length();
        int i = 0;
        int j = s.length() - 1;
        while(i < j){
        	if(s.charAt(i) != s.charAt(j)) return false;
        	i++;
        	j--;
        }

        // for(int i=0, j=len -1; i < j; i++, j--){
        // 	if(s.charAt(i) != s.charAt(j)) return false;
        // }

        return true;
    }
}

// Alternative: https://leetcode.com/discuss/60461/my-three-line-java-solution
public class Solution {
    public boolean isPalindrome(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
}