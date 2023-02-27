package euclid;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class InsertPage{
    private Stage window;
    private Button back, insert;
    private MainMenu mainMenu;
    private BorderPane border;
    private VBox vBoxLayout;
    private HBox hBoxLayout, categories, buttons;

    public void openWindow(BorderPane border) {
        this.border = border;
        
        try{
            createWindow();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    
    private void createWindow() throws Exception{
        border.getChildren().clear();
        vBoxLayout = new VBox(15);
        vBoxLayout.setAlignment(Pos.CENTER);
        hBoxLayout = new HBox();
        hBoxLayout.setAlignment(Pos.CENTER);
        hBoxLayout.setPadding(new Insets(55, 0, 0, 0));
        
        // Title section
        Text text = new Text();
        text.setFill(Color.BLACK);
        text.setText("Εισαγωγή Βιβλίου");
        text.setFont(Font.font("Copperplate",FontWeight.BOLD,80));
        hBoxLayout.getChildren().addAll(text);
        
        // Titles of input fields
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

        
        // Category Checkboxes section
        CheckBox c1 = new CheckBox("Δοκίμιο");
        CheckBox c2 = new CheckBox("Μυθιστόρημα");
        CheckBox c3 = new CheckBox("Επιστήμη");
        VBox firstCol = new VBox(c1, c2, c3);
        firstCol.setSpacing(20);
        CheckBox c4 = new CheckBox("Τέχνη");
        CheckBox c5 = new CheckBox("Εσωτερισμός");
        CheckBox c6 = new CheckBox("Θρησκεία");
        VBox secondCol = new VBox(c4, c5, c6);
        secondCol.setSpacing(20);
        CheckBox c7 = new CheckBox("Ψυχολογία");
        CheckBox c8 = new CheckBox("Αθλητισμός");
        CheckBox c9 = new CheckBox("Πολιτική");
        VBox thirdCol = new VBox(c7, c8, c9);
        thirdCol.setSpacing(20);
        categories = new HBox(firstCol, secondCol, thirdCol);
        categories.setSpacing(30);
        categories.setAlignment(Pos.CENTER);
        categories.setPadding(new Insets(30, 0, 0, 0));
        
        // Buttons section
        back = new Button("Πίσω");
        insert = new Button("Προσθήκη");
        
        buttons = new HBox(20);
        buttons.getChildren().addAll(back, insert);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(0, 0, 190, 0));
        back.setOnAction(e -> new MainMenu().openWindow(border));
        
        vBoxLayout.getChildren().addAll(textNumber, number,
                                        textName, name,
                                        textAuthor, author,
                                        textHouse, house,
                                        categories);
        
        border.setCenter(vBoxLayout);
        border.setTop(hBoxLayout);
        border.setBottom(buttons);
    }
}
