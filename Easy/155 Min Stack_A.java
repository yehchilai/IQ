/*
This question is from https://leetcode.com/problems/min-stack/
Difficulty: easy

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
*/

// New data structure, 128ms
public class MinStack {

    Node head;

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        if(head == null){
            head = new Node(x,x);
        } else{
            int min = Math.min(x, head.min);
            Node newNode = new Node(x, min);
            newNode.next = head;
            head = newNode;
        }
    }

    public void pop() {
       if(head != null){
           head = head.next;
       }
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    public class Node{

        public int val;
        public Node next;
        public int min;

        public Node(int value, int minimum){
            val = value;
            min = minimum;
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

// use library, 134 ms
public class MinStack {

    LinkedList<Integer> stack;
    int min;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<Integer>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        min = Math.min(min, x);
        stack.addFirst(min);
        stack.addFirst(x);
    }

    public void pop() {
        stack.poll();
        stack.poll();
        if(stack.size() == 0){
            min = Integer.MAX_VALUE;
        } else{
            min = getMin();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return stack.get(1);
    }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
