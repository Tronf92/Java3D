package rotations;

import java.awt.Frame.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;
import javax.vecmath.*;



public class Rotations extends Applet{
    SimpleUniverse simpleU;//simple universe class for Java3D
    
    public Rotations(){//constructor sometimes needed even empty
    }
    
    public void init(){
        setLayout(new BorderLayout());//standard Java code for BorderLayout
        
        Canvas3D c = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        add("Center",c);
        simpleU = new SimpleUniverse(c); //setup the universe, attach the canvas c
        
        BranchGroup scene = createSceneGraph();
        scene.compile();
        
        simpleU.addBranchGraph(scene); //add the scene to the simple universe
       
    }
    
    public BranchGroup createSceneGraph(){
        //Here we will create a basic SceneGraph with a ColorCube object

	// This BranchGroup is the root of the SceneGraph, 'objRoot' is the name I use,
	// and it is typically the standard name for it, but can be named anything.
        
        BranchGroup objRoot = new BranchGroup();
        
        TransformGroup cctg = new TransformGroup();
        
        ColorCube c = new ColorCube(0.5f);
        cctg.addChild(c);
        
        Transform3D cc3d = new Transform3D();
        cc3d.setTranslation(new Vector3f(0.8f ,1.0f ,-2.0f ));
        
        cctg.setTransform(cc3d);
        objRoot.addChild(cctg);
        
        cc3d.setTranslation(new Vector3f(0,0,0));
        cctg.setTransform(cc3d);
        
        Transform3D someRotation = new Transform3D();
        someRotation.rotY(Math.PI/4);
        
        cc3d.mul(someRotation);
        cc3d.setTranslation(new Vector3f(0.8f ,1.0f ,-2.0f));
        cctg.setTransform(cc3d);
        
        ViewingPlatform vp = simpleU.getViewingPlatform();
        TransformGroup View_TransformGroup = vp.getMultiTransformGroup().getTransformGroup(0);
        Transform3D View_Transform3D = new Transform3D();
        
        View_Transform3D.setTranslation(new Vector3f(0.0f,-1.0f,5.0f));
        View_TransformGroup.setTransform(View_Transform3D);
        
        return objRoot;
    }
    
    public void destroy(){
        simpleU.removeAllLocales();
    }
    
    
    public static void main(String[] args) {
        Frame frame = new MainFrame(new Rotations(),500,500);
    }
    
}
