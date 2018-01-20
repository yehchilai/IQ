/*
This question is from https://leetcode.com/problems/judge-route-circle/description/
Difficulty: easy

Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.

The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.

Example 1:
Input: "UD"
Output: true
Example 2:
Input: "LL"
Output: false
*/
class Solution {
    public boolean judgeCircle(String moves) {
        int ud=0;
        int lr=0;
        int length = moves.length();
        for(int i=0; i < length; i++){
            // System.out.println(moves.charAt(i));
            switch(moves.charAt(i)){
                case 'U':
                    ud += 1;
                    break;
                case 'D':
                    ud -= 1;
                    break;
                case 'L':
                    lr -= 1;
                    break;
                case 'R':
                    lr += 1;
                    break;
            }
        }
        // System.out.println(ud+ ", "+lr);
        if(ud == 0 && lr == 0){
            return true;
        }else{
            return false;
        }

    }
}