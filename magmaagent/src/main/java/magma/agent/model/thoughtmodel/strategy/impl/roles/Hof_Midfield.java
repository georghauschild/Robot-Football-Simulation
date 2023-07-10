package magma.agent.model.thoughtmodel.strategy.impl.roles;

import hso.autonomy.util.geometry.IPose2D;
import hso.autonomy.util.geometry.Pose2D;
import hso.autonomy.util.misc.ValueUtil;
import magma.agent.model.worldmodel.IRoboCupWorldModel;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;


public class Hof_Midfield extends Hof_Role{

    private final FieldArea fieldArea;

    public Hof_Midfield(IRoboCupWorldModel worldModel, String name, float priority, double minX, double maxX, FieldArea fieldArea, Vector3D offset) {
        super(worldModel, name, priority, minX, maxX, 0, offset);
        this.fieldArea = fieldArea;
    }

    @Override
    protected IPose2D determinePosition() {


        Vector3D ballPos = worldModel.getBall().getPosition();
        Vector3D ownGoalPos = worldModel.getOwnGoalPosition();

        double ballPosX = ballPos.getX();
        double ballPosY = ballPos.getY();

        double ypos = ValueUtil.limitAbs(ballPosY, worldModel.fieldHalfWidth());
        //double ypos = ValueUtil.limitAbs(ballPosY, 4);
        //double ypos = 0;
        double xpos = (ownGoalPos.getX() + ballPosX)/2;

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

        if(ballPosX < 3){
            target = new Vector3D(keepXLimits(ballPosX), ypos);
        }

        return new Pose2D(target);

    }

    @Override
    public double keepXLimits(double targetX) {
        return super.keepXLimits(targetX);
    }

    public Vector3D getOffsetUpperDef() {
        return new Vector3D(-2, 1, 0);
    }
    public Vector3D getOffsetLowerDef() {
        return new Vector3D(-2, -1, 0);
    }
    public Vector3D getOffsetUpperAtk() {
        return new Vector3D(1, 3, 0);
    }
    public Vector3D getOffsetLowerAtk() {
        return new Vector3D(1, -3, 0);
    }

}
