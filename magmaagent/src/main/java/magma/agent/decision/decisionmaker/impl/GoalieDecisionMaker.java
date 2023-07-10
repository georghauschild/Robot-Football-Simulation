/* Copyright 2008 - 2021 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under GPL-3 License (see gpl.txt).
 */

package magma.agent.decision.decisionmaker.impl;

import hso.autonomy.agent.decision.behavior.BehaviorMap;
import hso.autonomy.agent.decision.behavior.IBehavior;
import magma.agent.decision.behavior.IBehaviorConstants;
import magma.agent.decision.behavior.IKeepBehavior;
import magma.agent.decision.behavior.base.KeepEstimator;
import magma.agent.model.thoughtmodel.IRoboCupThoughtModel;
import magma.agent.model.worldmodel.IRoboCupWorldModel;

import java.util.ArrayList;
import java.util.Arrays;

public class GoalieDecisionMaker extends SoccerDecisionMaker
{
	private transient KeepEstimator keepEstimator;

	public GoalieDecisionMaker(BehaviorMap behaviors, IRoboCupThoughtModel thoughtModel)
	{
		super(behaviors, thoughtModel);
		keepEstimator = new KeepEstimator(thoughtModel);

		// Füge ein neues Item zur behaviorSuppliers-Liste hinzu
		behaviorSuppliers.add(this::goalieSpecificBehavior);
	}

	@Override
	protected String performFocusBall()
	{
		IBehavior behavior = behaviors.get(IBehaviorConstants.FOCUS_BALL_GOALIE);
		behavior.perform();
		return null;
	}

	protected String goalieSpecificBehavior(){
		return IBehaviorConstants.CELEBRATE;
	};
	@Override
	protected String move()
	{
		if (currentBehavior instanceof IKeepBehavior) {
			return null;
		}

		String keepBehavior = keepEstimator.decideKeepBehavior();
		if (keepBehavior != null) {
			currentBehavior.abort();
			return keepBehavior;
		}

	//	if (getWorldModel().isBallInCriticalArea() && getThoughtModel().isClosestToBall()) {
	//		return IBehaviorConstants.ATTACK;
	//	}

		if (getWorldModel().isBallInCriticalArea()) {
			return IBehaviorConstants.ATTACK;
		}

		return IBehaviorConstants.GOALIE_POSITIONING;
	}

	@Override
	protected boolean isMyTurnInPenalties()
	{
		return getWorldModel().getPenaltyState() == IRoboCupWorldModel.PenaltyState.HOLD;
	}
}