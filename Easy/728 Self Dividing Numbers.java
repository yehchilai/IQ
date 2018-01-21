/*
This question if from https://leetcode.com/problems/self-dividing-numbers/description/
Difficulty: easy

A self-dividing number is a number that is divisible by every digit it contains.

For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.

Also, a self-dividing number is not allowed to contain the digit zero.

Given a lower and upper number bound, output a list of every possible self dividing number, including the bounds if possible.

Example 1:
Input:
left = 1, right = 22
Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
Note:

The boundaries of each input argument are 1 <= left <= right <= 10000.
*/
class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new LinkedList<Integer>();
        for(int i = left; i <= right; i++){
            if(checkValue(i)){
                result.add(i);
            }
        }
        return result;
    }

    public boolean checkValue(int value){
        List<Integer> digits = new LinkedList<Integer>();
        int tmp = value;
        while(tmp > 0){
            int mod = tmp%10;
            if(mod == 0) return false;
            digits.add(mod);
            tmp = tmp/10;
        }
        while(digits.size() != 0){
            int divide = digits.get(0);
            if( (value%divide) != 0) return false;
            digits.remove(0);
        }
        return true;
    }
}

// Better?
class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList();
        for(int i=left;i<=right;i++){
            if (isDividing(i)){
                result.add(i);
            }
        }
        return result;
    }

    public Boolean isDividing(int num){
        int num2 = num;
        int remainder;
        while(num2 > 0){
            remainder = num2 % 10;
            if (remainder == 0){
                return false;
            }
            if ((num % remainder) != 0){
                return false;
            }
            num2 /= 10;
        }
        return true;
    }
}

// Best
class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList();
        for (int n = left; n <= right; ++n) {
            if (selfDividing(n)) ans.add(n);
        }
        return ans;
    }
    public boolean selfDividing(int n) {
        for (char c: String.valueOf(n).toCharArray()) {
            if (c == '0' || (n % (c - '0') > 0))
                return false;
        }
        return true;
    }
    /*
    Alternate implementation of selfDividing:
    public boolean selfDividing(int n) {
        int x = n;
        while (x > 0) {
            int d = x % 10;
            x /= 10;
            if (d == 0 || (n % d) > 0) return false;
        }
        return true;
    */
}