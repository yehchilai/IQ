/*
This question is from https://leetcode.com/problems/minimize-max-distance-to-gas-station/
Difficulty: hard

On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.

Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.

Return the smallest possible value of D.

Example:

Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
Output: 0.500000
Note:

stations.length will be an integer in range [10, 2000].
stations[i] will be an integer in range [0, 10^8].
K will be an integer in range [1, 10^6].
Answers within 10^-6 of the true value will be accepted as correct.
*/
// T:O(N*logD), S:O(1), 9 ms
class Solution {
    public double minmaxGasDist(int[] stations, int K) {
        double lo = 0;
        double hi = 100000000;

        while( hi - lo > 0.000001){
            double mid = (lo + hi) / 2.0;
            if(possible(mid, stations, K)){
                hi = mid;
            }else{
                lo = mid;
            }
        }

        return lo;
    }

    private boolean possible(double D, int[] stations, int K){
        int sum = 0;
        for(int i = 0;i < stations.length - 1; i++){
            sum += (int)((stations[i+1] - stations[i]) / D);
        }

        return sum <= K;
    }
}


// heap, T:O(N*KlogN)
class Solution {
    public double minmaxGasDist(int[] stations, int K) {

        PriorityQueue<int[]> pq = new PriorityQueue(new DistanceComparator());

        for(int i = 0; i < stations.length - 1; i++){
            pq.add(new int[]{stations[i+1] - stations[i], 1});
        }

        for(int i = 0; i < K; i++){
            int[] max = pq.poll();
            max[1] += 1;
            pq.add(max);
        }

        int[] ans = pq.poll();
        return (double)ans[0]/ ans[1];
    }
}

class DistanceComparator implements Comparator<int[]> {
    @Override
    public int compare(int[] a, int[] b){
        if((double)a[0]/a[1] - (double)b[0]/b[1] < 0) return 1;
        if((double)a[0]/a[1] - (double)b[0]/b[1] > 0) return -1;
        return 0;
    }
}
