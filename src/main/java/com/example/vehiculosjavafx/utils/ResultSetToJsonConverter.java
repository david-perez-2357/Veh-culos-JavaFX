package com.example.vehiculosjavafx.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetToJsonConverter {

    public static JSONArray convertResultSetToJson(ResultSet resultSet) throws SQLException {
        JSONArray jsonArray = new JSONArray();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            JSONObject obj = new JSONObject();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                Object value = resultSet.getObject(i);
                obj.put(columnName, value);
            }

            jsonArray.put(obj);
        }

        return jsonArray;
    }
}
