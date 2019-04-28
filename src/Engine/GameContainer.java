package Engine;

public class GameContainer implements Runnable {

    private Thread thread;
    public Window window;
    public Renderer renderer;
    public Input input;
    public AbstractGame game;

    public int width = 1280;
    public int height = 720;
    public int scale = 1;
    public String title = "title";


    private boolean running = false;
    private final double UPDATE_CUP = 1.0 / 60;
    double frameTime = 0;
    int frame = 0;
    int fps = 0;
    boolean render = false;

    public GameContainer(AbstractGame game) {
        this.game = game;
    }

    public void start() {
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);
        thread = new Thread(this);
        thread.run();
    }

    public void stop() {
        window.setVisible(false);
    }

    public void run() {

        running = true;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;
        while (running) {
            render = false;
            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;
            while (unprocessedTime >= UPDATE_CUP) {
                unprocessedTime -= UPDATE_CUP;
                render = true;

                game.update(this, (float) UPDATE_CUP);

                input.update();
                if (frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frame;
                    frame = 0;
                    System.out.println("FPS: " + fps);
                }
            }
            if (render) {
                renderer.clear();
                game.render(this, renderer);
                window.update();
                frame++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void render() {

    }

    public void dispose() {

    }
}
