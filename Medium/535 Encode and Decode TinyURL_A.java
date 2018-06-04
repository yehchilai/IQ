/*
This question is from https://leetcode.com/problems/encode-and-decode-tinyurl/description/
Difficulty: medium

Note: This is a companion problem to the System Design problem: Design TinyURL.
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.


*/

// T:O(1), S: O(N), 28 ms
public class Codec {

    Map<Integer, String> mMap = new HashMap();
    Random mRandom = new Random();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        boolean newKey = false;
        int shortUrl = 0;
        while(!newKey){
            shortUrl = mRandom.nextInt();
            if(!mMap.containsKey(shortUrl)){
                newKey = true;
                mMap.put(shortUrl, longUrl);
            }
        }
        return "http://tinyurl.com/" + String.valueOf(shortUrl);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String shortCode = shortUrl.replace("http://tinyurl.com/","");
        //System.out.println(shortCode);
        return mMap.get(Integer.valueOf(shortCode));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));

// with fixed length of url
public class Codec {

    String alphabets = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLOMNOPQRSTUVWXYZ";
    Map<String, String> mMap = new HashMap();
    Random mRandom = new Random();

    public String getRandom(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 6; i++){
            int r = mRandom.nextInt(64);
            sb.append(alphabets.charAt(r));
        }

        return sb.toString();
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        boolean newKey = false;
        String shortUrl = "";
        while(!newKey){
            shortUrl = getRandom();
            if(!mMap.containsKey(shortUrl)){
                newKey = true;
                mMap.put(shortUrl, longUrl);
            }
        }
        return "http://tinyurl.com/" + String.valueOf(shortUrl);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String shortCode = shortUrl.replace("http://tinyurl.com/","");
        System.out.println(shortCode);
        return mMap.get(shortCode);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));