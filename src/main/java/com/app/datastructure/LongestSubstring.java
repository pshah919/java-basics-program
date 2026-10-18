package com.app.datastructure;

public class LongestSubstring {

    public static void main(String[] args) {
        System.out.println(longestSub("abcdefabcddc"));
        System.out.println(longestSub("abcabc"));

    }

    public static int longestSub(String s) {
        int[] chars = new int[128];
        int right = 0;
        int left = 0;
        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            chars[r]++;

            while (chars[r] > 1) {
                char l = s.charAt(left);
                chars[l]--;
                left++;
            }

            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}
