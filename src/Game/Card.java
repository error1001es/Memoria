package Game;

import Engine.gfx.Image;

public class Card {
    private boolean Pressed = false;
    private int x = 0;
    private int y = 0;
    private boolean visible = true;
    private int id = 0;
    private Image img;

    public boolean isPressed() {
        return Pressed;
    }

    public Card(Image img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void Pressed(boolean Pressed) {
        this.Pressed = Pressed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
