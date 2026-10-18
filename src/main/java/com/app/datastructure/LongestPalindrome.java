package com.app.datastructure;

public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPali("abcdcbaasasa"));
    }

    static String longestPali(String s){
        for(int i=s.length();i>0;i--){
            for(int j=0;j<=s.length()-i;j++){
                if(check(j, j+i, s)){
                    return s.substring(j, i+j);
                }
            }
        }
        return "";
    }

    static boolean check(int left,int right,String s){
         right=right-1;
        while(left< right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
