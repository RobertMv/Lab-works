package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private int radiusInt, xRadiusInt = 0, yRadiusInt = 0;
    private Point circlePoint = new Point(xRadiusInt, yRadiusInt);
    private int xCircleMove, yCircleMove;
    private int xRectInt, yRectInt, hRectInt, wRectInt;
    private int xRectMove = 0, yRectMove = 0;
    private Point rectanglePoint = new Point(xRectInt, yRectInt);
    private int x1TriInt, y1TriInt, x2TriInt, y2TriInt, x3TriInt, y3TriInt;
    private int xTriMove = 0, yTriMove = 0;
    private Point trianglePoint1 = new Point(x1TriInt, y1TriInt);
    private Point trianglePoint2 = new Point(x2TriInt, y2TriInt);
    private Point trianglePoint3 = new Point(x3TriInt, y3TriInt);
    private int xRing, yRing, rRingSmall, rRingBig, xRingMove = 0, yRingMove = 0;
    private Point ringPoint = new Point(xRing, yRing);
    private int xFrame, yFrame, xBFrame, yBFrame, xFrameMove = 0, yFrameMove = 0, hFrameInt, wFrameInt;
    private Point framePoint = new Point(xFrame, yFrame);
    private Point framePointSmall = new Point(xFrame, yFrame);
    private boolean visibility = true;
    private boolean visibilityR = true;
    private boolean visibilityT = true;
    private boolean visibilityRg = true;
    private boolean visibilityF = true;
    private Random random = new Random();
    private Circle newCircle;
    private Rectangle newRectangle;
    private Triangle newTriangle;
    private Ring newRing;
    private Frame newFrame;
    private Rectangle smallR, bigR;
    private Circle small, big;
    private ArrayList<Circle> circles = new ArrayList<>();
    private ArrayList<Rectangle> rectangles = new ArrayList<>();
    private ArrayList<Triangle> triangles = new ArrayList<>();
    private ArrayList<Ring> rings = new ArrayList<>();
    private ArrayList<Frame> frames = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        //создаем вкладки для круга, прямоугольника, треугольника
        TabPane tabPane = new TabPane();
        Tab tabCircle = new Tab("Circle");
        Tab tabRectangle = new Tab("Rectangle");
        Tab tabTriangle = new Tab("Triangle");
        Tab tabRing = new Tab("Ring");
        Tab tabFrame = new Tab("Frame");

        //добавляем все вкладки чтобы их было видно
        tabPane.getTabs().add(tabCircle);
        tabPane.getTabs().add(tabRectangle);
        tabPane.getTabs().add(tabTriangle);
        tabPane.getTabs().add(tabRing);
        tabPane.getTabs().add(tabFrame);

        //запрещаем закрытие вкладок пользователем
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //элементы которые будут на всех вкладках
        Button createButtonCircle = new Button("Create Circle");
        Button moveButtonCircle = new Button("Move circle");
        Button randomButtonCircle = new Button("Random values");

        Button createButtonRect = new Button("Create rectangle");
        Button moveButtonRect = new Button("Move rectangle");
        Button randomButtonRect = new Button("Random values");

        Button createButtonTriang = new Button("Create triangle");
        Button moveButtonTriang = new Button("Move triangle");
        Button randomButtonTriang = new Button("Random values");

        Button createButtonRing = new Button("Create ring");
        Button moveButtonRing = new Button("Move ring");
        Button randomButtonRing = new Button("Random values");

        Button createButtonFrame = new Button("Create frame");
        Button moveButtonFrame = new Button("Move frame");
        Button randomButtonFrame = new Button("Random values");

        Button visibilityButton = new Button("Visibility of Circles is " + visibility);
        Button visibilityRButton = new Button("Visibility of Rectangles is " + visibilityR);
        Button visibilityTButton = new Button("Visibility of Triangles is " + visibilityT);
        Button visibilityRgButton = new Button("Visibility of rings is " + visibilityRg);
        Button visibilityFButton = new Button("Visibility of frames is " + visibilityF);

        Label settingForCreatingCircle = new Label("Settings for creating circle");
        Label settingForMovingCircle = new Label("Settings for moving circle");
        Label settingForCreatingRect = new Label("Settings for creating rectangle");
        Label settingForMovingRect = new Label("Settings for moving rectangle");
        Label settingForCreatingTriang = new Label("Settings for creating triangle");
        Label settingForMovingTriang = new Label("Settings for moving triangle");
        Label settingForCreatingRing = new Label("Settings for creating ring");
        Label settingForMovingRing = new Label("Settings for moving ring");
        Label settingForCreatingFrame = new Label("Settings for creating frame");
        Label settingForMovingFrame = new Label("Settings for moving frame");

        HBox createAndRandomButtonCircle = new HBox(10, createButtonCircle, randomButtonCircle);
        HBox createAndRandomButtonRect = new HBox(10, createButtonRect, randomButtonRect);
        HBox createAndRandomButtonTriang = new HBox(10, createButtonTriang, randomButtonTriang);
        HBox createAndRandomButtonRing = new HBox(10, createButtonRing, randomButtonRing);
        HBox createAndRandomButtonFrame = new HBox(10, createButtonFrame, randomButtonFrame);

        //для круга
        Label rLabel = new Label("Radius");
        TextField rField = new TextField();
        checkValue(rField);
        HBox rBox = new HBox(10, rLabel, rField);

        Label xLabel = new Label("X");
        TextField xField = new TextField();
        checkValue(xField);
        HBox xBox = new HBox(10, xLabel, xField);

        Label yLabel = new Label("Y");
        TextField yField = new TextField();
        checkValue(yField);
        HBox yBox = new HBox(10, yLabel, yField);

        //сдвиг по осям
        Label xLabelMoved = new Label("by X");
        TextField xFieldMoved = new TextField();
        checkValue(xFieldMoved);
        HBox xBoxMoved = new HBox(10, xLabelMoved, xFieldMoved);

        Label yLabelMoved = new Label("by Y");
        TextField yFieldMoved = new TextField();
        checkValue(yFieldMoved);
        HBox yBoxMoved = new HBox(10, yLabelMoved, yFieldMoved);

        VBox circleLayout = new VBox(settingForCreatingCircle, rBox, xBox, yBox,
                createAndRandomButtonCircle, settingForMovingCircle, xBoxMoved, yBoxMoved,
                moveButtonCircle, visibilityButton);
        VBox.setMargin(settingForCreatingCircle, new Insets(20, 10, 0, 20));
        VBox.setMargin(rBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(xBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(yBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(createAndRandomButtonCircle, new Insets(10, 10, 0, 20));
        VBox.setMargin(settingForMovingCircle, new Insets(20, 10, 0, 20));
        VBox.setMargin(xBoxMoved, new Insets(10, 10, 0, 20));
        VBox.setMargin(yBoxMoved, new Insets(10, 10, 0, 20));
        VBox.setMargin(moveButtonCircle, new Insets(10, 10, 0, 20));
        VBox.setMargin(visibilityButton, new Insets(10, 10, 0, 20));
        circleLayout.setStyle("-fx-background-color: rgba(132, 183, 234, 0.5);");
        tabCircle.setContent(circleLayout);

        //для прямоугольника
        Label xRectLabel = new Label("X of the top left corner");
        TextField xRectField = new TextField();
        checkValue(xRectField);
        HBox xRectBox = new HBox(10, xRectLabel, xRectField);

        Label yRectLabel = new Label("Y of the top left corner");
        TextField yRectField = new TextField();
        checkValue(yRectField);
        HBox yRectBox = new HBox(10, yRectLabel, yRectField);

        Label heightRectLabel = new Label("Height");
        TextField heightRectField = new TextField();
        checkValue(heightRectField);
        HBox heightRectBox = new HBox(10, heightRectLabel, heightRectField);

        Label widthRectLabel = new Label("Width");
        TextField widthRectField = new TextField();
        checkValue(widthRectField);
        HBox widthRectBox = new HBox(10, widthRectLabel, widthRectField);

        //сдвиг по осям
        Label xRectLabelMoved = new Label("by X");
        TextField xRectFieldMoved = new TextField();
        checkValue(xRectFieldMoved);
        HBox xRectBoxMoved = new HBox(10, xRectLabelMoved, xRectFieldMoved);

        Label yRectLabelMoved = new Label("by Y");
        TextField yRectFieldMoved = new TextField();
        checkValue(yRectFieldMoved);
        HBox yRectBoxMoved = new HBox(10, yRectLabelMoved, yRectFieldMoved);

        VBox rectangleLayout = new VBox(settingForCreatingRect, xRectBox, yRectBox,
                heightRectBox, widthRectBox, createAndRandomButtonRect, settingForMovingRect,
                xRectBoxMoved, yRectBoxMoved, moveButtonRect, visibilityRButton);
        VBox.setMargin(settingForCreatingRect, new Insets(20, 10, 0, 20));
        VBox.setMargin(xRectBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(yRectBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(heightRectBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(widthRectBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(createAndRandomButtonRect, new Insets(10, 10, 0, 20));
        VBox.setMargin(settingForMovingRect, new Insets(20, 10, 0, 20));
        VBox.setMargin(xRectBoxMoved, new Insets(10, 10, 0, 20));
        VBox.setMargin(yRectBoxMoved, new Insets(10, 10, 0, 20));
        VBox.setMargin(moveButtonRect, new Insets(10, 10, 0, 20));
        VBox.setMargin(visibilityRButton, new Insets(10, 10, 0, 20));
        rectangleLayout.setStyle("-fx-background-color: rgba(132, 183, 234, 0.5);");
        tabRectangle.setContent(rectangleLayout);

        //для треугольника
        Label x1TriLabel = new Label("X1");
        TextField x1TriField = new TextField();
        checkValue(x1TriField);
        HBox x1TriBox = new HBox(10, x1TriLabel, x1TriField);

        Label y1TriLabel = new Label("Y1");
        TextField y1TriField = new TextField();
        checkValue(y1TriField);
        HBox y1TriBox = new HBox(10, y1TriLabel, y1TriField);

        Label x2TriLabel = new Label("X2");
        TextField x2TriField = new TextField();
        checkValue(x2TriField);
        HBox x2TriBox = new HBox(10, x2TriLabel, x2TriField);

        Label y2TriLabel = new Label("Y2");
        TextField y2TriField = new TextField();
        checkValue(y2TriField);
        HBox y2TriBox = new HBox(10, y2TriLabel, y2TriField);

        Label x3TriLabel = new Label("X3");
        TextField x3TriField = new TextField();
        checkValue(x3TriField);
        HBox x3TriBox = new HBox(10, x3TriLabel, x3TriField);

        Label y3TriLabel = new Label("Y3");
        TextField y3TriField = new TextField();
        checkValue(y3TriField);
        HBox y3TriBox = new HBox(10, y3TriLabel, y3TriField);

        //сдвиг по осям
        Label xTriLabelMoved = new Label("by X");
        TextField xTriFieldMoved = new TextField();
        checkValue(xTriFieldMoved);
        HBox xTriBoxMoved = new HBox(10, xTriLabelMoved, xTriFieldMoved);

        Label yTriLabelMoved = new Label("by Y");
        TextField yTriFieldMoved = new TextField();
        checkValue(yTriFieldMoved);
        HBox yTriBoxMoved = new HBox(10, yTriLabelMoved, yTriFieldMoved);

        VBox triangleLayout = new VBox(settingForCreatingTriang, x1TriBox, y1TriBox,
                x2TriBox, y2TriBox, x3TriBox, y3TriBox, createAndRandomButtonTriang,
                settingForMovingTriang, xTriBoxMoved, yTriBoxMoved, moveButtonTriang,
                visibilityTButton);
        VBox.setMargin(settingForCreatingTriang, new Insets(20, 10, 0, 20));
        VBox.setMargin(x1TriBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(y1TriBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(x2TriBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(y2TriBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(x3TriBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(y3TriBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(createAndRandomButtonTriang, new Insets(10, 10, 0, 20));
        VBox.setMargin(settingForMovingTriang, new Insets(20, 10, 0, 20));
        VBox.setMargin(xTriBoxMoved, new Insets(10, 10, 0, 20));
        VBox.setMargin(yTriBoxMoved, new Insets(10, 10, 0, 20));
        VBox.setMargin(moveButtonTriang, new Insets(10, 10, 0, 20));
        VBox.setMargin(visibilityTButton, new Insets(10, 10, 0, 20));
        triangleLayout.setStyle("-fx-background-color: rgba(132, 183, 234, 0.5);");
        tabTriangle.setContent(triangleLayout);

        //для кольца
        Label rRingLabel = new Label("Radius of small circle");
        TextField rRingField = new TextField();
        checkValue(rRingField);
        HBox rRingBox = new HBox(10, rRingLabel, rRingField);

        Label rRingBigLabel = new Label("Radius of big circle");
        TextField rRingBigField = new TextField();
        checkValue(rRingBigField);
        HBox rRingBigBox = new HBox(10, rRingBigLabel, rRingBigField);

        Label xRingLabel = new Label("X");
        TextField xRingField = new TextField();
        checkValue(xRingField);
        HBox xRingBox = new HBox(10, xRingLabel, xRingField);

        Label yRingLabel = new Label("Y");
        TextField yRingField = new TextField();
        checkValue(yRingField);
        HBox yRingBox = new HBox(10, yRingLabel, yRingField);

        //сдвиг по осям
        Label xLabelRingMoved = new Label("by X");
        TextField xFieldRingMoved = new TextField();
        checkValue(xFieldRingMoved);
        HBox xBoxRingMoved = new HBox(10, xLabelRingMoved, xFieldRingMoved);

        Label yLabelRingMoved = new Label("by Y");
        TextField yFieldRingMoved = new TextField();
        checkValue(yFieldRingMoved);
        HBox yBoxRingMoved = new HBox(10, yLabelRingMoved, yFieldRingMoved);

        VBox ringLayout = new VBox(settingForCreatingRing, rRingBox, rRingBigBox,  xRingBox, yRingBox,
                createAndRandomButtonRing, settingForMovingRing, xBoxRingMoved, yBoxRingMoved,
                moveButtonRing, visibilityRgButton);
        VBox.setMargin(settingForCreatingRing, new Insets(20, 10, 0, 20));
        VBox.setMargin(rRingBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(rRingBigBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(xRingBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(yRingBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(createAndRandomButtonRing, new Insets(10, 10, 0, 20));
        VBox.setMargin(settingForMovingRing, new Insets(20, 10, 0, 20));
        VBox.setMargin(xBoxRingMoved, new Insets(10, 10, 0, 20));
        VBox.setMargin(yBoxRingMoved, new Insets(10, 10, 0, 20));
        VBox.setMargin(moveButtonRing, new Insets(10, 10, 0, 20));
        VBox.setMargin(visibilityRgButton, new Insets(10, 10, 0, 20));
        ringLayout.setStyle("-fx-background-color: rgba(132, 183, 234, 0.5);");
        tabRing.setContent(ringLayout);

        //для рамки
        Label xFrameLabel = new Label("X of the top left corner");
        TextField xFrameField = new TextField();
        checkValue(xFrameField);
        HBox xFrameBox = new HBox(10, xFrameLabel, xFrameField);

        Label yFrameLabel = new Label("Y of the top left corner");
        TextField yFrameField = new TextField();
        checkValue(yFrameField);
        HBox yFrameBox = new HBox(10, yFrameLabel, yFrameField);

        Label heightFrameLabel = new Label("Height");
        TextField heightFrameField = new TextField();
        checkValue(heightFrameField);
        HBox heightFrameBox = new HBox(10, heightFrameLabel, heightFrameField);

        Label widthFrameLabel = new Label("Width");
        TextField widthFrameField = new TextField();
        checkValue(widthFrameField);
        HBox widthFrameBox = new HBox(10, widthFrameLabel, widthFrameField);

        //сдвиг по осям
        Label xFrameLabelMoved = new Label("by X");
        TextField xFrameFieldMoved = new TextField();
        checkValue(xFrameFieldMoved);
        HBox xFrameBoxMoved = new HBox(10, xFrameLabelMoved, xFrameFieldMoved);

        Label yFrameLabelMoved = new Label("by Y");
        TextField yFrameFieldMoved = new TextField();
        checkValue(yFrameFieldMoved);
        HBox yFrameBoxMoved = new HBox(10, yFrameLabelMoved, yFrameFieldMoved);

        VBox frameLayout = new VBox(settingForCreatingFrame, xFrameBox, yFrameBox,
                heightFrameBox, widthFrameBox, createAndRandomButtonFrame, settingForMovingFrame,
                xFrameBoxMoved, yFrameBoxMoved, moveButtonFrame, visibilityFButton);
        VBox.setMargin(settingForCreatingFrame, new Insets(20, 10, 0, 20));
        VBox.setMargin(xFrameBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(yFrameBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(heightFrameBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(widthFrameBox, new Insets(10, 10, 0, 20));
        VBox.setMargin(createAndRandomButtonFrame, new Insets(10, 10, 0, 20));
        VBox.setMargin(settingForMovingFrame, new Insets(20, 10, 0, 20));
        VBox.setMargin(xFrameBoxMoved, new Insets(10, 10, 0, 20));
        VBox.setMargin(yFrameBoxMoved, new Insets(10, 10, 0, 20));
        VBox.setMargin(moveButtonFrame, new Insets(10, 10, 0, 20));
        VBox.setMargin(visibilityFButton, new Insets(10, 10, 0, 20));
        frameLayout.setStyle("-fx-background-color: rgba(132, 183, 234, 0.5);");
        tabFrame.setContent(frameLayout);

        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //обработчики кнопок для круга
        randomButtonCircle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newCircle = new Circle(visibility, random);
                if(visibility){
                    newCircle.DrawCircle(gc, Color.BLUE);
                }
                circles.add(newCircle);
            }
        });
        createButtonCircle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    radiusInt = Integer.parseInt(rField.getText());
                    circlePoint.setX(Integer.parseInt(xField.getText()));
                    circlePoint.setY(Integer.parseInt(yField.getText()));
                } catch (Exception e) {
                    System.out.println("put in the right value");
                }
                newCircle = new Circle(circlePoint, radiusInt, visibility);
                if(visibility){
                    newCircle.DrawCircle(gc, Color.BLUE);
                }
                circles.add(newCircle);
            }
        });
        moveButtonCircle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    xCircleMove = Integer.parseInt(xFieldMoved.getText());
                    yCircleMove = Integer.parseInt(yFieldMoved.getText());
                    clearCanvas(gc);
                    for(Circle circle : circles){
                        circle.MoveTo(xCircleMove, yCircleMove);
                        if(visibility){
                            circle.DrawCircle(gc, Color.BLUE);
                        }
                    }
                    if(visibilityR){
                        for(Rectangle rectangle : rectangles){
                            rectangle.DrawRectangle(gc, Color.GREEN);
                        }
                    }
                    if(visibilityT){
                        for(Triangle triangle : triangles){
                            triangle.DrawTriangle(gc);
                        }
                    }
                    if(visibilityRg){
                        for(Ring ring : rings){
                            ring.DrawRing(gc);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("put in the right value");
                }
            }
        });
        //обработчики кнопок для прямоугольника
        randomButtonRect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newRectangle = new Rectangle(visibilityR, random);
                if (visibilityR) {
                    newRectangle.DrawRectangle(gc, Color.GREEN);
                }
                rectangles.add(newRectangle);
            }
        });
        createButtonRect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    rectanglePoint.setX(Integer.parseInt(xRectField.getText()));
                    rectanglePoint.setY(Integer.parseInt(yRectField.getText()));
                    hRectInt = Integer.parseInt(heightRectField.getText());
                    wRectInt = Integer.parseInt(widthRectField.getText());
                } catch (Exception e) {
                    System.out.println("put in the right value");
                }
                newRectangle = new Rectangle(rectanglePoint, wRectInt, hRectInt, visibilityR);
                if (visibilityR) {
                    newRectangle.DrawRectangle(gc, Color.GREEN);
                }
                rectangles.add(newRectangle);
            }
        });
        moveButtonRect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    xRectMove = Integer.parseInt(xRectFieldMoved.getText());
                    yRectMove = Integer.parseInt(yRectFieldMoved.getText());
                    clearCanvas(gc);
                    for(Rectangle rectangle : rectangles){
                        rectangle.MoveTo(xRectMove, yRectMove);
                    }
                    if(visibility){
                        for(Circle circle : circles){
                            circle.DrawCircle(gc, Color.BLUE);
                        }
                    }
                    if(visibilityT){
                        for(Triangle triangle : triangles){
                            triangle.DrawTriangle(gc);
                        }
                    }
                    if(visibilityRg){
                        for(Ring ring : rings){
                            ring.DrawRing(gc);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("put in the right value");
                }
            }
        });
        //обработчики кнопок для треугольника
        randomButtonTriang.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newTriangle = new Triangle(visibilityR, random, Color.RED);
                if(visibilityT){
                    newTriangle.DrawTriangle(gc);
                }
                triangles.add(newTriangle);
            }
        });
        createButtonTriang.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    trianglePoint1.setX(Integer.parseInt(x1TriField.getText()));
                    trianglePoint1.setY(Integer.parseInt(y1TriField.getText()));
                    trianglePoint2.setX(Integer.parseInt(x2TriField.getText()));
                    trianglePoint2.setY(Integer.parseInt(y2TriField.getText()));
                    trianglePoint3.setX(Integer.parseInt(x3TriField.getText()));
                    trianglePoint3.setY(Integer.parseInt(y3TriField.getText()));
                } catch (Exception e) {
                    System.out.println("put in the right value");
                }
                newTriangle = new Triangle(trianglePoint1, trianglePoint2, trianglePoint3, visibilityR, Color.RED);
                if(visibilityT){
                    newTriangle.DrawTriangle(gc);
                }
                triangles.add(newTriangle);
            }
        });

        moveButtonTriang.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    xTriMove = Integer.parseInt(xTriFieldMoved.getText());
                    yTriMove = Integer.parseInt(yTriFieldMoved.getText());
                    clearCanvas(gc);
                    for(Triangle triangle : triangles){
                        triangle.MoveTo(xTriMove, yTriMove, gc, visibilityT);
                    }
                    if(visibility){
                        for(Circle circle : circles){
                            circle.DrawCircle(gc, Color.BLUE);
                        }
                    }
                    if(visibilityR){
                        for(Rectangle rectangle : rectangles){
                            rectangle.DrawRectangle(gc, Color.GREEN);
                        }
                    }
                    if(visibilityRg){
                        for(Ring ring : rings){
                            ring.DrawRing(gc);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("put in the right value");
                }
            }
        });
        //обработчики кнопок для кольца
        createButtonRing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    rRingBig = Integer.parseInt(rRingBigField.getText());
                    rRingSmall = Integer.parseInt(rRingField.getText());
                    rRingSmall = checkRadius(rRingBig, rRingSmall);
                    ringPoint.setX(Integer.parseInt(xRingField.getText()));
                    ringPoint.setY(Integer.parseInt(yRingField.getText()));
                    small = new Circle(ringPoint, rRingSmall, visibilityRg);
                    big = new Circle(ringPoint, rRingBig, visibilityRg);
                } catch (Exception e) {
                    System.out.println("put in the right value");
                }
                newRing = new Ring(small, big);
                if(visibilityRg){
                    newRing.DrawRing(gc);
                }
                rings.add(newRing);
            }
        });
        randomButtonRing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newRing = new Ring(visibilityRg, random);
                if(visibility){
                    newRing.DrawRing(gc);
                }
                rings.add(newRing);
            }
        });
        moveButtonRing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    xRingMove = Integer.parseInt(xFieldRingMoved.getText());
                    yRingMove = Integer.parseInt(yFieldRingMoved.getText());
                    newRing.Hide(gc);
                    for(Ring ring : rings){
                        ring.MoveTo(xRingMove, yRingMove);
                        if(visibilityRg){
                            ring.DrawRing(gc);
                        }
                    }
                    if(visibility){
                        for(Circle circle : circles){
                            circle.DrawCircle(gc, Color.BLUE);
                        }
                    }
                    if(visibilityR){
                        for(Rectangle rectangle : rectangles){
                            rectangle.DrawRectangle(gc, Color.GREEN);
                        }
                    }
                    if(visibilityT){
                        for(Triangle triangle : triangles){
                            triangle.DrawTriangle(gc);
                        }
                    }
                    if(visibilityF){
                        for(Frame frame : frames){
                            frame.DrawFrame(gc);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("put in the right value");
                }
            }
        });

        //обработчики кнопок для рамки
        randomButtonFrame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newFrame = new Frame(visibilityF, random);
                if (visibilityF) {
                    newFrame.DrawFrame(gc);
                }
                frames.add(newFrame);
            }
        });
        createButtonFrame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    framePoint.setX(Integer.parseInt(xFrameField.getText()));
                    framePoint.setY(Integer.parseInt(yFrameField.getText()));
                    framePointSmall.setX(framePoint.getX() + 10);
                    framePointSmall.setY(framePoint.getY() + 10);
                    hFrameInt = Integer.parseInt(heightFrameField.getText());
                    wFrameInt = Integer.parseInt(widthFrameField.getText());
                    bigR = new Rectangle(framePoint, hFrameInt, wFrameInt, visibilityF);
                    smallR = new Rectangle(framePointSmall, hFrameInt-20, wFrameInt-20, visibilityF);
                } catch (Exception e) {
                    System.out.println("put in the right value");
                }
                newFrame = new Frame(smallR, bigR);
                if (visibilityF) {
                    newFrame.DrawFrame(gc);
                }
                frames.add(newFrame);
            }
        });
        moveButtonFrame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    xFrameMove = Integer.parseInt(xFrameFieldMoved.getText());
                    yFrameMove = Integer.parseInt(yFrameFieldMoved.getText());
                    clearCanvas(gc);
                    for(Frame frame : frames){
                        frame.MoveTo(xFrameMove, yFrameMove);
                        if(visibilityF){
                            frame.DrawFrame(gc);
                        }
                    }
                    if(visibility){
                        for(Circle circle : circles){
                            circle.DrawCircle(gc, Color.BLUE);
                        }
                    }
                    if(visibilityR){
                        for(Rectangle rectangle : rectangles){
                            rectangle.DrawRectangle(gc, Color.GREEN);
                        }
                    }
                    if(visibilityT){
                        for(Triangle triangle : triangles){
                            triangle.DrawTriangle(gc);
                        }
                    }
                    if(visibilityRg){
                        for(Ring ring : rings){
                            ring.DrawRing(gc);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("put in the right value");
                }
            }
        });

        //для круга
        visibilityButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(visibility){
                    visibility = false;
                    for(Circle circle : circles){
                        circle.Hide(gc);
                    }
                    if(visibilityR){
                        for(Rectangle rectangle : rectangles){
                            rectangle.DrawRectangle(gc, Color.GREEN);
                        }
                    }
                    if(visibilityT){
                        for(Triangle triangle : triangles){
                            triangle.DrawTriangle(gc);
                        }
                    }
                    if(visibilityRg){
                        for(Ring ring : rings){
                            ring.DrawRing(gc);
                        }
                    }
                } else {
                    visibility = true;
                    for(Circle circle : circles){
                        circle.DrawCircle(gc, Color.BLUE);
                    }
                }
                visibilityButton.setText("Visibility of Circles is " + visibility);
            }
        });

        //для прямоугольника
        visibilityRButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(visibilityR){
                    visibilityR = false;
                    for(Rectangle rectangle : rectangles){
                        rectangle.Hide(gc);
                    }
                    if(visibility){
                        for(Circle circle : circles){
                            circle.DrawCircle(gc, Color.BLUE);
                        }
                    }
                    if(visibilityT){
                        for(Triangle triangle : triangles){
                            triangle.DrawTriangle(gc);
                        }
                    }
                    if(visibilityRg){
                        for(Ring ring : rings){
                            ring.DrawRing(gc);
                        }
                    }
                } else{
                    visibilityR = true;
                    for(Rectangle rectangle : rectangles){
                        rectangle.DrawRectangle(gc, Color.GREEN);
                    }
                }
                visibilityRButton.setText("Visibility of Rectangles is " + visibilityR);
            }
        });

        //для треуголника
        visibilityTButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(visibilityT){
                    visibilityT = false;
                    newTriangle.Hide(gc);
                    if(visibility){
                        for(Circle circle : circles){
                            circle.DrawCircle(gc, Color.BLUE);
                        }
                    }
                    if(visibilityR){
                        for(Rectangle rectangle : rectangles){
                            rectangle.DrawRectangle(gc, Color.GREEN);
                        }
                    }
                    if(visibilityRg){
                        for(Ring ring : rings){
                            ring.DrawRing(gc);
                        }
                    }
                } else {
                    visibilityT = true;
                    for(Triangle triangle : triangles){
                        triangle.DrawTriangle(gc);
                    }
                }
                visibilityTButton.setText("Visibility of Triangles is " + visibilityT);
            }
        });
        //для кольца
        visibilityRgButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(visibilityRg){
                    visibilityRg = false;
                    //newRing.Hide(gc);
                    clearCanvas(gc);
                    if(visibility){
                        for(Circle circle : circles){
                            circle.DrawCircle(gc, Color.BLUE);
                        }
                    }
                    if(visibilityR){
                        for(Rectangle rectangle : rectangles){
                            rectangle.DrawRectangle(gc, Color.GREEN);
                        }
                    }
                    if(visibilityT){
                        for(Triangle triangle : triangles){
                            triangle.DrawTriangle(gc);
                        }
                    }
                    if(visibilityF){
                        for(Frame frame : frames){
                            frame.DrawFrame(gc);
                        }
                    }
                } else {
                    visibilityRg = true;
                    for(Ring ring : rings){
                        ring.DrawRing(gc);
                    }
                }
                visibilityRgButton.setText("Visibility of Rings is " + visibilityRg);
            }
        });
        //для рамки
        visibilityFButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(visibilityF){
                    visibilityF = false;
                    clearCanvas(gc);
                    if(visibility){
                        for(Circle circle : circles){
                            circle.DrawCircle(gc, Color.BLUE);
                        }
                    }
                    if(visibilityR){
                        for(Rectangle rectangle : rectangles){
                            rectangle.DrawRectangle(gc, Color.GREEN);
                        }
                    }
                    if(visibilityT){
                        for(Triangle triangle : triangles){
                            triangle.DrawTriangle(gc);
                        }
                    }
                    if(visibilityRg){
                        for(Ring ring : rings){
                            ring.DrawRing(gc);
                        }
                    }
                } else {
                    visibilityF = true;
                    for(Frame frame : frames){
                        frame.DrawFrame(gc);
                    }
                }
                visibilityFButton.setText("Visibility of Frames is " + visibilityF);
            }
        });

        //коробка из двух элементов левого - меню, и правого - холста
        HBox root = new HBox(tabPane, canvas);

        primaryStage.setTitle("Lab 3");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 980, 600));
        primaryStage.show();
    }
    public void clearCanvas(GraphicsContext gc){
        gc.clearRect(0, 0, 600, 600);
    }

    public void checkValue(TextField tf) {
        tf.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = tf.getText().charAt(oldValue.intValue());
                    if (!(ch >= '0' && ch <= '9')) {
                        tf.setText(tf.getText().substring(0, tf.getText().length() - 1));
                    }
                }
            }

        });
    }

    public int checkRadius(int radiusBig, int radiusSmall){
        if(radiusSmall > radiusBig){
            System.out.println("Radius of inner circle is bigger " +
                    "than outer, so ring will be created with changed radius");
            return radiusBig - 10;
        } else{
            return radiusSmall;
        }
    }
}
