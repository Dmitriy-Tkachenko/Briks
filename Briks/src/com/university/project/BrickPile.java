package com.university.project;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class BrickPile {
    private ArrayList<Sprite> _bricks;

    public BrickPile(PlayField pf, Image img) {
        _bricks = new ArrayList<>();
        int startx = 80;
        int x = startx, y = 10;

        int _rows = 4;
        for (int r = 0; r < _rows; r++) {
            int _cols = 10;
            for (int c = 0; c < _cols; c++) {
                Rectangle pos = new Rectangle(x, y, img.getWidth(null), img.getHeight(null));

                Brick b = new Brick(pf, this, img, pos);
                pf.addSprite(b);
                _bricks.add(b);

                x += img.getWidth(null);
            }

            y += img.getHeight(null) + 2;
            x = startx;
        }
    }

    public int unbrokenCount() {
        int result = 0;

        for (Object brick : _bricks) {
            if (!((Brick) brick).isDead())
                result++;
        }

        return result;
    }

    public int brokenCount() {
        return _bricks.size() - unbrokenCount();
    }
}