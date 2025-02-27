package pieces;

import main.GamePanel;
import main.Type;

public class Rook extends Pieces {
    public Rook(int col, int row, int color) {
        super(col, row, color);

        //type = Type.KING;

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/w-rook");
        } else {
            image = getImage("/piece/b-rook");
        }
    }

    public boolean canMove(int targetCol, int targetRow){
        if (isWithThinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)){
            // Rook can move as long as either its col or row is the same
            if (targetCol == preCol || targetRow == preRow){
                if (isValidSquare(targetCol,targetRow) && !pieceIsOnStraightLine(targetCol, targetRow)){
                    return true;
                }
            }
        }
        return false;
    }
}