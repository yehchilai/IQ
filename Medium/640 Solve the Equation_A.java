/*
This question is from https://leetcode.com/problems/solve-the-equation/description/
Difficulty: medium

Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.

If there is no solution for the equation, return "No solution".

If there are infinite solutions for the equation, return "Infinite solutions".

If there is exactly one solution for the equation, we ensure that the value of x is an integer.

Example 1:
Input: "x+5-3+x=6+x-2"
Output: "x=2"
Example 2:
Input: "x=x"
Output: "Infinite solutions"
Example 3:
Input: "2x=x"
Output: "x=0"
Example 4:
Input: "2x+3x-6x=x+2"
Output: "x=-1"
*/
// T:O(N), S:O(1), 12ms
class Solution {
    public String solveEquation(String equation) {
        // string length
        int len = equation.length();
        // edge case
        if(len < 3) return "No solution";

        // split equation
        String[] e = equation.split("=");

        // edge case
        if(e.length != 2) return "No solution";

        // check left and right part string length
        int[] l = simplifyEquation(e[0]);
        int[] r = simplifyEquation(e[1]);
        // left x coefficient
        int xLeft = l[0];
        // left constant
        int left = l[1];
        // right x coeffictent
        int xRight = r[0];
        // right constant
        int right = r[1];
        // System.out.println(xLeft +", "+ left +", "+  xRight +", "+  right);
        // infinite case
        if((xLeft == xRight) && (left == right)) return "Infinite solutions";
        // contains answer
        int x = xLeft - xRight;
        int c = right - left;

        if(x != 0 && c%x == 0){
            return "x=" + String.valueOf(c/x);
        }

        return "No solution";
    }

    private int[] simplifyEquation(String s){
        // x value
        int xValue = 0;
        // constant value
        int cValue = 0;
        // split string
        String[] parts = s.split("(?=[+-])");
        // go throught each part
        for(String str : parts){
            if(str.equals("+x") || str.equals("x")){
                xValue += 1;
            }else if(str.equals("-x")){
                xValue -= 1;
            }else if(str.contains("x")){
                xValue += Integer.valueOf(str.substring(0, str.indexOf("x")));
            }else{
                cValue += Integer.valueOf(str);
            }
        }

        return new int[]{xValue, cValue};
    }
}