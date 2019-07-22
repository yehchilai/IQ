/**
This question is from https://leetcode.com/problems/string-compression/
DIfficulty: easy

Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.


Follow up:
Could you solve it using only O(1) extra space?


Example 1:

Input:
["a","a","b","b","c","c","c"]

Output:
Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

Explanation:
"aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".


Example 2:

Input:
["a"]

Output:
Return 1, and the first 1 characters of the input array should be: ["a"]

Explanation:
Nothing is replaced.


Example 3:

Input:
["a","b","b","b","b","b","b","b","b","b","b","b","b"]

Output:
Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

Explanation:
Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
Notice each digit has it's own entry in the array.


Note:

All characters have an ASCII value in [35, 126].
1 <= len(chars) <= 1000.

*/
// T:O(N), S:O(1), 1 ms (96.85%)
class Solution {
    public int compress(char[] chars) {
        if(chars == null || chars.length == 0) return 0;
        int index = 0;
        char current = chars[0];
        int count = 1;

        for(int i = 1; i < chars.length; i++){
            char next = chars[i];
            // if chars are different, update count and insert numbers
            if(current != next){
                chars[index] = current;
                index++;
                index = updateChars(chars, index, count);
                count = 1;

                current = next;
            }else{
                count++;
            }
        }
        // update the lest char
        chars[index] = current;
        index++;
        // check the count of the last char
        if(count > 1){
            index = updateChars(chars, index, count);
        }
        return index;
    }

    private int updateChars(char[] chars, int index, int count){
        if(count > 1){
            char[] numbers = Integer.toString(count).toCharArray();
            for(char c : numbers){
                chars[index] = c;
                index++;
            }
        }

        return index;
    }
}