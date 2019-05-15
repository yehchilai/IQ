/*
This question is from https://leetcode.com/problems/queue-reconstruction-by-height/
Difficulty: medium

Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

*/
// T:O(NlogN), S:O(N), 7ms
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        LinkedList<int[]> ans = new LinkedList();

        if(people == null || people.length == 0) return new int[0][0];

        Arrays.sort(people, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                 if(a[0] != b[0]){
                return b[0] - a[0]; // descending
                }else{
                    return a[1] - b[1]; // ascnnding
                }
            }

        });

        for(int i = 0; i < people.length; i++){
            if(people[i][1] >= ans.size()){
                ans.addLast(people[i]);
            }else{
                ans.add(people[i][1], people[i]);
            }
        }

        return ans.toArray(new int[ans.size()][2]);
    }
}


// Greedy?, T:O(NlogN), M:O(N), 19ms
public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if(people.length < 1) return new int[0][0];

        Arrays.sort(people, new arrComparator());
        LinkedList<int[]> list = new LinkedList<>();

        for(int i = 0; i < people.length; i++){
            if(people[i][1] >= list.size()){
                list.addLast(people[i]);
            }else{
                list.add(people[i][1],people[i]);
            }

        }

        return list.toArray(new int[people.length][]);
    }

    public class arrComparator implements Comparator<int[]>{
        @Override
        public int compare(int[] a, int[] b){
            // if(a[0] > b[0]) return -1;
            // if(a[0] < b[0]) return 1;
            // return 0;

            if(a[0] != b[0]){
                return -a[0] + b[0]; // Fisrt element: descending
            }else{
                return a[1] - b[1];  // Second element: ascending
            }

            // return a[0]!=b[0]?-a[0]+b[0]:a[1]-b[1];
        }
    }
}
