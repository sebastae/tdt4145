package ekt;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Person {

    public static final String ID_COL_LABEL = "PersonID";
    public static final String NAME_COL_LABEL = "namePerson";
    public static final String CODE_COL_LABEL = "fireSiffer";

    private int ID;
    private String name;
    private String code;

    public Person(int id, String name, String code){
        this.ID = id;
        this.code = code;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static Person getFromDatabase(int id){
        ConnectionManager manager = ConnectionManager.globalManager;
        ResultSet resultSet = manager.query("SELECT * FROM Person WHERE " + ID_COL_LABEL +  " = (?)", new Integer(id));
        try {
            if(resultSet.first()){
                String n = resultSet.getString(NAME_COL_LABEL);
                String c = resultSet.getString(CODE_COL_LABEL);

                return new Person(id, n, c);
            } else {
                throw new IllegalArgumentException("The person with id #" + id + " is not in the database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
