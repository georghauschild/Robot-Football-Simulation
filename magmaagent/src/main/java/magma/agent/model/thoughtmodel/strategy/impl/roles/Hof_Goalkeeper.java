package magma.agent.model.thoughtmodel.strategy.impl.roles;

import hso.autonomy.util.geometry.Angle;
import hso.autonomy.util.geometry.IPose2D;
import hso.autonomy.util.geometry.Pose2D;
import hso.autonomy.util.misc.ValueUtil;
import magma.agent.model.worldmodel.IRoboCupWorldModel;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Hof_Goalkeeper extends Role {

    public Hof_Goalkeeper(IRoboCupWorldModel worldModel, String name, float priority, double minX, double maxX) {
        super(worldModel, name, priority, minX, maxX );
    }

    @Override
    protected IPose2D determinePosition()
    {
        Vector3D ownGoalPos = worldModel.getOwnGoalPosition();
        Vector3D ballPos = worldModel.getBall().getPosition();

        double deltaBallGoalX = Math.abs(ownGoalPos.getX() - ballPos.getX());

        double targetY = ballPos.getY() / 3;
        targetY = ValueUtil.limitAbs(targetY, 3.4);

        double targetX = keepXLimits(minX + 0.3 * deltaBallGoalX);

        Vector3D target = new Vector3D(targetX, targetY, 0);
        Angle ballDirection = calculateBallDirection(target);
        return new Pose2D(target, ballDirection);
    }

    @Override
    protected IPose2D avoidGoal(IPose2D target)
    {
        return keepMinDistanceToGoal(target, 1);
    }
}
