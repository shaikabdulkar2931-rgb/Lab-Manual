class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int dx = coordinates[1][0] - coordinates[0][0];
        int dy = coordinates[1][1] - coordinates[0][1];

        for (int i = 2; i < coordinates.length; i++) {
            int currentDx = coordinates[i][0] - coordinates[0][0];
            int currentDy = coordinates[i][1] - coordinates[0][1];

            if (dy * currentDx != dx * currentDy) {
                return false;
            }
        }

        return true;
    }
}
