import greenfoot.*;

public class SnakeWorld extends World {
    private final int MAX_DOTS = (500 * 500) / (20 * 20);
    private int[] x = new int[MAX_DOTS];
    private int[] y = new int[MAX_DOTS];
    private Ular head, tail;
    private int dots = 4;
    private boolean firstTime = true;
    private int score = 0; // Variabel skor
    private GreenfootSound eatSound = new GreenfootSound("slurp.mp3"); // Suara saat makan
    private GreenfootSound gameOverSound = new GreenfootSound("gameover.wav"); // Suara saat game over

    public SnakeWorld() {
        super(500, 500, 1);

        for (int i = 0; i < 1; i++) {
            addObject(new Food(), Greenfoot.getRandomNumber(10) * 50 + 25, Greenfoot.getRandomNumber(10) * 50 + 25);
        }
        for (int z = 0; z < dots; z++) {
            x[z] = 100 - z * 20;
            y[z] = 20;
        }

        makeSnakeHead();
        makeSnake();
    }

    public void act() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[z - 1];
            y[z] = y[z - 1];
        }
    }

    // Metode untuk menampilkan skor di layar
    public void showScore() {
        showText("Score: " + score, 50, 30);
    }

    // Metode untuk menambah skor
    public void increaseScore(int points) {
        score += points;
        showScore(); // Memperbarui tampilan skor setelah penambahan skor
    }

    public void makeSnakeHead() {
        head = new Ular(0);
        addObject(head, x[0], y[0]);
    }

    public void makeSnake() {
        for (int i = 1; i < dots; i++) {
            Ular tail = new Ular(i);
            addObject(tail, x[i], y[i]);
        }
    }

    public void addDot() {
        int parentX = x[dots - 1];
        int parentY = y[dots - 1];
        tail = new Ular(dots);
        addObject(tail, parentX, parentY);
        dots++;
        increaseScore(1); // Contoh: Tambah skor setiap kali ular bertambah panjang
        eatSound.play(); // Putar suara makanan
    }

    public void addFood() {
        for (int i = 0; i < 1; i++) {
            addObject(new Food(), Greenfoot.getRandomNumber(10) * 50 + 25, Greenfoot.getRandomNumber(10) * 50 + 25);
        }
    }

    public void setDX(int d, int dx) {
        x[d] = dx;
    }

    public void setDY(int d, int dy) {
        y[d] = dy;
    }

    public void gameOver() {
        addObject(new GameOver(), getWidth() / 2, getHeight() / 2);
        gameOverSound.play(); // Putar suara game over
        Greenfoot.stop();
    }

    public int getMyX(int d) {
        return x[d];
    }

    public int getMyY(int d) {
        return y[d];
    }
}
