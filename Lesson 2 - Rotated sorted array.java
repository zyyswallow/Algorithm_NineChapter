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
Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element. The array may contain duplicates.  
http://www.lintcode.com/en/problem/find-minimum-in-rotated-sorted-array-ii/

public class Solution {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        // write your code here
        int len = num.length;
        int start = 0;
        int end = len - 1;
        while (start < end && num[start] == num[end]){  // [1,1,2,1]
                start++;
            }
        if (num[start] < num[end] || start == end){  // [1,1,-1,1] or [1,1,1]
            return num[start];
        }
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (num[mid] >= num[start]){
                start = mid;
            }
            if (num[mid] <= num[end]){
                end = mid;
            }
        }
        return Math.min(num[start], num[end]);
    }
}

不仅要考虑头尾值相等的情况，还要考虑把头部略过之后的数组是单调递增还是单调递减的。
所以，要先略过头部重复值，再判断是否单调递增。


3. Search in rotated sorted array   
Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.
http://www.lintcode.com/en/problem/search-in-rotated-sorted-array/

public class Solution {
    /** 
     *@param A : an integer rotated sorted array
     *@param target :  an integer to be searched
     *return : an integer
     */
    public int search(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0){
            return -1;
        }
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (A[mid] == target){
                return mid;
            } else if (A[mid] >= A[start]){  // mid在左端，有三种子情况
                if (target >= A[start] && target < A[mid]){  
                    end = mid;
                }else {
                    start = mid;
                }
            } else if (A[mid] <= A[end]){   // mid在右端，也有三种子情况
                if (target <= A[end] && target > A[mid]){
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        
        if (A[start] == target){
            return start;
        } else if (A[end] == target){
            return end;
        } else {
            return -1;
        }
    }
}

此题分类特别多，要按照mid在左边还是右边区分


4. Search in rotated sorted array II   
http://www.lintcode.com/en/problem/search-in-rotated-sorted-array-ii/

public class Solution {
    /** 
     * param A : an integer ratated sorted array and duplicates are allowed
     * param target :  an integer to be search
     * return : a boolean 
     */
    public boolean search(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0){
            return false;
        }
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end){
            if (A[start] == A[end]){   // 与上题比，多了给数组头部去重的步骤
                start++;
                continue;
            }
            int mid = start + (end - start) / 2;
            if (A[mid] == target){
                return true;
            } else if (A[mid] >= A[start]){  // mid在左端，有三种子情况
                if (target >= A[start] && target < A[mid]){  
                    end = mid;
                }else {
                    start = mid;
                }
            } else if (A[mid] <= A[end]){   // mid在右端，也有三种子情况
                if (target <= A[end] && target > A[mid]){
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        
        return A[start] == target || A[end] == target;
    }
}



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



