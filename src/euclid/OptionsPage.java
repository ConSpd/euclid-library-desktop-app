package euclid;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class OptionsPage{
    private File libraryFile;
    private Stage window;
    
    public void openWindow(){
        window = new Stage();
        BorderPane border = new BorderPane();
        
        // Options section
        HBox optionsLayout = new HBox(20);
        optionsLayout.setAlignment(Pos.CENTER);
        
        // Path to file 
        Text pathText = new Text("Αρχείο Βιβλιοθήκης");
        pathText.setFont(Font.font("Serif Regular", FontWeight.BOLD, 20));
        Button fileButton = new Button("Αρχείο");
        optionsLayout.getChildren().addAll(pathText, fileButton);
        fileButton.setOnAction(eh -> fileSelector());
        

        // Adding background to layout
        try{
            FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/src/euclid/etc/paper.jpg");
            Image img = new Image(fs);
            BackgroundImage myBi = new BackgroundImage( img,
                                                        BackgroundRepeat.NO_REPEAT,
                                                        BackgroundRepeat.NO_REPEAT,
                                                        BackgroundPosition.DEFAULT,
                                                        new BackgroundSize(BackgroundSize.AUTO,
                                                                           BackgroundSize.AUTO,
                                                                           true, true, false, true));
            border.setBackground(new Background(myBi));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        // Exit Button
        Button exit = new Button("Αποθήκευση & Έξοδος");
        HBox buttonLayout = new HBox(exit);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setPadding(new Insets(0, 0, 20, 0));
        exit.setOnAction(eh -> exitRoutine());
        
        // Setting all nodes to borderpane
        border.setCenter(optionsLayout);
        border.setBottom(buttonLayout);
        
        Scene scene = new Scene(border, 520, 220);
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(scene);
        window.setTitle("Options");
        Image icon = new Image(new File(System.getProperty("user.dir")+"/src/euclid/etc/Library_Icon.png").toURI().toString());
        window.getIcons().add(icon);
        window.showAndWait();
    }
    
    private void fileSelector(){
        FileChooser fileChooser = new FileChooser();
        libraryFile = fileChooser.showOpenDialog(window);
        try{
            PrintWriter pw = new PrintWriter("library_path.txt");
            pw.println(libraryFile);
            pw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void exitRoutine(){
        if (libraryFile == null)
            noFileError();
        else
            window.close();
    }
    
    private void noFileError(){
        Alert alert = new Alert(AlertType.NONE, "Δεν έχει προσδιοριστεί αρχείο Βιβλιοθήκης", ButtonType.CLOSE);
        alert.showAndWait();
    }
}
