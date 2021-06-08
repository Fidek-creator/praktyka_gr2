package application;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Zgloszenia implements Initializable {
    public ImageView img_menugl;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //wczytanie obrazu
        File plik = new File("src/images/domek.png");
        Image zdjecie = new Image(plik.toURI().toString());
        img_menugl.setImage(zdjecie);
    }
    public void img_menugl_M(MouseEvent mouseEvent) throws Exception {
        ZmienOkno.zmienSceneimg("menugl.fxml", 1080, 540, img_menugl);
    }
}
