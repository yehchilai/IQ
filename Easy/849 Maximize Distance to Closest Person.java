/*
This question is from https://leetcode.com/problems/maximize-distance-to-closest-person/
Difficulty: easy

In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.

Return that maximum distance to closest person.

Example 1:

Input: [1,0,0,0,1,0,1]
Output: 2
Explanation:
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
Example 2:

Input: [1,0,0,0]
Output: 3
Explanation:
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.
Note:

1 <= seats.length <= 20000
seats contains only 0s or 1s, at least one 0, and at least one 1.
*/
// T:O(N), S:O(1), 2 ms
class Solution {
    public int maxDistToClosest(int[] seats) {
        int max = 0;
        int count = 0;
        for(int i = 0; i < seats.length; i++){
            if(seats[i] == 1){
                count = 0;
            }else{
                count++;
                max = Math.max(max, (count + 1)/2);
            }
        }

        for(int i = 0; i < seats.length; i++){
            if(seats[i] == 1){
                max = Math.max(max, i);
                break;
            }
        }

        for(int i = seats.length - 1; i>=0; i--){
            if(seats[i] == 1){
                max = Math.max(max, seats.length - 1 - i);
                break;
            }
        }

        return max;
    }
}

// T:O(N), S:O(N), 4 ms
class Solution {
    public int maxDistToClosest(int[] seats) {
        ArrayList<Integer> list = new ArrayList();

        for(int i = 0; i < seats.length; i++){
            if(seats[i] == 1) list.add(i);
        }

        int max = 0;

        if(list.size() > 0) max = list.get(0);

        for(int i = 0; i < list.size() - 1; i++){
            max = Math.max(max, (list.get(i+1) - list.get(i))/ 2);
        }

        if(list.size() > 0) max = Math.max(max, seats.length - list.get(list.size() - 1) - 1);

        return max;
    }
}