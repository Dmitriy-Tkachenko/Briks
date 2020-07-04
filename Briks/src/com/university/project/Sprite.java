package com.university.project;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

abstract class Sprite {
    /*
     * @_img - изображение спрайта
     * @_pf  - игровое поле
     * @_pos - позиция и размеры спрайта
     * @_isDead - состояние - "мертв", "жив"
     * Мертвый спрайт удаляется с игрового поля
     */

    protected Image _img;
    protected PlayField _pf;
    protected Rectangle _pos;
    protected boolean _isDead;

    public Sprite(PlayField pf, Image img, Rectangle pos) {
        _pos = pos;
        _img = img;
        _pf = pf;
    }

    public void draw(Graphics g) {
        g.drawImage(_img, _pos.x, _pos.y, _pf);
    }

    /* Проверка на наличие коллизии. */
    public boolean testCollision(Sprite s) {
        if (s != this) {
            return _pos.intersects(s.getBounds());
        }
        return false;
    }

    /* Взять ограничивающий прямоугольник спрайта. */
    public Rectangle getBounds() {
        return _pos;
    }

    /* Мертв или жив. */
    public boolean isDead() {
        return _isDead;
    }

    /* Обновить состояние спрайта. */
    abstract public void update();

    /* Обработка соударения с шайбой. */
    abstract public void hitBy(Puck p);
}
