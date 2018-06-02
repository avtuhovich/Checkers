package com.spbstu.avtuhovich.chip;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Game {
    public int player = 1;
    public boolean isGame = true;
    private Deck d = new Deck();
    private int black = 12;
    private int white = 12;

    private boolean atack(Point p1, Point p2) {
        Point del = new Point((p2.x - p1.x) / 2, (p2.y - p1.y) / 2);
        if (d.getChip(p1.x + del.x, p1.y + del.y) == player * -1) {
            d.delete(p1.x + del.x, p1.y + del.y);
            d.moveChip(p1.x, p1.y, p2.x, p2.y);
            if (player == 1) black--;
            else white--;
            if (black == 0 || white == 0) isGame = false;
            return true;
        }
        return false;
    }

    private int ordinar(Point p1, Point p2) {
        if (Math.abs(p2.y - p1.y) == 2)
            if (atack(p1, p2))
                return 1;
            else
                return -2;
        if (Math.abs(p2.x - p1.x) == 1 && d.getChip(p2.x, p2.y) == 0) {
            d.moveChip(p1.x, p1.y, p2.x, p2.y);
            return 0;
        }
        return -1;
    }

    public boolean move(Point p1, List<Point> moves) {
        if (!isGame) return false;
        for (Point p : moves)
            if (!p.valid()) return false;
        boolean suc = false;
        for (Point p : moves) {
            if (Math.abs(p.x - p1.x) == Math.abs(p.y - p1.y) &&
                    (d.getChip(p1.x, p1.y) > 0 && player > 0 || d.getChip(p1.x, p1.y) < 0 && player < 0)) {
                int del = 0;
                if (Math.abs(p.y - p1.y) == 1) del = p.y - p1.y;
                if (Math.abs(p.y - p1.y) == 2) del = (p.y - p1.y) / 2;
                if (d.getChip(p1.x, p1.y) == player && del != 0) {
                    int code = ordinar(p1, p);
                    if (code == 0 || code == 1) suc = true;
                    else {
                        suc = false;
                        break;
                    }
                } else if (d.getChip(p1.x, p1.y) == player * 2) {
                    if (!vip(p1, p)) break;
                }
            }
            p1.x = p.x;
            p1.y = p.y;
        }
        if (suc) {
            player *= -1;
            return true;
        } else return false;
    }

    private boolean vip(Point p1, Point p2) {
        if (d.getChip(p2.x, p2.y) == 0) {
            int dx = p2.x - p1.x > 0 ? 1 : -1;
            int dy = p2.y - p1.y > 0 ? 1 : -1;
            Point temp = new Point(p1.x + dx, p1.y + dy);
            Point free = null;
            List<Point> del = new LinkedList<>();
            while (temp.x != p2.x && temp.y != p2.y) {
                if (d.getChip(temp.x, temp.y) == player * -1)
                    del.add(temp);
                else if (d.getChip(temp.x, temp.y) == player)
                    break;
                else if (d.getChip(temp.x, temp.y) == 0)
                    free = temp;
                temp.x += dx;
                temp.y += dy;
            }
            temp.x = p1.x + dx;
            temp.y = p1.y + dy;
            while (temp.x != free.x && temp.y != free.y) {
                if (del.contains(temp)) d.delete(temp.x, temp.y);
                temp.x += dx;
                temp.y += dy;
            }
            d.moveChip(p1.x, p1.y, free.x, free.y);
            return true;
        }
        return false;
    }

    public Deck getDeck() {
        return d;
    }

    public static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < 8 && y >= 0 && y < 8;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {

            return Objects.hash(x, y);
        }
    }
}
