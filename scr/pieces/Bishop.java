package pieces;

import main.GamePanel;
import main.Type;

public class Bishop extends Pieces {
    public Bishop(int col, int row, int color) {
        super(col, row, color);

        //type = Type.KING;

        if (color == GamePanel.WHITE) {
            image = getImage("/piece/w-bishop");
        } else {
            image = getImage("/piece/b-bishop");
        }
    }
}