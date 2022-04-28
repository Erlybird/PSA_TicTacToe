package org.example;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.JButton;

/**
 * Author:Jayesh Khattar
 *
 */
public class App implements ActionListener {

    private static final Logger logger = LogManager.getLogger(App.class);
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
    JLabel lblTotal = new JLabel();
    JLabel lblTotalCnt = new JLabel();


    JButton btnSimulation = new JButton();

    JButton human = new JButton();

    JButton[][] buttons = new JButton[3][3];
    Board board;

    Map<String, Integer> mov1 = new Hashtable<>();
    Map<String, Integer> mov2 = new Hashtable<>();
    Map<String, Integer> mov3 = new Hashtable<>();
    Map<String, Integer> mov4 = new Hashtable<>();
    int win = 0;
    int lose = 0;
    int draw = 0;

    int movCount = 0;
    String movCopy = "";

    App()
    {
        logger.info("initiating application");
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

    public void restartTheGame(){
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
        btnSimulation.setText("Train Menace");
        btnSimulation.setOpaque(true);

        lblTotal.setText("Total Count : ");
        lblTotal.setForeground(new Color(255,255,255));
        lblTotal.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblWin.setText("Won Count : ");
        lblWin.setForeground(new Color(255,255,255));
        lblWin.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblLoose.setText("Loose Count : ");
        lblLoose.setForeground(new Color(255,255,255));
        lblLoose.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblDraw.setText("Draw Count : ");
        lblDraw.setForeground(new Color(255,255,255));
        lblDraw.setFont(new Font("Times New Roman",Font.PLAIN,20));

        lblWinCnt.setForeground(new Color(255,255,255));
        lblWinCnt.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblLooseCnt.setForeground(new Color(255,255,255));
        lblLooseCnt.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblDrawCnt.setForeground(new Color(255,255,255));
        lblDrawCnt.setFont(new Font("Times New Roman",Font.PLAIN,20));
        lblTotalCnt.setForeground(new Color(255,255,255));
        lblTotalCnt.setFont(new Font("Times New Roman",Font.PLAIN,20));

//        result_panel.setLayout(new GridLayout(1,3));
        result_panel.setBackground(new Color(0,0,0));
        result_panel.add(lblTotal);
        result_panel.add(lblTotalCnt);
        result_panel.add(lblWin);
        result_panel.add(lblWinCnt);
        result_panel.add(lblLoose);
        result_panel.add(lblLooseCnt);
        result_panel.add(lblDraw);
        result_panel.add(lblDrawCnt);
        result_panel.add(lblTotal);
        result_panel.add(lblTotalCnt);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(heading_panel);
        mainPanel.add(difiiculty_panel);
        mainPanel.add(result_panel);

        heading_panel.setLayout(new GridLayout());
        heading_panel.setBounds(0,0,800,100);

        title_panel.setLayout(new FlowLayout());
        title_panel.setBackground(new Color(0,0,0));
        title_panel.setBounds(100,0,800,100);

        difiiculty_panel.setLayout(new GridLayout(1,4));
        difiiculty_panel.setBackground(new Color(255,255,255));
        difiiculty_panel.setBounds(150,0,800,50);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(0,0,0));

        difiiculty_panel.add(btnSimulation);
        difiiculty_panel.add(human);

        title_panel.add(textfield);
        heading_panel.add(game_h1);

        frame.add(mainPanel,BorderLayout.NORTH);
        frame.add(button_panel);
        //    frame.add(result_panel);

        textfield.setText("Select Game Type");
        JOptionPane.showMessageDialog(frame,"First Run Training... Click on Train Menace Button");
    }


    public static void main( String[] args )
    {
        logger.info("Application main method called");
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        new App();
    }


