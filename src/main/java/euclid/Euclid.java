package euclid;

import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Euclid extends Application{
    private MainMenu mainMenu;
    private BorderPane border;
    private Stage window;
    private Scene scene;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Searcher.prepareCsvFile();
        window = primaryStage;
        createMainWindow();
        mainMenu = new MainMenu();
        mainMenu.openWindow(border);
    }
    
    
    // Create the main scene and layout, insert background image, insert title
    private void createMainWindow() throws Exception{
        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        border = new BorderPane();
        
        // Reading background paper image and adding to layout
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
        
        
        scene = new Scene(border, screenSize.getWidth(), screenSize.getHeight());
        
        
        Image icon = new Image(new File(System.getProperty("user.dir")+"/src/euclid/etc/Library_Icon.png").toURI().toString());
        window.getIcons().add(icon);
        window.setTitle("Euclid Library");
        window.setScene(scene);
        window.show();
    }
}