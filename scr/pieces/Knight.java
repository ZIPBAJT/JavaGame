package pieces;

import main.GamePanel;
import main.Type;

public class Knight extends Pieces {
    public Knight(int col, int row, int color) {
        super(col, row, color);

        //type = Type.KING;

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/w-knight");
        } else {
            image = getImage("/piece/b-knight");
        }
    }

    public boolean canMove(int targetCol, int targetRow){

        if (isWithThinBoard(targetCol, targetRow)){
            if (Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 2){
                if (isValidSquare(targetCol, targetRow)){
                    return true;
                }
            }
        }
        return false;
    }
}