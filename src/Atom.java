import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

/**
 * Created by Andrey on 26.03.2016.
 */
public class Atom implements MoleculePart {

    private float x;
    private float y;
    private float z;

    private Color3f color;

    private String atom;


    @Override
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    @Override
    public Color3f getColor() {
        return color;
    }

    public void setColor(Color3f color) {
        this.color = color;
    }

    public String getAtom() {
        return atom;
    }

    public void setAtom(String atom) {
        this.atom = atom;
    }

    @Override
    public Point3d getBot() {
        return null;
    }

    @Override
    public Point3d getTop() {
        return null;
    }

    public Atom(float x, float y, float z, Color3f color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
        atom = "-";
    }

    public Atom(float x, float y, float z, String atom) {
        this.x = x;
        this.y = y;
        this.z = z;


        if (atom == "H" || atom == "h") {
            color = new Color3f(0.6f, 0.6f, 0.6f);
        }
        else if (atom == "C" || atom == "c") {
            color = new Color3f(0.25f, 0.25f, 0.25f);
        }
        else if (atom == "O" || atom == "o") {
            color = new Color3f(1.0f, 0.0f, 0.0f);
        }
        else if (atom == "N" || atom == "n") {
            color = new Color3f(0.0f, 0.0f, 1.0f);
        }
        else if (atom == "S" || atom == "s") {
            color = new Color3f(0.6f, 0.6f, 0.0f);
        }
        else if (atom == "P" || atom == "p") {
            color = new Color3f(0.3f, 0.0f, 0.0f);
        }
        else if (atom == "F" || atom == "f") {
            color = new Color3f(0.0f, 0.3f, 0.3f);
        }
        else if (atom == "Cl" || atom == "cl") {
            color = new Color3f(0.0f, 0.9f, 0.0f);
        }
        else if (atom == "Br" || atom == "br") {
            color = new Color3f(0.1f, 0.0f, 0.0f);
        }
        else if (atom == "I" || atom == "I") {
            color = new Color3f(0.9f, 0.0f, 0.9f);
        }
        else {
            color = new Color3f(0.8f, 0.2f, 0.6f);
        }


    }

}
