import com.sun.j3d.exp.swing.JCanvas3D;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import java.awt.*;

/**
 * Created by Andrey on 26.03.2016.
 */
public class SceneAtoms3D extends JPanel {

    private SimpleUniverse simpleUniverse;
    public BranchGroup sceneBranchGroup;
    private BoundingSphere boundingSphere;

    private static int WIDTH = 600;
    private static int HEIGHT = 600;
    private static final int BOUND_SIZE = 100;
    private static final Point3d USER_POSITION = new Point3d(0,5,20);



    public SceneAtoms3D(MoleculePart[] molecule) {

        setLayout( new BorderLayout() );
        setOpaque( false );
        setPreferredSize( new Dimension(WIDTH, HEIGHT));

        JCanvas3D jCanvas3D = new JCanvas3D(new GraphicsConfigTemplate3D()) {
            {
                this.enableEvents(AWTEvent.MOUSE_EVENT_MASK |
                        AWTEvent.MOUSE_MOTION_EVENT_MASK |
                        AWTEvent.MOUSE_WHEEL_EVENT_MASK);
            }
        };
        add("Center", jCanvas3D);
        jCanvas3D.setFocusable(true);
        jCanvas3D.requestFocus();
        jCanvas3D.setSize(600, 600);


        simpleUniverse = new SimpleUniverse(jCanvas3D.getOffscreenCanvas3D());
        simpleUniverse.getViewer().getView().setBackClipDistance(25.0);


        initializeScene(jCanvas3D, molecule);
        simpleUniverse.addBranchGraph(sceneBranchGroup);



    }

    /**
     * Initializes scene for drawing a molecule
     * @param jCanvas3D  jCanvas3D for adding controls
     */
    private void initializeScene(JCanvas3D jCanvas3D, MoleculePart[] molecule)
    {

        sceneBranchGroup = new BranchGroup();
        boundingSphere = new BoundingSphere(new Point3d(0,0,0), BOUND_SIZE);

       /* double radiusGameMap = BOUND_SIZE;
        boundingSphere = new BoundingSphere(new Point3d(radiusGameMap, 0.0, radiusGameMap),
                radiusGameMap * 6.0d);
        BoundingLeaf boundingLeaf = new BoundingLeaf(boundingSphere);
        PlatformGeometry platformGeom = new PlatformGeometry();
        platformGeom.addChild(boundingLeaf);*/

        addLights();
        addBackground();
        addUserPosition();
        addControls(jCanvas3D);

        for (MoleculePart part: molecule) {
            if (part instanceof Atom) {
                addSphere(part.getX(), part.getY(), part.getZ(), part.getColor());
            }
            if (part instanceof Bond) {
                addCylinder(part.getBot(), part.getTop(), part.getColor(), ((Bond) part).getNumberOfConnections());
            }
        }

        sceneBranchGroup.compile();


    }


    /**
     * Sets scene's background
     */
    private void addBackground() {
        Background back = new Background();
        back.setApplicationBounds( boundingSphere );
        back.setColor(0.0f, 0.0f, 0.0f);
        sceneBranchGroup.addChild( back );
    }

    /**
     * Sets scene's lights
     */
    private void addLights() {

        Color3f white = new Color3f(1.0f, 1.0f, 1.0f);

        // Setting one ambient light
        AmbientLight ambientLightNode = new AmbientLight(white);
        ambientLightNode.setInfluencingBounds(boundingSphere);
        sceneBranchGroup.addChild(ambientLightNode);

        // Setting two directional lights
        Vector3f light1Direction  = new Vector3f(-1.0f, -1.0f, -1.0f);
        Vector3f light2Direction  = new Vector3f(1.0f, -1.0f, 1.0f);

        DirectionalLight dirlight1 = new DirectionalLight(white, light1Direction);
        dirlight1.setInfluencingBounds(boundingSphere);
        sceneBranchGroup.addChild(dirlight1);

        DirectionalLight dirlight2 = new DirectionalLight(white, light2Direction);
        dirlight2.setInfluencingBounds(boundingSphere);
        sceneBranchGroup.addChild(dirlight2);
    }

    /**
     * Sets scene's controls
     * @param c  canvas3D for setting controls
     */
    private void addControls(JCanvas3D c) {
        OrbitBehavior orbitBehavior = new OrbitBehavior(c.getOffscreenCanvas3D(), OrbitBehavior.REVERSE_ALL);
        //orbitBehavior.setZoomFactor(-1d);
        orbitBehavior.setSchedulingBounds(boundingSphere);

        ViewingPlatform viewingPlatform = simpleUniverse.getViewingPlatform();
        viewingPlatform.setViewPlatformBehavior(orbitBehavior);

    }

    /**
     * Sets user's viewing position
     */
    private void addUserPosition()
    {
        ViewingPlatform viewingPlatform = simpleUniverse.getViewingPlatform();
        TransformGroup transformGroup =  viewingPlatform.getViewPlatformTransform();
        Transform3D transform3D = new Transform3D();
        transformGroup.getTransform(transform3D);
        transform3D.lookAt( USER_POSITION, new Point3d(0,0,0), new Vector3d(0,1,0));
        transform3D.invert();
        transformGroup.setTransform(transform3D);
    }

