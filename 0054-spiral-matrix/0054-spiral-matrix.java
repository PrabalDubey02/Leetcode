class Solution {
    public List<Integer> spiralOrder(int[][] arr) {
        ArrayList<Integer> ans = new ArrayList<>();
        int m = arr.length; 
        int n = arr[0].length;
        int fr=0;
        int lr = m-1;
        int fc = 0;
        int lc=n-1;
        while(fc<=lc && fr<=lr){
            for(int j = fc;j<=lc;j++)
                ans.add(arr[fr][j]);
            
            fr++;
            if(fr>lr || fc>lc) break;
            for(int i = fr;i<=lr;i++)
                ans.add(arr[i][lc]);
            
            lc--;
            if(fr>lr || fc>lc) break;
            for(int j = lc;j>=fc;j--)
                ans.add(arr[lr][j]);
            
            lr--;
            if(fr>lr || fc>lc) break;
            for(int i = lr;i>=fr;i--)
                ans.add(arr[i][fc]);
            
            fc++;
            if(fr>lr || fc>lc) break;
            

        }
        return ans;
    }
}