package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
//import vue.VBoxMenu;
public class Fenetre extends Application {

    public void start(Stage stage) throws IOException {
        VBoxMenu root = new VBoxMenu();
        File css = new File("css"+ File.separator+"style.css");
        Scene scene = new Scene(root, 400, 380);
        scene.getStylesheets().add(css.toURI().toString());
        stage.setScene(scene);
        stage.setTitle("APLI");
        stage.setWidth(600);
        stage.setHeight(700);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
