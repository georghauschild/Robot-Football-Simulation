/* Copyright 2008 - 2021 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under GPL-3 License (see gpl.txt).
 */

package magma.agent.decision.behavior.complex.walk;

import com.sun.org.apache.xpath.internal.operations.Variable;
import hso.autonomy.agent.decision.behavior.BehaviorMap;
import hso.autonomy.agent.decision.behavior.IBehavior;
import hso.autonomy.agent.model.thoughtmodel.IThoughtModel;
import hso.autonomy.util.geometry.Angle;
import hso.autonomy.util.geometry.IPose2D;
import hso.autonomy.util.geometry.Pose2D;
import hso.autonomy.util.geometry.PoseSpeed2D;
import magma.agent.decision.behavior.IBehaviorConstants;
import magma.agent.decision.behavior.complex.RoboCupSingleComplexBehavior;
import magma.agent.decision.behavior.ikMovement.walk.IKDynamicWalkMovement;
import magma.agent.model.thoughtmodel.strategy.impl.roles.FieldArea;
import magma.agent.model.thoughtmodel.strategy.impl.roles.Hof_Defender;
import magma.agent.model.thoughtmodel.strategy.impl.strategies.HofStrategy;
import magma.agent.model.thoughtmodel.strategy.impl.strategies.StrategyConfigurationHelper;
import magma.agent.model.worldmodel.IPlayer;
import magma.agent.model.worldmodel.IRoboCupWorldModel;
import magma.agent.model.worldmodel.IThisPlayer;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.HashMap;
import java.util.Map;

/**
 * Behavior we use when we're not actively running to the ball with Attack.
 *
 * @author Klaus Dorer
 */
public class PassivePositioning extends RoboCupSingleComplexBehavior {
    public PassivePositioning(IThoughtModel thoughtModel, BehaviorMap behaviors) {
        super(IBehaviorConstants.PASSIVE_POSITIONING, thoughtModel, behaviors);
    }


    @Override
    public IBehavior decideNextBasicBehavior() {


        Map<String, Vector3D> targetPositions = new HashMap<>();
        targetPositions.put("targetPosition2", HofStrategy.hofdef1.getNewPositionWithOffsetAndLimits());
        targetPositions.put("targetPosition3", HofStrategy.hofdef2.getNewPositionWithOffsetAndLimits());
        targetPositions.put("targetPosition4", HofStrategy.hofmid1.getNewPositionWithOffsetAndLimits());
        targetPositions.put("targetPosition5", HofStrategy.hofmid2.getNewPositionWithOffsetAndLimits());
        targetPositions.put("targetPosition6", HofStrategy.hofmid3.getNewPositionWithOffsetAndLimits());
        targetPositions.put("targetPosition7", HofStrategy.hofmid4.getNewPositionWithOffsetAndLimits());
        targetPositions.put("targetPosition8", HofStrategy.hofwing1.getNewPositionWithOffsetAndLimits());
        targetPositions.put("targetPosition9", HofStrategy.hofwing2.getNewPositionWithOffsetAndLimits());
        targetPositions.put("targetPosition10",HofStrategy.hofatk1.getNewPositionWithOffsetAndLimits());
        targetPositions.put("targetPosition11",HofStrategy.hofatk2.getNewPositionWithOffsetAndLimits());

        // Zugriff auf die Zielposition basierend auf der Spielernummer
        Vector3D playerTargetPosition = targetPositions.get("targetPosition" + getWorldModel().getThisPlayer().getID());

        WalkToPosition walkToPosition = (WalkToPosition) behaviors.get(IBehaviorConstants.WALK_TO_POSITION);
        Pose2D newPose = new Pose2D(playerTargetPosition);
        return walkToPosition.setPosition(
                new PoseSpeed2D(newPose, Vector2D.ZERO), 90, false, true, 0.8, IKDynamicWalkMovement.NAME_LOW_ACC);
    }


    public Vector3D findMidpoint(Vector3D p1, Vector3D p2) {
        double xMidpoint = (p1.getX() + p2.getX()) / 2;
        double yMidpoint = (p1.getY() + p2.getY()) / 2;
        return new Vector3D(xMidpoint, yMidpoint, 0);
    }

    public Vector3D halfValues(Vector3D vector3D) {
        double x = vector3D.getX() / 2;
        double y = vector3D.getY() / 2;
        double z = vector3D.getZ() / 2;
        return new Vector3D(x, y, z);
    }
}


