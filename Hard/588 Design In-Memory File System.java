/*
This question is from https://leetcode.com/problems/design-in-memory-file-system/
Difficulty: hard

Design an in-memory file system to simulate the following functions:

ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.

mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories in the path don't exist either, you should create them as well. This function has void return type.

addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content. If the file already exists, you need to append given content to original content. This function has void return type.

readContentFromFile: Given a file path, return its content in string format.



Example:

Input:
["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]

Output:
[null,[],null,null,["a"],"hello"]

Explanation:
filesystem


Note:

You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.
*/

// T:O(m+n+klogk), S:O(...), 82 ms (42.41%)
class FileSystem {

    File root;

    public FileSystem() {
        root = new File();
    }

    public List<String> ls(String path) {
        File current = root;
        // System.out.println("ls: "+path);
        List<String> list = new LinkedList();
        if(!path.equals("/")){
            String[] arr = path.split("/");
            for(int i = 1; i < arr.length; i++){
                current = current.files.get(arr[i]);
            }
            if(current.isFile){
                // System.out.println("ls: "+path+", is a file");
                list.add(arr[arr.length - 1]);
                return list;
            }
        }

        list = new LinkedList(current.files.keySet());
        Collections.sort(list);
        return list;
    }

    public void mkdir(String path) {
        // System.out.println("mkdir: "+path);
        File current = root;
        String[] arr = path.split("/");
        for(int i = 1; i < arr.length; i++){
            if(!current.files.containsKey(arr[i])){
                current.files.put(arr[i], new File());
            }
            current = current.files.get(arr[i]);
        }

    }

    public void addContentToFile(String filePath, String content) {
        // System.out.println("addContent: "+filePath);
        File current = root;
        String[] arr = filePath.split("/");
        for(int i = 1; i < arr.length; i++){
            if(!current.files.containsKey(arr[i])){
                current.files.put(arr[i], new File());
            }
            current = current.files.get(arr[i]);
        }

        current.isFile = true;
        current.content = current.content + content;
        // System.out.println("addContent: "+filePath + ", isFile: "+current.isFile);
    }

    public String readContentFromFile(String filePath) {
        // System.out.println("readContent: "+filePath);
        File current = root;
        String[] arr = filePath.split("/");
        for(int i = 1; i < arr.length; i++){

            current = current.files.get(arr[i]);
        }

        if(current.isFile){
            return current.content;
        }

        return "";
    }
}

class File{

    boolean isFile;
    HashMap<String, File> files;
    String content;

    public File(){
        isFile = false;
        files = new HashMap();
        content = "";
    }


}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */


public class FileSystem {
    class File {
        boolean isfile = false;
        HashMap < String, File > files = new HashMap < > ();
        String content = "";
    }
    File root;
    public FileSystem() {
        root = new File();
    }

    // O(m+n+klog(k))
    public List < String > ls(String path) {
        File t = root;
        List < String > files = new ArrayList < > ();
        if (!path.equals("/")) {
            String[] d = path.split("/");
            for (int i = 1; i < d.length; i++) {
                t = t.files.get(d[i]);
            }
            if (t.isfile) {
                files.add(d[d.length - 1]);
                return files;
            }
        }
        List < String > res_files = new ArrayList < > (t.files.keySet());
        Collections.sort(res_files);
        return res_files;
    }

    // O(m+n)
    public void mkdir(String path) {
        File t = root;
        String[] d = path.split("/");
        for (int i = 1; i < d.length; i++) {
            if (!t.files.containsKey(d[i]))
                t.files.put(d[i], new File());
            t = t.files.get(d[i]);
        }
    }
    // O(m+n)
    public void addContentToFile(String filePath, String content) {
        File t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.files.get(d[i]);
        }
        if (!t.files.containsKey(d[d.length - 1]))
            t.files.put(d[d.length - 1], new File());
        t = t.files.get(d[d.length - 1]);
        t.isfile = true;
        t.content = t.content + content;
    }
    // O(m+n)
    public String readContentFromFile(String filePath) {
        File t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.files.get(d[i]);
        }
        return t.files.get(d[d.length - 1]).content;
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */

//
 public class FileSystem {
    class Dir {
        HashMap < String, Dir > dirs = new HashMap < > ();
        HashMap < String, String > files = new HashMap < > ();
    }
    Dir root;
    public FileSystem() {
        root = new Dir();
    }

    // O(m+n+klog(k))
    public List < String > ls(String path) {
        Dir t = root;
        List < String > files = new ArrayList < > ();
        if (!path.equals("/")) {
            String[] d = path.split("/");
            for (int i = 1; i < d.length - 1; i++) {
                t = t.dirs.get(d[i]);
            }
            if (t.files.containsKey(d[d.length - 1])) {
                files.add(d[d.length - 1]);
                return files;
            } else {
                t = t.dirs.get(d[d.length - 1]);
            }
        }
        files.addAll(new ArrayList < > (t.dirs.keySet()));
        files.addAll(new ArrayList < > (t.files.keySet()));
        Collections.sort(files);
        return files;
    }
    // O(m+n)
    public void mkdir(String path) {
        Dir t = root;
        String[] d = path.split("/");
        for (int i = 1; i < d.length; i++) {
            if (!t.dirs.containsKey(d[i]))
                t.dirs.put(d[i], new Dir());
            t = t.dirs.get(d[i]);
        }
    }
    // O(m+n)
    public void addContentToFile(String filePath, String content) {
        Dir t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.dirs.get(d[i]);
        }
        t.files.put(d[d.length - 1], t.files.getOrDefault(d[d.length - 1], "") + content);
    }
    // O(m+n)
    public String readContentFromFile(String filePath) {
        Dir t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length - 1; i++) {
            t = t.dirs.get(d[i]);
        }
        return t.files.get(d[d.length - 1]);
    }
}
