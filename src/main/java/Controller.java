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
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

/**
 * Controller.java - the controller class for the whole FX project. This class provides interaction
 * with all other classes with the users inputs and record them in the Database.
 *
 * @author Khubaib Farah
 */

public class Controller {

  /**
   * TextField used to enter products name
   */
  @FXML
  private TextField productName;

  /**
   * TextField used to enter products manufacturer
   */
  @FXML
  private TextField Manufacturer;

  /**
   * ChoiceBox to determine product type
   */
  @FXML
  private ChoiceBox<ItemType> comboItemType;

  /**
   * TableView displaying all information about the product entered
   */
  @FXML
  private TableView<Product> existingProdTable;

  /**
   * TableColumn Holds the ID number of the product
   */
  @FXML
  private TableColumn<?, ?> idTableColumn;

  /**
   * TableColumn nHolds the name of the product
   */
  @FXML
  private TableColumn<?, ?> nameTableColumn;

  /**
   * TableColumn Holds the manufacturer name of the product
   */
  @FXML
  private TableColumn<?, ?> manufactureTableColumn;

  /**
   * TableColumn Holds the type of item of the product
   */
  @FXML
  private TableColumn<?, ?> itemTypeTableColumn;

  /**
   * Displays a message to the user if there is an error with submitting the product
   */
  @FXML
  private Label productLineErrorLabel;

  /**
   * ListView that shows all products entered for choosing to record
   */
  @FXML
  private ListView<String> chooseProduct;

  /**
   * ComboBox displays the quantity of records the user wants to produce
   * Can also type in amount
   */
  @FXML
  private ComboBox<String> comboRecordProduction;

  /**
   * Label produces a message letting the user know the record was saved
   * when submitted.
   */
  @FXML
  private Label recordNumberLabel;

  /**
   * TextArea displays all recorded products in the field
   */
  @FXML
  private TextArea productionLog;

  /**
   * TextField to enter the employees first and last name
   */
  @FXML
  private TextField nameTextField;

  /**
   * TextField to enter the employees password
   */
  @FXML
  private TextField passwordTextField;

  /**
   * Label displays if the user has successfully created an account
   */
  @FXML
  private Label employeeLabel;

  /**
   * this is what will give every method access to connect to the database
   */
  private Connection conn;

  /**
   * Holds the available products
   */
  private final ArrayList<Product> productLine = new ArrayList<>();

  /**
   * Holds the recorded products
   */
  private final ArrayList<ProductionRecord> productionRecords = new ArrayList<>();

  /**
   * Holds the employees registered
   */
  private final ArrayList<Employee> employees = new ArrayList<>();

  /**
   * Displays the products entered in the TableView
   */
  private final ObservableList<Product> observableProductLine = FXCollections.observableArrayList();

  /**
   * Displays information of products in the ListView
   */
  private final ObservableList<String> observableProductStrings = FXCollections
      .observableArrayList();

  /**
   * Holds the last number for the ID used in the program
   */
  int lastId;

  /**
   * Contains the production number that will be used for the next registered product
   */
  private int currentProductionNumber;

  /**
   * this holds the info for the current employee logged in.
   */
  private Employee currentEmployee = new Employee("", "");

  /**
   * This method automatically runs once the window opens.
   * It populates the combo boxes with choices.
   * It also connects to the database to populate the arraylist with data
   * from the database.
   */
  @FXML
  public void initialize() {

    // add type of item
    for (ItemType itemType : ItemType.values()) {
      comboItemType.getItems().add(itemType);
    }
    // Add amount to record number of selected items
    for (int i = 1; i < 11; i++) {
      String number = "" + i;
      comboRecordProduction.getItems().add(number);
    }

    comboRecordProduction.setEditable(true); //make editing amount possible

    // Selecting the first item
    chooseProduct.getSelectionModel().selectFirst();
    comboRecordProduction.getSelectionModel().selectFirst();
    comboItemType.getSelectionModel().selectFirst();

    connectToDatabase();

    setupProductLineTable();// Setting up the product line table

    loadProductLine();// Load product line from database

    loadProductionLog();// Load production records from database

    loadEmployees();// Load the employees from database


  }

