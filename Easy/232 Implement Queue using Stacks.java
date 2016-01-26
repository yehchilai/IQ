/*
This question is from https://leetcode.com/problems/implement-queue-using-stacks/

Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.

Notes:
You must use only standard operations of a stack -- 
which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. 
You may simulate a stack by using a list or deque (double-ended queue), 
as long as you use only standard operations of a stack.
You may assume that all operations are valid 
(for example, no pop or peek operations will be called on an empty queue).

Time Complexity: O(2N), N: the size of stack
*/

class MyQueue {

	Stack myStack = new Stack();
	Stack tmpStack = new Stack();

    // Push element x to the back of queue.
    public void push(int x) {
        myStack.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if(!myStack.empty()){
        	while(!myStack.empty()){
	        	tmpStack.push(myStack.pop());
	        }
	        tmpStack.pop();

	        while(!tmpStack.empty()){
	        	myStack.push(tmpStack.pop());
	        }
        }    }

    // Get the front element.
    public int peek() {
        int tmp = -1;
        if(!myStack.empty()){
        	while(!myStack.empty()){
	        	tmpStack.push(myStack.pop());
	        }
	        tmp = (int)tmpStack.peek();

	        while(!tmpStack.empty()){
	        	myStack.push(tmpStack.pop());
	        }
        }
        return tmp;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return myStack.empty();
    }
}