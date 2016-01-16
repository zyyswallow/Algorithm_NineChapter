1. Find minimum in rotate sorted array  
Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element. 
http://www.lintcode.com/en/problem/find-minimum-in-rotated-sorted-array/

public class Solution {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        // write your code here
        if (num[0] < num[num.length - 1]){   // 此题的特殊情况就是类似[1,2,3]
            return num[0];
        }
        int start = 0;
        int end = num.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (num[mid] > num[0]){
                start = mid;
            }
            if (num[mid] < num[num.length - 1]){
                end = mid;
            }
        }
        return Math.min(num[start], num[end]);
    }
}


2. Find minimum in rotate sorted array II    
http://www.lintcode.com/en/problem/find-minimum-in-rotated-sorted-array-ii/




3. Search in rotated sorted array   
http://www.lintcode.com/en/problem/search-in-rotated-sorted-array/



4. Search in rotated sorted array II   
http://www.lintcode.com/en/problem/search-in-rotated-sorted-array-ii/



5. Median of two sorted arrays   
http://www.lintcode.com/en/problem/median-of-two-sorted-arrays/


6. Recover rotated sorted array  
Given a rotated sorted array, recover it to sorted array in-place.
Example: [4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]  
http://www.lintcode.com/en/problem/recover-rotated-sorted-array/

public class Solution {
    /**
     * @param nums: The rotated sorted array
     * @return: void
     */
    public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
        // write your code
        if (nums == null || nums.size() == 1 || nums.get(0) < nums.get(nums.size() - 1)){
            return;  
        }
        int len = nums.size();
        for (int i = 1; i < len; i++){
            if (nums.get(i) < nums.get(i - 1)){  // 或者用nums.get(i)>nums.get(i+1)，包括单调递增的特殊情况。
                reverse(nums, 0, i - 1);   // 翻转左段
                reverse(nums, i, len - 1);  // 翻转右段
                reverse(nums, 0, len - 1);  // 整体翻转
                return;
            }
        }
    }
    
    public void reverse(ArrayList<Integer> nums, int start, int end){
        while(start < end){
            int temp = nums.get(start);
            nums.set(start, nums.get(end));
            nums.set(end, temp);
            start++;
            end--;
        }
    }
}


7. Rotate string    
Given a string and an offset, rotate string by offset. (rotate from left to right)
Given "abcdefg".
offset=0 => "abcdefg"
offset=1 => "gabcdef"
offset=2 => "fgabcde"
offset=3 => "efgabcd"
http://www.lintcode.com/en/problem/rotate-string/

public class Solution {
    /**
     * @param str: an array of char
     * @param offset: an integer
     * @return: nothing
     */
    public void rotateString(char[] str, int offset) {
        // write your code here
        if (str == null || str.length == 0){
            return;
        }
        int len = str.length;
        offset = offset % len;
        reverse(str, 0, len - offset - 1);  // 先翻转左段
        reverse(str, len - offset, len - 1);  // 翻转右段
        reverse(str, 0, len - 1);   // 整体翻转
    }
    
    public void reverse(char[] str, int start, int end){
        while (start < end){
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
    }
}


8. Reverse words in a string  
Given an input string, reverse the string word by word.
For example, Given s = "the sky is blue", return "blue is sky the". 
http://www.lintcode.com/en/problem/reverse-words-in-a-string/

public class Solution {
    /**
     * @param s : A string
     * @return : A string
     */
    public String reverseWords(String s) {
        // write your code
        String[] words = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = words.length - 1; i >=0 ; i--){
            if (!words[i].equals("")){
                sb.append(words[i]);
                sb.append(" ");
            }
        }
        if (sb.length() != 0){   // sb可能为“”
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}

这个解法没有在最前面判断s是否为“”，因为split和append之后，sb仍可能为“”，还是要在return前判断sb的长度。



