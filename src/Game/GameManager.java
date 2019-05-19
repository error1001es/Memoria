package Game;

import Engine.AbstractGame;
import Engine.GameContainer;
import Engine.Renderer;
import Engine.gfx.Image;

import java.awt.event.MouseEvent;

public class GameManager extends AbstractGame {
    private int block = 0;
    private Image background = new Image("Game/Background.jpg");
    private Card card[] = {null, new Card(new Image("Game/Card_close.gif")), new Card(new Image("Game/Card_close.gif")), new Card(new Image("Game/Card_close.gif")), new Card(new Image("Game/Card_close.gif"))};

    public GameManager() {
        card[1].setId(2);
        card[2].setId(1);
        card[3].setId(1);
        card[4].setId(2);
        card[1].setX(100);
        card[1].setY(150);
        card[2].setX(card[1].getX() + 250);
        card[2].setY(150);
        card[3].setX(card[2].getX() + 250);
        card[3].setY(150);
        card[4].setX(card[3].getX() + 250);
        card[4].setY(150);
    }

    public void PressCard(int number) {
        card[number].Pressed(true);
        new Thread(() -> {
            block+=1;
            card[number].setImg(new Image("Game/Card" + card[number].getId() + "_open.gif"));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            card[number].setImg(new Image("Game/Card_close.gif"));
            card[number].Pressed(false);
            block-=1;
        }).start();
    }

    @Override
    public void update(GameContainer Game, float dt) {
        if (collision(card[1], Game.input.mouseX, Game.input.mouseY)) {
            if ((Game.input.isButtonDown(MouseEvent.BUTTON1))) {
                if (block != 2) PressCard(1);
            }
        }
        if (collision(card[2], Game.input.mouseX, Game.input.mouseY)) {
            if ((Game.input.isButtonDown(MouseEvent.BUTTON1))) {
                if (block != 2) PressCard(2);
            }
        }
        if (collision(card[3], Game.input.mouseX, Game.input.mouseY)) {
            if ((Game.input.isButtonDown(MouseEvent.BUTTON1))) {
                if (block != 2) PressCard(3);
            }
        }
        if (collision(card[4], Game.input.mouseX, Game.input.mouseY)) {
            if ((Game.input.isButtonDown(MouseEvent.BUTTON1))) {
                if (block != 2) PressCard(4);
            }
        }
        for (int a_check = 1; a_check <= 4; a_check++) {
            if (card[a_check].isPressed()) {
                for (int b_check = 1; b_check <= 4; b_check++) {
                    if (a_check != b_check && card[b_check].isPressed()) {
                        if (card[a_check].getId() == card[b_check].getId()) {
                            card[a_check].setVisible(false);
                            card[a_check].Pressed(false);
                            card[b_check].setVisible(false);
                            card[b_check].Pressed(false);
                            System.out.println("Хорош");

                        }
                    }
                }
            }
        }
    }

    @Override
    public void render(GameContainer Game, Renderer r) {
        r.drawImage(background, 0, 0);

        if (card[1].isVisible()) r.drawImage(card[1].getImg(), card[1].getX(), card[1].getY());
        if (card[2].isVisible()) r.drawImage(card[2].getImg(), card[2].getX(), card[2].getY());
        if (card[3].isVisible()) r.drawImage(card[3].getImg(), card[3].getX(), card[3].getY());
        if (card[4].isVisible()) r.drawImage(card[4].getImg(), card[4].getX(), card[4].getY());
    }

    public boolean collision(Card a, int mouseX, int mouseY) {
        int obj_a_MinX = a.getX();
        int obj_a_MaxX = a.getX() + a.getImg().w;
        int obj_a_MinY = a.getY();
        int obj_a_MaxY = a.getY() + a.getImg().h;

        if (mouseX > obj_a_MinX && mouseX < obj_a_MaxX) {
            if (mouseY > obj_a_MinY && mouseY < obj_a_MaxY) {
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        GameContainer Game = new GameContainer(new GameManager());
        Game.start();
    }
}