    void recolor() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.board[i][j] == 'E') {
//                    buttons[i][j].setForeground();
                    buttons[i][j].setText("");
                    buttons[i][j].setEnabled(true);
                }
                if (board.board[i][j] == 'X') {
                    buttons[i][j].setForeground(new Color(255,0,0));
                    buttons[i][j].setText("X");
                    buttons[i][j].setEnabled(false);
                } else if (board.board[i][j] == 'O') {
                    buttons[i][j].setForeground(new Color(0,0,255));
                    buttons[i][j].setText("O");
                    buttons[i][j].setEnabled(false);
                } else buttons[i][j].setBackground(new Button().getBackground());
            }
        }
    }

    public void allActionListener() {
        btnSimulation.addActionListener(e ->
        {
            try {
                simulationFunction();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        human.addActionListener(e ->{
            startGame();
        });
    }

    public void startGame() {
        board = new Board();
        recolor();
        movCount = 0;
        movCopy = "";
    }

    public void simulationFunction() throws InterruptedException {
        Random rn = new Random();
        for(int kk=0;kk<100;kk++) {
            try {
                int i = 0, j = 0;
                Map<Integer, String> movMap = new Hashtable<>();
                int mov=0;
                String movState = "";
                while (!board.finished) {
                    int answer = board.availableMoves.get(rn.nextInt(board.availableMoves.size()));
                    movState += answer;
                    i = answer / 3;
                    j = answer % 3;
                    board.move(i, j);
                    recolor();
                    System.out.println("Board after X move - ");
                    board.printBoard();
                    System.out.println();
                    if (board.finished) {
                        logger.info("Game ended. "+(board.winner == 'X' ? "X(Random Bot) WINS!" : board.winner == 'O' ? "O(Training Menace) WINS!" : "DRAW!"));
                    } else {
                        Minimax min = new Minimax();
                        int move = min.minimax(board, board.player);
                        movState += move;
                        System.out.println("Board after O move - ");
                        board.printBoard();
                        System.out.println();
                        recolor();
                    }
                    if (board.finished) {
                        logger.info("Game ended. "+(board.winner == 'X' ? "X(Random Bot) WINS!" : board.winner == 'O' ? "O(Training Menace) WINS!" : "DRAW!"));
                    }
                    movMap.put(mov,movState);
                    mov++;
                }
                calculateTotals();
                for (Integer b : movMap.keySet()) {
                    int cx = 0;
                    String key = movMap.get(b);
                    switch(b) {
                        case 0: {
                            if (mov1.containsKey(key))
                                cx = mov1.get(key);
                            giveScore(mov1, key, cx);
                            break;
                        }
                        case 1:
                            if (mov2.containsKey(key))
                                cx = mov2.get(key);
                            giveScore(mov2, key, cx);
                            break;
                        case 2:
                            if (mov3.containsKey(key))
                                cx = mov3.get(key);
                            giveScore(mov3, key, cx);
                            break;
                        case 3:
                            if (mov4.containsKey(key))
                                cx = mov4.get(key);
                            giveScore(mov4, key, cx);
                            break;
                        default:
                            break;
                    }
                }
                restartTheGame();
            }
            catch (Exception ex) {
                logger.info("Exception occurred in training mode -"+ex.getMessage());
            }
        }
        logger.info("Training Finished. Menace ready to challange.");
        logger.info("Win Count = "+win);
        logger.info("Lose Count = "+lose);
        logger.info("Draw Count = "+draw);
        lblWinCnt.setText(String.valueOf(win));
        lblLooseCnt.setText(String.valueOf(lose));
        lblDrawCnt.setText(String.valueOf(draw));
        lblTotalCnt.setText(String.valueOf(win+lose+draw));
        JOptionPane.showMessageDialog(frame,"Training Finished.. Play against player .. Click on Player Button");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        movCount++;
        playTurn(e);
    }

    public void playTurn(ActionEvent e) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].equals(e.getSource())) {
                    buttons[i][j].setEnabled(false);

                    if (board.player == 'X') {
                        movCopy += (i*3+j);
                        board.move(i, j);
                        recolor();
                    }
                    try {
                        if (board.player == 'O') {
                            int mov = useHashMap(i,j);
                            recolor();
                        }
                    }
                    catch (Exception ex) {
                        logger.info("Exception occurred in menace move-"+ex.getMessage());
                    }
                    if (board.finished) {
                        JOptionPane.showMessageDialog(frame,
                                board.winner == 'X' ? "Player WINS!" :
                                        board.winner == 'O' ? "Menace WINS!" :
                                                "DRAW!");
                    }
                }
            }
        }
    }
    public int useHashMap(int i, int j) {
        int m = 0;
        if(movCount == 1) {
            m = returnState(mov1);
        }
        else if(movCount == 2) {
            m = returnState(mov2);
        }
        else if(movCount == 3) {
            m = returnState(mov3);
        }
        else if(movCount == 4) {
            m = returnState(mov4);
        }
        if(m != 0) {
            i = m/3;
            j = m%3;
            board.move(i, j);
            return m;
        }
        else {
            Minimax min = new Minimax();
            min.minimax(board, board.player);
        }
        return m;
    }

    public void giveScore(Map<String, Integer> mov, String key, int cx) {
        if (board.winner == 'O') {
            cx++;
            logger.info("Game status - Menace Wins "+"beta score for "+key +" position - "+cx);
        } else if (board.winner == 'X') {
            cx--;
            logger.info("Game status - Random Bot Wins "+"gama score for "+key +" position - "+cx);
        }
        else {
            logger.info("Game status - Draw "+"delta score for "+key +" position - "+cx);
        }
        mov.put(key, cx);
    }

    public void calculateTotals() {
        if (board.winner == 'O') {
            win++;
        } else if (board.winner == 'X') {
            lose++;
        }
        else {
            draw++;
        }

    }

    public int returnState(Map<String, Integer> mov) {
        String stat = "";
        int high = Integer.MIN_VALUE;
        for(String state : mov.keySet()) {
            if(state.startsWith(movCopy) & mov.get(state) > high) {
                high = mov.get(state);
                stat = state;

            }
        }
        int ans=0;
        if(stat != "") {
            ans = Integer.parseInt(stat.substring(stat.length()-1));
        }
        return ans;
    }
}
