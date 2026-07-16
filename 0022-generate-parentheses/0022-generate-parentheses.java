class Solution {
    public void helper(int n , int l , int r, String s ,List<String> ans ){
        if(r==n ){
            ans.add(s);
            return; 

        }
        
        if(r<l) helper(n,l,r+1,s+")",ans);
        if(l<n) helper(n,l+1,r,s+"(",ans);
    }
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        helper(n,0,0,"",ans);
        return ans ;
        
    }
}