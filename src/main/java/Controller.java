import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField productName;

    @FXML
    private TextField manufactureName;

    @FXML
    private Button btnProduct;

    @FXML
    private Button btnRecord;

    @FXML
    void addProduct(ActionEvent event) {
        System.out.println("Some Product");

    }

    @FXML
    void recordProduction(ActionEvent event) {
        System.out.println("something Recorded");

    }

}