/**
 This code is covered by the GNU General Public License
 detailed at http://www.gnu.org/copyleft/gpl.html

 Flight Club docs located at http://www.danb.dircon.co.uk/hg/hg.htm
 Dan Burton , Nov 2001
 */

package org.flightclub;

import java.applet.Applet;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class XCGameApplet extends Applet {
    private final XCGame app = new XCGame();

    @Override
    public void init() {
        this.setBackground(Color.white);
        app.setSize(getSize());
        add(app);
        app.init(new AppletInterface(this));

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                app.eventManager.handleEvent(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                app.eventManager.handleEvent(e);
            }
        });
        this.requestFocus();
    }

    @Override
    public void start() {
        app.start();
    }

    @Override
    public void stop() {
        app.stop();
    }
}
