/**
 This code is covered by the GNU General Public License
 detailed at http://www.gnu.org/copyleft/gpl.html

 Flight Club docs located at http://www.danb.dircon.co.uk/hg/hg.htm
 Dan Burton , Nov 2001
 */

package org.flightclub;

import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 * default event handler
 */
public class EventManager {
    protected final Vector<Object> objs;
    final static int MAX_Q = 20;
    final KeyEvent[] queue = new KeyEvent[MAX_Q];
    int queueNum = 0;

    public EventManager() {
        objs = new Vector<>();
    }

    /**
     * add an object to the list of objects to be
     * notified when an event happens
     */
    public void addNotification(Object o) {
        objs.addElement(o);
    }

    public void removeNotification(Object o) {
        objs.removeElement(o);
    }

    /**
     * add event to queue
     */
    public boolean handleEvent(KeyEvent e) {
        if (queueNum < MAX_Q) {
            queue[queueNum] = e;
            queueNum++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * process event at head of the queue
     */
    public void tick() {
        KeyEvent e = popQueue();
        if (e == null) return;

        for (int i = 0; i < objs.size(); i++) {
            EventInterface ei = (EventInterface) objs.elementAt(i);
            callEventHelper(ei, e);
        }
    }

    void callEventHelper(EventInterface ei, KeyEvent e) {
        switch (e.getID()) {
            case KeyEvent.KEY_RELEASED:
                ei.keyReleased(e);
                break;
            case KeyEvent.KEY_PRESSED:
                ei.keyPressed(e);
                break;
            default:
        }
    }

    /**
     * return event at head of the queue or null if queue is empty
     */
    KeyEvent popQueue() {
        if (queueNum == 0) return null;

        KeyEvent e = queue[0];

        //shuffle up one
        for (int i = 0; i < queueNum - 1; i++) {
            queue[i] = queue[i + 1];
        }

        queue[queueNum - 1] = null;
        queueNum--;
        return e;
    }
}
