/*
This quesiton is from https://leetcode.com/problems/cut-off-trees-for-golf-event/description/
Difficulty: hard

You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

0 represents the obstacle can't be reached.
1 represents the ground can be walked through.
The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.

You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

Example 1:
Input:
[
 [1,2,3],
 [0,0,4],
 [7,6,5]
]
Output: 6
Example 2:
Input:
[
 [1,2,3],
 [0,0,0],
 [7,6,5]
]
Output: -1
Example 3:
Input:
[
 [2,3,4],
 [0,0,5],
 [8,7,6]
]
Output: 6
Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
Hint: size of the given matrix will not exceed 50x50.
*/
// exceed time limitation
class Solution {

    public int cutOffTree(List<List<Integer>> forest) {

        //min-heap to store tree information and sort tree height in the ascending order
        PriorityQueue<Tree> minHeap = new PriorityQueue(11, new TreeComparator());

        int height = forest.size();
        int width = forest.get(0).size();

        // go through the map and put the tree height and location into a min-heap
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int tree = forest.get(i).get(j);
                if(tree > 1) minHeap.add(new Tree(tree, i, j, 0));
            }
        }

        // initial location to go through all trees
        int distance = 0, startRow = 0, startColumn = 0, targetRow = 0, targetColumn = 0;
        // get the first tree which need to be cut.
        Tree currentTree = minHeap.poll();
        do{
            // System.out.println(currentTree.height + ", row: "+currentTree.row+", column: "+currentTree.column);
            targetRow = currentTree.row;
            targetColumn = currentTree.column;
            int d = distance(forest, startRow, startColumn, targetRow, targetColumn);
            // if d is equal to -1, the target location cannot be reachable.
            if(d == -1){
                return -1;
            }
            distance = distance + d;
            // System.out.println("distance: "+d);
            // update the current position
            startRow = currentTree.row;
            startColumn = currentTree.column;
            // get the next tree
            currentTree = minHeap.poll();
        }
        while(currentTree != null);

        return distance;
    }

    private int distance(List<List<Integer>> forest, int sr, int sc, int tr, int tc){
        // queue to record avalible next moves
        LinkedList<Tree> q = new LinkedList<Tree>();
        // resord travaled trees
        HashSet<Integer> set = new HashSet<Integer>();
        q.add(new Tree(forest.get(sr).get(sc), sr, sc, 0));
        int distance = 0, rows = forest.size(), columns = forest.get(0).size();

        while(q.size()>0){
            Tree currentTree = q.poll();
            // System.out.println("Tree: "+currentTree.height);
            // if the current position is the target position, return the distance from source to here.
            if(currentTree.row == tr && currentTree.column == tc)
                return currentTree.distance;

            // if the current position reachable, put adjacent points into the queue
            if(currentTree.height > 0 && !set.contains(currentTree.height)){
                set.add(currentTree.height);
                // down
                if(currentTree.row - 1 >= 0) q.add(new Tree(forest.get(currentTree.row - 1).get(currentTree.column), currentTree.row - 1, currentTree.column, currentTree.distance + 1));
                // up
                if(currentTree.row + 1 < rows) q.add(new Tree(forest.get(currentTree.row + 1).get(currentTree.column), currentTree.row + 1, currentTree.column, currentTree.distance + 1));
                // left
                if(currentTree.column - 1 >= 0) q.add(new Tree(forest.get(currentTree.row).get(currentTree.column - 1), currentTree.row, currentTree.column - 1, currentTree.distance + 1));
                // right
                if(currentTree.column + 1 < columns) q.add(new Tree(forest.get(currentTree.row).get(currentTree.column + 1), currentTree.row, currentTree.column + 1, currentTree.distance + 1));
            }

        }
        return -1;
    }


    // Tree class to record the tree infromation (height and location)
    class Tree{
        int height;
        int row;
        int column;
        int distance;

        public Tree(int h, int r, int c, int d){
            this.height = h;
            this.row = r;
            this.column = c;
            this.distance = d;
        }
    }

    // Tree comparator ascending
    class TreeComparator implements Comparator<Tree>{
        public int compare(Tree a, Tree b){
            if(a.height < b.height) return -1;
            if(a.height > b.height) return 1;
            return 0;
        }
    }
}

// Solution
class Solution{
	int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList();
        for (int r = 0; r < forest.size(); ++r) {
            for (int c = 0; c < forest.get(0).size(); ++c) {
                int v = forest.get(r).get(c);
                if (v > 1) trees.add(new int[]{v, r, c});
            }
        }

        Collections.sort(trees, (a, b) -> Integer.compare(a[0], b[0]));

        int ans = 0, sr = 0, sc = 0;
        for (int[] tree: trees) {
            int d = dist(forest, sr, sc, tree[1], tree[2]);
            if (d < 0) return -1;
            ans += d;
            sr = tree[1]; sc = tree[2];
        }
        return ans;
    }

    public int dist(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int R = forest.size(), C = forest.get(0).size();
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[]{sr, sc, 0});
        boolean[][] seen = new boolean[R][C];
        seen[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == tr && cur[1] == tc) return cur[2];
            for (int di = 0; di < 4; ++di) {
                int r = cur[0] + dr[di];
                int c = cur[1] + dc[di];
                if (0 <= r && r < R && 0 <= c && c < C &&
                        !seen[r][c] && forest.get(r).get(c) > 0) {
                    seen[r][c] = true;
                    queue.add(new int[]{r, c, cur[2]+1});
                }
            }
        }
        return -1;
    }
}