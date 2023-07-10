package magma.agent.model.thoughtmodel.strategy.impl.strategies;

import magma.agent.model.thoughtmodel.IRoboCupThoughtModel;
import magma.agent.model.thoughtmodel.strategy.impl.roles.*;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class HofStrategy extends BaseStrategy {

    public static final String NAME = "HofStrat";

    public static Hof_Defender hofdef1, hofdef2;
    public static Hof_Midfield hofmid1, hofmid2, hofmid3, hofmid4;
    public static Hof_Wing hofwing1, hofwing2;
    public static Hof_Attacker hofatk1, hofatk2;


    public HofStrategy(IRoboCupThoughtModel thoughtModel) {

        super(NAME, thoughtModel);
        Vector3D v1 = new Vector3D(-0, 0, 0);
        Hof_Goalkeeper hofgoalkeeper = new Hof_Goalkeeper(thoughtModel.getWorldModel(), "hofgoalkeeper", 1, 0, 1.5);
        availableRoles.add(hofgoalkeeper);

        Vector3D v2 = new Vector3D(-5, 2, 0);
        Vector3D v3 = new Vector3D(-5, -2, 0);
        hofdef1 = new Hof_Defender(thoughtModel.getWorldModel(), "Verteidigung 1", 2, -2, 7, v2, FieldArea.UPPER);
        hofdef2 = new Hof_Defender(thoughtModel.getWorldModel(), "Verteidigung 2", 3, -2, 7, v3, FieldArea.LOWER);
        availableRoles.add(hofdef1);
        availableRoles.add(hofdef2);

        Vector3D v4 = new Vector3D(-2, 2.5, 0);
        Vector3D v5 = new Vector3D(-1, -1, 0);
        Vector3D v6 = new Vector3D(-1, -1, 0);
        Vector3D v7 = new Vector3D(-2, -2.5, 0);
        hofmid1 = new Hof_Midfield(thoughtModel.getWorldModel(), "Mittelfeld 1", 4, -1.5, 10, FieldArea.UPPER, v4);
        hofmid2 = new Hof_Midfield(thoughtModel.getWorldModel(),"Mittelfeld 2",5,-1.5,10, FieldArea.LOWER, v5);
        hofmid3 = new Hof_Midfield(thoughtModel.getWorldModel(),"Mittelfeld 3",6,-2,11, FieldArea.UPPER, v6);
        hofmid4 = new Hof_Midfield(thoughtModel.getWorldModel(),"Mittelfeld 4",7,-2,11, FieldArea.LOWER, v7);
        availableRoles.add(hofmid1);
        availableRoles.add(hofmid2);
        availableRoles.add(hofmid3);
        availableRoles.add(hofmid4);

        Vector3D v8 = new Vector3D(3, 5, 0);
        Vector3D v9 = new Vector3D(3, -5, 0);
        hofwing1 = new Hof_Wing(thoughtModel.getWorldModel(), "Wing 1", 8, -2, 11, FieldArea.UPPER, v8);
        hofwing2 = new Hof_Wing(thoughtModel.getWorldModel(),"Wing 2",9,-2,11, FieldArea.LOWER, v9);
        availableRoles.add(hofwing1);
        availableRoles.add(hofwing2);

        Vector3D v10 = new Vector3D(4, 2.5, 0);
        Vector3D v11 = new Vector3D(4, -2.5, 0);
        hofatk1 = new Hof_Attacker(thoughtModel.getWorldModel(), "Angreifer 1", 10, -1, 11, FieldArea.UPPER, v10);
        hofatk2 = new Hof_Attacker(thoughtModel.getWorldModel(),"Angreifer 2",11,-1,11, FieldArea.LOWER, v11);
        availableRoles.add(hofatk1);
        availableRoles.add(hofatk2);

    }
}
