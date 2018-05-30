/*
Thiq question is from https://leetcode.com/problems/count-primes/
Difficulty: easy


Description:

Count the number of prime numbers less than a non-negative number, n.


Time Complexity: O(N log logN)
*/


/* Hint !!!!!
see hits on https://leetcode.com/problems/count-primes/hints/
*/
public class Solution {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        for(int i = 1; i < n ; i++){
        	isPrime[i] = true;
        }
        for(int i = 2; i * i < n ; i++){
        	if(isPrime[i] == false) continue;
        	for(int j= 2 ; j*i < n ; j++){
        	    isPrime[j*i] = false;
        	}
        	// alternative : can start from the square of prime (faster)
        	// for (int j = i * i; j < n; j = j+i) {
	        //  	isPrime[j] = false;
	        // }
        }

        int count = 0;
        for(int i = 1; i< n; i++){
        	if(isPrime[i] == true) count++;
        }
        return count;
    }
}
