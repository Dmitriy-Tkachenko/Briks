package com.university.project;

import java.awt.Graphics;
import java.util.ArrayList;

/* Множество всех спрайтов находящихся на игровом поле.
 * Выделен в отдельный класс для "разгрузки"
 * игрового поля.
 */

class SpriteVector extends ArrayList<Sprite> {
    public void draw(Graphics g) {
        for (Sprite s : this) s.draw(g);
    }

    /* Проверка коллизии */
    public Sprite testCollision(Sprite test) {
        for (Sprite s : this) {

            if (test == s)
                continue;

            if (test.testCollision(s))
                return s;
        }

        return null;
    }

    /* Обновить состояние всех спрайтов */
    public void update() {
        for (int i = 0; i < size(); i++) {
            Sprite s = get(i);
            s.update();
            if (s.isDead()) {
                remove(i);
            }
        }
    }
}