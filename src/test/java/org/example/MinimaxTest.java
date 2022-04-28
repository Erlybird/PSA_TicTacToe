
package org.example;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author sangramvuppala
 */
public class MinimaxTest {

    @Test
    public void testcase1(){
        Minimax a = new Minimax();
        Board current = new Board();
        char[][] testcurrent = { {'O','E','X'},{'X','E','E'},{'X','O','O'}};
        current.board = testcurrent;
        assertEquals(a.max_node(current,'X'), 4);
    }

    @Test
    public void testCase2(){
        Minimax b = new Minimax();
        Board current = new Board();
        char[][] testcurrent = { {'O','E','X'},{'X','E','E'},{'X','O','O'}};
        current.board = testcurrent;
        assertEquals(b.min_node(current,'X'), -1);
    }
}
