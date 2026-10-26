package utiles;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class OsPopUpUtil {

	public static void OkPopup() {
		try {
			Thread.sleep(3000);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			} catch(Exception e) {
				throw new RuntimeException("OS popup is not handled " , e);
			}
	}
}
