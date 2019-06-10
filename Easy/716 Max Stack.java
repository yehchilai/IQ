/*
This question is from https://leetcode.com/problems/max-stack/
Difficulty: easy

Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5);
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.

*/
// T:O(N), S:O(N), 86 ms
class MaxStack {

    Stack<Integer> stack;
    Stack<Integer> maxStack;

    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack();
        maxStack = new Stack();
    }

    public void push(int x) {
        stack.push(x);
        if(maxStack.empty()){
            maxStack.push(x);
        }else{
            maxStack.push(Math.max(maxStack.peek(), x));
        }
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        // System.out.println(maxStack.size());
        return maxStack.peek();
    }

    public int popMax() {
        int max = peekMax();
        Stack<Integer> tmp = new Stack();

        while(!stack.empty()){
            int current = pop();
            if(current == max){
                break;
            }
            tmp.push(current);
        }

        while(!tmp.empty()){
            push(tmp.pop());
        }

        return max;
    }
}

// T:O(N), S:O(N), 353 ms
class MaxStack {

    Stack<Integer> stack;
    Stack<Integer> tmp;
    int max;

    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack();
        tmp = new Stack();
        max = Integer.MIN_VALUE;
    }

    public void push(int x) {
        stack.push(x);
        max = Math.max(max, x);
    }

    public int pop() {
        int current = stack.pop();

        if(current == max){
            max = Integer.MIN_VALUE;
            while(!stack.empty()){
                tmp.push(stack.pop());
            }

            while(!tmp.empty()){
                int num = tmp.pop();
                max = Math.max(max, num);
                stack.push(num);
            }
        }

        return current;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return max;
    }

    public int popMax() {
        int currentMax = max;
        boolean isPop = false;
        while(!stack.empty()){
            int current = stack.pop();
            if(!isPop && current == max){
                isPop = true;
                max = Integer.MIN_VALUE;
                continue;
            }
            tmp.push(current);
        }

        while(!tmp.empty()){
            int current = tmp.pop();
            max = Math.max(max, current);
            stack.push(current);
        }

        return currentMax;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */