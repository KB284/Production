public class MoviePlayer extends Product implements MultimediaControl {

  private MonitorType monitorType;
  private Screen screen;


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

  @Override
  public void setType(String type) {

  }
}