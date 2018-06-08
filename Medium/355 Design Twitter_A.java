/*
This qestion is from https://leetcode.com/problems/design-twitter/description/
Difficulty: medium

Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);

*/
// T:O(), S:O(), 196ms
class Twitter {

    // users
    Map<Integer, User> mUsers;
    // recent tweets
    int mTime;

    /** Initialize your data structure here. */
    public Twitter() {
        mUsers = new HashMap();
        mTime = 0;
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        User u = mUsers.getOrDefault(userId, new User(userId));
        // System.out.println("UserId: "+ userId + ", feed: " + tweetId);
        mUsers.put(userId, u);
        u.feeds.add(0, new Tweet(tweetId, mTime));
        mTime++;
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new LinkedList();

        User currentUser = mUsers.get(userId);
        if(currentUser != null){
            PriorityQueue<Tweet> pq = new PriorityQueue(new Comparator<Tweet>(){
                public int compare(Tweet a, Tweet b){
                    if(a.time < b.time) return 1;
                    if(a.time > b.time) return -1;
                    return 0;
                }
            });
            //System.out.println(currentUser.follow.size());
            for(int key : currentUser.follow.keySet()){
                List<Tweet> feeds = currentUser.follow.get(key).feeds;
                for(Tweet t : feeds){
                    pq.add(t);
                }
            }
            if(!currentUser.follow.containsKey(currentUser.id)){
                for(Tweet t: currentUser.feeds){
                    pq.add(t);
                }
            }

            // System.out.println("pq.size(): "+pq.size());
            while(pq.size() > 0 && result.size() < 10){
                //System.out.println("pq.poll");
                result.add(pq.poll().id);
            }
        }

        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        User follower = mUsers.getOrDefault(followerId, new User(followerId));
        User followee = mUsers.getOrDefault(followeeId, new User(followeeId));
        follower.follow.put(followee.id, followee);
        mUsers.put(followerId, follower);
        mUsers.put(followeeId, followee);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        User follower = mUsers.getOrDefault(followerId, new User(followerId));
        User followee = mUsers.getOrDefault(followeeId, new User(followeeId));
        follower.follow.remove(followee.id);
        mUsers.put(followerId, follower);
        mUsers.put(followeeId, followee);
    }

    class User{
        int id;
        List<Tweet> feeds;
        Map<Integer, User> follow;

        public User(int id){
            this.id = id;
            this.feeds = new LinkedList();
            this.follow = new HashMap();
        }
    }

    class Tweet{
        int id;
        int time;
        public Tweet(int id, int time){
            this.id = id;
            this.time = time;
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */