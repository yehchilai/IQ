/*
This question is from https://leetcode.com/problems/design-log-storage-system/
Difficulty: medium

You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.

Design a log storage system to implement the following functions:

void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.


int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.

Example 1:
put(1, "2017:01:01:23:59:59");
put(2, "2017:01:01:22:59:59");
put(3, "2016:01:01:00:00:00");
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
Note:
There will be at most 300 operations of Put or Retrieve.
Year ranges from [2000,2017]. Hour ranges from [00,23].
Output for Retrieve has no order required.
*/

// 87 ms (24.96%)
class LogSystem {

    TreeMap<Long, Integer> map;
    HashMap<String, Integer> granuleMap;
    public LogSystem() {
        map = new TreeMap();
        granuleMap = new HashMap();
        granuleMap.put("Year", 0);
        granuleMap.put("Month", 1);
        granuleMap.put("Day", 2);
        granuleMap.put("Hour", 3);
        granuleMap.put("Minute", 4);
        granuleMap.put("Second", 5);

    }

    public void put(int id, String timestamp) {
        String[] arr = timestamp.split(":");
        int[] times = new int[arr.length];
        for(int i = 0 ;i < arr.length; i++){
            times[i] = Integer.valueOf(arr[i]);
        }
        long time = convert(times);
        map.put(time, id);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        String[] sArr = s.split(":");
        String[] eArr = e.split(":");
        int[] sTimes = new int[sArr.length];
        int[] eTimes = new int[eArr.length];

        for(int i = 0; i < sArr.length; i++){
            sTimes[i] = Integer.valueOf(sArr[i]);
            eTimes[i] = Integer.valueOf(eArr[i]);
        }

        long sTime = convertByGranule(sTimes, gra, false);
        long eTime = convertByGranule(eTimes, gra, true);

        List<Integer> ans = new LinkedList();

        for(int value : map.subMap(sTime, eTime).values()){
            ans.add(value);
        }

        return ans;
    }

    private long convertByGranule(int[] times, String gra, boolean isEndTime){
        int[] base = new int[]{1999, 0, 0, 0, 0, 0};
        for(int i = 0; i <= granuleMap.get(gra); i++){
            base[i] = times[i];
        }

        if(isEndTime){
            base[granuleMap.get(gra)]++;
        }

        return convert(base);
    }

    private long convert(int[] time){
        if(time.length < 6) return 0;
        time[1] = time[1] - (time[1] == 0? 0:1);
        time[2] = time[2] - (time[2] == 0? 0:1);

        return (time[0] - 1999L) * 12 * 31 * 24 * 60 * 60 +
            time[1] * 31 * 24 * 60 * 60 +
            time[2] * 24 * 60 * 60 +
            time[3] * 60 * 60 +
            time[4] * 60 +
            time[5];
    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */

public class LogSystem {
    TreeMap < Long, Integer > map;
    public LogSystem() {
        map = new TreeMap < Long, Integer > ();
    }

    public void put(int id, String timestamp) {
        int[] st = Arrays.stream(timestamp.split(":")).mapToInt(Integer::parseInt).toArray();
        map.put(convert(st), id);
    }
    public long convert(int[] st) {
        st[1] = st[1] - (st[1] == 0 ? 0 : 1);
        st[2] = st[2] - (st[2] == 0 ? 0 : 1);
        return (st[0] - 1999L) * (31 * 12) * 24 * 60 * 60 + st[1] * 31 * 24 * 60 * 60 + st[2] * 24 * 60 * 60 + st[3] * 60 * 60 + st[4] * 60 + st[5];
    }
    public List < Integer > retrieve(String s, String e, String gra) {
        ArrayList < Integer > res = new ArrayList();
        long start = granularity(s, gra, false);
        long end = granularity(e, gra, true);
        for (long key: map.tailMap(start).keySet()) {
            if (key >= start && key < end)
                res.add(map.get(key));
        }
        return res;
    }

    public long granularity(String s, String gra, boolean end) {
        HashMap < String, Integer > h = new HashMap();
        h.put("Year", 0);
        h.put("Month", 1);
        h.put("Day", 2);
        h.put("Hour", 3);
        h.put("Minute", 4);
        h.put("Second", 5);
        String[] res = new String[] {"1999", "00", "00", "00", "00", "00"};
        String[] st = s.split(":");
        for (int i = 0; i <= h.get(gra); i++) {
            res[i] = st[i];
        }
        int[] t = Arrays.stream(res).mapToInt(Integer::parseInt).toArray();
        if (end)
            t[h.get(gra)]++;
        return convert(t);
    }
}