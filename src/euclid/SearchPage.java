package euclid;

import javafx.scene.layout.BorderPane;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class SearchPage {
    BorderPane border;
    
    
    public void openWindow(BorderPane border) {
        this.border = border;
        createWindow();
        


    }
    
    private void createWindow(){
        border.getChildren().clear();
        
        
        // Title section
        Text title = new Text("Αναζήτηση Βιβλίου");
        title.setFont(Font.font("Copperplate",FontWeight.BOLD,80));
        HBox titleLayout = new HBox(title);
        titleLayout.setAlignment(Pos.CENTER);
        titleLayout.setPadding(new Insets(55, 0, 0, 0));
        
        
        /*
        **** Search section
        */
        // Text Info
        Text info = new Text("Γράψε κριτήριο αναζήτησης");
        info.setFont(Font.font("Serif",FontWeight.LIGHT,20));
        
        // Option Fields
        ToggleGroup tg = new ToggleGroup();
        
        
        RadioButton titleSelection = new RadioButton("Τίτλος");
        titleSelection.setToggleGroup(tg);
        titleSelection.setSelected(true);
        RadioButton authorSelection = new RadioButton("Συγγραφέας");
        authorSelection.setToggleGroup(tg);
        RadioButton houseSelection = new RadioButton("Εκδοτικός Οίκος");
        houseSelection.setToggleGroup(tg);
        RadioButton yearSelection = new RadioButton("Έτος");
        yearSelection.setToggleGroup(tg);
        RadioButton numberSelection = new RadioButton("Αριθμός");
        numberSelection.setToggleGroup(tg);
        
        GridPane categories = new GridPane();
        categories.add(titleSelection, 0, 0);
        categories.add(authorSelection, 1, 0);
        categories.add(houseSelection, 2, 0);
        categories.add(yearSelection, 3, 0);
        categories.add(numberSelection, 4, 0);
        categories.setAlignment(Pos.CENTER);
        categories.setVgap(20);
        categories.setHgap(40);

        // Search Field
        TextField prompt = new TextField();
        prompt.setMinSize(40, 60);
        prompt.setMaxWidth(900);
        prompt.setAlignment(Pos.CENTER);
        prompt.setFont(Font.font("Serif",FontWeight.SEMI_BOLD,50));
        Button searchButton = new Button("Αναζήτησε");
        
        VBox middleLayout = new VBox(30);
        middleLayout.setAlignment(Pos.CENTER);
        middleLayout.getChildren().addAll(info, prompt, categories, searchButton);
        /*
        ****
        */
        
        
        
        // Back Button
        Button back = new Button("Πίσω");
        HBox backLayout = new HBox(back);
        backLayout.setAlignment(Pos.CENTER);
        backLayout.setPadding(new Insets(0, 0, 190, 0));
        back.setOnAction(eh -> new MainMenu().openWindow(border));
        
        
        border.setTop(titleLayout);
        border.setCenter(middleLayout);
        border.setBottom(backLayout);
    }
    
}
