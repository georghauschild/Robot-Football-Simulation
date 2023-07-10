# Robot-Football-Simulation
![RoboCupFullBanner](https://github.com/georghauschild/Robot-Football-Simulation/assets/37111215/25c9135b-c01b-40ba-8d2c-471205c532f7)
This project is forked from [magmaoffenburg base code](https://github.com/magmaOffenburg/magmaRelease). It represents a semester project for a Robotics course, with my primary focus on creating dynamic, responsive formations for a soccer robot game. The project serves as an introduction to robotics and the functions described in the readme file reflect only my contribution to the group project.
This project is an extension of the original project by [Magma Offenburg](https://github.com/magmaOffenburg/magmaRelease) and represents a semester-long effort within a robotics course. My main goal of this project was to innovate upon the original design by developing new, responsive formations for soccer robots, which adapt to the ongoing state of the game and implementing a custom goalkeeper. Furthermore, we've also implemented other unique roles, behaviours, animations strategies and architecture changes.

## Goalkeeper
The goalkeeper role has been specifically designed to guard the goalpost. Our implementation features strategies to maximize coverage of the goalpost.
One of the primary responsibilities of a goalkeeper is to position themselves correctly in relation to the game ball and their own goal. By doing so, they create a defensive barrier that makes it difficult for the opposing team to score. This positioning is determined by calculating a line between the ball and our goal, and then selecting a point on this line where the goalkeeper should stand.  
The chosen point on the line should be close to the goal to ensure its protection. However, it is also important that the point is not too close to the goal, as this could lead to collisions between the goalkeeper and the goal structure. Such collisions can result in falling over and get stuck and will compromise the goalkeeper's ability to defend the goal effectively.
![RoboCupLogo](https://github.com/georghauschild/Robot-Football-Simulation/assets/37111215/e825a8ce-2fa1-4eed-993f-b30c3988bbf4)

