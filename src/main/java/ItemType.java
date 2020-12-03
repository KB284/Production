/**
 * ItemType.java - This enumerator is used to show the possible
 *                 types of items.
 */
public enum ItemType {

  AUDIO("AU"), VISUAL("VI"), AUDIOMOBILE("AM"), VISUAL_MOBILE("VM");

  public final String label;

  ItemType(String c) {
    this.label = c;
  }
}
