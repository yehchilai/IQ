/*
This question is from https://leetcode.com/problems/jewels-and-stones/description/
difficulty: easy

You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".

Example 1:

Input: J = "aA", S = "aAAbbbb"
Output: 3
Example 2:

Input: J = "z", S = "ZZ"
Output: 0
Note:

S and J will consist of letters and have length at most 50.
The characters in J are distinct.

*/
class Solution {
    public int numJewelsInStones(String J, String S) {
        HashMap<Character, Integer> stones = new HashMap();
        int sLength = S.length();
        for (int i = 0; i < sLength; i++){
            char c = S.charAt(i);
            if (stones.containsKey(c)){
                stones.put(c, stones.get(c) + 1);
            }else{
                stones.put(c, 1);
            }
        }

        int output = 0;
        int jLength = J.length();
        for(int i=0; i< jLength; i++){
            char c = J.charAt(i);
            if(stones.containsKey(c)){
                output = output + stones.get(c);
            }
        }

        return output;
    }
}