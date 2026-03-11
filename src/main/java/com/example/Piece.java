package com.example;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;
    
    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;
         
        try {
            if (this.img == null) {
                this.img = ImageIO.read(new File(System.getProperty("user.dir")+img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }
    
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    //precondition: g and currentSquare must be on-null valid objects.
    //postcondition: the image stored in the img property of this object is drawn to the screen.
    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.

    // Pre-condition: board must not be null, start must be a valid square on board, board is a valid 8x8 array
    // Pos-condition: Returns all squares reachable by knight movement pattern regardless of occupancy.

    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {

        ArrayList<Square> controlled = new ArrayList<Square>();

        if ((start.getRow() - 2 >= 0) && (start.getCol() + 1 < 8)) {
            Square upRight = board[start.getRow() - 2][start.getCol() + 1];
            controlled.add(upRight);
        }
        
        if ((start.getRow() - 2 >= 0) && (start.getCol() - 1 >= 0)) {
            Square upLeft = board[start.getRow() - 2][start.getCol() - 1];
            controlled.add(upLeft);
        }

        if ((start.getRow() + 2 < 8) && (start.getCol() + 1 < 8)) {
            Square downRight = board[start.getRow() + 2][start.getCol() + 1];
            controlled.add(downRight);
        }

        if ((start.getRow() + 2 < 8) && (start.getCol() - 1 >= 0)) {
            Square downLeft = board[start.getRow() + 2][start.getCol() - 1];
            controlled.add(downLeft);
        }

        if ((start.getRow() - 1 >= 0) && (start.getCol() + 2 < 8)) {
            Square rightUp = board[start.getRow() - 1][start.getCol() + 2];
            controlled.add(rightUp);
        }

        if ((start.getRow() - 1 >= 0) && (start.getCol() - 2 >= 0)) {
            Square leftUp = board[start.getRow() - 1][start.getCol() - 2];
            controlled.add(leftUp);
        }

        
        if ((start.getRow() + 1 < 8) && (start.getCol() + 2 < 8)) {
            Square rightDown = board[start.getRow() + 1][start.getCol() + 2];
            controlled.add(rightDown);
        }

        if ((start.getRow() + 1 < 8) && (start.getCol() - 2 >= 0)) {
            Square leftDown = board[start.getRow() + 1][start.getCol() - 2];
            controlled.add(leftDown);
        }    

        return controlled;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.

    // Piece rules: It has same moveset as regular knight: (2 up, 1 right), (1 up, 2 right), (2 up, 1 left) (1 up, 2 left), (2 down, 1 right), (1 down, 2 right), (2 down, 1 left) and (1 down, 2 left).
    // Pre-condition: board must not be null, start must be a valid square on board, board is a valid 8x8 array
    // Pos-condition: Returns all knight-movement squares that are within board boundaries and are either empty or occupied by an opponent’s piece.

    public ArrayList<Square> getLegalMoves(Board b, Square start){
        ArrayList<Square> moves = new ArrayList<Square>();
        if ((start.getRow() - 2 >= 0) && (start.getCol() + 1 < 8)) {
            Square upRight = b.getSquareArray()[start.getRow() - 2][start.getCol() + 1];
            if (upRight.isOccupied() == false) {
                moves.add(upRight);
            }
            else if (upRight.getOccupyingPiece() != null && upRight.getOccupyingPiece().getColor() != color) {
                moves.add(upRight);
            }
        }

        if ((start.getRow() - 2 >= 0) && (start.getCol() - 1 >= 0)) {
            Square upLeft = b.getSquareArray()[start.getRow() - 2][start.getCol() - 1];
            if (upLeft.isOccupied() == false) {
                moves.add(upLeft);
            }
            else if (upLeft.getOccupyingPiece() != null && upLeft.getOccupyingPiece().getColor() != color) {
                moves.add(upLeft);
            }
        }

        if ((start.getRow() + 2 < 8) && (start.getCol() + 1 < 8)) {
            Square downRight = b.getSquareArray()[start.getRow() + 2][start.getCol() + 1];
            if (downRight.isOccupied() == false) {
                moves.add(downRight);
            }
            else if (downRight.getOccupyingPiece() != null && downRight.getOccupyingPiece().getColor() != color) {
                moves.add(downRight);
            }
        }

        if ((start.getRow() + 2 < 8) && (start.getCol() - 1 >= 0)) {
            Square downLeft = b.getSquareArray()[start.getRow() + 2][start.getCol() - 1];
            if (downLeft.isOccupied() == false) {
                moves.add(downLeft);
            }
            else if (downLeft.getOccupyingPiece() != null && downLeft.getOccupyingPiece().getColor() != color) {
                moves.add(downLeft);
            }
        }

        if ((start.getRow() - 1 >= 0) && (start.getCol() + 2 < 8)) {
            Square rightUp = b.getSquareArray()[start.getRow() - 1][start.getCol() + 2];
            if (rightUp.isOccupied() == false) {
                moves.add(rightUp);
            }
            else if (rightUp.getOccupyingPiece() != null && rightUp.getOccupyingPiece().getColor() != color) {
                moves.add(rightUp);
            }
        }

        if ((start.getRow() - 1 >= 0) && (start.getCol() - 2 >= 0)) {
            Square leftUp = b.getSquareArray()[start.getRow() - 1][start.getCol() - 2];
            if (leftUp.isOccupied() == false) {
                moves.add(leftUp);
            }
            else if (leftUp.getOccupyingPiece() != null && leftUp.getOccupyingPiece().getColor() != color) {
                moves.add(leftUp);
            }
        }

        
        if ((start.getRow() + 1 < 8) && (start.getCol() + 2 < 8)) {
            Square rightDown = b.getSquareArray()[start.getRow() + 1][start.getCol() + 2];
            if (rightDown.isOccupied() == false) {
                moves.add(rightDown);
            }
            else if (rightDown.getOccupyingPiece() != null && rightDown.getOccupyingPiece().getColor() != color) {
                moves.add(rightDown);
            }
        }

        if ((start.getRow() + 1 < 8) && (start.getCol() - 2 >= 0)) {
            Square leftDown = b.getSquareArray()[start.getRow() + 1][start.getCol() - 2];
            if (leftDown.isOccupied() == false) {
                moves.add(leftDown);
            }
            else if (leftDown.getOccupyingPiece() != null && leftDown.getOccupyingPiece().getColor() != color) {
                moves.add(leftDown);
            }
        }

    	return moves;
    }
}