package pieces;

import main.GamePanel;
import main.Type;

public class Pawn extends Pieces {
    public Pawn(int col, int row, int color) {
        super(col, row, color);

        //type = Type.KING;

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/w-pawn");
        } else {
            image = getImage("/piece/b-pawn");
        }
    }
}