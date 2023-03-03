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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private BorderPane border;
    private SearchPage searchPage;
    private Searcher searcher;
    private InsertPage insertPage;
    
    MainMenu(Searcher searcher){
        this.searcher = searcher;
        searchPage = new SearchPage(this);
        insertPage = new InsertPage(this);
    }
    
    public void openWindow(BorderPane border){
        this.border = border;
        try{
            initializeScreen(); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void initializeScreen() throws FileNotFoundException{
        border.getChildren().clear();
        
        // Title section
        HBox hBoxLayout = new HBox();
        hBoxLayout.setAlignment(Pos.CENTER);
        hBoxLayout.setPadding(new Insets(55, 0, 0, 0));
        Text text = new Text("Κύριο Μενού");
        text.setFont(Font.font("Copperplate",FontWeight.BOLD,80));
        hBoxLayout.getChildren().addAll(text);

        /*
        *** Middle section
        */
        //Creating the middle box 
        VBox vBoxLayout = new VBox();
        vBoxLayout.setAlignment(Pos.CENTER);
        vBoxLayout.setPadding(new Insets(0, 0, 55, 0));
        
        // Initializing Buttons
        Button searchBookBtn = new Button("Αναζήτηση Βιβλίου");
        searchBookBtn.setMinSize(250, 150);
        searchBookBtn.setFont(Font.font("Copperplate",15));
        Button insertBookBtn = new Button("Εισαγωγή Βιβλίου");
        insertBookBtn.setMinSize(250, 150);
        insertBookBtn.setFont(Font.font("Copperplate",15));
        Button optionsBtn = new Button("Ρυθμίσεις");
        optionsBtn.setMinSize(250, 150);
        optionsBtn.setFont(Font.font("Copperplate",15));
        
        // Button animations
        searchBookBtn.setOnMouseEntered(eh -> enlargeButton(searchBookBtn));
        searchBookBtn.setOnMouseExited(eh -> decreaseButton(searchBookBtn));
        insertBookBtn.setOnMouseEntered(eh -> enlargeButton(insertBookBtn));
        insertBookBtn.setOnMouseExited(eh -> decreaseButton(insertBookBtn));
        optionsBtn.setOnMouseEntered(eh -> enlargeButton(optionsBtn));
        optionsBtn.setOnMouseExited(eh -> decreaseButton(optionsBtn));
        
        // Button functionalities
        searchBookBtn.setOnAction(e -> searchPage.openWindow(border, searcher));
        insertBookBtn.setOnAction(e -> insertPage.openWindow(border));
        optionsBtn.setOnAction(e -> {
            OptionsPage optionsPage = new OptionsPage();
            optionsPage.openWindow(searcher, searchPage);
        });
        
        // Adding background image to the buttons
        try{
            FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/src/euclid/etc/button.png");
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
            optionsBtn.setBackground(new Background(myBi));
        }catch(Exception e){
            e.printStackTrace();
        }
        /*
        ***
        */
        
        // Adding title and buttons to BorderPane
        vBoxLayout.getChildren().addAll(searchBookBtn, insertBookBtn, optionsBtn);
        
        border.setCenter(vBoxLayout);
        border.setTop(hBoxLayout);
    }
    
    private void enlargeButton(Button btn){
        Duration duration = Duration.millis(200);
        //Create new scale transition
        ScaleTransition scaleTransition = new ScaleTransition(duration, btn);
        //Set how much X should enlarge
        scaleTransition.setToX(1.1);
        //Set how much Y should enlarge
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    }
    private void decreaseButton(Button btn){
        Duration duration = Duration.millis(200);
        //Create new scale transition
        ScaleTransition scaleTransition = new ScaleTransition(duration, btn);
        //Set how much X should enlarge
        scaleTransition.setToX(1);
        //Set how much Y should enlarge
        scaleTransition.setToY(1);
        scaleTransition.play();
    }
}
