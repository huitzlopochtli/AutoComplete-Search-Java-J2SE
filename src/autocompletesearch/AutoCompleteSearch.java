package autocompletesearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author PK
 */
public class AutoCompleteSearch extends Application {
    public AutoCompleteTextField textBox;
    SortedSet set;
    Button search;
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        set = new TreeSet(); 
        this.textBox = new AutoCompleteTextField(set);
        searchDatabase();
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));
        
        
        //Top
        HBox hBoxBorderTop = new HBox(10);
        borderPane.setTop(hBoxBorderTop);
        hBoxBorderTop.getChildren().add(textBox);
        
        search = new Button("Search and Go!");
        hBoxBorderTop.getChildren().add(search);
        hBoxBorderTop.setAlignment(Pos.CENTER);
        
        
        
        search.setOnAction((ActionEvent e) -> {
            try {
                searchButtonClicked();
            } catch (UnknownHostException | FileNotFoundException ex) {
                Logger.getLogger(AutoCompleteSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        Scene scene = new Scene(borderPane, 360, 400);
        
        primaryStage.setTitle("Crime Syndicate!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    private void searchDatabase(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("CrimeList.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    // printing out each line in the file
                    set.add(x);
                } 
            } catch (IOException e) {
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
    
    private void searchButtonClicked() throws UnknownHostException, FileNotFoundException{
        InetAddress ip = InetAddress.getLocalHost();
        String justIP = ip.getHostAddress();
        System.out.println(justIP);
        popUp popUp1 = new popUp();
        if(!textBox.getText().isEmpty()){
            popUp1.popUpWindow("Report Submitted", textBox.getText() + " Report Submitted from Banani, Dhaka, Bangladesh(" + justIP + ")");
            if(entryNotFound())
                writeFile();
        }
    }
    void writeFile(){
        try(FileWriter fw = new FileWriter("CrimeList.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)){
            out.print("\n"+textBox.getText());
        } catch (IOException e) {
           System.out.print(e);
        }
    }
    
    boolean entryNotFound() throws FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader("CrimeList.txt"));
            try {
                String x;
                while ( (x = br.readLine()) != null ) {
                    if(textBox.getText().equals(x))
                        return false;
                } 
            } catch (IOException e) {
            }
            return true;
    }
}
