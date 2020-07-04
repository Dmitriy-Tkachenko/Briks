package com.university.project;

import javax.swing.*;
import java.awt.*;

class Match {
    private PlayField _pf;
    private MediaTracker _tracker;
    private Image[] _lib;
    private String _message = "";

    public Match() {
        JFrame _top = new JFrame("Bricks");
        _pf = new PlayField(this);
        _tracker = new MediaTracker(_pf);
        _lib = new Image[3];
        _lib[0] = _pf.getToolkit().getImage("puck.gif");
        _lib[1] = _pf.getToolkit().getImage("puddle.gif");
        _lib[2] = _pf.getToolkit().getImage("brick.gif");
        _tracker.addImage(_lib[0], 0);
        _tracker.addImage(_lib[1], 0);
        _tracker.addImage(_lib[2], 0);

        JMenuBar mbar = new JMenuBar();
        _top.setJMenuBar(mbar);

        JMenu file = new JMenu("Game");
        JMenuItem i1, i2, i3, i4;
        file.add(i1 = new JMenuItem("start"));
        file.add(i2 = new JMenuItem("pause"));
        file.add(i3 = new JMenuItem("resume"));
        file.add(i4 = new JMenuItem("exit"));

        mbar.add(file);
        _top.setSize(600, 400);

        i1.addActionListener(e -> start());

        i2.addActionListener(e -> _pf.stop());

        i3.addActionListener(e -> _pf.start());

        i4.addActionListener(e -> System.exit(0));

        _top.add("Center", _pf);
        _top.setVisible(true);
    }

    public void start() {
        try {
            _tracker.waitForID(0);
        } catch (InterruptedException e) {
            return;
        }

        _message = "";
        _pf.clean();

        new BrickPile(_pf, _lib[2]);
        PuckSupply _ps = new PuckSupply(3, _pf, _lib[0]);

        _pf.addSprite(new Puddle(_pf,_lib[1]));
        _pf.addSprite(_ps.get());

        _pf.start();
    }

    public void loose() {
        _message = "You Loose";
        _pf.repaint();
        _pf.stop();
    }

    public void win() {
        _message = "You win";
        _pf.repaint();
        _pf.stop();
    }

    public static void main(String[] args) {
        new Match();
    }

    public String getMessage() {
        return _message;
    }
}
