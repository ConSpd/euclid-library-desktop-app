package euclid;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

public class BookInsert{
    Stage window;
    VBox layout_1;
    Button back;
    MainMenu mainMenu;

    public void openWindow(VBox layout) {
//        this.window = window;
        this.layout_1 = layout;
        try{
            createWindow();
        }catch(Exception e){
            e.printStackTrace();
        }
        back.setOnAction(e -> new MainMenu().openWindow(layout));
        
    }
    
    
    private void createWindow() throws Exception{
        layout_1.getChildren().clear();
        
        Text text = new Text();
        text.setFill(Color.BLACK);
        text.setText("Εισαγωγή Βιβλίου");text.setX(50);text.setY(50);
        text.setFont(Font.font("Copperplate",FontWeight.BOLD,80));
        
        // Text
        Text textNumber = new Text("Αριθμός (Αν μείνει κενό θα μπεί το επόμενο νούμερο)");
        Text textName = new Text("Τίτλος");
        Text textAuthor = new Text("Συγγραφέας");
        Text textHouse = new Text("Οίκος");
        
        // Text input fields
        TextField number = new TextField();        
        number.setMaxWidth(60);
        number.setAlignment(Pos.CENTER);
        
        TextField name = new TextField();
        name.setMaxWidth(200);
        name.setAlignment(Pos.CENTER);
        
        TextField author = new TextField();
        author.setMaxWidth(200);
        author.setAlignment(Pos.CENTER);
        
        TextField house = new TextField();
        house.setMaxWidth(200);
        house.setAlignment(Pos.CENTER);

        
        // Category Checkboxes
        CheckBox c1 = new CheckBox("Δοκίμιο");
        CheckBox c2 = new CheckBox("Μυθιστόρημα");
        CheckBox c3 = new CheckBox("Επιστήμη");
        
        
        // Back to Main Menu Button 
        back = new Button("Πίσω");
        
        layout_1.getChildren().addAll(text, 
                                      textNumber, number,
                                      textName, name,
                                      textAuthor, author,
                                      textHouse, house,
                                      c1, c2, c3,
                                      back);
    }
}
