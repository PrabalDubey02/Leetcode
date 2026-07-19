class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int sumA = 0;
        int sumB = 0;
         for (int a : aliceSizes)
            sumA += a;

        for (int b : bobSizes)
            sumB += b;

        
        
       
           for (int a : aliceSizes) {
            for (int b : bobSizes) {
                 
                if (sumA - a + b == sumB - b + a)
                    return new int[]{a, b};
            }
        }

        return new int[]{};

    }
}