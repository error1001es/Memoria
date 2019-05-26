package Game;

import Engine.AbstractGame;
import Engine.GameContainer;
import Engine.Renderer;
import Engine.gfx.Image;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Level_3 extends AbstractGame {
    private int block = 0;
    private int Score = 0;
    private int LastScore = 0;
    private Image background = new Image("Game/Background.jpg");
    private Image win = new Image("Game/Winner.jpg");
    private Card card[] = {null, new Card(new Image("Game/Card_close.gif")), new Card(new Image("Game/Card_close.gif")), new Card(new Image("Game/Card_close.gif")), new Card(new Image("Game/Card_close.gif")),new Card(new Image("Game/Card_close.gif")),new Card(new Image("Game/Card_close.gif"))};

    public Level_3() {
        card[1].setId(2);
        card[2].setId(1);
        card[3].setId(1);
        card[4].setId(2);
        card[5].setId(2);
        card[6].setId(2);
        card[1].setX(250);
        card[1].setY(50);
        card[2].setX(card[1].getX() + 300);
        card[2].setY(50);
        card[3].setX(card[2].getX() + 300);
        card[3].setY(50);
        card[4].setX(250);
        card[4].setY(350);
        card[5].setX(card[4].getX() + 300);
        card[5].setY(350);
        card[6].setX(card[5].getX() + 300);
        card[6].setY(350);
    }

    public void PressCard(int number) {
        if (block != 2 && card[number].isVisible() == true) {
            card[number].Pressed(true);
            new Thread(() -> {
                block += 1;
                card[number].setImg(new Image("Game/Card" + card[number].getId() + "_open.gif"));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                card[number].setImg(new Image("Game/Card_close.gif"));
                card[number].Pressed(false);
                block -= 1;
            }).start();
        }
        for (int a_check = 1; a_check <= 6; a_check++) {
            if (card[a_check].isPressed()) {
                for (int b_check = 1; b_check <= 6; b_check++) {
                    if (a_check != b_check && card[b_check].isPressed() && card[a_check].getId() == card[b_check].getId()) {
                        int Card1 = a_check;
                        int Card2 = b_check;
                        new Thread(() -> {
                            card[Card1].Pressed(false);
                            card[Card2].Pressed(false);
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            card[Card1].setVisible(false);
                            card[Card2].setVisible(false);
                        }).start();
                        LastScore = Score;
                        Score += 1;
                        break;
                    }
                }
                if (LastScore < Score) {
                    break;
                }
            }
        }
    }

    @Override
    public void update(GameContainer Game, float dt) {
        if (Game.input.isKeyDown(KeyEvent.VK_SPACE)) {
            System.out.println("Score: " + Score);
        }
        if (collision(card[1], Game.input.mouseX, Game.input.mouseY)) {
            if ((Game.input.isButtonDown(MouseEvent.BUTTON1))) {
                PressCard(1);
            }
        }
        if (collision(card[2], Game.input.mouseX, Game.input.mouseY)) {
            if ((Game.input.isButtonDown(MouseEvent.BUTTON1))) {
                PressCard(2);
            }
        }
        if (collision(card[3], Game.input.mouseX, Game.input.mouseY)) {
            if ((Game.input.isButtonDown(MouseEvent.BUTTON1))) {
                PressCard(3);
            }
        }
        if (collision(card[4], Game.input.mouseX, Game.input.mouseY)) {
            if ((Game.input.isButtonDown(MouseEvent.BUTTON1))) {
                PressCard(4);
            }
        }
        if (collision(card[5], Game.input.mouseX, Game.input.mouseY)) {
            if ((Game.input.isButtonDown(MouseEvent.BUTTON1))) {
                PressCard(5);
            }
        }
        if (collision(card[6], Game.input.mouseX, Game.input.mouseY)) {
            if ((Game.input.isButtonDown(MouseEvent.BUTTON1))) {
                PressCard(6);
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
        if (card[5].isVisible()) r.drawImage(card[5].getImg(), card[5].getX(), card[5].getY());
        if (card[6].isVisible()) r.drawImage(card[6].getImg(), card[6].getX(), card[6].getY());
        if (Score==3){
            r.drawImage(win,r.pW/2-win.w/2,r.pH/2-win.h/2);
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(1);
            }).start();
        }
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
        GameContainer Game = new GameContainer(new Level_3());
        Game.start();
    }
}
