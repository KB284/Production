import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ProductionRecord.java- This class represents how data of the product is recorded
 *
 * @author Khuabib Farah
 */
public class ProductionRecord {

  /**
   * This field holds a specific number for every product recorded.
   */
  int productionNumber;

  /**
   * this field holds the ID of all products created.
   */
  private int productID;

  /**
   * this field contains a serial number for any products made.
   */
  private String serialNumber;

  /**
   * this field holds the format for dates of when products are made.
   */
  private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

  /**
   * this field holds the date of when products are made.
   */
  private Date dateProduced = new Date();

  /**
   * this field holds the name of the employee who made the product.
   */
  private String creator;

  /**
   * This constructor for the class takes in an argument for the productId.
   *
   * @param productID special code number for the product.
   */
  public ProductionRecord(int productID) {
    this.productID = productID;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
    dateFormat.format(dateProduced);
  }

  /**
   * This constructor for the class takes in four argument parameters.
   *
   * @param productionNumber number for total products made.
   * @param productID special code number for the product.
   * @param serialNumber special code number for the product.
   * @param dateProduced the date the product was made.
   */
  public ProductionRecord(
      int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productID = productID;
    this.productionNumber = productionNumber;
    this.serialNumber = serialNumber;
    dateProduced = new Date();
    this.dateProduced = dateProduced;
  }

  /**
   * This constructor for the class takes in five argument parameters.
   *
   * @param productNum number for total products made.
   * @param productId special code number for the product.
   * @param serialNum special code number for the product.
   * @param dateProduced the date the product was made.
   * @param creator the name of the employee who made the product.
   */
  public ProductionRecord(int productNum, int productId, String serialNum, Timestamp dateProduced,
      String creator) {

  }

  String getCreator() {
    return creator;
  }

  public int getProductionNum() {
    return productionNumber;
  }

  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public int getProductID() {
    return productID;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public String getSerialNum() {
    return serialNumber;
  }

  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public DateFormat getDateFormat() {
    return dateFormat;
  }

  public void setDateFormat(DateFormat dateFormat) {
    this.dateFormat = dateFormat;
  }

  public Date getProdDate() {
    return dateProduced;
  }

  public void setProdDate(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  public ProductionRecord(Product P, int itemCount, String username) {
    serialNumber = P.manufacturer.substring(0, 3) + P.type.label + "0000" + itemCount;
  }

  /**
   * This method returns a string with information on the product produced.
   *
   * @return String with productionProduct, productID, serialNumber, and dateProduced.
   */
  public String toString() {
    return "Prod. Num: " + productionNumber
        + " Product ID: " + productID
        + " Serial Num: " + serialNumber
        + " Date: " + dateProduced;
  }
}