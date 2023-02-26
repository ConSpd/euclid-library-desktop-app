package euclid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MainMenu {
    Button searchBookBtn, insertBookBtn;
    Stage window;
    Scene scene;
    VBox vBoxLayout;
    HBox hBoxLayout;
    BorderPane border;
    Text text;
    
    public void openWindow(BorderPane border){
        this.border = border;
        try{
            initializeScreen(); 
        }catch(Exception e){
            e.printStackTrace();
        }
        searchBookBtn.setOnAction(e -> {
                System.out.println("Hey");
                System.out.println("Man");
        });
        insertBookBtn.setOnAction(e -> new BookInsert().openWindow(border));
        
        // Adding title and buttons to BorderPane
        vBoxLayout.getChildren().addAll(searchBookBtn, insertBookBtn);
        border.setCenter(vBoxLayout);
        border.setTop(hBoxLayout);
    }
    
    private void initializeScreen() throws FileNotFoundException{
        border.getChildren().clear();
        
        // Creating the middle box 
        vBoxLayout = new VBox();
        vBoxLayout.setAlignment(Pos.CENTER);
        vBoxLayout.setPadding(new Insets(0, 0, 55, 0));
        
        // Creating top box with title
        hBoxLayout = new HBox();
        hBoxLayout.setAlignment(Pos.CENTER);
        hBoxLayout.setPadding(new Insets(55, 0, 0, 0));
        text = new Text("Κύριο Μενού");
        text.setFont(Font.font("Copperplate",FontWeight.BOLD,80));
        hBoxLayout.getChildren().addAll(text);
        
        // Initializing Buttons
        searchBookBtn = new Button("Αναζήτηση Βιβλίου");
        searchBookBtn.setMinSize(250, 150);
        searchBookBtn.setFont(Font.font("Copperplate",15));

        searchBookBtn.setOnMouseEntered(eh -> enlargeButton(searchBookBtn));
        searchBookBtn.setOnMouseExited(eh -> decreaseButton(searchBookBtn));

        insertBookBtn = new Button("Εισαγωγή Βιβλίου");
        insertBookBtn.setMinSize(250, 150);
        insertBookBtn.setFont(Font.font("Copperplate",15));
        
        insertBookBtn.setOnMouseEntered(eh -> enlargeButton(insertBookBtn));
        insertBookBtn.setOnMouseExited(eh -> decreaseButton(insertBookBtn));
        
        // Adding background image to the buttons
        try{
            FileInputStream fs = new FileInputStream("/Users/conspd/Software/Euclid/src/euclid/button.png");
            Image img = new Image(fs);
            BackgroundImage myBi = new BackgroundImage(img ,
                                                       BackgroundRepeat.NO_REPEAT,
                                                       BackgroundRepeat.NO_REPEAT,
                                                       BackgroundPosition.CENTER,
                                                       new BackgroundSize(BackgroundSize.AUTO,
                                                                          BackgroundSize.AUTO,
                                                                          true, true, true, false));
            searchBookBtn.setBackground(new Background(myBi));
            insertBookBtn.setBackground(new Background(myBi));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void enlargeButton(Button btn){
        Duration duration = Duration.millis(200);
        //Create new scale transition
        ScaleTransition scaleTransition = new ScaleTransition(duration, btn);
        //Set how much X should enlarge
        scaleTransition.setByX(0.05);
        //Set how much Y should
        scaleTransition.setByY(0.05);
        scaleTransition.play();
    }
    private void decreaseButton(Button btn){
        Duration duration = Duration.millis(200);
        //Create new scale transition
        ScaleTransition scaleTransition = new ScaleTransition(duration, btn);
        //Set how much X should enlarge
        scaleTransition.setByX(-0.05);
        //Set how much Y should
        scaleTransition.setByY(-0.05);
        scaleTransition.play();
    }
}
