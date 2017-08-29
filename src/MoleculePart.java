import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

/**
 * Created by Andrey on 26.03.2016.
 */
public interface MoleculePart {

    float getX();
    float getY();
    float getZ();

    Point3d getBot();
    Point3d getTop();

    Color3f getColor();
}
