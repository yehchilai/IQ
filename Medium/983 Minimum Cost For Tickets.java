/**
This question is from https://leetcode.com/problems/minimum-cost-for-tickets/
DIfficulty: medium

In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.

Train tickets are sold in 3 different ways:

a 1-day pass is sold for costs[0] dollars;
a 7-day pass is sold for costs[1] dollars;
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.

Return the minimum number of dollars you need to travel every day in the given list of days.



Example 1:

Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation:
For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total you spent $11 and covered all the days of your travel.
Example 2:

Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
Output: 17
Explanation:
For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
In total you spent $17 and covered all the days of your travel.


Note:

1 <= days.length <= 365
1 <= days[i] <= 365
days is in strictly increasing order.
costs.length == 3
1 <= costs[i] <= 1000
*/

// T:O(N), S:O(N), 2 ms (44.25%)
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        if(days == null || days.length == 0) return 0;

        Deque<int[]> pass7 = new ArrayDeque();
        Deque<int[]> pass30 = new ArrayDeque();
        int ans = 0;

        for(int day : days){
            while(pass7.size() > 0 && (day - pass7.peek()[0]) >= 7) pass7.poll();
            while(pass30.size() > 0 && (day - pass30.peek()[0]) >= 30) pass30.poll();
            pass7.add(new int[]{day, ans + costs[1]});
            pass30.add(new int[]{day, ans + costs[2]});
            // System.out.println("7: "+Arrays.toString(pass7.peek()));
            // System.out.println("30: "+Arrays.toString(pass30.peek()));

            int min = Math.min(ans + costs[0], pass7.peek()[1]);
            min = Math.min(min, pass30.peek()[1]);
            ans = min;
        }

        return ans;
    }
}

// T:O(365), S:O(365), 1 ms (94.03%)
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        if(days == null || days.length == 0) return 0;

        int[] memo = new int[366];
        memo[0] = 0;
        int index = 0;
        for(int i = 1 ;i < 366; i++){
            if(index >= days.length || i != days[index]){
                memo[i] = memo[i - 1];
            }else{
                memo[i] = Math.min(memo[i - 1] + costs[0], memo[Math.max(0, i - 7) ] + costs[1]);
                memo[i] = Math.min(memo[i], memo[Math.max(0, i - 30)] + costs[2]);
                index++;
                // System.out.println(i+", "+memo[i]);
            }

        }

        return memo[365];
    }
}