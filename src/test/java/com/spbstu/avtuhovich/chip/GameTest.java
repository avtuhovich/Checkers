package com.spbstu.avtuhovich.chip;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class GameTest {

    private Game game = new Game();

    @Test
    public void move() {
        List<Game.Point> moves = new LinkedList<>();
        moves.add(new Game.Point(4, 4));
        Assertions.assertTrue(game.move(new Game.Point(3, 5), moves));
        Assertions.assertEquals(0, game.getDeck().getChip(3, 5));
        Assertions.assertEquals(1, game.getDeck().getChip(4, 4));

        moves.clear();
        moves.add(new Game.Point(3, 3));
        Assertions.assertTrue(game.move(new Game.Point(2, 2), moves));
        Assertions.assertEquals(0, game.getDeck().getChip(2, 2));
        Assertions.assertEquals(-1, game.getDeck().getChip(3, 3));

        moves.clear();
        moves.add(new Game.Point(2, 2));
        Assertions.assertTrue(game.move(new Game.Point(4, 4), moves));
        Assertions.assertEquals(0, game.getDeck().getChip(4, 4));
        Assertions.assertEquals(0, game.getDeck().getChip(3, 3));
        Assertions.assertEquals(1, game.getDeck().getChip(2, 2));

        moves.clear();
        moves.add(new Game.Point(1, 3));
        Assertions.assertTrue(game.move(new Game.Point(3, 1), moves));
        Assertions.assertEquals(0, game.getDeck().getChip(3, 1));

        moves.clear();
        moves.add(new Game.Point(0, 4));
        Assertions.assertTrue(game.move(new Game.Point(1, 5), moves));
        moves.clear();
        moves.add(new Game.Point(3, 3));
        Assertions.assertTrue(game.move(new Game.Point(4, 2), moves));
        moves.clear();
        moves.add(new Game.Point(2, 2));
        moves.add(new Game.Point(4, 4));
        Assertions.assertTrue(game.move(new Game.Point(0, 4), moves));
        Assertions.assertEquals(1, game.getDeck().getChip(4, 4));
        Assertions.assertEquals(0, game.getDeck().getChip(3, 3));
        Assertions.assertEquals(0, game.getDeck().getChip(2, 2));
    }
}