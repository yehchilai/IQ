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

Time Complexity: O()
*/
public class Solution {
    public String convert(String s, int numRows) {
    	if(numRows == 0) return s;
        StringBuilder[] sb = new StringBuilder[numRows];
        boolean rev = false;
        int index = 0;
        int row = 0;
        int len = s.length();
        while(index < len){
        	if(rev){
        		sb[row].append(s.charAt(index));
        		index++;
        		row--;
        		if(row == 0){
        			rev = false;
        			row++;
        		}
        	}else{
        		sb[row].append(s.charAt(index));
        		index++;
        		row++;
        		if(row == numRows){
        			rev = true;
        			row = row - 2;
        		} 
        	}
        }
        for(int i = 1; i < numRows ; i++){
        	sb[0].append(sb[i].toString());
        }
        return sb[0].toString();
    }
}