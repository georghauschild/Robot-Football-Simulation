package magma.agent.model.thoughtmodel.strategy.impl.roles;

import hso.autonomy.util.geometry.IPose2D;
import hso.autonomy.util.geometry.Pose2D;
import magma.agent.model.worldmodel.IRoboCupWorldModel;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Hof_Agressor extends Hof_Role{

    private final FieldArea fieldArea;

    public Hof_Agressor(IRoboCupWorldModel worldModel, String name, float priority, double minX, double maxX, FieldArea fieldArea, Vector3D offset) {
        super(worldModel, name, priority, minX, maxX, 0, offset);
        this.fieldArea = fieldArea;
    }

    @Override
    protected IPose2D determinePosition() {

        Vector3D ballPos = worldModel.getBall().getPosition();

        double ballPosX = ballPos.getX();
        double ballPosY = ballPos.getY();

        if (fieldArea == FieldArea.UPPER){
            if (ballPosY < worldModel.fieldHalfWidth()) {
                ballPosY = worldModel.fieldHalfWidth();
            }
        }else{
            if (ballPosY > worldModel.fieldHalfWidth()) {
               ballPosY = worldModel.fieldHalfWidth();
            }
        }

        Vector3D target = new Vector3D(keepXLimits(ballPosX) ,ballPosY, 0);

        return new Pose2D(target);
    }

    @Override
    protected IPose2D avoidGoal(IPose2D target)
    {
        return keepMinDistanceToGoal(target, 1.9);
    }

    @Override
    public Vector3D getOffset() {
        return offset;
    }
}