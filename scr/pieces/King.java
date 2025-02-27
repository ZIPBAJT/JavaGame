package pieces;

import main.GamePanel;
import main.Type;

public class King extends Pieces {
    public King(int col, int row, int color) {
        super(col, row, color);

        //type = Type.KING;

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/w-king");
        } else {
            image = getImage("/piece/b-king");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {
        if (isWithThinBoard(targetCol, targetRow)) {
            //Movement
            if (Math.abs(targetCol - preCol) +
                    Math.abs(targetRow - preRow) == 1 ||
                    Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1) {

                if (isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}