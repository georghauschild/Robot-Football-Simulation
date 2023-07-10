/* Copyright 2008 - 2021 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under GPL-3 License (see gpl.txt).
 */

package magma.agent.decision.behavior.basic;

import hso.autonomy.agent.model.worldmodel.IVisibleObject;
import hso.autonomy.agent.model.worldmodel.InformationSource;
import hso.autonomy.util.geometry.IPose2D;
import hso.autonomy.util.geometry.Pose2D;
import hso.autonomy.util.geometry.PoseSpeed2D;
import magma.agent.IMagmaConstants;
import magma.agent.decision.behavior.IBehaviorConstants;
import magma.agent.decision.behavior.complex.walk.WalkToPosition;
import magma.agent.model.thoughtmodel.IRoboCupThoughtModel;
import magma.agent.model.worldmeta.impl.SayMessage;
import magma.agent.model.worldmodel.IRoboCupWorldModel;
import magma.agent.model.worldmodel.IThisPlayer;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Used for communication with the server
 *
 * @author Klaus Dorer
 */
public class MoveHofGoalkeeper extends RoboCupBehavior
{
	public MoveHofGoalkeeper(IRoboCupThoughtModel thoughtModel)
	{
		super(IBehaviorConstants.MOVE_HOFGOALKEEPER, thoughtModel);
	}

	@Override
	public void perform()
	{
		super.perform();


		 IRoboCupWorldModel worldModel = getWorldModel();
		 IThisPlayer thisPlayer = worldModel.getThisPlayer();
		 Vector3D goalPosition = worldModel.getOwnGoalPosition();
		 Vector3D ballPosition = worldModel.getBall().getPosition();

		 // Position the goalkeeper between the ball and the center of the goal
		 double x = goalPosition.getX() + 1;
		 double y = (goalPosition.getY() + ballPosition.getY()) / 2;

		 // Create a Pose2D object with the given position and a default orientation of 0
		 IPose2D pose = new Pose2D(x, y);

		 // Create a Vector2D object for speed with default values of 0
		 Vector2D speed = new Vector2D(0, 0);

		 // Create a PoseSpeed2D object with the given pose and speed
		 PoseSpeed2D poseSpeed = new PoseSpeed2D(pose, speed);

	//	 WalkToPosition walkToPosition = (WalkToPosition) behaviors.get(IBehaviorConstants.WALK_TO_POSITION);

		//IRoboCupWorldModel worldModel = getWorldModel();


	}
}
