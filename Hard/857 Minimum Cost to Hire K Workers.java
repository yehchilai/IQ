/*
This question is from https://leetcode.com/problems/minimum-cost-to-hire-k-workers/
Difficulty: hard

There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].

Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:

Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
Every worker in the paid group must be paid at least their minimum wage expectation.
Return the least amount of money needed to form a paid group satisfying the above conditions.



Example 1:

Input: quality = [10,20,5], wage = [70,50,30], K = 2
Output: 105.00000
Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
Example 2:

Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
Output: 30.66667
Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.


Note:

1 <= K <= N <= 10000, where N = quality.length = wage.length
1 <= quality[i] <= 10000
1 <= wage[i] <= 10000
Answers within 10^-5 of the correct answer will be considered correct.
*/
// T:O(NlogN), S:O(N), 23 ms
class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {

        Worker[] workers = new Worker[wage.length];

        for(int i = 0; i < wage.length; i++){
            workers[i] = new Worker(quality[i], wage[i]);
        }

        double min = Double.MAX_VALUE;

        // sort ration from low to high
        // Collections.sort(workers, (a, b) -> (int)(a.ratio - b.ratio));
        Arrays.sort(workers, new WorkerSort());
        // max heap to keep the largest quality
        PriorityQueue<Worker> pq = new PriorityQueue(new WorkerComparator());

        int qSum = 0;
        for(Worker w : workers){
            pq.add(w);
            qSum += w.quality;

            if(pq.size() == K){
                min = Math.min(min, qSum * w.ratio);
                Worker topQuality = pq.poll();
                qSum -= topQuality.quality;
            }
        }

        return min;
    }
}

class WorkerSort implements Comparator<Worker>{
    @Override
    public int compare(Worker a, Worker b){

        if(a.ratio - b.ratio > 0) return 1;
        if(a.ratio - b.ratio < 0) return -1;
        return 0;
    }
}

class WorkerComparator implements Comparator<Worker>{
    @Override
    public int compare(Worker a, Worker b){
        return b.quality - a. quality;
    }
}

class Worker{
    int quality;
    int wage;
    double ratio;

    Worker(int q, int w){
        quality = q;
        wage = w;
        ratio = (double)wage / quality;
    }

}