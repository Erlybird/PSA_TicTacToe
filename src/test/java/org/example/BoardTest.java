package org.example;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author sangramvuppala
 */
public class BoardTest {
    @Test
    public void testCase3(){

        Board b = new Board();
        assertEquals(b.player, 'X');
        char[][] testb = {{'E', 'E', 'E'}, {'E', 'E', 'X'}, {'E', 'E', 'E'}};

        b.move(1,2);
        assertArrayEquals(b.board, testb);
        b.setPlayer('O');
        assertEquals(b.player, 'O');
        b.move(1,0);
        testb[1][0] = 'O';
        assertArrayEquals(b.board, testb);


//        b.move(0,1);
//        b.move(2,2);
//        b.move(0,2);
//        assertEquals(3, mb.getMove());
    }

    @Test //checking winner
    public void testCase4(){
        Board a = new Board();
        char[][] testa = {{'X','O','E'},{'O','X','O'},{'X','O','X'}};
        a.board = testa;
        a.checkWinner();
        assertEquals(a.winner,'X');

    }
}
