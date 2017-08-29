import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import java.awt.*;

/**
 * Created by Andrey on 26.03.2016.
 */
public class Molecule3D {

    public static void main(String[] args) {

        JFrame form = new JFrame();
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setBackground(new Color(255, 255, 255));
        form.setBounds(0, 0, 600, 600);
        //form.setLocationRelativeTo(null);


        // Adding atoms and connections
        int numberOfMoleculeParts = 22;
        MoleculePart[] moleculePart = new MoleculePart[numberOfMoleculeParts];
        moleculePart[0] = new Atom(0, 0, 0, "H");
        moleculePart[1] = new Atom(2, 2, 2, "C");
        moleculePart[2] = new Atom(2, -2, 2, "O");
        moleculePart[3] = new Atom(2, 2, -2, "N");
        moleculePart[4] = new Atom(-2, 2, 2, "S");
        moleculePart[5] = new Atom(4, 4, 4, "P");
        moleculePart[6] = new Atom(4, -4, 4, "F");
        moleculePart[7] = new Atom(-4, 4, 4, "Cl");
        moleculePart[8] = new Atom(4, 4, -4, "Br");
        moleculePart[9] = new Atom(-4, -4, -4, "I");
        moleculePart[10] = new Atom(-2, -2, -2, "metal");
        moleculePart[11] = new Bond(new Point3d(0, 0, 0), new Point3d(2, 2, 2), new Color3f(0.67f, 0.67f, 0.67f), 1);
        moleculePart[12] = new Bond(new Point3d(0, 0, 0), new Point3d(2, 2, 2), new Color3f(0.67f, 0.67f, 0.67f), 1);
        moleculePart[13] = new Bond(new Point3d(0, 0, 0), new Point3d(2, -2, 2), new Color3f(0.67f, 0.67f, 0.67f), 1);
        moleculePart[14] = new Bond(new Point3d(2, -2, 2), new Point3d(-2, -2, -2), new Color3f(0.67f, 0.67f, 0.67f), 3);
        moleculePart[15] = new Bond(new Point3d(2, -2, 2), new Point3d(2, 2, -2), new Color3f(0.67f, 0.67f, 0.67f), 1);
        moleculePart[16] = new Bond(new Point3d(4, 4, 4), new Point3d(4, 4, -4), new Color3f(0.67f, 0.67f, 0.67f), 1);
        moleculePart[17] = new Bond(new Point3d(-4, 4, 4), new Point3d(-2, 2, 2), new Color3f(0.67f, 0.67f, 0.67f), 1);
        moleculePart[18] = new Bond(new Point3d(-4, -4, -4), new Point3d(-2, -2, -2), new Color3f(0.67f, 0.67f, 0.67f), 1);
        moleculePart[19] = new Bond(new Point3d(2, 2, 2), new Point3d(-2, 2, 2), new Color3f(0.67f, 0.67f, 0.67f), 2);
        moleculePart[20] = new Bond(new Point3d(2, -2, 2), new Point3d(4, -4, 4), new Color3f(0.67f, 0.67f, 0.67f), 2);
        moleculePart[21] = new Bond(new Point3d(4, 4, -4), new Point3d(2, 2, -2), new Color3f(0.67f, 0.67f, 0.67f), 1);

        SceneAtoms3D atoms3D = new SceneAtoms3D(moleculePart);
        form.getContentPane().add(atoms3D, BorderLayout.CENTER);
        form.pack();
        form.setResizable(false);
        form.setVisible(true);




    }
}
