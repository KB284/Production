/**
 * ScreenSpec.java- an Interface that guarantees that all methods that
 *                  contain it have the methods needed.
 *
 * @author Khubaib Farah
 */
interface ScreenSpec {

  String getResolution();

  int getRefreshRate();

  int getResponseTime();
}