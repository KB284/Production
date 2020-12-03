/**
 * Product.java- class used to make methods that will be used for other
 *               classes that inherit it. Contains all the information
 *               and functionality a product will have.
 *
 * @author Khubaib Farah
 */
public abstract class Product implements Item {

  /**
   * This field holds an id for each product
   */
  public int id;

  /**
   * This field holds the name for the products.
   */
  public String name;

  /**
   * This field holds the manufacturer name that made the product.
   */
  public String manufacturer;

  /**
   * This field holds the item type of the product.
   */
  public ItemType type;

  /**
   * Constructor for the class that takes in three argument parameters.
   *
   * @param name name of the product
   * @param manufacturer manufacturer that made the product
   * @param type the type of product
   */
  Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * Constructor for the class that takes in four argument parameters.
   *
   * @param id the id number for the product.
   * @param name name of the product
   * @param manufacturer manufacturer that made the product
   * @param type the type of product
   */
  Product(int id, String name, String manufacturer, ItemType type) {
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }


  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  @Override
  public String getManufacturer() {
    return manufacturer;
  }

  public void setType(ItemType type) {
    this.type = type;
  }

  public ItemType getType() {
    return type;
  }

  public String toString() {
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
  }
}