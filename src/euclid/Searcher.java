package euclid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Searcher {
    private FileInputStream libraryFile;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    
    Searcher(){
        prepareCsvFile();
    }
    
    private void prepareCsvFile(){
        try{
            Alert alert = new Alert(AlertType.NONE, "Preparing File", ButtonType.CLOSE);
            alert.showAndWait();
            BufferedReader br = new BufferedReader(new FileReader("library_path.txt"));
            libraryFile = new FileInputStream(new File(br.readLine()));
            workbook = new XSSFWorkbook(libraryFile);
            sheet = workbook.getSheetAt(0);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void search(){
//        for (Row row : sheet){
//            Cell cell = row.getCell(2);
//            System.out.println(cell.toString());
//        }
        XSSFRow row = sheet.getRow(30);
        XSSFCell cell = row.getCell(3);
        System.out.println(cell.getStringCellValue());
    }
}
