package pieces;

import main.GamePanel;
import main.Type;

public class Queen extends Pieces {
    public Queen(int col, int row, int color) {
        super(col, row, color);

        //type = Type.KING;

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/w-queen");
        } else {
            image = getImage("/piece/b-queen");
        }
    }
}