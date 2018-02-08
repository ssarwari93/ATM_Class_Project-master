package cosc.groupproject.option2;

import cosc.groupproject.option2.gui.LogInWindow;
import cosc.groupproject.option2.gui.MenuWindow;
import cosc.groupproject.option2.utilities.References;

/**
 * Created by Pandaism(Mike Nguyen) on 3/21/16.
 */
public class Main {

    public static void main(String[] args) {
        LogInWindow logInWindow = new LogInWindow(References.LOGIN_WINDOW_WIDTH, References.LOGIN_WINDOW_HEIGHT);
        logInWindow.setVisible(true);

    }
}
