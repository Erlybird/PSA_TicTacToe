package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testCase1() throws InterruptedException {
        App ap = new App();
        App.main(null);
        ap.allActionListener();
        ap.simulationFunction();
     //   assertTrue( true );
    }
    @Test
    public void testCase2()
    {
        App ap = new App();
        App.main(null);
        ap.buttons[0][0].doClick();
       // assertTrue( true );
    }
    @Test
    public void testCase3()
    {
        App ap = new App();
        App.main(null);
        ap.restartTheGame();
        //    assertTrue(true);

    }
    @Test
    public void testCase4() {
        App ap = new App();
        App.main(null);
        ap.recolor();
    }
}