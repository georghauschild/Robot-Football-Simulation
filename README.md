# Robot-Football-Simulation
![RoboCupFullBanner](https://github.com/georghauschild/Robot-Football-Simulation/assets/37111215/25c9135b-c01b-40ba-8d2c-471205c532f7)
This project is forked from [magmaoffenburg base code](https://github.com/magmaOffenburg/magmaRelease). It represents a semester project for a Robotics course, with my primary focus on creating dynamic, responsive formations for a soccer robot game. The project serves as an introduction to robotics and the functions described in the readme file reflect only my contribution to the group project.
This project is an extension of the original project by [Magma Offenburg](https://github.com/magmaOffenburg/magmaRelease) and represents a semester-long effort within a robotics course. My main goal of this project was to innovate upon the original design by developing new, responsive formations for soccer robots, which adapt to the ongoing state of the game and implementing a custom goalkeeper. Furthermore, we've also implemented other unique roles, behaviours, animations strategies and architecture changes.

## Goalkeeper
The goalkeeper role has been specifically designed to guard the goalpost. Our implementation features strategies to maximize coverage of the goalpost.
One of the primary responsibilities of a goalkeeper is to position themselves correctly in relation to the game ball and their own goal. By doing so, they create a defensive barrier that makes it difficult for the opposing team to score. This positioning is determined by calculating a line between the ball and our goal, and then selecting a point on this line where the goalkeeper should stand.  
The chosen point on the line should be close to the goal to ensure its protection. However, it is also important that the point is not too close to the goal, as this could lead to collisions between the goalkeeper and the goal structure. Such collisions can result in falling over and get stuck and will compromise the goalkeeper's ability to defend the goal effectively.

## Responsive Formation
The dynamically adapting formation, which is based on the concept of "shifting", is a tactic commonly used in professional football. Shifting refers to the movement of individual players as well as the collective unit with the aim of either reducing space around the ball or closing gaps in the defensive line. The concept of shifting formation revolves around the idea of maintaining a compact defensive structure while simultaneously preventing the opposition from exploiting any weaknesses. This is achieved by constantly adjusting the position of the players in relation to the position of the ball to ensure optimal coverage of key areas on the pitch.  
> "Shifting is the movement of the individual as well as the collective, which has the aim of either reducing the space to the ball or closing holes in one's own defensive formation." -[spielverlagerung.de](https://spielverlagerung.de/verschieben/) (translated)

### Simplified Implementation Process
The following simplified process outlines the approximate sequence of position calculation. The detailed process can be found in the code.

During the initialization of the players, each one is assigned an individual offset. This offset describes the formation position that each player should take relative to the ball. For example, the left sided defender should always be positioned significantly behind the ball and a little bid left of it during the match.   
```
Vector3D offsetLeftDefender = new Vector3D(-5, 2, 0);
// -5 units on x-axis for staying behind the ball
// 2 units on y-axis for staying left of the ball
```

On the other hand, the striker should generally position themselves in front of the ball to take an offensive position.  
```
Vector3D offsetRightAttacker = new Vector3D(4, -2.5, 0);
// 4 units on x-axis for staying in front of the ball
// -2.5 units on y-axis for staying right of the ball
```

The actual position on the playing field is calculated as follows: Ball position + Offset.  
This means that the player moves towards the ball position, which is modified individually by the offset.
```
Vector3D ballPosition = worldModel.getBall().getPosition()
Vector3D targetPosition = ballPosition.add(offset);
```

Afterwards, it will be checked whether the target position is still within the two goals.
```
Vector3D targetPosition2 = targetPosition;

if (targetPosition.getX() >= enemyGoalPosition.getX()){
			targetPosition = new Vector3D(targetPosition2.getX()-3,targetPosition2.getY(),0);
}
if (targetPosition.getX() <= ownGoalPosition.getX()){
			targetPosition = new Vector3D(targetPosition2.getX()+3,targetPosition2.getY(),0);
}
```

After the final calculation, the player is assigned to his new position.
```
WalkToPosition walkToPosition = (WalkToPosition) behaviors.get(IBehaviorConstants.WALK_TO_POSITION);
Pose2D newPose = new Pose2D(playerTargetPosition);
return walkToPosition.setPosition(
       new PoseSpeed2D(newPose, Vector2D.ZERO), 90, false, true, 0.8, IKDynamicWalkMovement.NAME_LOW_ACC);
```

It is important to note that players are not naturally bound to this position. They should only return to this optimized position if they do not have any other commands. Therefore, it is also declared as PassivePositioning and included in the Move Sequence. Other actions such as going to the ball and shooting take priority.
```
protected transient List<Supplier<String>> behaviorSuppliers =
    new ArrayList<>(Arrays.asList(this::calibrateCamera, this::performSay, this::beamHome,
        this::sendPassCommand, this::getUp, this::reactToGameEnd, this::performFocusBall, this::getReady,
        this::waitForGameStart, this::waitForOpponentActions, this::searchBall, this::move));
```
![RoboCupLogo](https://github.com/georghauschild/Robot-Football-Simulation/assets/37111215/e825a8ce-2fa1-4eed-993f-b30c3988bbf4)
