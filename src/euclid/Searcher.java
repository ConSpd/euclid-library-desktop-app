package euclid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Searcher {
    private static FileInputStream libraryFile;
    private static XSSFWorkbook workbookXSSF;
    private static HSSFWorkbook workbookHSSF;
    private static XSSFSheet sheetXSSF;
    private static HSSFSheet sheetHSSF;
    
    public static void prepareCsvFile(){
        try{
//            Alert alert = new Alert(AlertType.NONE, "Preparing File", ButtonType.CLOSE);
//            alert.showAndWait();
            BufferedReader br = new BufferedReader(new FileReader("library_path.txt"));
            String libraryFileName = br.readLine();
            libraryFile = new FileInputStream(new File(libraryFileName));
            
            System.out.println(libraryFileName);
            
            // Checking if file is xlsx or xls or something else in order to use XSSF or HSSF
            Pattern pattern = Pattern.compile("\\.xlsx$",Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(libraryFileName);
            boolean matchFound = matcher.find();
            
            if (matchFound){  // It is a xlsx file
                workbookXSSF = new XSSFWorkbook(libraryFile);
                sheetXSSF = workbookXSSF.getSheetAt(0);
                workbookHSSF = null; // We set is as null because when we change the file in program the HSSF was before that initialized, but now we changed to XSSF
                sheetHSSF = null;    // Same goes for the rest of nulls
            }else{ // It is a xls file
                workbookHSSF = new HSSFWorkbook(libraryFile);
                sheetHSSF = workbookHSSF.getSheetAt(0);
                workbookXSSF = null;
                sheetXSSF = null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void search(){
//        for (Row row : sheet){
//            Cell cell = row.getCell(2);
//            System.out.println(cell.toString());
//        }
        if (workbookXSSF != null){
            Alert alert = new Alert(AlertType.NONE, "XLSX File", ButtonType.CLOSE);
            alert.showAndWait();
            XSSFRow row = sheetXSSF.getRow(30);
            XSSFCell cell = row.getCell(3);
            System.out.println(cell.getStringCellValue());
        }else{
            Alert alert = new Alert(AlertType.NONE, "XLS File", ButtonType.CLOSE);
            alert.showAndWait();
            HSSFRow row = sheetHSSF.getRow(30);
            HSSFCell cell = row.getCell(3);
            System.out.println(cell.getStringCellValue());
        }
    }
}
