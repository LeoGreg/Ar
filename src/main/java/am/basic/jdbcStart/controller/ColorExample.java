package am.basic.jdbcStart.controller;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ColorExample extends Application {
    @Override
    public void start(Stage stage) {
        //Drawing a Circle
        Circle circle = new Circle();

        //Setting the properties of the circle
        circle.setCenterX(300.0f);
        circle.setCenterY(180.0f);
        circle.setRadius(90.0f);

        //Setting color to the circle
        circle.setFill(Color.DARKRED);

        //Setting the stroke width
        circle.setStrokeWidth(3);

        //Setting color to the stroke
        circle.setStroke(Color.DARKSLATEBLUE);

        //Drawing a text
        Text text = new Text("This is a colored circle");

        //Setting the font of the text
        text.setFont(Font.font("Edwardian Script ITC", 50));

        //Setting the position of the text
        text.setX(155);
        text.setY(50);

        //Setting color to the text
        text.setFill(Color.BEIGE);
        text.setStrokeWidth(2);
        text.setStroke(Color.DARKSLATEBLUE);

        //Creating a Group object
        Group root = new Group(circle, text);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 300);

        //Setting title to the Stage
        stage.setTitle("Color Example");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.effect.Light;
//import javafx.scene.effect.Lighting;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.stage.Stage;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//
//public class DistantLightingExample extends Application {
//    @Override
//    public void start(Stage stage) {
//        //Creating a Text object
//        Text text = new Text();
//
//        //Setting font to the text
//        text.setFont(Font.font(null, FontWeight.BOLD, 40));
//
//        //setting the position of the text
//        text.setX(60);
//        text.setY(50);
//
//        //Setting the text to be embedded.
//        text.setText("Welcome to Tutorialspoint");
//
//        //Setting the color of the text
//        text.setFill(Color.RED);
//
//        //Drawing a Circle
//        Circle circle = new Circle();
//
//        //Setting the center of the circle
//        circle.setCenterX(300.0f);
//        circle.setCenterY(160.0f);
//
//        //Setting the radius of the circle
//        circle.setRadius(100.0f);
//
//        //setting the fill color of the circle
//        circle.setFill(Color.CORNFLOWERBLUE);
//
//        //Instantiating the Light.Distant class
//        Light.Distant light = new Light.Distant();
//
//        //Setting the properties of the light source
//        light.setAzimuth(45.0);
//        light.setElevation(30.0);
//
//        //Instantiating the Lighting class
//        Lighting lighting = new Lighting();
//
//        //Setting the source of the light
//        lighting.setLight(light);
//
//        //Applying the lighting effect to the text
//        text.setEffect(lighting);
//
//        //Applying the lighting effect to the circle
//        circle.setEffect(lighting);
//
//        //Creating a Group object
//        Group root = new Group(text,circle);
//
//        //Creating a scene object
//        Scene scene = new Scene(root, 600, 300);
//
//        //Setting title to the Stage
//        stage.setTitle("Distant light effect example");
//
//        //Adding scene to the stage
//        stage.setScene(scene);
//
//        //Displaying the contents of the stage
//        stage.show();
//    }
//    public static void main(String args[]){
//        launch(args);
//    }
//}