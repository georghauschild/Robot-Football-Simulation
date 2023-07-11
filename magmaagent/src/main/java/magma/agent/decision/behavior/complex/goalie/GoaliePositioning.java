/* Copyright 2008 - 2021 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under GPL-3 License (see gpl.txt).
 */

package magma.agent.decision.behavior.complex.goalie;

import hso.autonomy.agent.decision.behavior.BehaviorMap;
import hso.autonomy.agent.decision.behavior.IBehavior;
import hso.autonomy.agent.model.thoughtmodel.IThoughtModel;
import hso.autonomy.util.geometry.*;
import hso.autonomy.util.misc.ValueUtil;
import magma.agent.decision.behavior.IBehaviorConstants;
import magma.agent.decision.behavior.base.KeepEstimator;
import magma.agent.decision.behavior.complex.RoboCupSingleComplexBehavior;
import magma.agent.decision.behavior.complex.walk.WalkToPosition;
import magma.agent.decision.behavior.ikMovement.walk.IKDynamicWalkMovement;
import magma.agent.model.worldmodel.IRoboCupWorldModel;
import magma.agent.model.worldmodel.IThisPlayer;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * @author Pret
 */
public class GoaliePositioning extends RoboCupSingleComplexBehavior
{
	public static final int CYCLES_BEFORE_STAND_STILL = 50;

	private final KeepEstimator keepEstimator;

	private int cycles;

	public GoaliePositioning(IThoughtModel thoughtModel, BehaviorMap behaviors)
	{
		super(IBehaviorConstants.GOALIE_POSITIONING, thoughtModel, behaviors);
		keepEstimator = new KeepEstimator(thoughtModel);
		cycles = 0;
	}
	//find position between goal und ball
	public static Vector2D findMidpoint(Vector2D p1, Vector2D p2) {
		double xMidpoint = (p1.getX() + p2.getX()) / 2;
		double yMidpoint = (p1.getY() + p2.getY()) / 2;

		return new Vector2D(xMidpoint, yMidpoint);
	}


	@Override
	public IBehavior decideNextBasicBehavior()
	{
	 IRoboCupWorldModel worldModel = getWorldModel();
	 IThisPlayer thisPlayer = worldModel.getThisPlayer();
	 Vector3D goalPosition3D = worldModel.getOwnGoalPosition();
	 Vector3D ballPosition3D = worldModel.getBall().getPosition();

	 // get goal position in 2D and a little bid in front of goal
	 double x = goalPosition3D.getX() + 0.3;
	 double y = goalPosition3D.getY();
	 Vector2D goalPosition = new Vector2D(x, y);

	// get ball position in 2D
	x = ballPosition3D.getX();
	y = ballPosition3D.getY();
	Vector2D ballPosition = new Vector2D(x, y);

	Vector2D newPosition = findMidpoint(goalPosition, ballPosition);

		if (!worldModel.getMap().getFieldArea().contains(newPosition)){
			newPosition = new Vector2D(goalPosition3D.getX(),goalPosition3D.getY()+1);
		}

	 WalkToPosition walkToPosition = (WalkToPosition) behaviors.get(IBehaviorConstants.WALK_TO_POSITION);
		Pose2D newPose = new Pose2D(newPosition);
		 return walkToPosition.setPosition(
				 new PoseSpeed2D(newPose, Vector2D.ZERO), 90, true, true, 0.8, IKDynamicWalkMovement.NAME_LOW_ACC);
		 // return behaviors.get(IBehaviorConstants.CELEBRATE);

	}
}
