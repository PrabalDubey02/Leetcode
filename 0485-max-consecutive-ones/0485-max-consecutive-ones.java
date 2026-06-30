
        class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int max=0;
        for(int ele : nums){
            if(ele == 1){
                count++;
                max = Math.max(max,count);
            }
            else{ count = 0;}

            }
        
       return max; 
    }
    }

    
