/*
This question is from https://leetcode.com/problems/find-the-difference/
Difficulty: easy

Given two strings s and t which consist of only lowercase letters.

String t is generated by random shuffling string s and then add one more letter at a random position.

Find the letter that was added in t.

Example:

Input:
s = "abcd"
t = "abcde"

Output:
e

Explanation:
'e' is the letter that was added.

*/

// bit manipulate: 7ms
public class Solution {
    public char findTheDifference(String s, String t) {
        int sumS = 0;
        int lengthS = s.length();
        int sumT = 0;
        int lengthT = t.length();
        for(int i = 0 ;i < lengthS; i++){
            sumS = sumS + (int)s.charAt(i);
        }
        
        for(int i = 0 ;i < lengthT; i++){
            sumT = sumT + (int)t.charAt(i);
        }
        
        return (char)(sumT-sumS);
    }
}

// HashMap/Hashtable: 32ms
public class Solution {
    public char findTheDifference(String s, String t) {
        Map<Character, Integer> table = new HashMap<Character, Integer>();
        int sLength = s.length();
        int tLength = t.length();
        for(int i = 0 ; i < sLength; i++){
            Integer count = table.get(s.charAt(i));
            if(count == null){
                table.put(s.charAt(i), 1);
            }else{
                count = count + 1;
                table.put(s.charAt(i), count); 
            }
        }
        
        for(int i = 0; i < tLength; i++){
            Integer count = table.get(t.charAt(i));
            if(count == null || count == 0){
                return t.charAt(i);
            } else{
                count = count - 1;
                table.put(t.charAt(i), count);
            }
        }
        
        return ' ';
    }
}