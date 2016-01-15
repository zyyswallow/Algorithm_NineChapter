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


4. Permutations
Given a list of numbers, return all possible permutations.
http://www.lintcode.com/en/problem/permutations/

class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        if (nums == null){
            return list;
        }
        ArrayList<Integer> solution = new ArrayList<Integer>();
        helper(list, solution, nums);
        return list;
    }
    
    public void helper(ArrayList<ArrayList<Integer>> list, ArrayList<Integer> solution, ArrayList<Integer> nums){
        if (solution.size() == nums.size()){   // 加入list的条件与subset不同啦
            list.add(new ArrayList<Integer>(solution));
            return;
        }
        for (int i = 0; i < nums.size(); i++){
            if (solution.contains(nums.get(i))){   // 用contains()去检查arraylist里面是否存在某个值
                continue;
            }
            solution.add(nums.get(i));
            helper(list, solution, nums);
            solution.remove(solution.size() - 1);
        }
    }
}


5. Unique Permutations (Permutations II)
Given a list of numbers with duplicate number in it. Find all unique permutations.
http://www.lintcode.com/en/problem/permutations-ii/

class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of unique permutations.
     */
    public ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (nums == null || nums.size() == 0){
            return res;
        }
        Collections.sort(nums);
        ArrayList<Integer> list = new ArrayList<Integer>();
        int[] visited = new int[nums.size()];   // 用一个visited[] 来继续index对应的数字是否已经选出
        helper(res, list, nums, visited);
        return res;
    }
    
    public void helper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list, ArrayList<Integer> nums, int[] visited){
        if (list.size() == nums.size()){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = 0; i < nums.size(); i++){
            if (visited[i] == 1 || (i != 0 && nums.get(i) == nums.get(i - 1) && visited[i - 1] == 0)){
                continue;   // 1.这个数已被选出 2.这是一串相同的数，并且i之前的数都还未被选出
            }
            list.add(nums.get(i));
            visited[i] = 1;
            helper(res, list, nums, visited);
            list.remove(list.size() - 1);
            visited[i] = 0;
        }
        
    }
}

对于[1,2,2,2,3]
当已选出一个2时，这个2只能是i=1的2，下一次也只能选i=2的那个2，不能是后面的2(visited[i-1]=0)，要按顺序依次选出。
(i != 0 && nums.get(i) == nums.get(i - 1) && visited[i - 1] == 0)
按照这个规定，可以保证每个排列不重复。


6. Combination Sum
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.
http://www.lintcode.com/en/problem/combination-sum/

public class Solution {
    /**
     * @param candidates: A list of integers
     * @param target:An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length == 0){
            return res;
        }
        Arrays.sort(candidates);
        ArrayList<Integer> list = new ArrayList<Integer>();
        helper(res, list, candidates, 0, target);
        return res;
    }
    
    public void helper(ArrayList<List<Integer>> res, ArrayList<Integer> list, int[] candidates, int pos, int target){
        if (target == 0){
            res.add(new ArrayList<Integer>(list));
            return;
        } else if (target < 0){
            return;
        }
        for (int i = pos; i < candidates.length; i++){  // i = pos，保证下一个选的数大于等于当前数 => 答案不重复
            list.add(candidates[i]);
            target -= candidates[i];
            helper(res, list, candidates, i, target);
            list.remove(list.size() - 1);
            target += candidates[i];
        }
    }
}


7. Letter Combinations of a Phone Number
Given a digit string, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below.
http://www.lintcode.com/en/problem/letter-combinations-of-a-phone-number/

public class Solution {
    /**
     * @param digits A digital string
     * @return all posible letter combinations
     */
    public ArrayList<String> letterCombinations(String digits) {
        // Write your code here
        String[] letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ArrayList<String> list = new ArrayList<String>();
        if (digits == null || digits.length() == 0){
            return list;
        }
        helper(list, "", letters, 0, digits);
        return list;
    }
    
    public void helper(ArrayList<String> list, String str, String[] letters, int pos, String digits){
        if (str.length() == digits.length()){
            list.add(str);
            return;
        }
        int digit = digits.charAt(pos) - '0';
        for (int i = 0; i < letters[digit].length(); i++){
            helper(list, str + letters[digit].charAt(i), letters, pos + 1, digits);
        }
    }
}


8. Palindrome Partitioning
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.
http://www.lintcode.com/en/problem/palindrome-partitioning/

public class Solution {
    /**
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        // write your code here
        ArrayList<List<String>> res = new ArrayList<List<String>>();
        if (s == null || s.length() == 0){
            return res;
        }
        ArrayList<String> list = new ArrayList<String>();
        helper(res, list, 0, s);
        return res;
    }
    
    public void helper(ArrayList<List<String>> res, ArrayList<String> list, int pos, String s){
        if (pos >= s.length()){
            res.add(new ArrayList<String>(list));
            return;
        }
        for (int i = pos; i < s.length(); i++){
            String str = s.substring(pos, i + 1);
            if (isPalindrome(str)){
                list.add(str);
                helper(res, list, i + 1, s);
                list.remove(list.size() - 1);
            }
        }
    }
    
    public boolean isPalindrome(String str){
        if (str == null || str.length() == 0){
            return false;
        }
        int start = 0;
        int end = str.length() - 1;
        while (start < end){
            if (str.charAt(start) != str.charAt(end)){
                return false;
            }
            start++;  // 注意while循环 Time Limit Exceeded
            end--;
        }
        return true;
    }
}

此题其实就是对字符串中插入隔板的排列


9. Restore IP Addresses
Given a string containing only digits, restore it by returning all possible valid IP address combinations.
Example: Given "25525511135", return["255.255.11.135","255.255.111.35"]
Order does not matter.
http://www.lintcode.com/en/problem/restore-ip-addresses/

public class Solution {
    /**
     * @param s the IP string
     * @return All possible valid IP addresses
     */
    public ArrayList<String> restoreIpAddresses(String s) {
        // Write your code here
        ArrayList<String> list = new ArrayList<String>();
        if (s == null || s.length() < 4 || s.length() > 12){
            return list;
        }
        helper(list, "", 0, s, 4);  // 要记录"."的个数
        return list;
    }
    
    public void helper(ArrayList<String> list, String address, int pos, String s, int dot){
        if (pos >= s.length() && dot == 0){
            list.add(address.substring(0, address.length() - 1));
            return;
        }
        for (int i = pos; i < s.length(); i++){
            String temp = s.substring(pos, i + 1);
            if (Integer.parseInt(temp) > 255 || (temp.startsWith("0") && !temp.equals("0"))){
                break;   // 1.数字不能大于255 2.不能是“010”这种以0开头的数字
            }
            address += temp + ".";
            dot--;
            helper(list, address, i + 1, s, dot);
            address = address.substring(0, address.length() - temp.length() - 1);
            dot++;
        }
    }
}










