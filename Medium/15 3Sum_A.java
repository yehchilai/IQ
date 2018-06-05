/*
This question is from https://leetcode.com/problems/3sum/description/
Difficulty: medium

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/
// T: O(N^2), S:O(N), 108ms
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // sort the array
        Arrays.sort(nums);

        // result
        List<List<Integer>> result = new LinkedList();

        // go through all elements in the array
        for(int i = 0; i < nums.length - 2; i++){
            if(i == 0 || ( i > 0 && nums[i] != nums[i-1])){
                int start = i+1, end = nums.length - 1, sum = 0 - nums[i];
                while(start < end){
                    if((nums[start] + nums[end]) == sum){
                        result.add(Arrays.asList(nums[i], nums[start], nums[end]));
                        while(start < end && nums[start] == nums[start + 1] ) start++;
                        while(start < end && nums[end] == nums[end - 1]) end--;
                        start++;
                        end--;
                    }else if((nums[start] + nums[end]) > sum){
                        end--;
                    }else{
                        start++;
                    }
                }
            }

        }
        return result;
    }
}
// BFS, duplicate result (not work)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // q to store element which need to be process
        Deque<Element> q = new LinkedList<Element>();
        // insert initail elements
        for(int i = 0; i < nums.length; i++){
            Element e = new Element(nums[i]);
            e.visited.add(i);
            q.add(e);
        }
        // number of sums
        int layer = 1;

        // result
        List<List<Integer>> result = new LinkedList();

        while(q.size() > 0){
            if(layer > 3) break;
            int size = q.size();
            // go through all element in this layer
            for(int i = 0; i < q.size(); i++){
                Element e = q.poll();
                print(e.list);
                // check all combination of other elements
                for(int j= 0; j < nums.length; j++){
                    // check if the element is visited
                    if(!e.visited.contains(j)){
                        e.list.add(nums[j]);
                        e.visited.add(j);
                        if(e.list.size() == 3 && sum(e.list) == 0) result.add(new LinkedList(e.list));
                        Element newE= new Element(e.list, e.visited);
                        q.add(newE);
                        e.list.remove(e.list.size() - 1);
                    }
                }
            }
            layer++;
        }

        return result;
    }

    private void print(List<Integer> list){
        for(int i =0; i < list.size(); i++){
            System.out.print(list.get(i));
        }
        System.out.println();
    }

    private int sum(List<Integer> list){
        int sum = 0;
        for(int i = 0; i < list.size(); i++){
            sum += list.get(i);
        }
        return sum;
    }

    class Element{
        List<Integer> list;
        HashSet<Integer> visited;

        public Element(int e){
            this.list = new LinkedList();
            this.list.add(e);
            this.visited = new HashSet();
        }

        public Element(List<Integer> list, Set<Integer> set){
            this.list = new LinkedList(list);
            this.visited = new HashSet(set);
        }
    }
}