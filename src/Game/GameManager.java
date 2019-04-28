package Game;

import Engine.AbstractGame;
import Engine.GameContainer;
import Engine.Renderer;
import Engine.gfx.Image;

import java.awt.event.MouseEvent;

public class GameManager extends AbstractGame {
    private Image background = new Image("Game/Background.jpg");
    private Card Card1;
    private Card Card2;
    private Card Card3;

    public GameManager() {
        Card1 = new Card(new Image("Game/Card1.gif"));
        Card2 = new Card(new Image("Game/Card1.gif"));
        Card3 = new Card(new Image("Game/Card1.gif"));
        Card1.setX(250);
        Card1.setY(150);
        Card2.setX(Card1.getX() + 250);
        Card2.setY(150);
        Card3.setX(Card2.getX() + 250);
        Card3.setY(150);
    }

    @Override
    public void update(GameContainer Game, float dt) {
        if (collision(Card1, Game.input.mouseX, Game.input.mouseY)) {
            if ((Game.input.isButton(MouseEvent.BUTTON1))) {
                Card1.Pressed(true);
                new Thread(() -> {
                    Card1.setImg(new Image("Game/Card1_open.gif"));
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Card1.setImg(new Image("Game/Card1.gif"));
                }).start();
                Card1.Pressed(false);
            }

        }
        if (collision(Card2, Game.input.mouseX, Game.input.mouseY)) {
            if ((Game.input.isButton(MouseEvent.BUTTON1))) {
                Card2.Pressed(true);
                new Thread(() -> {
                    Card2.setImg(new Image("Game/Card1_open.gif"));
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Card2.setImg(new Image("Game/Card1.gif"));
                }).start();
                Card2.Pressed(false);
            }

        }
        if (collision(Card3, Game.input.mouseX, Game.input.mouseY)) {
            if ((Game.input.isButton(MouseEvent.BUTTON1))) {
                Card3.Pressed(true);
                new Thread(() -> {
                    Card3.setImg(new Image("Game/Card1_open.gif"));
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Card3.setImg(new Image("Game/Card1.gif"));
                }).start();
                Card3.Pressed(false);
            }

        }

    }

    @Override
    public void render(GameContainer Game, Renderer r) {
        r.drawImage(background, 0, 0);
        r.drawImage(Card1.getImg(), Card1.getX(), Card1.getY());
        r.drawImage(Card2.getImg(), Card2.getX(), Card2.getY());
        r.drawImage(Card3.getImg(), Card3.getX(), Card3.getY());
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
