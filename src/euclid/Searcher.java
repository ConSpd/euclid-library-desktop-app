package euclid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CachingTokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.el.GreekAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

public class Searcher {
    private static FileInputStream libraryFile;
    private static XSSFWorkbook workbookXSSF;
    private static HSSFWorkbook workbookHSSF;
    private static XSSFSheet sheetXSSF;
    private static HSSFSheet sheetHSSF;
    
    private static Analyzer analyzer;
    private static CachingTokenFilter queryCache;
    private static CharTermAttribute queryTermAttribute;
    
    public static void prepareCsvFile(){
        try{
//            Alert alert = new Alert(AlertType.NONE, "Preparing File", ButtonType.CLOSE);
//            alert.showAndWait();
            BufferedReader br = new BufferedReader(new FileReader("library_path.txt"));
            String libraryFileName = br.readLine();
            libraryFile = new FileInputStream(new File(libraryFileName));
            
            
            // Checking if file is xlsx or xls or something else in order to use XSSF or HSSF
            Pattern pattern = Pattern.compile("\\.xlsx$",Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(libraryFileName);
            boolean matchFound = matcher.find();
            
            // Open file and get first sheet
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
        // Checking the language of the query
        Pattern pattern = Pattern.compile("[a-zA-Z]",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(query);
        boolean matchFound = matcher.find();
        if(matchFound)
            analyzer = new EnglishAnalyzer();
        else
            analyzer = new GreekAnalyzer();
        
        TokenStream queryStream = analyzer.tokenStream(null, new StringReader(query));
        queryCache = new CachingTokenFilter(queryStream);
        
        queryTermAttribute = queryCache.addAttribute(CharTermAttribute.class);
        
        
        // Checking the category entered
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

//        System.out.println("Category="+categ);
        if (workbookXSSF != null)
            for (Row row : sheetXSSF)
                searchForMatches(row, query, categ);
        else
            for (Row row : sheetHSSF)
                searchForMatches(row, query, categ);
        
        try{
            queryCache.close();
            queryStream.close();
            analyzer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static void searchForMatches(Row row, String query, int category){
        try{
            DataFormatter formatter = new DataFormatter();
            
//            String cellString = row.getCell(category).getStringCellValue();
            String cellString = formatter.formatCellValue(row.getCell(category));
            Analyzer cellAnalyzer = new GreekAnalyzer();
            
            TokenStream cellStream = cellAnalyzer.tokenStream(null, new StringReader(cellString));
            CharTermAttribute cellTermAttribute = cellStream.addAttribute(CharTermAttribute.class);
            
            try{
                cellStream.reset();
                while (cellStream.incrementToken()) {
                    queryCache.reset();
                    while (queryCache.incrementToken()){
                        String queryTerm = queryTermAttribute.toString();
                        String cellTerm = cellTermAttribute.toString();
                        if (queryTerm.equals(cellTerm)){
                            Results result = addToResults(row);
                            System.out.println(result.toString());
                        }
                    }
                }
                cellStream.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }catch(NullPointerException e){}
    }
    
    private static Results addToResults(Row row){
        DataFormatter formatter = new DataFormatter();
        int number = row.getRowNum()+1;
        String name = formatter.formatCellValue(row.getCell(2));
        String author = formatter.formatCellValue(row.getCell(3));
        String publisher = formatter.formatCellValue(row.getCell(4));
        String year = formatter.formatCellValue(row.getCell(5));
        Results result = new Results.ResultsBuilder()
                                    .number(number)
                                    .name(name)
                                    .author(author)
                                    .publisher(publisher)
                                    .year(year)
                                    .category("Null")
                                    .build();
        return result;
    }
}


