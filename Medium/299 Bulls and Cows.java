/*
This question is from https://leetcode.com/problems/bulls-and-cows/
Difficulty: easy

You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend
to guess what the number is. Each time your friend makes a guess,
you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit
and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows").
Your friend will use successive guesses and hints to eventually derive the secret number.

For example:

Secret number:  "1807"
Friend's guess: "7810"
Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
Write a function to return a hint according to the secret number and friend's guess,
use A to indicate the bulls and B to indicate the cows. In the above example, your function should return "1A3B".

Please note that both secret number and friend's guess may contain duplicate digits, for example:

Secret number:  "1123"
Friend's guess: "0111"
In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.

*/
// T:O(3N), S:O(1), 1 ms
class Solution {
    public String getHint(String secret, String guess) {
        int[] nums = new int[10];
        int len = secret.length();
        for(int i = 0; i < len; i++){
            char c = secret.charAt(i);
            nums[c - '0']++;
        }

        int A = 0;
        int B = 0;

        for(int i = 0; i < len; i++){
            char s = secret.charAt(i);
            char g = guess.charAt(i);

            if(s == g){
                A++;
                nums[g - '0']--;
            }
        }

        for(int i = 0; i < len; i++){
            char s = secret.charAt(i);
            char g = guess.charAt(i);

            if(s != g && nums[g - '0'] > 0){
                B++;
                nums[g - '0']--;
            }
        }

        return A+"A"+B+"B";
    }
}


// Wrong Anwser
public class Solution {
    public String getHint(String secret, String guess) {
        String result = "";
        char[] secretArr = secret.toCharArray();
        char[] guessArr = guess.toCharArray();
        Map<Character, ArrayList<Integer>> map = new HashMap<Character, ArrayList<Integer>>();
        int a = 0;
        int b = 0;
        for(int i = 0; i< secretArr.length; i++){
        	if(map.containsKey(secretArr[i])){
        		ArrayList<Integer> tmpList = map.get(secretArr[i]);
        		tmpList.add(i);
        		map.put(secretArr[i], tmpList);
        	}else{
        		ArrayList<Integer> tmpList = new ArrayList<Integer>();
        		tmpList.add(i);
        		map.put(secretArr[i], tmpList);
        	}
        }
        for(int i = 0; i< guessArr.length; i++){
        	if(map.containsKey(guessArr[i])){
        		ArrayList<Integer> tmpList = map.get(guessArr[i]);
        		for(int j = 0; j < tmpList.size(); j++){
        			if(tmpList.get(j) == i){
	        			a++;
	        			tmpList.remove(j);
	        			if(tmpList.size() == 0) map.remove(guessArr[i]);
	        			break;
	        		}else{
        				b++;
	        		}
        		}
        	}
        }
        return Integer.toString(a)+"A"+Integer.toString(b)+"B";
    }
}

// Correct : https://leetcode.com/discuss/68660/java-solution-with-two-buckets
public class Solution {
    public String getHint(String secret, String guess) {
        int bull = 0, cow = 0;

        int[] sarr = new int[10];
        int[] garr = new int[10];

        for(int i = 0; i < secret.length(); i++){
            if(secret.charAt(i) != guess.charAt(i)){
                sarr[secret.charAt(i)-'0']++;
                garr[guess.charAt(i)-'0']++;
            }else{
                bull++;
            }
        }

        for(int i = 0; i <= 9; i++){
            cow += Math.min(sarr[i], garr[i]);
        }

        return (bull + "A" + cow + "B");
    }
}