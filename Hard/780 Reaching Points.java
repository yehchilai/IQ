/**
This question is from https://leetcode.com/problems/reaching-points/
Difficulty: hard

A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).

Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.

Examples:
Input: sx = 1, sy = 1, tx = 3, ty = 5
Output: True
Explanation:
One series of moves that transforms the starting point to the target is:
(1, 1) -> (1, 2)
(1, 2) -> (3, 2)
(3, 2) -> (3, 5)

Input: sx = 1, sy = 1, tx = 2, ty = 2
Output: False

Input: sx = 1, sy = 1, tx = 1, ty = 1
Output: True

Note:

sx, sy, tx, ty will all be integers in the range [1, 10^9].
*/

// log(max(tx, ty)), S:O(1), 0 ms (100%)
class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {

        while(tx >= sx && ty >= sy){
            if(tx == ty) break;

            if(tx > ty){

                if(ty > sy){
                    tx = tx % ty;
                }else{
                    return (tx - sx) % ty == 0;
                }

            }else{

                if(tx > sx){
                    ty = ty % tx;
                }else{
                    return (ty - sy) % tx == 0;
                }
            }
        }

        return (tx == sx && ty == sy);
    }
}

// T:O(tx or ty), S:O(1), Time Limit Exceeded
class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {

        while(tx >= sx && ty >= sy){
            if(tx == sx && ty == sy) return true;

            // Repeatedly substract the smaller value of tx and ty.
            if(tx > ty){
                tx = tx - ty;
            }else{
                ty = ty - tx;
            }
        }

        return false;
    }
}

// T:O(2^tx+ty), S:O(tx*ty), stack over flow
class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {

        if(sx > tx || sy > ty) return false;
        if(sx == tx && sy == ty) return true;

        return reachingPoints(sx + sy, sy, tx, ty) || reachingPoints(sx, sy + sx, tx, ty);
    }
}