package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Triangle {
    private int x1, y1, x2, y2, x3, y3;
    private Point t1Point = new Point(x1, y1);
    private Point t2Point = new Point(x2, y2);
    private Point t3Point = new Point(x3, y3);
    private boolean visibility;
    private Color tColor;

    public Triangle(Point point1, Point point2, Point point3, boolean visibility, Color tColor) {
        this.t1Point = point1;
        this.t2Point = point2;
        this.t3Point = point3;
        this.visibility = visibility;
        this.tColor = tColor;
    }
    public Triangle(boolean visibility, Random random, Color tColor) {
        this.t1Point.setX(random.nextInt(((500 - 1) + 1) + 1));
        this.t1Point.setY(random.nextInt(((500 - 1) + 1) + 1));
        this.t2Point.setX(random.nextInt(((500 - 1) + 1) + 1));
        this.t2Point.setY(random.nextInt(((500 - 1) + 1) + 1));
        this.t3Point.setX(random.nextInt(((500 - 1) + 1) + 1));
        this.t3Point.setY(random.nextInt(((500 - 1) + 1) + 1));
        this.visibility = visibility;
        this.tColor = tColor;
    }

    public void DrawTriangle(GraphicsContext gc){
        gc.setFill(tColor);
        gc.fillPolygon(new double[]{t1Point.getX(), t2Point.getX(), t3Point.getX()},
                new double[]{t1Point.getY(), t2Point.getY(), t3Point.getY()}, 3);
    }
    public void Hide(GraphicsContext gc){
        gc.clearRect(0, 0, 600, 600);
    }

    public void MoveTo(int xMove, int yMove, GraphicsContext gc, boolean visibilityT){
        t1Point.setX(t1Point.getX() + xMove);
        t2Point.setX(t2Point.getX() + xMove);
        t3Point.setX(t3Point.getX() + xMove);
        t1Point.setY(t1Point.getY() + yMove);
        t2Point.setY(t2Point.getY() + yMove);
        t3Point.setY(t3Point.getY() + yMove);
        if(visibilityT){
            DrawTriangle(gc);
        }
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public int getY3() {
        return y3;
    }

    public void setY3(int y3) {
        this.y3 = y3;
    }

    public Point getT1Point() {
        return t1Point;
    }

    public void setT1Point(Point t1Point) {
        this.t1Point = t1Point;
    }

    public Point getT2Point() {
        return t2Point;
    }

    public void setT2Point(Point t2Point) {
        this.t2Point = t2Point;
    }

    public Point getT3Point() {
        return t3Point;
    }

    public void setT3Point(Point t3Point) {
        this.t3Point = t3Point;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public Color gettColor() {
        return tColor;
    }

    public void settColor(Color tColor) {
        this.tColor = tColor;
    }
}
