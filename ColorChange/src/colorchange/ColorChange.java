package colorchange;

import java.awt.Frame;
import java.awt.*;
import java.awt.event.*;

import java.applet.Applet;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import javax.vecmath.*;

public class ColorChange extends Applet implements MouseListener{
    SimpleUniverse simpleU;
    Appearance polygon1Appearance = new Appearance();
    Color3f color1 = new Color3f(1.0f,1.0f,0.0f);
    ColoringAttributes color1Ca = new ColoringAttributes(color1,1);
    Color3f color2 = new Color3f(1.0f, 0.0f,0.0f);
    ColoringAttributes color2Ca = new ColoringAttributes(color2,1);
    boolean yellow = true;
    
    public BranchGroup createSceneGraph(){
        BranchGroup objRoot = new BranchGroup();
        polygon1Appearance.setColoringAttributes(color1Ca);
        polygon1Appearance.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
        
        QuadArray polygon1 = new QuadArray(4, QuadArray.COORDINATES);
        polygon1.setCoordinate(0, new Point3f(0f, 0f, 0f));
        polygon1.setCoordinate(1, new Point3f(2f, 0f, 0f));
        polygon1.setCoordinate(2, new Point3f(2f, 3f, 0f));
        polygon1.setCoordinate(3, new Point3f(0f, 3f, 0f));
        objRoot.addChild(new Shape3D(polygon1,polygon1Appearance));
        
        return objRoot;
    }
    
    public void mouseClicked(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    
    public void mousePressed(MouseEvent e){
        System.out.println(yellow);
        System.out.println(e);
        if(yellow){
            polygon1Appearance.setColoringAttributes(color2Ca);
            yellow = false;
        }
        else{
            polygon1Appearance.setColoringAttributes(color1Ca);
            yellow = true;
        }
    }
    
    public ColorChange(){}
    
    public void destroy(){
        simpleU.removeAllLocales();
    }
    
    public void init(){
        setLayout(new BorderLayout());
        Canvas3D c = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        
        c.addMouseListener(this);
        add("Center",c);
        BranchGroup scene = createSceneGraph();
        simpleU = new SimpleUniverse();
        TransformGroup tg = simpleU.getViewingPlatform().getViewPlatformTransform();
        Transform3D t3d = new Transform3D();
        t3d.setTranslation(new Vector3f(0f,0f,0f));
        tg.setTransform(t3d);
        scene.compile();
        simpleU.addBranchGraph(scene);
        
        
    }
        
   
    public static void main(String[] args) {
       Frame frame = new MainFrame(new ColorChange(), 500, 500);
    }
    
}
