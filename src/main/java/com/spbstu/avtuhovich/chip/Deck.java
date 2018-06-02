package com.spbstu.avtuhovich.chip;

public class Deck {
    private int[][] work = new int[][]{
            {-1, 0, -1, 0, -1, 0, -1, 0},
            {0, -1, 0, -1, 0, -1, 0, -1},
            {-1, 0, -1, 0, -1, 0, -1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1}
    };

    public int getChip(int x, int y) {
        return work[y][x];
    }

    public void moveChip(int x1, int y1, int x2, int y2) {
        int tmp = work[y1][x1];
        work[y1][x1] = work[y2][x2];
        work[y2][x2] = tmp;
        if (work[y2][x2] == 1 && y2 == 0 ||
                work[y2][x2] == -1 && y2 == 8)
            upgrade(x2, y2);
    }

    public void delete(int x, int y) {
        work[y][x] = 0;
    }

    private void upgrade(int x, int y) {
        if (work[y][x] > 0 && work[y][x] < 3)
            work[y][x]++;
        else if (work[y][x] > -2)
            work[y][x]--;
    }
}
