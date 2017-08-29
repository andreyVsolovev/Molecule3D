import javax.vecmath.Color3f;
import javax.vecmath.Point3d;


/**
 * Created by Andrey on 26.03.2016.
 */
public class Bond implements MoleculePart {

    private Point3d bot;
    private Point3d top;
    private Color3f color;
    private int numberOfConnections;

    @Override
    public Point3d getBot() {
        return bot;
    }

    public void setBot(Point3d bot) {
        this.bot = bot;
    }

    @Override
    public Point3d getTop() {
        return top;
    }

    public void setTop(Point3d top) {
        this.top = top;
    }

    @Override
    public Color3f getColor() {
        return color;
    }


    public void setColor(Color3f color) {
        this.color = color;
    }

    public int getNumberOfConnections() {
        return numberOfConnections;
    }

    public void setNumberOfConnections(int numberOfConnections) {
        this.numberOfConnections = numberOfConnections;
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public float getZ() {
        return 0;
    }

    public Bond(Point3d bot, Point3d top, Color3f color) {
        this.bot = bot;
        this.top = top;
        this.color = color;
        numberOfConnections = 1;
    }

    public Bond(Point3d bot, Point3d top, Color3f color, int numberOfConnections) {
        this.bot = bot;
        this.top = top;
        this.color = color;
        this.numberOfConnections = numberOfConnections;
    }

}
