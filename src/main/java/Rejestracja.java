import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;


public class Rejestracja implements Initializable, Serializable {


//deklaracja labelów, przycisków itp. z Scen Builder'a

    @FXML
    private ImageView rejestracja_logo;
    @FXML
    private TextField rejestracja_imie, rejestracja_nazwisko, rejestracja_pesel, rejestracja_login, rejestracja_email;
    @FXML
    private PasswordField rejestracja_haslo, rejestracja_haslo2;
    @FXML
    private Button btn_zarejestruj, btn_mjk, btn_wyczysc, btn_zamknij;


    // zmienna przechowywujaca informacje ktore pola nie spelniaja wymagan
    private String blad = "";

    private static final EmailValidator validator = EmailValidator.getInstance();

//METODY

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File plik = new File("src/images/logobiale.png");
        Image zdjecie = new Image(plik.toURI().toString());
        rejestracja_logo.setImage(zdjecie);

    }

    // metoda czyszczaca wszystkie pola
    public void wyczyscButton() {
        rejestracja_imie.setText("");
        rejestracja_nazwisko.setText("");
        rejestracja_pesel.setText("");
        rejestracja_login.setText("");
        rejestracja_haslo.setText("");
        rejestracja_haslo2.setText("");
        rejestracja_email.setText("");

    }

    //metoda zamykajaca aplikacje
    public void zamknijButton(ActionEvent actionEvent) {
        Platform.exit();
    }

    //metoda sprawdzajaca i dopisujaca do zmiennej pola ktore sa niepoprawne
    public void sprawdzRejestracja() {
        //zmienna jest czyszczona
        blad = "\n";

        // pole imie
        if (rejestracja_imie.getLength() < 3 || rejestracja_imie.getLength() > 20) {
            blad += "Imie \n";
        }

        // pole nazwisko
        if (rejestracja_nazwisko.getLength() < 3 || rejestracja_nazwisko.getLength() > 20) {
            blad += "Nazwisko \n";
        }


        if (weryfikujPesel(rejestracja_pesel.getText()) == false) {
            blad += "Pesel \n";
        }

        // pole login
        if (rejestracja_login.getLength() < 5 || rejestracja_login.getLength() > 20) {
            blad += "Login \n";
        }

        // pole haslo
        if (rejestracja_haslo.getLength() < 5 || rejestracja_haslo.getLength() > 20 ||
                rejestracja_haslo2.getLength() < 5 || rejestracja_haslo2.getLength() > 20) {
            blad += "Haslo \n";

        }
        //sprawdzenie czy hasla sa identyczne
        if (!rejestracja_haslo.getText().equals(rejestracja_haslo2.getText())) {

            blad += "Hasla nie sa identyczne \n";
        }

        // pole email
        if (weryfikujEmail(rejestracja_email.getText()) == false) {
            blad += "Email\n";
        }

    }

    public void komunikat() {
        //sprawdzenie czy zmienna przechowywujace nieprawidlowe pola jest pusta

        if (blad.isBlank()) {//SUKCES
            Alert sukces = new Alert(Alert.AlertType.INFORMATION);
            sukces.setTitle("Powiadomienie");
            sukces.setHeaderText(null);
            sukces.setContentText("Rejestracja zakonczona sukcesem!");

            sukces.showAndWait();
        } else {//PORAZKA
            Alert porazka = new Alert(Alert.AlertType.ERROR);
            porazka.setTitle("Powiadomienie");
            porazka.setHeaderText(null);
            porazka.setContentText("Rejestracja zakonczaona niepowodzeniem.\nPopraw nastepujace pola: " + blad);

            porazka.showAndWait();
        }

    }

    // metoda weryfikujaca pesel
    public boolean weryfikujPesel(String pesel) {
        if (pesel.length() == 11) {

            int pierwszaCyfra = Integer.parseInt(pesel.substring(0, 1));
            int drugaCyfra = Integer.parseInt(pesel.substring(1, 2));
            int trzeciaCyfra = Integer.parseInt(pesel.substring(2, 3));
            int czwartaCyfra = Integer.parseInt(pesel.substring(3, 4));
            int piataCyfra = Integer.parseInt(pesel.substring(4, 5));
            int szostaCyfra = Integer.parseInt(pesel.substring(5, 6));
            int siodmaCyfra = Integer.parseInt(pesel.substring(6, 7));
            int osmaCyfra = Integer.parseInt(pesel.substring(7, 8));
            int dziewiataCyfra = Integer.parseInt(pesel.substring(8, 9));
            int dziesiataCyfra = Integer.parseInt(pesel.substring(9, 10));
            int jedenastaCyfra = Integer.parseInt(pesel.substring(10, 11));

            int check = 1 * pierwszaCyfra + 3 * drugaCyfra + 7 * trzeciaCyfra
                    + 9 * czwartaCyfra + 1 * piataCyfra + 3 * szostaCyfra + 7
                    * siodmaCyfra + 9 * osmaCyfra + 1 * dziewiataCyfra + 3
                    * dziesiataCyfra;
            int lastNumber = check % 10;
            int controlNumber = 10 - lastNumber;

            if (controlNumber == jedenastaCyfra) {
                return true;
            }

        }
        return false;
    }

//metoda weryfikujaca email

    public static boolean weryfikujEmail(final String email) {
        return validator.isValid(email);
    }

    public void zarejestrujButton(ActionEvent actionEvent) {
        sprawdzRejestracja();
        komunikat();

    }
}