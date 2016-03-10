/*
This question is from https://leetcode.com/problems/zigzag-conversion/
Difficulty: easy

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

Time Complexity: O(N)
*/
// 13ms
public class Solution {
    public String convert(String s, int numRows) {
    	if(numRows == 1) return s;
        StringBuilder[] sb = new StringBuilder[numRows];
        boolean rev = false;
        int index = 0;
        int row = 0;
        int len = s.length();
        for (int i = 0; i < sb.length; i++) {
		    sb[i] = new StringBuilder("");
		}
        while(index < len){
        	if(rev){
        		sb[row].append(s.charAt(index));
        		index++;
        		row--;
        		if(row == 0){
        			rev = false;
        			row = 0;
        		}else if(row < 0){
        		    rev = false;
        			row = 1;
        		}
        	}else{
        		sb[row].append(s.charAt(index));
        		index++;
        		row++;
        		if(row >= numRows){
        			rev = true;
        			row = numRows - 2;
        		} 
        	}
        }
        for(int i = 1; i < numRows ; i++){
        	sb[0].append(sb[i].toString());
        }
        return sb[0].toString();
    }
}

// alternative: 6ms
public class Solution {
    public String convert(String s, int numRows) {
        if(numRows < 2 || s.length() < numRows) return s;
        char[] converted = new char[s.length()];
        int step = (numRows - 1) << 1, index = 0;
        for(int i = 0; i <= numRows - 1; ++i) {
            for(int j = i, addChar = step - i; j < s.length(); j += step, addChar += step) {
                converted[index++] = s.charAt(j);
                if(i % (numRows - 1) != 0 && addChar < s.length()) converted[index++] = s.charAt(addChar);
            }
        }
        return new String(converted);
    }
}