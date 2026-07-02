class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;
        int [] farr = new int[26];
        for(int i =0;i<s.length();i++){
            char ch = s.charAt(i);
            farr[ch-'a']++;
        }  
         for(int i =0;i<t.length();i++){
            char ch = t.charAt(i);
            farr[ch-'a']--; }
         for(int i =0;i<farr.length;i++){
            if(farr[i]!=0) return false;}
            
            return true;


    }
}