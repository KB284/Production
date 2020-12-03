/**
 * Widget.Java- This class is a subclass used to make objects for
 *              the Product class.
 *
 * @author Khubaib Farah
 */
class Widget extends Product implements Item {

  /**
   * This constructor takes in four argument parameters.
   *
   * @param id ID for the product
   * @param name name of the product
   * @param manufacturer manufacturer for the product
   * @param type type of product
   */
  Widget(int id, String name, String manufacturer, ItemType type) {
    super(id, name, manufacturer, type);
  }
}
