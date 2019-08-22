/**
This question is from https://leetcode.com/problems/high-five/
Difficulty: easy

Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.

Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  The average score is calculated using integer division.



Example 1:

Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
Output: [[1,87],[2,88]]
Explanation:
The average of the student with id = 1 is 87.
The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.


Note:

1 <= items.length <= 1000
items[i].length == 2
The IDs of the students is between 1 to 1000
The score of the students is between 1 to 100
For each student, there are at least 5 scores

*/

// T:O(NlogN), S:O(1), 4 ms (88.21%)
class Solution {
    public int[][] highFive(int[][] items) {

        if(items == null || items.length == 0) return items;
        Arrays.sort(items, new ScoreComparator());

        // System.out.println(Arrays.toString(items[0]));

        LinkedList<int[]> ans = new LinkedList();

        int id = items[0][0];
        int score = 0;
        int count = 0;

        for(int i  = 0; i < items.length; i++){
            int currentID = items[i][0];
            int currentScore = items[i][1];

            if(id == currentID){
                if(count >= 5) continue;
                count++;
                score += currentScore;
                // System.out.println("id: "+id+", count: "+ count + ", score: "+ score);

            }else{
                // System.out.println("id: "+id+", count: "+ count + ", score: "+ score);
                ans.add(new int[]{id, score / 5});
                id = currentID;
                score = currentScore;
                count = 1;
                // System.out.println("id: "+id+", count: "+ count + ", score: "+ score);

            }
        }

        ans.add(new int[]{id, score / 5});

        int[][] result = new int[ans.size()][2];

        for(int i = 0; i < result.length; i++){
            result[i][0] = ans.get(i)[0];
            result[i][1] = ans.get(i)[1];
        }
        return result;
    }
}

class ScoreComparator implements Comparator<int[]>{
    @Override
    public int compare(int[] a, int[] b){
        if(a[0] == b[0]){
            return b[1] - a[1];
        }else{
            return a[0] - b[0];
        }
    }
}