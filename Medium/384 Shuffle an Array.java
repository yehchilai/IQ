/*
This question is from https://leetcode.com/problems/shuffle-an-array/
Difficulty: medium

Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

*/

// ArrayList, T:O(N), M:O(N), 279ms
public class Solution {

    ArrayList<Integer> list;

    public Solution(int[] nums) {
        list = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; i++){
            list.add(nums[i]);
        }
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        int[] arr = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            arr[i] = list.get(i);
        }

        return arr;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        LinkedList<Integer> tmpList = new LinkedList<Integer>();
        tmpList.addAll(list);
        int[] result = new int[list.size()];
        Random random = new Random();
        for(int i = result.length - 1; i >= 0; i--){
            int index = random.nextInt(tmpList.size());
            result[i] = tmpList.get(index);
            tmpList.remove(index);
        }
        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */


// Other solutioin. Using swap method. https://discuss.leetcode.com/topic/53985/well-explained-o-n-java-solution-by-using-random-class-and-swapping-current-with-a-random-previous-index
