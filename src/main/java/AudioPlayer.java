/**
 * AudioPlayer.java- This class demonstrates how audio players can be
 *                   produced in a factory.
 *
 * @author Khubaib Farah
 */
public class AudioPlayer extends Product implements MultimediaControl {

  /**
   * This field holds the type of audio files a player can play
   */
  private String supportedAudioFormats;

  /**
   * This field holds the playlist used for the player.
   */
  private String supportedPlaylistFormats;

  /**
   * A construct made for the class. Takes in four arguments;
   * name, manufacturer, supportedAudioFormats,
   * and supportedPlaylistFormats.
   *
   * @param name name of the audio player
   * @param manufacturer manufacturer for the audio player
   * @param supportedAudioFormats the details of the audio player
   * @param supportedPlaylistFormats the type it can play
   */
  AudioPlayer(String name, String manufacturer, String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  public void play() {
    System.out.println("Playing");
  }

  public void stop() {
    System.out.println("Stopping");
  }

  public void previous() {
    System.out.println("Previous");
  }

  public void next() {
    System.out.println("Next");
  }

  /**
   * This method creates a string with details about the player
   *
   * @return a string with the players details
   */
  public String toString() {
    return super.toString()
        + "\nSupported Audio Formats: " + supportedAudioFormats
        + "\nSupported Playlist Formats: " + supportedPlaylistFormats;
  }
}
