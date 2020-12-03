/**
 * Item.java- This interface is used for other classes
 *            to make sure that they all have the necessary
 *            methods.
 *
 * @author Khubaib Farah
 */
interface Item {

  int getId();

  void setName(String name);

  String getName();

  void setManufacturer(String manufacturer);

  String getManufacturer();

}