  /**
   * This method connects to the database and saves a field name that can be used
   * for other methods.
   */
  private void connectToDatabase() {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/HR";

    //Database credentials
    final String USER = "";
    final String PASS = "";
    Connection conn= null;
    Statement stmt = null;

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();

      String sql = "SELECT * FROM Product";

      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        System.out.println(rs.getString(1));
      }

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println();

    } catch (
        SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method runs once the "Add Product" button is clicked
   * and adds the information inputted into the database
   */
  @FXML
  public void productionLineButtonAction() {
    try {
      // Throwing an exception if one of the fields is left blank
      if (productName.getText().isEmpty() || Manufacturer.getText().isEmpty()) {
        throw new NullPointerException();
      }

      final String JDBC_DRIVER = "org.h2.Driver";
      final String DB_URL = "jdbc:h2:./res/HR";
      final String USER = "";
      final String PASS = "";

      Connection conn;

      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL);

      // Getting user input values
      String name = productName.getText();
      String manufacturer = Manufacturer.getText();
      ItemType item = comboItemType.getValue();
      lastId++; // Incrementing the last ID value

      // Creating product specified
      Widget product = new Widget(lastId, name, manufacturer, item);

      productLine.add(product);// Adding to products array list
      observableProductLine.add(product); // Displaying in table view
      observableProductStrings.add(product.toString());// Display in list view

      // Making a statement and running it
      PreparedStatement pstmt =
          conn.prepareStatement("INSERT INTO Product (TYPE, MANUFACTURER, NAME)"
              + " VALUES (?,?,?)");

      pstmt.setString(3, name);
      pstmt.setString(2, manufacturer);
      pstmt.setString(1, item.toString());
      pstmt.execute();
      pstmt.close();
      conn.close();

    } catch (NullPointerException n) {
      // Display message to user
      productLineErrorLabel.setText("Please complete all fields");
      productLineErrorLabel.setVisible(true);

      // Hiding label
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(6));
      visiblePause.setOnFinished(event -> productLineErrorLabel.setVisible(false));
      visiblePause.play();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method runs once the "Record Production" button is clicked
   * It takes in result from productionLineButtonAction
   * and saves it as a choice in the Product tab
   */
  @FXML
  public void recordProductionButtonAction() {
    String quantity = comboRecordProduction.getValue();
    System.out.println("record production button clicked");
    try {
      int numProduced = Integer.parseInt(quantity);
      System.out.println("Produced: " + numProduced);
      int productIndex = chooseProduct.getSelectionModel().getSelectedIndex();
      Widget productToProduce = (Widget) productLine.get(productIndex);
      ArrayList<ProductionRecord> productionRun = new ArrayList<>();
      for (int i = 0; i < numProduced; i++) {
        productionRun.add(
            new ProductionRecord(
                productToProduce, currentProductionNumber++, currentEmployee.getUsername()));
      }

      showProduction(productionRun);// Displaying the results

      addToProductionDB(productionRun);// Adding to database

      // Display message to user
      recordNumberLabel.setText("Production Recorded");
      recordNumberLabel.setVisible(true);

      // Hiding label
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
      visiblePause.setOnFinished(event -> recordNumberLabel.setVisible(false));
      visiblePause.play();

    } catch (NumberFormatException ex) {
      System.out.println("Please enter only numbers");

      // Displaying error label
      recordNumberLabel.setText("Please enter only whole numbers");
      recordNumberLabel.setVisible(true);

      // Hiding label
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(6));
      visiblePause.setOnFinished(event -> recordNumberLabel.setVisible(false));
      visiblePause.play();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Method runs when the "Log in" button is clicked
   * sets the current employee logging in as the current user of the program
   */
  public void loginButtonAction() {
    // Boolean to keep track of if the login was successful
    boolean successfulLogin = false;

    // Getting the full name and password entered by the user
    String fullName = nameTextField.getText();
    String password = passwordTextField.getText();

    // Saving the results of the login attempt
    Employee employeeToLogin = new Employee(fullName, password);
    // Setting currentEmployee as a new Employee created
    for (Employee employee : employees) {
      System.out.println(employee.getUsername());
      if (employee.getUsername().equals(employeeToLogin.getUsername())
          && employee.getPassword().equals(employeeToLogin.getPassword())) {
        // Setting the employee entered as the current employee
        currentEmployee = employeeToLogin;
        successfulLogin = true;
        // Display message to user
        employeeLabel.setText("Signed in as " + currentEmployee.getName());
        employeeLabel.setVisible(true);
        // Hiding label
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
        visiblePause.setOnFinished(event -> employeeLabel.setVisible(false));
        visiblePause.play();
        break;
      }
    }
    if (!successfulLogin) {
      // Display message to user
      employeeLabel.setText("Incorrect name and/or password");
      employeeLabel.setVisible(true);
      // Hiding label
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(6));
      visiblePause.setOnFinished(event -> employeeLabel.setVisible(false));
      visiblePause.play();
    }
  }

  /**
   * This method runs when the "Create User" button is clicked
   * This verifies the user that the info inputted is correct and displays
   * a message of confirmation that the employee account has been created and logged in.
   */
  public void createAccountButtonAction() {
    // Getting full name and password entered by the user
    String fullName = nameTextField.getText();
    String password = passwordTextField.getText();

    // Creating a new Employee object
    Employee newEmployee = new Employee(fullName, password);

    // Checking if their username or password is rejected
    if (newEmployee.getUsername().equals("default")) {
      // Display message to user
      employeeLabel.setText("Please enter your first and last name separated by a space");
      employeeLabel.setVisible(true);
      // Hiding label
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(6));
      visiblePause.setOnFinished(event -> employeeLabel.setVisible(false));
      visiblePause.play();

    } else if (newEmployee.getPassword().equals("pw")) {
      // Display message to user
      employeeLabel.setText(
          "Please use an uppercase letter, lowercase letter, and special character in your "
              + "password");
      employeeLabel.setVisible(true);
      // Hiding label
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(6));
      visiblePause.setOnFinished(event -> employeeLabel.setVisible(false));
      visiblePause.play();

    } else {
      // Adding to the employees ArrayList
      employees.add(newEmployee);
      // Setting the newly created account as the current account
      currentEmployee = newEmployee;
      // Adding the account to the database
      addEmployeeToDatabase(newEmployee);

      // Display message to user
      employeeLabel.setText("Account Created. Signed in as " + currentEmployee.getName());
      employeeLabel.setVisible(true);
      // Hiding label
      PauseTransition visiblePause = new PauseTransition(Duration.seconds(6));
      visiblePause.setOnFinished(event -> employeeLabel.setVisible(false));
      visiblePause.play();
    }
  }



  /**
   * This method uses the database to get the products that were submitted
   * and adds them to the production line list.
   */
  private void loadProductLine() {
    try {
      // Setting lastId to zero
      lastId = 0;

      // Making a statement
      // Inspect code says possible null pointer here but is already in a try block
      Statement stmt = conn.createStatement();

      // Executing query and collecting results
      ResultSet rs = stmt.executeQuery("SELECT * FROM Product");

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
        Widget product = new Widget(id, name, manufacturer, item);

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

  /**
   * This method uses the database to get information from the recorded product
   * in the Production line tab and created objects in the product tab list.
   */
  private void loadProductionLog() {
    try {
      // Making a statement
      Statement stmt = conn.createStatement();

      // Executing query and collecting results
      ResultSet rs = stmt.executeQuery("SELECT * FROM ProductionRecord");

      // Resetting the currentProductionNumber
      currentProductionNumber = 1;
      // Looping through results
      while (rs.next()) {
        // Adding 1 to current production number
        currentProductionNumber++;
        // Storing data into variables
        int productNum = rs.getInt(1);
        int productId = rs.getInt(2);
        String serialNum = rs.getString(3);
        Timestamp dateProduced = rs.getTimestamp(4);
        String creator = rs.getString(5);

        // Creating a production record from the database values
        ProductionRecord productionRecord =
            new ProductionRecord(productNum, productId, serialNum, dateProduced, creator);

        // Adding to the ArrayList
        productionRecords.add(productionRecord);
      }

      // Displaying to the text area
      showProduction(productionRecords);

      // Closing statement
      stmt.close();

    } catch (NullPointerException npe) {
      System.out.println("Null Pointer");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method loads the arraylist with the created data from the Employee
   * table.
   */
  private void loadEmployees() {
    try {
      // Making a statement
      // Inspect code says possible null pointer here but is already in a try block
      Statement stmt = conn.createStatement();

      // Executing query and collecting results
      ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");

      // Looping through results
      while (rs.next()) {

        // Storing data into variables
        String fullName = rs.getString(1);
        String password = rs.getString(2);

        // Creating a product from the database values
        Employee employee = new Employee(fullName, password);

        // Adding to the ArrayList
        employees.add(employee);
      }

      // Closing statement
      stmt.close();

    } catch (NullPointerException npe) {
      System.out.println("Null Pointer");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method takes in username and password of the employee and
   * adds them to the EMPLOYEE table,
   *
   * @param employee the object with details that have to be added.
   */
  private void addEmployeeToDatabase(Employee employee) {
    try {

      final String JDBC_DRIVER = "org.h2.Driver";
      final String DB_URL = "jdbc:h2:./res/HR";
      final String USER = "";
      final String PASS = "";

      Connection conn;

      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL);

      // Making a statement and running it
      PreparedStatement pstmt =
          conn.prepareStatement("INSERT INTO EMPLOYEE (FULL_NAME, PASSWORD)" + "VALUES (?,?)");

      pstmt.setString(1, employee.getName().toString());
      pstmt.setString(2, employee.getPassword());
      pstmt.execute();
      pstmt.close();
      conn.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method adds a list of recorded products to the PRODUCTIONRECORD database table.
   */
  private void addToProductionDB(ArrayList<ProductionRecord> productionRun) {
    try {
      for (ProductionRecord record : productionRun) {
        // Making a statement and running it

        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./res/HR";
        final String USER = "";
        final String PASS = "";

        Connection conn;

        // STEP 1: Register JDBC driver
        Class.forName(JDBC_DRIVER);

        //STEP 2: Open a connection
        conn = DriverManager.getConnection(DB_URL);

        PreparedStatement prepStat =
            conn.prepareStatement(
                "INSERT INTO ProductionRecord (PRODUCTION_NUM,PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED)"
                    + "VALUES (?,?,?,?)");

        prepStat.setInt(1, record.getProductID());
        prepStat.setString(2, record.getSerialNum());
        prepStat.setTimestamp(3, new Timestamp(record.getProdDate().getTime()));
        prepStat.setString(4, record.getCreator());
        prepStat.execute();
        prepStat.close();
        conn.close();
      }
    } catch (NullPointerException npe) {
      System.out.println("Please complete all fields");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("ERROR");
    }
  }



  /**
   * This method takes the existing products table and the recorded product list
   * and set them up.
   */
  private void setupProductLineTable() {
    idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    manufactureTableColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    itemTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

    // Adding items to the existingProducts table view
    existingProdTable.setItems(observableProductLine);

    // Adding that array list to the choose product list view
    chooseProduct.setItems(observableProductStrings);
  }

  /**
   * This method shows the production log and its text area
   */
  private void showProduction(ArrayList<ProductionRecord> productionRun) {
    for (ProductionRecord record : productionRun) {
      productionLog.appendText(record.toString() + "\n");
    }
  }

  /**
   * This methods purpose is to take in the password that is passed in and and
   * returns the results.
   */
  private String reversePassword(String password) {
    // Getting last character of the string
    String lastChar = password.substring(password.length() - 1);

    // Checking if there is more than one character in the string
    if (password.length() == 1) {
      // Returning the only remaining character
      return lastChar;
    } else {
      // Getting all but the last character of the string
      String allButLast = password.substring(0, password.length() - 1);
      // Returning the last character plus the result of this method called with allButLast
      return lastChar + reversePassword(allButLast);
    }
  }

}


