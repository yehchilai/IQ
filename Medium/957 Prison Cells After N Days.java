/*
This question is from https://leetcode.com/problems/prison-cells-after-n-days/
Difficulty: medium

There are 8 prison cells in a row, and each cell is either occupied or vacant.

Each day, whether the cell is occupied or vacant changes according to the following rules:

If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
Otherwise, it becomes vacant.
(Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)

We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.

Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)



Example 1:

Input: cells = [0,1,0,1,1,0,0,1], N = 7
Output: [0,0,1,1,0,0,0,0]
Explanation:
The following table summarizes the state of the prison on each day:
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]

Example 2:

Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
Output: [0,0,1,1,1,1,1,0]


Note:

cells.length == 8
cells[i] is in {0, 1}
1 <= N <= 10^9
*/
// T:O(N/cycle), S:O(N), 3 ms
class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        if(cells==null || cells.length==0 || N<=0) return cells;
        int cycle = 0;

        HashSet<String> set = new HashSet();

        // set.add(Arrays.toString(cells));

        boolean hasCycle = false;

        // System.out.println("tmp: " + Arrays.toString(tmp));

        for(int i = 0 ;i < N; i++){
            int[] next = nextDay(cells);
            String str = Arrays.toString(next);
            if(set.contains(str)){
                hasCycle = true;
                break;
            }
            set.add(str);
            cycle++;
            cells = next;
        }


        if(hasCycle){
            N = N % cycle;
            for(int i = 0 ;i < N; i++){
                cells = nextDay(cells);
            }
        }

        return cells;
    }

    private int[] nextDay(int[] cells){

        int[] tmp = new int[cells.length];

        // tmp[0] = 0;
        for(int c = 1; c < cells.length - 1; c++){
            if(cells[c - 1] == cells[c + 1]){
                tmp[c] = 1;
            }else{
                tmp[c] = 0;
            }
        }
        // tmp[cells.length - 1] = 0;

        return tmp;
    }
}

// T:O(N), S:O(N), Time Limit Exceeded
class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        int[] tmp = new int[cells.length];
        for(int i = 0; i < N; i++){
            tmp[0] = 0;
            for(int c = 1; c < cells.length - 1; c++){
                if(cells[c - 1] == cells[c + 1]){
                    tmp[c] = 1;
                }else{
                    tmp[c] = 0;
                }
            }
            tmp[cells.length - 1] = 0;
            cells = tmp;
            // for(int n : cells) System.out.print(n+",");
            // System.out.println();
            tmp = new int[cells.length];
        }

        return cells;
    }
}