package ekt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager extends DBConn {

    public static final ConnectionManager globalManager = new ConnectionManager();

    public ConnectionManager() {
        connect();
    }

    public ResultSet query(String sql, Object... parameters) {
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            for (int i = 0; i < parameters.length; i++) {
                this.setParam(stmt, i+1, parameters[i]);
            }

            ResultSet resultSet = stmt.executeQuery();
            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void setParam(PreparedStatement stmt, int index, Object param) throws SQLException {
        stmt.setObject(index, param);
    }


}
