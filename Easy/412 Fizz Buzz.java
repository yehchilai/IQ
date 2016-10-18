/*
This question is from https://leetcode.com/problems/fizz-buzz/
Difficulty: easy

Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. 
For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
*/
// 5ms
public class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> result = new LinkedList<String>();
        for(int i = 1; i < n + 1; i++){
            String str = "";
            if(i%3 == 0)str = str +"Fizz";
            if(i%5 == 0) str = str + "Buzz";
            if(str.equals("")){
                result.add(Integer.toString(i));
            }else{
                result.add(str);
            }
        }
        return result;
    }
}

// 4ms
public class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> result = new LinkedList<String>();
        for(int i = 1; i < n + 1; i++){
            String str = "";
            if(i%15 == 0){
                str = "FizzBuzz";
            }else if(i%3 == 0){
                str = "Fizz";
            }else if(i%5 == 0){
                str = "Buzz";
            } else{
                str = Integer.toString(i);
            }
            result.add(str);
        }
        return result;
    }
}

// 3ms
public class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> result = new LinkedList<String>();
        for(int i = 1; i < n + 1; i++){
            if(i%15 == 0){
                result.add("FizzBuzz");
            }else if(i%3 == 0){
                result.add("Fizz");
            }else if(i%5 == 0){
                result.add("Buzz");
            } else{
                result.add(Integer.toString(i));
            }
        }
        return result;
    }
}
