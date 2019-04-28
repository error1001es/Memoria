package Menu;

import Engine.AbstractGame;
import Engine.GameContainer;
import Engine.Renderer;
import Engine.gfx.Image;
import Game.GameManager;

import java.awt.event.MouseEvent;

public class MenuManager extends AbstractGame {
    private Image background = new Image("Menu/Background.jpg");
    private MenuButton buttonStart;
    private MenuButton buttonStatistics;
    private MenuButton buttonExit;

    public MenuManager() {
        buttonStart = new MenuButton(new Image("Menu/StartButton.png"));
        buttonStatistics = new MenuButton(new Image("Menu/StatisticsButton.png"));
        buttonExit = new MenuButton(new Image("Menu/ExitButton.png"));
        buttonStart.setX((background.w / 2) - (buttonStart.getImg().w / 2));
        buttonStatistics.setX((background.w / 2) - (buttonStatistics.getImg().w / 2));
        buttonExit.setX((background.w / 2) - (buttonExit.getImg().w / 2));
        buttonStart.setY(150);
        buttonStatistics.setY(buttonStart.getY() + 150);
        buttonExit.setY(buttonStatistics.getY() + 150);
    }

    @Override
    public void update(GameContainer Menu, float dt) {
        if (collision(buttonStart, Menu.input.mouseX, Menu.input.mouseY)) {
            if (Menu.input.isButton(MouseEvent.BUTTON1)) {
                Menu.stop();
                GameContainer Game = new GameContainer(new GameManager());
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
        r.drawImage(buttonStart.getImg(), buttonStart.getX(), buttonStart.getY());
        r.drawImage(buttonStatistics.getImg(), buttonStatistics.getX(), buttonStatistics.getY());
        r.drawImage(buttonExit.getImg(), buttonStart.getX(), buttonExit.getY());
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
