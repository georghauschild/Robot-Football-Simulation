package magma.agent.model.thoughtmodel.strategy.impl.roles;

import hso.autonomy.util.geometry.IPose2D;
import hso.autonomy.util.geometry.Pose2D;
import magma.agent.model.worldmodel.IRoboCupWorldModel;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Hof_Defender extends Hof_Role{

    public Hof_Defender(IRoboCupWorldModel worldModel, String name, float priority, double minX, double maxX, Vector3D offset, FieldArea fieldArea) {
        super(worldModel, name, priority, minX, maxX, 0, offset);
    }

    @Override
    protected IPose2D determinePosition() {

        //gerade noch einfacher Syntax; Vielleicht in ManToManMarker schauen?
        //Upper fieldhalf lower field half?
        Vector3D ballPos = worldModel.getBall().getPosition();

        double ballPosX = ballPos.getX();
        double ballPosY = ballPos.getY();

        return new Pose2D(ballPosX,ballPosY);
    }

    @Override
    protected IPose2D avoidGoal(IPose2D target)
    {
        return keepMinDistanceToGoal(target, 1.9);
    }

    @Override
    public IPose2D getTargetPose() {

        if (name.equals("Verteidigung 1")){
            targetPose = new Pose2D(-9, 3.1);
        }else {
            targetPose = new Pose2D(-9, -3.3);
        }
        return super.getTargetPose();
    }

}
