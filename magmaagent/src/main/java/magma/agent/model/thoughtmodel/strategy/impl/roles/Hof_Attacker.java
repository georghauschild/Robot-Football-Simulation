package magma.agent.model.thoughtmodel.strategy.impl.roles;

import hso.autonomy.util.geometry.*;
import hso.autonomy.util.geometry.IPose2D;
import hso.autonomy.util.misc.ValueUtil;
import java.util.ArrayList;
import java.util.List;
import magma.agent.model.thoughtmodel.IRoboCupThoughtModel;
import magma.agent.model.worldmodel.IPlayer;
import magma.agent.model.worldmodel.IRoboCupWorldModel;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;


public class Hof_Attacker extends Hof_Role{


    public Hof_Attacker(IRoboCupWorldModel worldModel, String name, float priority, double minX, double maxX, FieldArea fieldArea, Vector3D offset) {
        super(worldModel, name, priority, minX, maxX, 0, offset);
    }

    //TODO: Ähnliches Verhalten wie WING?
    @Override
    protected IPose2D determinePosition() {


        // In OpenSpaceSeeker schauen -> Eventuell eine DefaultRole?
        //Als erstes: Einfach in den Raum vor´s Tor stellen

        Vector2D ballPosition2D = VectorUtils.to2D(worldModel.getBall().getPosition());

        double ballPosY = ballPosition2D.getY();
        double xpos = ballPosition2D.getX();


        Vector3D target = new Vector3D(keepXLimits(xpos) ,ballPosY, 0);

        Angle ballDirection = calculateBallDirection(target);

        return new Pose2D(target, ballDirection);
    }
}
