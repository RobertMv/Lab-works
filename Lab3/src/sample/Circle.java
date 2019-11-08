package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Circle {
    private int x, y;
    private Point cPoint = new Point(x, y);
    private int r;
    private boolean visibility;
    private Color cColor;
    public Circle(Point point,  int r, boolean visibility) {
        this.cPoint = point;
        this.r = r;
        this.visibility = visibility;
    }

    public Circle(boolean visibility, Random random) {
        this.cPoint.setX(random.nextInt(((500 - 1) + 1) + 1));
        this.cPoint.setY(random.nextInt(((500 - 1) + 1) + 1));
        this.r = random.nextInt(((70 - 1) + 1) + 1);
        this.visibility = visibility;
    }

    public void DrawCircle(GraphicsContext gc, Color cColor){
        gc.setFill(cColor);
        gc.fillOval(cPoint.getX()-r, cPoint.getY()-r, 2*r, 2*r);
    }

    public void Hide(GraphicsContext gc){
        gc.clearRect(cPoint.getX()-r, cPoint.getY()-r, r*2, r*2);
    }

    public void MoveTo(int xMove, int yMove){
        cPoint.setX(cPoint.getX() + xMove);
        cPoint.setY(cPoint.getY() + yMove);
    }

    public Point getPoint() {
        return cPoint;
    }

    public void setPoint(Point point) {
        this.cPoint = point;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

}
