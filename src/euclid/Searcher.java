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
import org.apache.poi.ss.usermodel.Row;

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
    
    public static void search(String query, String category){
        int categ;
        switch(category){   // Depending on the format of the excel file change this part, maybe add it to UI 
            case "Τίτλος": 
                categ = 2;
                break;
            case "Συγγραφέας": 
                categ = 3;
                break;
            case "Εκδοτικός Οίκος": 
                categ = 4;
                break;
            case "Έτος":
                categ = 5;
                break;
            case "Αριθμός":
                categ = 6;
                break;
            default:
                categ = 2;
                break;
        }
        System.out.println("Category="+categ);
        if (workbookXSSF != null)
            for (Row row : sheetXSSF)
                searchForMatches(row, query, categ);
        else
            for (Row row : sheetHSSF)
                searchForMatches(row, query, categ);
    }
    
    private static void searchForMatches(Row row, String query, int category){
        try{
            String tmp = row.getCell(category).getStringCellValue();
            if (tmp.equals(query))
                System.out.println("Found it! Αt "+row.getRowNum());
        }catch(NullPointerException e){}
    }
}
