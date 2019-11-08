package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Ring {

    private Circle bigCircle, smallCircle;
    public Random random = new Random();

    public Ring(Circle smallCircle, Circle bigCircle) {
        this.bigCircle = bigCircle;
        this.smallCircle = smallCircle;
    }

    public Ring(boolean visibility, Random random) {
        int x = 0, y = 0;
        Point rgPoint = new Point(x, y);
        this.random = random;
        int r = random.nextInt(((70 - 1) + 1) + 1);
        rgPoint.setX(random.nextInt(((500 - 1) + 1) + 1));
        rgPoint.setY(random.nextInt(((500 - 1) + 1) + 1));
        bigCircle = new Circle(rgPoint, r + 10, visibility);
        smallCircle = new Circle(rgPoint, r, visibility);
    }

    public void DrawRing(GraphicsContext gc){
        bigCircle.DrawCircle(gc, Color.YELLOW);
        smallCircle.DrawCircle(gc, Color.WHITE);
    }

    public void Hide(GraphicsContext gc){
        gc.clearRect(0, 0, 600, 600);
    }

    public void MoveTo(int xMove, int yMove){
        bigCircle.MoveTo(xMove, yMove);
        smallCircle.MoveTo(xMove, yMove);
    }
}
