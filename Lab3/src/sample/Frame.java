package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Frame {
    public Random random = new Random();
    private Rectangle bigRectangle, smallRectangle;

    public Frame(Rectangle smallRectangle, Rectangle bigRectangle){
        this.smallRectangle = smallRectangle;
        this.bigRectangle = bigRectangle;
    }

    public Frame(boolean visibility, Random random){
        this.random = random;
        int x = 0, y = 0;
        Point fPoint = new Point(x, y);
        Point fPointSmall = new Point(x, y);
        fPoint.setX(random.nextInt(((500 - 1) + 1) + 1));
        fPoint.setY(random.nextInt(((500 - 1) + 1) + 1));
        fPointSmall.setX(fPoint.getX() + 10);
        fPointSmall.setY(fPoint.getY() + 10);
        int a = random.nextInt(((200 - 1) + 1) + 1);
        int b = random.nextInt(((200 - 1) + 1) + 1);
        bigRectangle = new Rectangle(fPoint, a, b, visibility);
        smallRectangle = new Rectangle(fPointSmall, a-20, b-20, visibility);
    }

    public void DrawFrame(GraphicsContext gc){
        bigRectangle.DrawRectangle(gc, Color.BLUE);
        smallRectangle.DrawRectangle(gc, Color.WHITE);
    }

    public void Hide(GraphicsContext gc){
        gc.clearRect(0, 0, 600, 600);
    }

    public void MoveTo(int xMove, int yMove){
        bigRectangle.MoveTo(xMove, yMove);
        smallRectangle.MoveTo(xMove, yMove);
    }
}
