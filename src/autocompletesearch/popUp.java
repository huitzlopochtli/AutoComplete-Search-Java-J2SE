package autocompletesearch;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author PK
 */
public class popUp {
    public Button Ok;
    public void popUpWindow(String Title, String message){
        Stage popUpStage = new Stage();
        
        Label messageLable = new Label(message);
        Ok = new Button("OK");
        Ok.setOnAction(e -> close(popUpStage));
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(messageLable, Ok);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        
        popUpStage.setScene(scene);
        
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setTitle(Title);
        popUpStage.setResizable(false);
        popUpStage.setAlwaysOnTop(true);
        popUpStage.setMaxWidth(300);
        popUpStage.setMinWidth(200);
        popUpStage.showAndWait();
        popUpStage.setAlwaysOnTop(true);
    }

    private void close(Stage popUpStage) {
        popUpStage.close();
    }
}
