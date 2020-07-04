package com.university.project;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/* Игровая лопатка */

class Puddle extends MovableSprite implements KeyListener {
    static final int LEFT = 37;
    static final int RIGHT = 39;
    static final int alpha = 10;
    private int attempt = 0;
    private boolean spaceIsPressed = false, puckGlued = false, performed = false;
    private Puck puck;

    public Puddle(PlayField p, Image pic) {
        super(
                p,
                pic,
                new Rectangle(
                        p.getWidth() / 2,
                        p.getHeight() - 20,
                        pic.getWidth(p),
                        pic.getHeight(p)),
                0,
                10
        );

        _pf.addKeyListener(this);
    }

    public void move() {
        if (_isMoving) {
            Rectangle b = _pf.getBoundary();
            _pos.x += _v.getSpeedX();
            if (puckGlued) puck._pos.x += _v.getSpeedX();
            if (_pos.x < b.x)
                _pos.x = b.x;
            else if (_pos.x + _pos.width > b.x + b.width)
                _pos.x = b.x + b.width - _pos.width;
        }
    }

    public void hitBy(Puck p) {
        if (p.getDirection() == 90 && !spaceIsPressed) {
            p.setDirection(270 + alpha);
        } else {
            int px = p.getBounds().x + p.getBounds().width/2;
            int l  = (int) (_pos.x +_pos.width*(1.0/3));
            int r  = (int) (_pos.x +_pos.width*(2.0/3));

            if ( px < l || px > r ) {
                p.getVelocity().reverse();
            } else {
                p.getVelocity().reverseY();
            }
        }
        if (spaceIsPressed && !performed) {
            p.stopMoving();
            p._pos.y -= 3;
            puck = p;
            performed = true;
        }
    }

    /* Обработка нажатия клавиши */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case LEFT:
                if (!spaceIsPressed || puck == null) {
                    startMoving();
                    _v.setDirection(180);
                } else {
                    startMoving();
                    _v.setDirection(180);
                    puckGlued = true;
                }
                return;
            case RIGHT:
                if (!spaceIsPressed || puck == null) {
                    startMoving();
                    _v.setDirection(0);
                } else {
                    startMoving();
                    _v.setDirection(0);
                    puckGlued = true;
                }
                return;
            case KeyEvent.VK_SPACE:
                if (attempt != 1) {
                    spaceIsPressed = true;
                    attempt = 1;
                }  else if (puck != null){
                    spaceIsPressed = false;
                    puckGlued = false;
                    puck.startMoving();
                }
        }
    }

    /* Обработка отжатия клавиши */
    public void keyReleased(KeyEvent e) {
        stopMoving();
    }

    public void keyTyped(KeyEvent e) {
    }
}

