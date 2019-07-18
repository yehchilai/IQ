/*
This question is from https://leetcode.com/problems/roman-to-integer/

Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.

Time Complexity: O(N)
*/
// T:O(N), S:O(1), 6 ms (52%)
class Solution {
    public int romanToInt(String s) {
        if(s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        char[] arr = s.toCharArray();

        int sum = 0;
        int prev = map.get(arr[arr.length - 1]);
        sum = sum + prev;
        for(int i = arr.length - 2; i >= 0 ; i--){
            int current = map.get(arr[i]);
            if(current < prev){
                sum = sum - current;
            }else{
                sum = sum + current;
            }

            prev = current;
        }

        return sum;
    }
}

// This anwser is from https://leetcode.com/discuss/77060/java-easy-version-to-understand
public class Solution {
    public int romanToInt(String s) {
    	Map<Character,Integer> map = new HashMap<Character,Integer>();
    	map.put('I', 1);
	    map.put('V', 5);
	    map.put('X', 10);
	    map.put('L', 50);
	    map.put('C', 100);
	    map.put('D', 500);
	    map.put('M', 1000);
        char[] arr = s.toCharArray();
        int l = arr.length;
        int result = 0;
        result = result + map.get(arr[l - 1]);
        for(int i = l - 2; i >= 0 ; i--){
        	if(map.get(arr[i]) >= map.get(arr[i + 1])){
        		result += map.get(arr[i]);
        	}else{
        		result -= map.get(arr[i]);
        	}
        }
		return result;
    }
}

// Other answer is from https://leetcode.com/discuss/79270/java-clean-and-fast-solution
    // public int romanToInt(String s) {
    //     int num = 0;
    //     int l = s.length();
    //     int last = 1000;
    //     for (int i = 0; i < l; i++){
    //         int v = getValue(s.charAt(i));
    //         if (v > last) num = num - last * 2;
    //         num = num + v;
    //         last = v;
    //     }
    //     return num;
    // }
    // private int getValue(char c){
    //     switch(c){
    //         case 'I' : return 1;
    //         case 'V' : return 5;
    //         case 'X' : return 10;
    //         case 'L' : return 50;
    //         case 'C' : return 100;
    //         case 'D' : return 500;
    //         case 'M' : return 1000;
    //         default : return 0;
    //     }
    // }