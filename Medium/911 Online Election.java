/*
This question is from https://leetcode.com/problems/online-election/
Difficulty: medium

In an election, the i-th vote was cast for persons[i] at time times[i].

Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.

Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.



Example 1:

Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
Output: [null,0,1,1,0,0,1]
Explanation:
At time 3, the votes are [0], and 0 is leading.
At time 12, the votes are [0,1,1], and 1 is leading.
At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
This continues for 3 more queries at time 15, 24, and 8.


Note:

1 <= persons.length = times.length <= 5000
0 <= persons[i] <= persons.length
times is a strictly increasing array with all elements in [0, 10^9].
TopVotedCandidate.q is called at most 10000 times per test case.
TopVotedCandidate.q(int t) is always called with t >= times[0].

*/
// T:O(N), S:O(N), 149 ms
class TopVotedCandidate {

    List<Vote> list;

    public TopVotedCandidate(int[] persons, int[] times) {

        list = new ArrayList();

        int leading = -1;
        int countForLeading = 0;
        HashMap<Integer, Integer> count = new HashMap();

        for(int i = 0; i < persons.length; i++){
            int person = persons[i];
            int currentCount = count.getOrDefault(person, 0) + 1;
            count.put(person, currentCount);

            if(currentCount >= countForLeading){

                if(leading != person){
                    leading = person;
                    list.add(new Vote(leading, times[i]));
                }
                countForLeading = currentCount;
            }
        }
    }

    public int q(int t) {
        int low = 1;
        int high = list.size();

        while(low < high){
            int mid = low + (high - low)/2;
            int midTime = list.get(mid).time;
            if(midTime <= t){
                low = mid + 1;
            }else{
                high = mid;
            }
        }

        return list.get(low - 1).person;
    }
}

class Vote{
    int person;
    int time;

    Vote(int p, int t){
        this.person = p;
        this.time = t;
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */