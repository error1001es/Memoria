package Menu;

import Engine.AbstractGame;
import Engine.GameContainer;
import Engine.Renderer;
import Engine.gfx.Image;
import Game.Level_1;
import Game.Level_2;
import Game.Level_3;

import java.awt.event.MouseEvent;

public class MenuManager extends AbstractGame {
    private Image background = new Image("Menu/Background.jpg");
    private MenuButton buttonLevel_1;
    private MenuButton buttonLevel_2;
    private MenuButton buttonLevel_3;
    private MenuButton buttonStatistics;
    private MenuButton buttonExit;

    public MenuManager() {
        buttonLevel_1 = new MenuButton(new Image("Menu/Level_1_btn.png"));
        buttonLevel_2 = new MenuButton(new Image("Menu/Level_2_btn.png"));
        buttonLevel_3 = new MenuButton(new Image("Menu/Level_3_btn.png"));
        buttonStatistics = new MenuButton(new Image("Menu/StatisticsButton.png"));
        buttonExit = new MenuButton(new Image("Menu/ExitButton.png"));
        buttonLevel_2.setX((background.w / 2) - (buttonStatistics.getImg().w / 2));
        buttonLevel_1.setX(buttonLevel_2.getX()-300);
        buttonLevel_3.setX(buttonLevel_2.getX()+300);
        buttonStatistics.setX((background.w / 2) - (buttonStatistics.getImg().w / 2));
        buttonExit.setX((background.w / 2) - (buttonExit.getImg().w / 2));
        buttonLevel_1.setY(150);
        buttonLevel_2.setY(150);
        buttonLevel_3.setY(150);
        buttonStatistics.setY(buttonLevel_1.getY() + 150);
        buttonExit.setY(buttonStatistics.getY() + 150);
    }

    @Override
    public void update(GameContainer Menu, float dt) {
        if (collision(buttonLevel_1, Menu.input.mouseX, Menu.input.mouseY)) {
            if (Menu.input.isButton(MouseEvent.BUTTON1)) {
                Menu.stop();
                GameContainer Game = new GameContainer(new Level_1());
                Game.start();
            }
        }
        if (collision(buttonLevel_2, Menu.input.mouseX, Menu.input.mouseY)) {
            if (Menu.input.isButton(MouseEvent.BUTTON1)) {
                Menu.stop();
                GameContainer Game = new GameContainer(new Level_2());
                Game.start();
            }
        }
        if (collision(buttonLevel_3, Menu.input.mouseX, Menu.input.mouseY)) {
            if (Menu.input.isButton(MouseEvent.BUTTON1)) {
                Menu.stop();
                GameContainer Game = new GameContainer(new Level_3());
                Game.start();
            }
        }
        if (collision(buttonExit, Menu.input.mouseX, Menu.input.mouseY)) {
            if (Menu.input.isButton(MouseEvent.BUTTON2)) {
                System.exit(0);

            }
        }
        if (collision(buttonStatistics, Menu.input.mouseX, Menu.input.mouseY)) {
            if (Menu.input.isButton(MouseEvent.BUTTON3)) {
                System.out.println("СТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТТАТИСТИКА");
            }
        }

    }

    @Override
    public void render(GameContainer Menu, Renderer r) {
        r.drawImage(background, 0, 0);
        r.drawImage(buttonLevel_1.getImg(), buttonLevel_1.getX(), buttonLevel_1.getY());
        r.drawImage(buttonLevel_2.getImg(), buttonLevel_2.getX(), buttonLevel_2.getY());
        r.drawImage(buttonLevel_3.getImg(), buttonLevel_3.getX(), buttonLevel_3.getY());
        r.drawImage(buttonStatistics.getImg(), buttonStatistics.getX(), buttonStatistics.getY());
        r.drawImage(buttonExit.getImg(),buttonExit.getX(),buttonExit.getY());
    }

    public boolean collision(MenuButton a, int mouseX, int mouseY) {
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
        GameContainer Menu = new GameContainer(new MenuManager());
        Menu.start();
    }
}
