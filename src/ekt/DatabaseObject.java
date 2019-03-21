package ekt;

import java.sql.SQLException;

public abstract class DatabaseObject {

    public static DatabaseObject getFromDatabase(int id) throws SQLException {
        return null;
    }

    public abstract void save();

}
