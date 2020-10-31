public abstract class Product implements Item {

  public int id;
  public String name;
  public String manufacturer;
  public ItemType type;


  Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

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

  public String getType() {
    return String.valueOf(type);
  }

  public String toString() {
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type.label;
  }

  public abstract void setType(String type);
}

class Widget extends Product implements Item {

  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }

  @Override
  public String getType() {
    return null;
  }

  @Override
  public void setType(String type) {

  }
}