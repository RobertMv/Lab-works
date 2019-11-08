package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Rectangle {
    private int x, y, a, b;
    private Point rPoint = new Point(x, y);
    private boolean visibility;
    private Color rColor;

    public Rectangle(Point point,  int a, int b, boolean visibility) {
        this.rPoint = point;
        this.a = a;
        this.b = b;
        this.visibility = visibility;
    }

    public Rectangle(boolean visibility, Random random) {
        this.rPoint.setX(random.nextInt(((500 - 1) + 1) + 1));
        this.rPoint.setY(random.nextInt(((500 - 1) + 1) + 1));
        this.a = random.nextInt(((100 - 1) + 1) + 1);
        this.b = random.nextInt(((100 - 1) + 1) + 1);
        this.visibility = visibility;
    }

    public void DrawRectangle(GraphicsContext gc, Color rColor){
        gc.setFill(rColor);
        gc.fillRect(rPoint.getX(), rPoint.getY(), a, b);
    }

    public void Hide(GraphicsContext gc){
        gc.clearRect(rPoint.getX(), rPoint.getY(), a, b);
    }

    public void MoveTo(int xMove, int yMove){
        rPoint.setX(rPoint.getX() + xMove);
        rPoint.setY(rPoint.getY() + yMove);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public Point getrPoint() {
        return rPoint;
    }

    public void setrPoint(Point rPoint) {
        this.rPoint = rPoint;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public Color getrColor() {
        return rColor;
    }

    public void setrColor(Color rColor) {
        this.rColor = rColor;
    }
}
