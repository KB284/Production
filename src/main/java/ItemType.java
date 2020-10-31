public enum ItemType {

  AUDIO("AU"), VISUAL("VI"), AUDIOMOBILE("AM"), VISUAL_MOBILE("VM");

  public final String label;

  ItemType(String c) {
    this.label = c;
  }
}
