package euclid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class MainMenu {
    Button searchBookBtn, insertBookBtn;
    Stage window;
    Scene scene;
    VBox layout;
    Text text;
    
    public void openWindow(VBox layout){
        this.layout = layout;
        try{
            initializeScreen(); 
        }catch(Exception e){
            e.printStackTrace();
        }
        searchBookBtn.setOnAction(e -> {
                System.out.println("Hey");
                System.out.println("Man");
        });
        insertBookBtn.setOnAction(e -> new BookInsert().openWindow(layout));

        layout.getChildren().addAll(text, searchBookBtn, insertBookBtn);
    }
    
    private void initializeScreen() throws FileNotFoundException{
        layout.getChildren().clear();
        text = new Text("Κύριο Μενού");
        text.setFont(Font.font("Copperplate",FontWeight.BOLD,80));
        
        
        
        
        searchBookBtn = new Button("Αναζήτηση Βιβλίου");
        searchBookBtn.setMinSize(250, 150);
        searchBookBtn.setFont(Font.font("Copperplate",15));
        
        insertBookBtn = new Button("Εισαγωγή Βιβλίου");
        insertBookBtn.setMinSize(250, 150);
        insertBookBtn.setFont(Font.font("Copperplate",15));
        
        try{
            FileInputStream fs = new FileInputStream("/Users/conspd/Software/Euclid/src/euclid/button.png");
            Image img = new Image(fs);
//            ImageView immg = new ImageView(img);
//            immg.fitWidthProperty().bind(searchBookBtn.widthProperty());
//            immg.fitHeightProperty().bind(searchBookBtn.heightProperty());
//            searchBookBtn.setGraphic(immg);
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
}
