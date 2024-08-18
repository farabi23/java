class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
            ArrayList <Boolean> res = new ArrayList();

            int max = 0;
          // first had to fine the max num in array
            for ( int i = 0; i < candies.length; i++){
                if (candies[i] > max){
                    max = candies[i];
                }
            }
// and then compare it to the num in array that is added extra candy
            for ( int i = 0; i < candies.length; i++){

                if ( (candies[i] + extraCandies) >= max ){
                    res.add(true);
                }else{
                    res.add(false);
                }

            }
        return res;
    }
}
