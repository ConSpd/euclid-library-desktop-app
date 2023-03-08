package euclid;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ResultsWindow {
    
    private List<Results> resultsList;
    private Stage window;
    private int selectedBook = 0;
    
    public void openResultsUI(List<Results> resultsList){
        // Check if there were no books found
        if (resultsList.isEmpty()){
            Alert alert = new Alert(AlertType.NONE, "Δεν βρέθηκαν βιβλία!", ButtonType.CLOSE);
            alert.showAndWait();
            return;
        }
        
        this.resultsList = resultsList;
        window = new Stage();

        BorderPane resultsBorder = makeMainWindow();
        
        makeTitle(resultsBorder);
        
        // Middle section holds the image and the info of the book
        makeMiddleSection(resultsBorder, selectedBook);
        
        makeBottomSection(resultsBorder);
        finishWindow(resultsBorder);
        
    }
    
    private void makeTitle(BorderPane border){
        //Add top layer
        Text title = new Text("Αποτελέσματα");
        title.setFont(Font.font("Copperplate", FontWeight.BOLD,50));
        
        VBox titleLayout = new VBox();
        titleLayout.getChildren().add(title);
        titleLayout.setAlignment(Pos.CENTER);
        border.setTop(titleLayout);
    }
    
    private void makeMiddleSection(BorderPane border, int selectedBook){
        // At resultsLayout left is the image and right is the book info
        HBox resultsLayout = new HBox(20);
        
        addBookImage(resultsLayout);
        addBookDescription(resultsLayout);
        border.setCenter(resultsLayout);
        
        
    }
    
    private void addBookDescription(HBox resultsLayout){
        // Book info section
        VBox bookInfoLayout = new VBox(20);
        bookInfoLayout.setAlignment(Pos.CENTER_LEFT);
        bookInfoLayout.setPadding(new Insets(55, 20, 55, 20));
        
        String res = resultsList.get(selectedBook).toString();
        
        Text number = new Text("Αριθμός");
        Text bookNumber = new Text(Integer.toString(resultsList.get(selectedBook).getNumber()));
        number.setFont(Font.font("Serif Regular", FontWeight.BOLD, 20));

        Text name = new Text("Τίτλος");
        Text bookName = new Text(resultsList.get(selectedBook).getName());
        name.setFont(Font.font("Serif Regular", FontWeight.BOLD, 20));
        
        Text author = new Text("Συγγραφέας");
        Text bookAuthor = new Text(resultsList.get(selectedBook).getAuthor());
        author.setFont(Font.font("Serif Regular", FontWeight.BOLD, 20));
        
        Text publisher = new Text("Εκδότης");
        Text bookPublisher = new Text(resultsList.get(selectedBook).getPublisher());
        publisher.setFont(Font.font("Serif Regular", FontWeight.BOLD, 20));
        
        Text year = new Text("Έτος");
        Text bookYear = new Text(resultsList.get(selectedBook).getYear());
        year.setFont(Font.font("Serif Regular", FontWeight.BOLD, 20));
        
        bookInfoLayout.getChildren().addAll(number, bookNumber,
                                            name, bookName,
                                            author, bookAuthor,
                                            publisher, bookPublisher,
                                            year, bookYear);
        
        resultsLayout.getChildren().add(bookInfoLayout);
    }
    
    private void addBookImage(HBox resultsLayout){
        try{
            // Book Image
            FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/src/euclid/etc/Book.jpg");
            Image bookImg = new Image(fs);
            ImageView imgView = new ImageView(bookImg);
            resultsLayout.getChildren().add(imgView);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void makeBottomSection(BorderPane border){
        // Arrows at bottom of window
        HBox arrowsLayout = new HBox(150);
        Button leftArrowBtn = new Button();
        leftArrowBtn.setMinSize(128, 128);
        Button rightArrowBtn = new Button();
        rightArrowBtn.setMinSize(128, 128);
        arrowsLayout.getChildren().addAll(leftArrowBtn, rightArrowBtn);
        arrowsLayout.setAlignment(Pos.CENTER);
        
        try{
            // Left Arrow image
            FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/src/euclid/etc/leftArrow.png");
            Image leftImg = new Image(fs);
            BackgroundImage myBi = new BackgroundImage( leftImg,
                                            BackgroundRepeat.NO_REPEAT,
                                            BackgroundRepeat.NO_REPEAT,
                                            BackgroundPosition.DEFAULT,
                                            new BackgroundSize(BackgroundSize.AUTO,
                                                               BackgroundSize.AUTO,
                                                               true, true, false, true));
            leftArrowBtn.setBackground(new Background(myBi));
            
            // Right Arrow Image
            fs = new FileInputStream(System.getProperty("user.dir")+"/src/euclid/etc/rightArrow.png");
            Image rightImg = new Image(fs);
            myBi = new BackgroundImage( rightImg,
                                            BackgroundRepeat.NO_REPEAT,
                                            BackgroundRepeat.NO_REPEAT,
                                            BackgroundPosition.DEFAULT,
                                            new BackgroundSize(BackgroundSize.AUTO,
                                                               BackgroundSize.AUTO,
                                                               true, true, false, true));
            rightArrowBtn.setBackground(new Background(myBi));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        leftArrowBtn.setOnAction(e -> {
                    if(selectedBook > 0)
                        makeMiddleSection(border, selectedBook--);
                });
        
        rightArrowBtn.setOnAction(e -> {
                    if(selectedBook < resultsList.size() - 1)
                        makeMiddleSection(border, selectedBook++);
                });
        
        border.setBottom(arrowsLayout);
    }
    
    private BorderPane makeMainWindow(){
        BorderPane resultsBorder = new BorderPane();
        window.setHeight(800);
        window.setWidth(900);
        
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
            resultsBorder.setBackground(new Background(myBi));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return resultsBorder;
    }
    
    private void finishWindow(BorderPane border){
        Scene scene = new Scene(border);
        window.setScene(scene);
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Results");
        Image icon = new Image(new File(System.getProperty("user.dir")+"/src/euclid/etc/Library_Icon.png").toURI().toString());
        window.show();
    }

}
