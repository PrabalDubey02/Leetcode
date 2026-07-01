import java.util.HashMap;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {

        if (valueDiff < 0) return false;

        HashMap<Long, Long> map = new HashMap<>();
        long bucketSize = (long) valueDiff + 1;

        for (int i = 0; i < nums.length; i++) {

            long num = (long) nums[i];
            long bucketId = getBucketId(num, bucketSize);

           
            if (map.containsKey(bucketId))
                return true;

            if (map.containsKey(bucketId - 1) &&
                Math.abs(num - map.get(bucketId - 1)) <= valueDiff)
                return true;

            
            if (map.containsKey(bucketId + 1) &&
                Math.abs(num - map.get(bucketId + 1)) <= valueDiff)
                return true;

            map.put(bucketId, num);

           
            if (i >= indexDiff) {
                long oldBucket = getBucketId(nums[i - indexDiff], bucketSize);
                map.remove(oldBucket);
            }
        }

        return false;
    }

    private long getBucketId(long num, long size) {
        if (num >= 0)
            return num / size;
        return ((num + 1) / size) - 1;
    }
}