package org.example;

import java.util.ArrayList;
/**
 * Author:Mayank Yadav
 *
 */

public class Board {


    public char[][] board;
    public char player;
    public char winner;
    int moves;
    public boolean finished;
    ArrayList<Integer> availableMoves;

    public String boardKey() {
        String key = "";
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                key += board[i][j];
            }
        }
        return key;
    }

    public Board() {
        this.board = new char[3][3];
        this.player = 'X';
        this.winner = 'E';
        this.finished = false;
        this.moves = 0;
        this.availableMoves = new ArrayList<Integer>();

        // set all board pieces with empty (no player has marked it yet)
        for (int i=0; i<3; i++)
        {
            for (int j=0; j<3; j++)
            {
                this.board[i][j] = 'E';
            }
        }

        // initialize empty cells that could be marked
        for (int i=0; i<9; i++)
        {
            this.availableMoves.add(i);
        }
    }

    // command-line view of the board
    public void printBoard()
    {
        for (int i=0; i<3; i++)
        {
            for (int j=0; j<3; j++)
            {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setPlayer(char player)
    {
        this.player = player;
        //System.out.println("called");
    }

    // mark move of the player and remove it from all possible moves
    public void move(int x, int y)
    {
        this.board[x][y] = this.player;
        this.moves++;

        this.availableMoves.remove(Integer.valueOf(x * 3 + y));

        this.checkWinner();
        if (!this.finished) {
            if(this.player == 'X')
                this.player = 'O';
            else
                this.player = 'X';
        }
    }


    void checkWinner() {
        // horizontal checking
        if (this.board[0][0] == this.board[0][1] && this.board[0][1] == this.board[0][2] && this.board[0][0] != 'E') {
            this.finished = true;
            this.winner = this.player;
        }

        if (this.board[1][0] == this.board[1][1] && this.board[1][1] == this.board[1][2] && this.board[1][0] != 'E') {
            this.finished = true;
            this.winner = this.player;
        }

        if (this.board[2][0] == this.board[2][1] && this.board[2][1] == this.board[2][2] && this.board[2][0] != 'E') {
            this.finished = true;
            this.winner = this.player;
        }

        // vertical checking
        if (this.board[0][0] == this.board[1][0] && this.board[1][0] == this.board[2][0] && this.board[0][0] != 'E') {
            this.finished = true;
            this.winner = this.player;
        }

        if (this.board[0][1] == this.board[1][1] && this.board[1][1] == this.board[2][1] && this.board[0][1] != 'E') {
            this.finished = true;
            this.winner = this.player;
        }

        if (this.board[0][2] == this.board[1][2] && this.board[1][2] == this.board[2][2] && this.board[0][2] != 'E') {
            this.finished = true;
            this.winner = this.player;
        }

        // diagonal checking
        if (this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2] && this.board[0][0] != 'E') {
            this.finished = true;
            this.winner = this.player;
        }

        if (this.board[0][2] == this.board[1][1] && this.board[1][1] == this.board[2][0] && this.board[1][1] != 'E') {
            this.finished = true;
            this.winner = this.player;
        }

        // draw
        if (this.availableMoves.size() == 0 && this.winner == 'E') {
            this.finished = true;
        }
    }

    // returns a copy of the board
    Board copyThis()
    {
        Board copy = new Board();

        for (int i=0; i<3; i++)
        {
            for (int j=0; j<3; j++)
            {
                copy.board[i][j] = this.board[i][j];
            }
        }
        copy.player = this.player;
        copy.winner = this.winner;
        copy.availableMoves = new ArrayList<Integer>();
        copy.availableMoves.addAll(this.availableMoves);
        copy.moves = this.moves;
        copy.finished = this.finished;
        return copy;
    }
}