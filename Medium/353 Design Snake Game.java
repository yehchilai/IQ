/*
This question is from https://leetcode.com/problems/design-snake-game/
Difficulty: medium

Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:

Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)
*/

// T:O(N), S:O(N), 98ms
class SnakeGame {

    LinkedList<Pair> snake;
    LinkedList<Pair> foodQ;
    int xMax;
    int yMax;
    HashMap<String, Pair> map;
    int foodNum;

    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        // System.out.println("width: "+width+", height: "+height);
        yMax = height;
        xMax = width;
        // initialize snake
        snake = new LinkedList();
        snake.add(new Pair(0, 0));
        // initialize food queue
        foodNum = 0;
        foodQ = new LinkedList();
        for(int i = 0 ; i < food.length; i++){
            int x = food[i][1];
            int y = food[i][0];
            foodQ.add(new Pair(x, y));
        }
        // setup direction
        map = new HashMap();
        map.put("U", new Pair(0, -1));
        map.put("L", new Pair(-1, 0));
        map.put("R", new Pair(1, 0));
        map.put("D", new Pair(0, 1));
    }

    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
        @return The game's score after the move. Return -1 if game over.
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        Pair step = map.get(direction);
        Pair current = snake.peek();
        int x = current.x + step.x;
        int y = current.y + step.y;
        // System.out.println("x: "+ x+ ", y: "+y+", xMax: "+xMax+", yMax: "+yMax+", snake: "+snake.size());
        // hit boarder
        if(x < 0 || x >= xMax || y < 0 || y >= yMax) return -1;
        // hit self
        for(int i = 0; i < snake.size() - 1; i++){
            Pair p = snake.get(i);
            if(p.x == x && p.y == y) return -1;
        }
        Pair next = new Pair(x, y);
        snake.addFirst(next);

        if(foodQ.size() > 0){
            Pair food = foodQ.peek();

            if(next.x == food.x && next.y == food.y){
                foodQ.poll();
                foodNum++;
            }else{
                // System.out.println("remove snake");
                snake.removeLast();
            }
        }else{
            snake.removeLast();
        }

        return foodNum;
    }
}

class Pair{

    int x;
    int y;

    Pair(int a, int b){
        x = a;
        y = b;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */