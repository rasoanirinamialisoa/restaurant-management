package com.example.restaurantmanagement.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SetParameter {
    public static <T> void setParameter(PreparedStatement preparedStatement, int index, T value) throws SQLException {
        if (value == null) {
            preparedStatement.setNull(index, java.sql.Types.NULL);
        } else if (value instanceof String) {
            preparedStatement.setString(index, (String) value);
        } else if (value instanceof Integer) {
            preparedStatement.setInt(index, (Integer) value);
        } else if (value instanceof Double) {
            preparedStatement.setDouble(index, (Double) value);
        } else if (value instanceof java.sql.Date) {
            preparedStatement.setDate(index, (java.sql.Date) value);
        } else if (value instanceof java.sql.Timestamp) {
            preparedStatement.setTimestamp(index, (java.sql.Timestamp) value);
        } else {
            throw new SQLException("Type de paramètre non pris en charge: " + value.getClass().getName());
        }
    }

}
