/*
This question is from https://leetcode.com/problems/palindrome-number/
Difficulty: easy

Determine whether an integer is a palindrome. Do this without extra space.

Time Complexity: O()
*/

// Without extra space
public class Solution{
	public boolean isPalindrome(int x){
		if(x < 0) return false;
		int tmp = x;
		int palindrome = 0;
		while(tmp > 0){
			palindrome = palindrome * 10 + tmp%10;
			tmp = tmp/10;
		}
		return palindrome == x;
	}

}


// With extra space
public class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        char[] palindrome = Integer.toString(x).toCharArray();
        for(int i = 0; i < palindrome.length/2; i++){
        	if(palindrome[i] != palindrome[palindrome.length - 1 - i])
        		return false;
        }
        return true;
    }
}

/*
Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", 
you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.
*/