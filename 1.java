1. strStr
http://www.lintcode.com/en/problem/strstr/
For a given source string and a target string, you should output the first index(from 0) of target string in source string.
If target does not exist in source, just return -1.

class Solution {
    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        //write your code here
        if (source == null || target == null){
            return -1;
        }
        int slen = source.length();
        int tlen = target.length();
        if (tlen == 0){    // target = ""
            return 0;
        }
        if (slen < tlen){
            return -1;
        }
        for (int i = 0; i < slen; i++){
            for (int j = 0; j < tlen; j++){
                if (i + j >= slen || source.charAt(i + j) != target.charAt(j)){
                    break;
                }
                if (j == tlen - 1){   // 或者在for循环外面用if(j==tlen) return i; 可以合并前面判断tlen=0的情况
                    return i;
                }
            }
        }
        return -1;
    }
}


2. Subsets
http://www.lintcode.com/en/problem/subsets/
Given a set of distinct integers, return all possible subsets.

class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        // write your code here
        ArrayList list = new ArrayList();
        if (nums == null || nums.length == 0){
            return list;
        }
        Arrays.sort(nums);
        ArrayList<Integer> set = new ArrayList<Integer>();
        helper(list, set, 0, nums);
        return list;
    }
    
    public void helper(ArrayList list, ArrayList<Integer> set,int pos, int[] nums){
        list.add(new ArrayList<Integer>(set));   // 先add一个空集
        for (int i = pos; i < nums.length; i++){
            set.add(nums[i]);
            helper(list, set, i + 1, nums);   // 再加入比1大的数
            set.remove(set.size() - 1);
        }
    }
}


3. Subsets II
Given a list of numbers that may has duplicate numbers, return all possible subsets
http://www.lintcode.com/en/problem/subsets-ii/

class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> S) {
        // write your code here
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        if (S == null || S.size() == 0){
            return list;
        }
        Collections.sort(S);
        ArrayList<Integer> set = new ArrayList<Integer>();
        helper(list, set, 0, S);
        return list;
    }
    
    public void helper(ArrayList<ArrayList<Integer>> list, ArrayList<Integer> set,
                        int pos, ArrayList<Integer> S){
        list.add(new ArrayList<Integer>(set));
        for (int i = pos; i < S.size(); i++){
            if (i != pos && S.get(i) == S.get(i - 1)){   // 背！
                continue;
            }
            set.add(S.get(i));
            helper(list, set, i + 1, S);
            set.remove(set.size() - 1);
        }
    }
}

对于[1,2,2,2,2,3]
若选两个数组成subset，只能出现一次[1,2] => 用if控制跳过重复的2，只处理第一个2 (好理解)
若选三个数组成subset，只能出现一次[1,2,2] 
=> 此时set里面已有[1,2]（2为上一次递归中加入的第一个出现的2），这次递归用if仍可以控制pos=2的那个2，不处理后面的2 