    /**
     * Draws a spherical atom in space
     * @param x  x coordinate of atom in space
     * @param y  y coordinate of atom in space
     * @param z  z coordinate of atom in space
     * @param color  color of atom
     */
    public void addSphere(float x, float y, float z, Color3f color) {


        Transform3D transform3D = new Transform3D();
        transform3D.set(new Vector3f(x, y, z));

        TransformGroup transformGroup = new TransformGroup(transform3D);
        Appearance appearance = new Appearance();

        Color3f ambientColour = color;
        Color3f diffuseColour = color;
        Color3f specularColour = new Color3f(1.0f, 1.0f, 1.0f);
        Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);
        float shininess = 20.0f;
        appearance.setMaterial(new Material(ambientColour, emissiveColour, diffuseColour, specularColour, shininess));

        float r = 1.0f;
        // if atom is a Hydrogen then radius is smaller than usually:
        if (color.equals(new Color3f(0.6f, 0.6f, 0.6f))) {
            r = 0.6f;
        }

        transformGroup.addChild(new Sphere(r, Sphere.GENERATE_NORMALS, 120, appearance));
        sceneBranchGroup.addChild(transformGroup);
    }



    /**
     * Draws a cylindrical atom bond
     * @param b  bot coordinates of the cylinder
     * @param t  top coordinates of the cylinder
     */
    public void addCylinder(Point3d b, Point3d t, Color3f color, int n) {

        Appearance appearance = new Appearance();
        Color3f ambientColour = color;
        Color3f diffuseColour = color;
        Color3f specularColour = new Color3f(1.0f, 1.0f, 1.0f);
        Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);
        float shininess = 80.0f;
        appearance.setMaterial(new Material(ambientColour, emissiveColour,diffuseColour, specularColour, shininess));

        double radius;
        if (n == 1) {
            radius = 0.2;
        }
        else if (n == 2) {
            radius = 0.3;
        }
        else if (n == 3) {
            radius = 0.45;
        }
        else {
            radius = 0.2;
        }



        int edges = 40;

        Vector3d bot = new Vector3d();
        bot.x = b.x;
        bot.y = b.y;
        bot.z = b.z;
        Vector3d top = new Vector3d();
        top.x = t.x;
        top.y = t.y;
        top.z = t.z;

        // Calculating cylinder's center
        Vector3d center = new Vector3d();
        center.x = (top.x - bot.x) / 2.0 + bot.x;
        center.y = (top.y - bot.y) / 2.0 + bot.y;
        center.z = (top.z - bot.z) / 2.0 + bot.z;

        // Calculating cylinder's height and unit vector along cylinder axis
        Vector3d unit = new Vector3d();
        // unit = top - bot;
        unit.sub(top, bot);
        double height = unit.length();
        unit.normalize();

    /* A Java3D cylinder is created lying on the Y axis by default.
       The idea here is to take the desired cylinder's orientation
       and perform t tranformation on it to get it ONTO the Y axis.
       Then this transformation matrix is inverted and used on t
       newly-instantiated Java 3D cylinder. */

        // calculate vectors for rotation matrix
        // rotate object in any orientation, onto Y axis (exception handled below)
        // (see page 418 of _Computer Graphics_ by Hearn and Baker)
        Vector3d uX = new Vector3d();
        Vector3d uY = new Vector3d();
        Vector3d uZ = new Vector3d();
        double magX;
        Transform3D rotateFix = new Transform3D();

        uY = new Vector3d(unit);
        uX.cross(unit, new Vector3d(0, 0, 1));
        magX = uX.length();
        // magX == 0 if object's axis is parallel to Z axis
        if (magX != 0) {
            uX.z = uX.z / magX;
            uX.x = uX.x / magX;
            uX.y = uX.y / magX;
            uZ.cross(uX, uY);
        }
        else {
            // formula doesn't work if object's axis is parallel to Z axis
            // so rotate object onto X axis first, then back to Y at end
            double magZ;
            // (switched z -> y, y -> x, x -> z from code above)
            uX = new Vector3d(unit);
            uZ.cross(unit, new Vector3d(0, 1, 0));
            magZ = uZ.length();
            uZ.x = uZ.x / magZ;
            uZ.y = uZ.y / magZ;
            uZ.z = uZ.z / magZ;
            uY.cross(uZ, uX);
            // rotate object 90 degrees CCW around Z axis--from X onto Y
            rotateFix.rotZ(Math.PI / 2.0);
        }

        // create the rotation matrix
        Transform3D transMatrix = new Transform3D();
        Transform3D rotateMatrix =
                new Transform3D(new Matrix4d
                        (uX.x, uX.y, uX.z, 0,
                        uY.x, uY.y, uY.z, 0,
                        uZ.x, uZ.y, uZ.z, 0,
                        0,  0,  0,  1));
        // invert the matrix; need to rotate it off of the Z axis
        rotateMatrix.invert();
        // rotate the cylinder into correct orientation
        transMatrix.mul(rotateMatrix);
        transMatrix.mul(rotateFix);
        // translate the cylinder away
        transMatrix.setTranslation(center);
        // create the transform group
        TransformGroup tg = new TransformGroup(transMatrix);


        Cylinder cyl = new Cylinder((float) radius, (float) height, Cylinder.GENERATE_NORMALS, edges, 1, appearance);
        tg.addChild(cyl);
        BranchGroup cylBg = new BranchGroup();
        cylBg.addChild(tg);
        sceneBranchGroup.addChild(cylBg);
    }


}
