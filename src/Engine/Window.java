package Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {
    public JFrame window = new JFrame();
    public BufferedImage image;
    public Canvas canvas;
    public BufferStrategy bufferStrategy;
    public Graphics g;
    public void setVisible(boolean Visible){
        window.setVisible(Visible);
    }


    public Window(GameContainer gc) {
        image = new BufferedImage(gc.width, gc.height, BufferedImage.TYPE_INT_RGB);
        canvas = new Canvas();
        Dimension s = new Dimension((int) (gc.width * gc.scale), (int) (gc.height * gc.scale));
        canvas.setPreferredSize(s);
        canvas.setMinimumSize(s);
        canvas.setMaximumSize(s);
        window = new JFrame(gc.title);
        window.setVisible(true);
        window.setSize(gc.width * gc.scale, gc.height * gc.scale);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.add(canvas, BorderLayout.CENTER);
        window.pack();
        window.setLocationRelativeTo(null);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        g = bufferStrategy.getDrawGraphics();


    }

    public void update() {
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        bufferStrategy.show();
    }

}
