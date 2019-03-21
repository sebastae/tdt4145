package ekt;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Exercise extends DatabaseObject{

    public static final String ID_COL_LABEL = "ExID";
    public static final String NAME_COL_LABEL = "name_";


    private int ID;
    private String name;

    public Exercise(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public static Exercise getFromDatabase(int id) throws SQLException {

        ConnectionManager manager = ConnectionManager.globalManager;
        ResultSet resultSet = manager.query("SELECT * FROM Ex WHERE " + ID_COL_LABEL + " = (?)", new Integer(id));

        if(resultSet.first()){
            return new Exercise(resultSet.getInt(ID_COL_LABEL), resultSet.getString(NAME_COL_LABEL));
        }

        return null;
    }

    public void save(){

    }

}
