/* Copyright 2008 - 2021 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under GPL-3 License (see gpl.txt).
 */

package magma.agent.model.thoughtmodel.strategy.impl.strategies;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import magma.agent.model.thoughtmodel.IRoboCupThoughtModel;
import magma.agent.model.thoughtmodel.strategy.ITeamStrategy;

public class StrategyConfigurationHelper
{
	public static final String DEFAULT_STRATEGY = HofStrategy.NAME;     //Funktionert das?

	@FunctionalInterface
	public interface StrategyConstructor {
		ITeamStrategy create(IRoboCupThoughtModel thoughtModel);
	}

	public static final Map<String, StrategyConstructor> STRATEGIES;

	static
	{
		Map<String, StrategyConstructor> strategies = new LinkedHashMap<>();
		strategies.put(HofStrategy.NAME, HofStrategy::new);
		STRATEGIES = Collections.unmodifiableMap(strategies);
	}
}
