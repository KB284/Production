/**
 * MoviePlayer.java- This class represents movie players that can be made
 *                   in the factory.
 *
 * @author Khubaib Farah
 */
public class MoviePlayer extends Product implements MultimediaControl {

  /**
   * This field holds the type of monitor for the movie player.
   */
  private MonitorType monitorType;

  /**
   * this field holds the screen the player contains.
   */
  private Screen screen;

  /**
   * This constructor for the class takes in four argument parameters.
   *
   * @param name name for the movie player
   * @param manufacturer the manufacturer for the movie player
   * @param screen class with information of the movie player
   * @param monitorType type of screen the movie player has
   */
  MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.monitorType = monitorType;
    this.screen = screen;
  }

  public void play() {
    System.out.println("Playing movie");
  }

  public void stop() {
    System.out.println("Stopping movie");
  }

  public void previous() {
    System.out.println("Previous movie");
  }

  public void next() {
    System.out.println("Next movie");
  }

  public String toString() {
    return super.toString() + "\n" + screen.toString() + "\nMonitor Type: " + monitorType;
  }
}