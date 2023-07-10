package magma.agent.model.thoughtmodel.strategy.impl.roles;

import hso.autonomy.util.geometry.Angle;
import hso.autonomy.util.geometry.IPose2D;
import hso.autonomy.util.geometry.Pose2D;
import magma.agent.model.worldmodel.IRoboCupWorldModel;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Hof_Wing extends Hof_Role{

    private final FieldArea fieldArea;

    public Hof_Wing(IRoboCupWorldModel worldModel, String name, float priority, double minX, double maxX, FieldArea fieldArea, Vector3D offset) {
        super(worldModel, name, priority, minX, maxX, 0, offset);
        this.fieldArea = fieldArea;
    }

    @Override
    protected IPose2D determinePosition() {

        //Offener Platz? OpenSpaceSeeker?
        Vector3D ballPos = worldModel.getBall().getPosition();

        double ballPosX = ballPos.getX();
        double ballPosY = ballPos.getY();
        //offset
        double xOffset = 10;

        double ypos = ballPosY;
        double xpos = ballPosX + xOffset;

        if (fieldArea == FieldArea.UPPER){
            if (ypos< worldModel.fieldHalfWidth()) {
                ypos = worldModel.fieldHalfWidth();
            }
        }else{
            if (ypos> worldModel.fieldHalfWidth()) {
                ypos = worldModel.fieldHalfWidth();
            }
        }

        Vector3D target = new Vector3D(keepXLimits(xpos) ,ypos, 0);

        Angle ballDirection = calculateBallDirection(target);

        return new Pose2D(target, ballDirection);
    }
}
