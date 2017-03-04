package movingpolygon;

import java.awt.Frame;
import java.awt.*;
import java.awt.event.*;

import java.applet.Applet;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import javax.vecmath.*;


public class MovingPolygon extends Applet implements MouseListener {
    SimpleUniverse simpleU;
    TransformGroup polyTG = new TransformGroup();
    Transform3D poly3D = new Transform3D();
    Transform3D poly3D1 = new Transform3D();
    Transform3D poly3D2 = new Transform3D();
    
    public BranchGroup createSceneGraph(){
        BranchGroup objRoot = new BranchGroup();
        Appearance polygon1Appearance = new Appearance();
        Color3f color1 = new Color3f(1.0f,0.0f,0.0f);
        ColoringAttributes color1ca = new ColoringAttributes(color1,1);
        polygon1Appearance.setColoringAttributes(color1ca);
        QuadArray polygon1 = new QuadArray(4,QuadArray.COORDINATES);
        polygon1.setCoordinate(0, new Point3f(0f,0f,0f));
        polygon1.setCoordinate(1, new Point3f(3f,0f,0f));
        polygon1.setCoordinate(2, new Point3f(3f,2f,0f));
        polygon1.setCoordinate(3, new Point3f(0f,2f,0f));
        
        polyTG.addChild(new Shape3D(polygon1, polygon1Appearance));
        objRoot.addChild(polyTG);
        
        poly3D.setTranslation(new Vector3f(0f,0f,0f));
        poly3D1.setTranslation(new Vector3f(-0.5f,0,-5f));
        poly3D2.setTranslation(new Vector3f(0.5f,0f,5f));
        
        polyTG.setTransform(poly3D);
        polyTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        return objRoot;
    }
    
    
    public void mouseClicked (MouseEvent e){}
    public void mouseReleased (MouseEvent e){}
    public void mouseEntered (MouseEvent e){}
    public void mouseExited (MouseEvent e){}
    
    public void mousePressed(MouseEvent e){
        if((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK ){
            polyTG.setTransform(poly3D1);
        }
        if ((e.getModifiers()& InputEvent.BUTTON2_MASK) == InputEvent.BUTTON2_MASK){
            polyTG.setTransform(poly3D);
        }
        
        if ((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK){
            polyTG.setTransform(poly3D2);
        }
    }
    
    public void MovingPolygon(){}
    
    public void destroy(){
        simpleU.removeAllLocales();
    }
    
    public void init(){
        setLayout(new BorderLayout());
        
        Canvas3D c = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        
        c.addMouseListener(this);
        add("Center",c);
        BranchGroup scene = createSceneGraph();
        simpleU = new SimpleUniverse(c);
        TransformGroup tg = simpleU.getViewingPlatform().getViewPlatformTransform();
        Transform3D t3d = new Transform3D();
        t3d.setTranslation(new Vector3f(0f,0f,10f));
        tg.setTransform(t3d);
        scene.compile();
        simpleU.addBranchGraph(scene);
        
        
        
    }
    
    public static void main(String[] args) {
        Frame frame = new MainFrame(new MovingPolygon(),500,500);
    }
    
}
