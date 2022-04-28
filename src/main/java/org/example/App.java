package org.example;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 * Hello world!
 *
 */
public class App implements ActionListener {

    private static final Logger logger = Logger.getLogger(String.valueOf(App.class));
    Random random = new Random();
    JFrame frame = new JFrame();

    JPanel heading_panel = new JPanel();
    JPanel title_panel = new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel difiiculty_panel = new JPanel();
    JPanel result_panel = new JPanel();
    JPanel button_panel = new JPanel();

    JLabel textfield = new JLabel();
    JLabel game_h1 = new JLabel();

    JLabel h1 = new JLabel();
    JLabel h2 = new JLabel();

    JLabel lblWin = new JLabel();
    JLabel lblWinCnt = new JLabel();
    JLabel lblLoose = new JLabel();
    JLabel lblLooseCnt = new JLabel();
    JLabel lblDraw = new JLabel();
    JLabel lblDrawCnt = new JLabel();


    JButton btnSimulation = new JButton();

    JButton human = new JButton();

    JButton[][] buttons = new JButton[3][3];
    Board board;

    Map<String, Integer> map1 = new Hashtable<>();
    App()
    {
        configureLayout();

        addButtons();
        board = new Board();
        allActionListener();
    }


    private void addButtons()
    {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                buttons[i][j] = new JButton();
                button_panel.add(buttons[i][j]);
                buttons[i][j].setFont(new Font("MV Boli", Font.BOLD, 120));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
            }
        }
    }

    private void restartTheGame(){
        button_panel.removeAll();
        addButtons();
        board = new Board();
        button_panel.updateUI();
        button_panel.repaint();
        textfield.setText("");
    }

    private void configureLayout()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        game_h1.setBackground(new Color(0,0,0));
        game_h1.setForeground(new Color(255,255,255));
        game_h1.setFont(new Font("Times New Roman",Font.BOLD,75));
        game_h1.setHorizontalAlignment(JLabel.CENTER);
        game_h1.setText("Tic-Tac-Toe");
        game_h1.setOpaque(true);

        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25,255,0));
        textfield.setFont(new Font("Times New Roman",Font.PLAIN,45));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("");
        textfield.setOpaque(false);


        human.setBackground(new Color(50,50,50));
        human.setForeground(new Color(255,255,255));
        human.setFont(new Font("Times New Roman",Font.PLAIN,35));
        human.setHorizontalAlignment(JButton.CENTER);
        human.setBorder(BorderFactory.createLineBorder(new Color(40,40,40), 5));
        human.setMargin(new Insets(10, 30, 10, 30));
        human.setText("Player");
        human.setOpaque(true);


        btnSimulation.setBackground(new Color(50,50,50));
        btnSimulation.setForeground(new Color(255,255,255));
        btnSimulation.setFont(new Font("Times New Roman",Font.PLAIN,35));
        btnSimulation.setHorizontalAlignment(JButton.CENTER);
        btnSimulation.setBorder(BorderFactory.createLineBorder(new Color(40,40,40), 5));
        btnSimulation.setMargin(new Insets(10, 30, 10, 30));
        btnSimulation.setText("Simulation");
        btnSimulation.setOpaque(true);



        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(heading_panel);
        mainPanel.add(difiiculty_panel);
        mainPanel.add(title_panel);

        heading_panel.setLayout(new GridLayout());
        heading_panel.setBounds(0,0,800,100);

        title_panel.setLayout(new FlowLayout());
        title_panel.setBackground(new Color(0,0,0));
        title_panel.setBounds(100,0,800,100);

        difiiculty_panel.setLayout(new GridLayout(1,4));
        difiiculty_panel.setBackground(new Color(255,255,255));
        difiiculty_panel.setBounds(150,0,800,50);

        //     result_panel.setLayout(new GridLayout(1,3));
        //   result_panel.setBackground(new Color(255,255,255));
        // result_panel.setBounds(150,0,800,50);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(0,0,0));

        difiiculty_panel.add(human);
        difiiculty_panel.add(btnSimulation);


        title_panel.add(textfield);
        heading_panel.add(game_h1);

        frame.add(mainPanel,BorderLayout.NORTH);
        frame.add(button_panel);
        //    frame.add(result_panel);

        textfield.setText("Select Game Type");
    }


    public static void main( String[] args )
    {
        logger.info("App Class main method");
        new App();
    }


    void recolor() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.board[i][j] == 'X') {
                    buttons[i][j].setForeground(new Color(255,0,0));
                    buttons[i][j].setText("X");
//                    buttons[i][j].setEnabled(false);
                } else if (board.board[i][j] == 'O') {
                    buttons[i][j].setForeground(new Color(0,0,255));
                    buttons[i][j].setText("O");
//                    buttons[i][j].setEnabled(false);
                } else buttons[i][j].setBackground(new Button().getBackground());
            }
        }
    }

    private void allActionListener() {
        btnSimulation.addActionListener(e ->
        {
            simulationFunction();
        });
    }

    public void simulationFunction() {
        Random rn = new Random();
        for(int kk=0;kk<100;kk++){
            Timer timer = new Timer(300, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = 0, j = 0;
                    Set<Board> localLst = new HashSet<Board>();
                    //move 1
                    Minimax min = new Minimax();
                    int move = min.minimax(board, board.player);
                    recolor();
                    board.printBoard();
                    try { Thread.sleep(200);} catch (InterruptedException ex) {ex.printStackTrace();}


                    System.out.println();
                    if (board.finished) {
                        logger.info("Hello this is a info message");
                        logger.log(Level.ALL, "here");
                        ((Timer)e.getSource()).stop();
                        System.out.println(board.winner == 'X' ? "RED WINS!" : board.winner == 'O' ? "BLUE WINS!" : "DRAW!");
                        try { Thread.sleep(1300);} catch (InterruptedException ex) {ex.printStackTrace();}
                        restartTheGame();
                        localLst.add(board);
                        //enableBoxes(false);
                    } else {
                        int answer = board.availableMoves.get(rn.nextInt(board.availableMoves.size()));
                        i = answer / 3;
                        j = answer % 3;
                        board.move(i, j);
                        board.printBoard();
                        System.out.println();
                        recolor();
                        try { Thread.sleep(200);} catch (InterruptedException ex) {ex.printStackTrace();}
                        localLst.add(board);
                    }

                    if (board.finished) {
                        System.out.println(board.winner == 'X' ? "RED WINS!" : board.winner == 'O' ? "BLUE WINS!" : "DRAW!");
                        textfield.setText( board.winner == 'X' ? "RED WINS!" : board.winner == 'O' ? "BLUE WINS!" : "DRAW!");
                        ((Timer)e.getSource()).stop();

                        try { Thread.sleep(1300);} catch (InterruptedException ex) {ex.printStackTrace();}
                        restartTheGame();
                    }
                  //  try { Thread.sleep(1000);} catch (InterruptedException ex) {ex.printStackTrace();}
                    for (Board b : localLst) {
                        int cx = 0;
                        if (map1.containsKey(b.boardKey()))
                            cx = map1.get(b.boardKey());
                        if (board.winner == 'x')
                            cx++;
                        else if (board.winner == 'o')
                            cx--;
                        map1.put(b.boardKey(), cx);
                    }
                }
            });
            timer.start();
            for (String str : map1.keySet()) {
                System.out.println("Board - " + str + " value - " + map1.get(str));
            }
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
