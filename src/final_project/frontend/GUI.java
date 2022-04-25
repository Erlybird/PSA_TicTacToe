package final_project.frontend;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;
import final_project.backend.Board;


import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import java.util.*;

public class GUI implements ActionListener {
	JFrame frame;
	JPanel mainPanel;
	JPanel btnPanel;
	JPanel boxPanel;
	JPanel heading_panel;
	JPanel title_panel;
	
	JButton[][] boxes;
	JButton[][] buttons;
	JButton startButton;
	Board board;

	boolean isAI;
	boolean isX;
	boolean isFirst;
	
	JLabel frameTitle; 
	JLabel gameStatus; 
	
	JButton btnEasy;
	JButton btnMenace;
	JButton btnSimulation;
	JButton btnHuman;
	
	Map<Board, Integer> map1 = new HashMap<Board, Integer>();

	public GUI() {
		initComponents();
	}
	
	void initComponents()
	{
		this.boxes = new JButton[3][3];
		this.buttons = new JButton[3][2];
		this.board = null;

		this.isAI = false;
		this.isX = true;
		this.isFirst = true;

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		
		frameTitle = new JLabel();
		gameStatus = new JLabel();
		
		btnEasy = new JButton();
		btnMenace = new JButton();
		btnSimulation = new JButton();
		btnHuman = new JButton();

		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		
		
		this.btnPanel = new JPanel();
		this.btnPanel.setLayout(new GridLayout(1,4));
		this.btnPanel.setBackground(new Color(255,255,255));
		this.btnPanel.setBounds(150,0,800,50);
		
		this.boxPanel = new JPanel();
		this.boxPanel.setLayout(new GridLayout(3,3));
		this.boxPanel.setBackground(new Color(0,0,0));
		
		heading_panel = new JPanel();
		heading_panel.setLayout(new GridLayout());
		heading_panel.setBounds(0,0,800,100);
		
		title_panel = new JPanel();
		title_panel.setLayout(new FlowLayout());
		title_panel.setBackground(new Color(0,0,0));
		title_panel.setBounds(100,0,800,100);
		
		
		
		configureLayout();
		addBoxes();
		addButtons();
		allActionListener();
		
		this.mainPanel.add(heading_panel);
		this.mainPanel.add(title_panel);
		this.mainPanel.add(btnPanel);
		
		frame.add(this.mainPanel,BorderLayout.NORTH);
		frame.add(boxPanel);
	}
	
	
	private void configureLayout()
	{
		
		frameTitle.setBackground(new Color(0,0,0));
		frameTitle.setForeground(new Color(255,255,255));
		frameTitle.setFont(new Font("Times New Roman",Font.BOLD,65));
		frameTitle.setHorizontalAlignment(JLabel.CENTER);
		frameTitle.setText("Tic-Tac-Toe");
		frameTitle.setOpaque(true);
		
		gameStatus.setBackground(new Color(25,25,25));
		gameStatus.setForeground(new Color(25,255,0));
		gameStatus.setFont(new Font("Times New Roman",Font.PLAIN,40));
		gameStatus.setHorizontalAlignment(JLabel.CENTER);
		gameStatus.setText("Game Status: Select Game Level");
		
		heading_panel.add(frameTitle);
		title_panel.add(gameStatus);

	}

