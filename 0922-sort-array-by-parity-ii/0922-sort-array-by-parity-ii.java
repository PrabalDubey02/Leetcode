class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        
        for(int i = 0;i<nums.length;i=i+2){
            for(int j =1;j<nums.length;j=j+2){
                if(nums[i]%2!=0 && nums[j]%2==0) swap(nums, i, j);

            }
        }
        return nums;
    }
    private void swap(int[] arr, int i , int j){
        int temp =arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}