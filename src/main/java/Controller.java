/****************************************************************************************************************
 *  Author:KFarah
 *  File:Controller.java
 *  Date:9/19/2020
 *  Purpose:Allow the user to input products name, type, and manufacturer into the database
 *          on products made.
 ****************************************************************************************************************/


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

  @FXML
  private TextField productName;
  @FXML
  private TextField Manufacturer;
  @FXML
  private ChoiceBox<String> comboItemType;
  @FXML
  private Button btnAddProduct;
  @FXML
  private TableView<Product> existingProdTable;
  @FXML
  private TableColumn<?, ?> nameTableColumn;
  @FXML
  private TableColumn<?, ?> manufactureTableColumn;
  @FXML
  private TableColumn<?, ?> itemTypeTableColumn;
  @FXML
  private Label lblProductInfo;
  @FXML
  private ListView<String> chooseProduct;
  @FXML
  private ComboBox<String> comboRecordProduction;
  @FXML
  private Button btnRecord;
  @FXML
  private TextArea productionLog;

  private Connection conn;

  public static ObservableList<Product> productLine = FXCollections.observableArrayList();

  private final ObservableList<Product> observableProductLine = FXCollections.observableArrayList();

  private final ObservableList<String> observableProductStrings =
      FXCollections.observableArrayList();

  @FXML
  public void showDetail(ActionEvent event) {
    connectToDatabase();
  }

  @FXML
  void addProduct(ActionEvent event) {
    connectToDatabase();
  }

  @FXML
  void recordProduction(ActionEvent event) {
    connectToDatabase();
  }


  public void initialize() {

    connectToDatabase();

    loadProductLine();

    setupProductLineTable();

    for (int i = 1; i < 11; i++) {
      String number = "" + i;
      comboRecordProduction.getItems().add(number);
    }
    for (ItemType IT : ItemType.values()) {
      comboItemType.getItems().add(String.valueOf(IT));
    }

  }
//============================================================================================================

  private void loadProductLine() {
    try {
      // Setting lastId to zero
      int lastId = 0;

      Statement stmt = conn.createStatement();

      // Executing query and collecting results
      ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");

      // Looping through results
      while (rs.next()) {
        // Incrementing lastId
        lastId++;
        // Storing data into variables
        int id = rs.getInt(1);
        String name = rs.getString(2);
        String type = rs.getString(3);
        String manufacturer = rs.getString(4);
        // Getting the proper item type from the code
        ItemType item;
        switch (type) {
          case "AM":
            item = ItemType.AUDIOMOBILE;
            break;
          case "AU":
            item = ItemType.AUDIO;
            break;
          case "VI":
            item = ItemType.VISUAL;
            break;
          case "VM":
            item = ItemType.VISUAL_MOBILE;
            break;
          default:
            item = null;
        }
        // Creating product objects from the database information
        Widget product = new Widget(name, manufacturer, item);
        // Adding those objects to the array list and observableLists
        productLine.add(product);
        observableProductLine.add(product);
        observableProductStrings.add(product.toString());
      }
      // Closing statement
      stmt.close();
    } catch (NullPointerException npe) {
      System.out.println("Null Pointer");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
//==========================================================================================================

  private void setupProductLineTable() {
    ObservableList<Product> data = populateList();
    nameTableColumn.setCellValueFactory(new PropertyValueFactory("Name"));
    manufactureTableColumn.setCellValueFactory(new PropertyValueFactory("Manufacturer"));
    itemTypeTableColumn.setCellValueFactory(new PropertyValueFactory("Type"));
    existingProdTable.setItems(data);

    // Adding items to the existingProducts table view
    existingProdTable.setItems(observableProductLine);

    // Adding that array list to the choose product list view
    chooseProduct.setItems(observableProductStrings);
  }

  public static ObservableList<Product>populateList(){
    return FXCollections.observableArrayList();
  }

  public void connectToDatabase() {

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

      String insertSql = "INSERT INTO Product(name, manufacturer, type) "
          + "VALUES ( ?, ?, ? )";

      PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
      preparedStatement.setString(1, productName.getText());
      preparedStatement.setString(2, Manufacturer.getText());
      preparedStatement.setString(3, ItemType.AUDIO.label);

      preparedStatement.executeUpdate();

      String sql = "select * FROM Product";

      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {

        System.out.println(rs.getString(1));
        System.out.println(rs.getString(2));
        System.out.println(rs.getString(3));
        System.out.println(rs.getString(4));
      }

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}


