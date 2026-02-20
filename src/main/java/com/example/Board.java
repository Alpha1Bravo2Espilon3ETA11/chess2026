package com.example;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Board extends JPanel implements MouseListener {
    // Game State
    private Square[][] board = new Square[8][8];
    private GameWindow gw;
    private boolean whiteTurn = true; // White moves first
    
    // Selection Logic
    private Square selectedSquare = null;
    private ArrayList<Square> legalMoves = new ArrayList<>();

    public Board(GameWindow gw) {
        this.gw = gw;
        this.setLayout(new GridLayout(8, 8));
        this.setPreferredSize(new Dimension(500, 500));
        this.addMouseListener(this);
        
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Alternating colors for chess board
                boolean isWhite = (row + col) % 2 == 0;
                board[row][col] = new Square(this, isWhite, row, col);
                this.add(board[row][col]);
            }
        }
        setupPieces();
    }

    private void setupPieces() {
        // This is where you would place Pawns, Rooks, etc.
        // Example: board[0][0].put(new Rook(false)); 
        // Note: You must implement the specific Piece subclasses (Pawn, Rook, etc.) 
        // to pass the correct image paths and colors.
    }

    public Square[][] getSquareArray() {
        return board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Determine which square was clicked based on pixel coordinates
        Component clickedComponent = this.getComponentAt(e.getPoint());
        if (!(clickedComponent instanceof Square)) return;

        Square clickedSquare = (Square) clickedComponent;

        if (selectedSquare == null) {
            // PHASE 1: Selecting a piece
            if (clickedSquare.isOccupied() && clickedSquare.getOccupyingPiece().getColor() == whiteTurn) {
                selectedSquare = clickedSquare;
                // Get legal moves from the Piece class logic
                legalMoves = selectedSquare.getOccupyingPiece().getLegalMoves(this, selectedSquare);
                
                // Visual feedback: Highlight the square (optional enhancement)
                selectedSquare.setBackground(Color.YELLOW); 
            }
        } else {
            // PHASE 2: Moving the piece
            if (legalMoves.contains(clickedSquare)) {
                movePiece(selectedSquare, clickedSquare);
                whiteTurn = !whiteTurn; // Switch turn
            }
            
            // Reset selection
            selectedSquare.setBackground(null); // Remove highlight
            selectedSquare = null;
            legalMoves.clear();
        }
        repaint();
    }

    private void movePiece(Square from, Square to) {
        Piece p = from.removePiece();
        to.put(p);
        
        // Check for victory/checkmate here
        // if (isCheckmate(!whiteTurn)) gw.checkmateOccurred(whiteTurn);
    }

    // --- Mandatory MouseListener overrides ---
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
e