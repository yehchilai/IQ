/*
This question is from https://leetcode.com/problems/guess-number-higher-or-lower/
Difficulty: easy

We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
Example:
n = 10, I pick 6.

Return 6.
*/

// Binary search, T:O(logN), M:O(1), 2ms
public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int i = 0;
        int j = n;
        int result = 0;
        while(i <= j){
            int current = i + (j-i)/2;
            int g = guess(current);
            if( g < 0){
                j = current - 1;
            }else if(g > 0){
                i = current + 1;
            }else{
                return current;
            }
        }

        return 0;
    }
}
