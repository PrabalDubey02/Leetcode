class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        
          List<Integer> ans =  new ArrayList<>();
          int i = 0;
        while (i < nums.length) {
           
            int idx = nums[i] - 1;

            if (idx == i ) {
                i++;
                continue;
            }

            if (nums[i] == nums[idx]) {
                
                i++;
                continue;
            }

            swap(nums, i, idx);
        }
        for( i =0; i < nums.length; i++){
            if(nums[i]!=i+1) ans.add(nums[i]);
        }

        return ans;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
        
    
