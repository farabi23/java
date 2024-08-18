class Solution {
    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);

        Arrays.sort(students);

       int moves = 0;
       // basically need to count the min number of moves to seat each student, to
       // their corresponding ith seat

        for (int i = 0; i < seats.length; i++){
            moves = moves + Math.abs(seats[i] - students[i]);
        
        }
       
        
        return moves;
    }
}
