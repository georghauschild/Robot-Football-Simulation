/* Copyright 2008 - 2021 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under GPL-3 License (see gpl.txt).
 */

package magma.agent.model.thoughtmodel.strategy.impl.formations;

import hso.autonomy.util.geometry.Angle;
import hso.autonomy.util.geometry.Pose2D;

public class OwnKickOffFormation extends Formation
{
	public OwnKickOffFormation()
	{

		//TODO: Easier Kickoff -> Standing in the middle

		// Kick off formation to opponent half
		//		poses.put(1, new Pose2D(-14.5, 0));
		//		poses.put(2, new Pose2D(-11.4, 0));
		//		poses.put(3, new Pose2D(-6, -6));
		//		poses.put(4, new Pose2D(-7.75, -3.25));
		//		poses.put(5, new Pose2D(-8, 0));
		//		poses.put(6, new Pose2D(-4, -4));
		//		poses.put(7, new Pose2D(-0.5, -6));
		//		poses.put(8, new Pose2D(-0.3, 0.2, Angle.deg(-35)));
		//		poses.put(9, new Pose2D(-0.8, 1.2));
		//		poses.put(10, new Pose2D(-4.75, 1.5));
		//		poses.put(11, new Pose2D(-6, 6));

		// Changed to fit other kickoff logic
		poses.put(1, new Pose2D(-10, 0));

		poses.put(2, new Pose2D(-8.5, 2));
		poses.put(3, new Pose2D(-8.5, -2));

		poses.put(4, new Pose2D(-6, 4.5));
		poses.put(5, new Pose2D(-6, 1.5));
		poses.put(6, new Pose2D(-6, -1.5));
		poses.put(7, new Pose2D(-6, -4.5));

		poses.put(8, new Pose2D(-3, 5.5));
		poses.put(9, new Pose2D(-3, -5.5));

		poses.put(10, new Pose2D(-1.75, 1));
		poses.put(11, new Pose2D(-1.75, -1));
	}
}