	// create clickable boxes
	void addBoxes() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.boxes[i][j] = new JButton();
				this.boxes[i][j].setFont(new Font("MV Boli",Font.BOLD,120));
				this.boxes[i][j].setFocusable(false);
				this.boxes[i][j].setEnabled(false);
				this.boxes[i][j].addActionListener(this);
				this.boxPanel.add(this.boxes[i][j]);
			}
		}
	}

	// create boxes for options
	void addButtons() {
		
		btnHuman.setBackground(new Color(50,50,50));
		btnHuman.setForeground(new Color(255,255,255));
		btnHuman.setFont(new Font("Times New Roman",Font.PLAIN,35));
		btnHuman.setHorizontalAlignment(JButton.CENTER);
		btnHuman.setBorder(BorderFactory.createLineBorder(new Color(40,40,40), 5));
		btnHuman.setMargin(new Insets(10, 30, 10, 30));
		btnHuman.setText("Player");
		btnHuman.setOpaque(true);
		
		
		btnEasy.setBackground(new Color(50,50,50));
		btnEasy.setForeground(new Color(255,255,255));
		btnEasy.setFont(new Font("Times New Roman",Font.PLAIN,35));
		btnEasy.setHorizontalAlignment(JButton.CENTER);
		btnEasy.setBorder(BorderFactory.createLineBorder(new Color(40,40,40), 5));
		btnEasy.setMargin(new Insets(10, 30, 10, 30));
		btnEasy.setText("Naive Bot");
		btnEasy.setOpaque(true);
		
		btnMenace.setBackground(new Color(50,50,50));
		btnMenace.setForeground(new Color(255,255,255));
		btnMenace.setFont(new Font("Times New Roman",Font.PLAIN,35));
		btnMenace.setHorizontalAlignment(JButton.CENTER);
		btnMenace.setBorder(BorderFactory.createLineBorder(new Color(40,40,40), 5));
		btnMenace.setMargin(new Insets(10, 30, 10, 30));
		btnMenace.setText("Menace Bot");
		btnMenace.setOpaque(true);
		
		btnSimulation.setBackground(new Color(50,50,50));
		btnSimulation.setForeground(new Color(255,255,255));
		btnSimulation.setFont(new Font("Times New Roman",Font.PLAIN,35));
		btnSimulation.setHorizontalAlignment(JButton.CENTER);
		btnSimulation.setBorder(BorderFactory.createLineBorder(new Color(40,40,40), 5));
		btnSimulation.setMargin(new Insets(10, 30, 10, 30));
		btnSimulation.setText("Simulation");
		btnSimulation.setOpaque(true);
		
		this.btnPanel.add(btnHuman);
		this.btnPanel.add(btnEasy);
		this.btnPanel.add(btnMenace);
		this.btnPanel.add(btnSimulation);
		
	}

	// enable/disable boxes
	void enableBoxes(boolean bool) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				boxes[i][j].setEnabled(bool);
			}
		}

		if (!bool) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					boxes[i][j].setBackground(Color.WHITE);
				}
			}
		}
	}

	// enable/disable buttons
	void enableButtons(boolean bool) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				buttons[i][j].setEnabled(bool);
			}
		}
		startButton.setEnabled(bool);

		if (bool) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 2; j++) {
					buttons[i][j].setBackground(Color.WHITE);
				}
			}

			startButton.setBackground(Color.WHITE);
		}
	}

	// recolor boxes depends on board placements
	void recolor() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board.board[i][j] == 'X') {
					boxes[i][j].setBackground(Color.RED);
					boxes[i][j].setEnabled(false);
				} else if (board.board[i][j] == 'O') {
					boxes[i][j].setBackground(Color.BLUE);
					boxes[i][j].setEnabled(false);
				} else boxes[i][j].setBackground(Color.WHITE);
			}
		}
	}

	// Call the Minimax Program to start the Minimax algorithm
	void moveAI() {
		// Change this for Alpha-beta Pruning
//		AlphaBeta m = new AlphaBeta();
//		m.minimax(board, Integer.MIN_VALUE, Integer.MAX_VALUE, board.player);
		
		// Minimax m = new Minimax();
		// m.minimax(board, board.player);
		recolor();
	}
	
	private void allActionListener() 
	{
		
		btnEasy.addActionListener(e ->
		{
			
		});
		
		btnHuman.addActionListener(e ->
		{
			enableBoxes(true);
			board = new Board();
		});
		
		btnSimulation.addActionListener(e ->
		{
			enableBoxes(true);
			board = new Board();
			simulationFunction();
		});
		
		
		btnMenace.addActionListener(e ->
		{
			
		});
		
		
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (boxes[i][j].equals(e.getSource())) {
					boxes[i][j].setBackground(this.board.player == 'X' ? Color.RED : Color.BLUE);
					boxes[i][j].setEnabled(false);

					if (isAI && isX && board.player == 'X') {
						board.move(i, j);
						moveAI();
					}

					if (isAI && !isX && board.player == 'O') {
						board.move(i, j);
					 	moveAI();
					}

					if (!isAI) {
						board.move(i,j);
						board.setPlayer(board.player == 'X' ? 'X' : 'O');
					}

					if (board.finished) {
						JOptionPane.showMessageDialog(frame, 
							board.winner == 'X' ? 
								"RED WINS!" 
							: board.winner == 'O' ? 
								"BLUE WINS!" 
							: "DRAW!");
						enableBoxes(false);
						enableButtons(true);
					}
				}
			}
		}

		

	}
	
	public void simulationFunction() {
        
            if (isX) board.player = 'X';
            else board.player = 'O';

                Random rn = new Random();
                for(int kk=0;kk<10;kk++) {
                int counter = 0;
                int i = 0, j = 0;
                Set<Board> localLst = new HashSet<Board>();
                while(!board.finished) {
                    //                    board.availableMoves(move)
                    //move 1
                    int answer = rn.nextInt(8);
                    i = answer / 3;
                    j = answer % 3;
//                    board.availableMoves.get();
                    board.move(i,j);
                    recolor();
                    answer = rn.nextInt(8);
                    i = answer / 3;
                    j = answer % 3;
                    board.move(i,j);
                    recolor();
                    localLst.add(board);
                }
                if (board.finished) {
                    JOptionPane.showMessageDialog(frame,
                            board.winner == 'x' ?
                                    "RED WINS!"
                                    : board.winner == 'o' ?
                                    "BLUE WINS!"
                                    : "DRAW!");
                    enableBoxes(false);
                }
                for(Board b : localLst) {
                    int cx = 0;
                    if(map1.containsKey(b)) 
                        cx = map1.get(b);
                    if(board.winner == 'x')
                         cx++;
                    else if(board.winner == 'o')
                        cx--;
                    map1.put(b, cx);
                }      

                map1.put(board, counter);
                board = new Board();
            }
        
        for(Board b : map1.keySet()) {
            System.out.println("Board - "+b + " value - "+ map1.get(b));
        }
	}
 
	
	
	
	
	public static void main(String args[]) {
		

                GUI frame = new GUI();
        
	}
}