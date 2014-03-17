/**
 This code is covered by the GNU General Public License
 detailed at http://www.gnu.org/copyleft/gpl.html

 Flight Club docs located at http://www.danb.dircon.co.uk/hg/hg.htm
 Dan Burton , Nov 2001
 */

package org.flightclub;

/**
 * Unit test the framework
 * Run ModelViewerTestApp from the command line to invoke this
 * extension of the ModelViewer
 */
public class ModelViewerTest extends ModelViewer {

    GliderUser glider;

    public void init(ModelEnv a) {
        super.init(a);

        glider = new GliderUser(this, new Vector3d(0, 0, 1));
        glider.takeOff(new Vector3d(0, 0, (float) 0.5));
        cameraMan.subject1 = glider;

        compass = new Compass(this);
        slider = new DataSlider(this);
        slider.label = "V";
    }

    public void tick(Clock c) {
        super.tick(c);
        compass.setArrow(glider.v.x, glider.v.y);
        slider.setValue(glider.v.z * 200);
        System.out.println(glider.v.z);
        //slider.setValue(1);
    }
}