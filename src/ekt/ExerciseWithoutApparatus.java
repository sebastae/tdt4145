package ekt;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseWithoutApparatus extends DatabaseObject{

    public static final String ID_COL_LABEL = "ID";
    public static final String INSTRUCTIONS_COL_LABEL = "Instruction";
    public static final String EXERCISE_COL_LABEL = "ExID";

    private int ID;
    private Exercise exercise;
    private String instructions;

    public ExerciseWithoutApparatus(int ID, Exercise exercise, String instructions) {
        this.ID = ID;
        this.exercise = exercise;
        this.instructions = instructions;
    }

    public int getID() {
        return ID;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public String getInstructions() {
        return instructions;
    }

    public static ExerciseWithoutApparatus getFromDatabase(int id) throws SQLException {
        ConnectionManager manager = ConnectionManager.globalManager;
        ResultSet resultSet = manager.query("SELECT * FROM NoApparatus WHERE " + ID_COL_LABEL + " = (?)", new Integer(id));

        if (resultSet.first()) {
            return new ExerciseWithoutApparatus(resultSet.getInt(ID_COL_LABEL),
                    Exercise.getFromDatabase(resultSet.getInt(EXERCISE_COL_LABEL)),
                    resultSet.getString(INSTRUCTIONS_COL_LABEL));
        }

        return null;
    }

    public void save() {

    }

}
