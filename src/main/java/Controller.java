import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.text.AbstractDocument;
import java.sql.*;

public class Controller {

    @FXML
    private TextField productName;

    @FXML
    private TextField manufactureName;

    @FXML
    private ChoiceBox<String> comboItemType;

    @FXML
    private ComboBox<String> comboRecordProduction;

    @FXML
    private TableView<AbstractDocument.Content> tableProduct;

    @FXML
    private TableColumn<AbstractDocument.Content, String> nameTableColumn;

    @FXML
    private TableColumn<AbstractDocument.Content, String> manufactureTableColumn;

    @FXML
    private TableColumn<AbstractDocument.Content, String> itemTypeTableColumn;

    @FXML
    private Label lblProductInfo;

    @FXML
    private Button btnProduct;

    @FXML
    private Button btnRecord;

    @FXML
    void showDetail(ActionEvent event) {
        connectToDatabase();
    }

    @FXML
    void addProduct(ActionEvent event) {
        System.out.println("Galaxy S20");

    }

    @FXML
    void recordProduction(ActionEvent event) {
        System.out.println("Another Galaxy S20");

    }

    public void initialize() {

        for (int count = 1; count <= 10; count++) {
            comboRecordProduction.getItems().add(String.valueOf(count));
        }

    }


    private void connectToDatabase() {

        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./res/HR";

        //  Database credentials
        final String USER = "";
        final String PASS = "";
        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            stmt = conn.createStatement();

            String nameOfProduct = productName.getText();
            String nameOfManufacturer = manufactureName.getText();

            String insertSql = "INSERT INTO Product(type, manufacturer, name) "
                    + "VALUES ( 'AUDIO', 'Apple', 'iPod' )";

            stmt.executeUpdate(insertSql);

            String sql = "SELECT type, maufacturer, name " +
                    "FROM PRODUCT where name=" + nameOfProduct +
                    "type=" + nameOfManufacturer;

            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            String empName = rs.getString(1);
            String empManufacturer = rs.getString(2);
            String empItemType = rs.getString(3);
            //System.out.println(empName+ " " + empManufacturer + " " + empItemType);

            lblProductInfo.setText(empName + " " + empManufacturer + " " + empItemType);

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



