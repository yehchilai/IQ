/*
This question is from https://leetcode.com/problems/count-and-say/
Difficulty: easy

The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.

Time Complexity: O()
*/
// 22ms
public class Solution {
    public String countAndSay(int n) {
    	String result = "1";
        if(n == 1) return result;
        for(int i = 1 ; i< n; i++){
        	char[] str = result.toCharArray();
        	result = "";
        	int count = 1;
        	char previous = str[0];
        	for(int j = 1; j < str.length; j++){
        		if(previous != str[j]){
        			result += Integer.toString(count) + Character.toString(previous);
        			previous = str[j];
        			count = 1;
        		}else{
        			count++;
        		}
        	}
        	result += Integer.toString(count) + Character.toString(previous);
        }
        return result;
    }
}
// alternative(2ms) : https://leetcode.com/discuss/79263/java-easy-to-read-also-fast
public class Solution {
   public String countAndSay(int n) {
        String ret = ""+1;
    
        while(--n  > 0)
            ret = apply(ret);
    
        return ret;
    }
    
    String apply(String s){
        StringBuilder ret = new StringBuilder();
    
        for(int i = 0, count =0; i  < s.length() ; ){
            while(i + count < s.length() && s.charAt(i) == s.charAt(i + count))
                count ++;
    
            ret.append(count).append(s.charAt(i));
            i += count; 
            count = 0;
        }
    
        return ret.toString();
    }
}