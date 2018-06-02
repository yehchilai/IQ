/*
This question is from https://leetcode.com/problems/top-k-frequent-words/description/
Difficulty: medium

Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.

*/
// T: O(N + K + Nlog(N) + Klog(N)), S: O(2N), 24ms , N is the length of words, K is k
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        // hash map to compute the frequent of words
        HashMap<String, Integer> map = new HashMap();

        // go through the words array
        for(int i = 0; i < words.length; i++){
            int count = map.getOrDefault(words[i], 0);
            map.put(words[i], count + 1);
        }

        // max heap to record a word with it frequent
        PriorityQueue<Element> heap = new PriorityQueue(11, new EComparator());
        // Insert element into a max heap
        for(Map.Entry<String, Integer> pair : map.entrySet()){
            heap.add(new Element(pair.getKey(), pair.getValue()));
        }

        // result list
        ArrayList<String> result = new ArrayList();

        // eadge case
        if(k == 0) return result;

        // append the result
        for(int i = 0; i < k; i++){
            result.add(heap.poll().str);
        }

        return result;
    }

    class Element{
        String str;
        int val;

        public Element(String s, int v){
            this.str = s;
            this.val = v;
        }
    }

    class EComparator implements Comparator<Element>{

        public int compare(Element a, Element b){
            if(a.val < b.val) return 1;
            if(a.val > b.val) return -1;
            int min = Math.min(a.str.length(), b.str.length());
            for(int i = 0; i < min; i++){
                if(a.str.charAt(i) < b.str.charAt(i)) return -1;
                if(a.str.charAt(i) > b.str.charAt(i)) return 1;
            }

            if(a.str.length() < b.str.length()) return -1;
            if(a.str.length() > b.str.length()) return 1;
            return 0;
        }
    }
}

// https://leetcode.com/problems/top-k-frequent-words/solution/
// T: O(Nlog(N)), S: O(N)
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> candidates = new ArrayList(count.keySet());
        Collections.sort(candidates, (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w1.compareTo(w2) : count.get(w2) - count.get(w1));

        return candidates.subList(0, k);
    }
}

// https://leetcode.com/problems/top-k-frequent-words/solution/
// Time Complexity: O(Nlogk), where NN is the length of words. We count the frequency of each word in O(N) time, then we add NN words to the heap, each in O(logk) time. Finally, we pop from the heap up to kk times. As k≤N, this is O(Nlogk) in total.
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<String>(
                (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w2.compareTo(w1) : count.get(w1) - count.get(w2) );

        for (String word: count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> ans = new ArrayList();
        while (!heap.isEmpty()) ans.add(heap.poll());
        Collections.reverse(ans);
        return ans;
    }
}