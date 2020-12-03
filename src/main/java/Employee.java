import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Employee.java- This class is used to simulate an employee.
 *                Includes full name and password integration.
 *
 * @author Khubaib Farah
 */
class Employee {

  /**
   * This holds the name of the employee with a space between first and
   * last name.
   */
  private StringBuilder name;

  /**
   * This holds the username created by the employee
   * using the first initial of their first name and full last name.
   */
  private String username;

  /**
   * This holds the password.
   */
  private String password;

  /**
   * This holds the employees email password.
   */
  private String email;

  /**
   * This constructor for the class holds two parameters.
   *
   * @param name Full name entered by employee
   * @param password password entered by employee
   */
  Employee(String name, String password) {
    // Changing the String to a StringBuilder
    StringBuilder builderName = new StringBuilder(name);

    // Setting name
    this.name = builderName;

    // Checking for valid name
    if (checkName(builderName)) {
      // If the name is valid setting username and email
      setUsername(builderName);
      setEmail(builderName);
    } else {
      // If the name is not valid setting to default values
      username = "default";
      email = "user@oracleacademy.Test";
    }

    // Checking for valid password
    if (isValidPassword(password)) {
      // If password is valid setting it to password
      this.password = password;
    } else {
      // setting to default
      this.password = "pw";
    }
  }

  /**
   * This method creates an email using the name inputted
   * into the system by the employee.
   *
   * @param name Full name entered by employee
   */
  private void setEmail(StringBuilder name) {
    // Setting up regex to get first and last names
    final String regex = "[a-zA-Z]+";
    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Matcher matcher = pattern.matcher(name);
    // Saving first and last name
    String firstName = "";
    String lastName = "";
    if (matcher.find()) {
      firstName = matcher.group(0);
    }
    if (matcher.find()) {
      lastName = matcher.group(0);
    }
    // Creating email
    email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@oracleacademy.Test";
  }

  /**
   * This method uses the StringBuilder and creates a username using
   * their first initial and last name and sets it in lowercase
   *
   * @param name Full name entered by employee
   */
  private void setUsername(StringBuilder name) {
    // Setting up regex to get first and last names
    final String regex = "[a-zA-Z]+";
    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Matcher matcher = pattern.matcher(name);
    // Getting first and last name
    String firstName = "";
    String lastName = "";
    if (matcher.find()) {
      firstName = matcher.group(0);
    }
    if (matcher.find()) {
      lastName = matcher.group(0);
    }
    // Creating username
    username = firstName.toLowerCase().charAt(0) + lastName.toLowerCase();
  }

  /**
   * This method checks for the user to see if they have an uppercase,
   * lowercase, and a special character or symbol.
   *
   * @param password password entered by the user
   * @return Boolean to determine if that password is good or not
   */
  private boolean isValidPassword(String password) {
    final String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]*$";

    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Matcher matcher = pattern.matcher(password);

    // If the password has a lowercase letter, uppercase letter, and symbol
    return matcher.find();
  }

  /**
   * This method is used in the Employee constructor to check if
   * there is a space between first and last name. Will return false
   * if there is no space.
   *
   * @param name name Full name entered by employee
   * @return checks if the name has a space or not
   */
  private boolean checkName(StringBuilder name) {
    final String regex = "\\s";
    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Matcher matcher = pattern.matcher(name);

    // If there is a space return true else return false
    return matcher.find();
  }

  /**
   * This is a getter for the name field.
   *
   * @return name of employee
   */
  public StringBuilder getName() {
    return name;
  }

  /**
   * This is a getter for the password field.
   *
   * @return password
   */
  public String getPassword() {
    return password;
  }

  /**
   * this is a getter for username field
   *
   * @return username
   */
  String getUsername() {
    return username;
  }

  /**
   * this creates a string that holds the information about
   * the employee.
   *
   * @return a string with employee information.
   */
  public String toString() {
    return "Employee Details\nName : " + name
        + "\nUsername : " + username
        + "\nEmail : " + email
        + "\nInitial Password : " + password;
  }
}
