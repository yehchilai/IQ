/*
This question is from https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
Difficulty: medium

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
*/
// T: O(3^N), S: O(3^N), 4ms
class Solution {
    public List<String> letterCombinations(String digits) {

        // a queue to restore all possible result
        LinkedList<String> result = new LinkedList();

        // check the input
        if(digits.length() == 0) return result;

        // get the digits mapping
        String[] map = {"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

        // digits length
        int len = digits.length();

        // set the initial charactor
        result.add("");
        for(int i = 0; i < len; i++){
            // get the current digit
            int digit = Character. getNumericValue(digits.charAt(i));
            // get the mapping charactors of the digit
            String str = map[digit];
            // check if the string length of all elements is less than i+1
            while(result.peek().length() < i+1){
                // get the first string in the queue
                String first = result.poll();
                // digit mapping length
                int dLen = str.length();
                // go through all charactor and put the combination back to the queue
                for(int j = 0; j < dLen; j++){
                    result.add(first + str.charAt(j));
                }
            }
        }

        return result;
    }
}