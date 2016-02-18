/*
This question is from https://leetcode.com/problems/length-of-last-word/
Difficulty: easy

Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.

Time Complexity: O()
*/
public class Solution {
    public int lengthOfLastWord(String s) {
        String[] strArr = s.split(" ");
        if(strArr.length > 0){
            return strArr[strArr.length - 1].length();
        }else{
            return 0;
        }
    }
}

// 0 ms : from https://leetcode.com/discuss/80235/2-lines-0ms-java-solution
public class Solution{
	public int lengthOfLastWord(String s) {
    s= s.trim();
    return s.length() - 1 - s.lastIndexOf(" ");
}
}