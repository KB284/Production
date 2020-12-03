/**
 * Screen.java - This class is used to represent the screen that a MoviePlayer can have.
 *
 * @author Khubaib Farah
 */
public class Screen implements ScreenSpec {

  /**
   * this field has the type of resolution for the screen.
   */
  private final String resolution;

  /**
   * This field holds the refresh rate of the screen.
   */
  private int refreshRate;

  /**
   * this field holds the response time of the screen.
   */
  private int responseTime;

  /**
   * This constructor contains three argument parameters.
   *
   * @param resolution value for the resolution.
   * @param refreshRate value of the refresh rate.
   * @param responseTime value of the response time.
   */
  Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  public String getResolution() {
    return resolution;
  }

  public int getRefreshRate() {
    return refreshRate;
  }

  public int getResponseTime() {
    return responseTime;
  }

  /**
   * This method creates a string with information of the movie player.
   *
   * @return a String with details of the movie player.
   */
  public String toString() {
    return "Screen:"
        + "\nResolution: " + resolution
        + "\nRefresh rate: " + refreshRate
        + "\nResponse time: " + responseTime;
  }
}