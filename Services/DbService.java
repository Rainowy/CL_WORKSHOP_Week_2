package Services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbService {

    private static String dbUrl;
    private static String dbUser = "root";
    private static String dbPass = "sabaka";

    private static void initParams(String dbName) {
        dbUrl = "jdbc:mysql://192.168.1.108/" + dbName + "?useSSL=false&characterEncoding=utf8&useLegacyDatetimeCode=false&serverTimezone=UTC&useOldAliasMetadataBehavior=true";
    }

    public static Connection getConnection(String dbName) throws SQLException {

        initParams(dbName);

        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }

    //execute query without generated keys
    public static void executeQuery(String query, String[] params, String dbName) throws SQLException {

        Connection conn = getConnection(dbName);

        PreparedStatement st = conn.prepareStatement(query);

        setParams(params, st);

        st.executeUpdate();
    }

    //execute query and get generated keys
    public static int executeInsert(String query, String[] params, String dbName) throws Exception {

        Connection conn = getConnection(dbName);

        String[] generatedKeys = {"ID"};

        PreparedStatement st = conn.prepareStatement(query, generatedKeys);

        setParams(params, st);

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            int newId = rs.getInt(1);
            return newId;
        } else {
            throw new Exception("No new ID");
        }
    }

    public static List<String[]> getData(String query, String[] params, String dbName) throws SQLException {

        Connection conn = getConnection(dbName);

        PreparedStatement st = conn.prepareStatement(query);

        setParams(params, st);

        ResultSet rs = st.executeQuery();

        ResultSetMetaData data = rs.getMetaData();

        List<String[]> result = new ArrayList<>();

        while (rs.next()) {
            String[] row = new String[data.getColumnCount()];
            for (int i = 1; i <= data.getColumnCount(); i++) {
                String currentColumnName = data.getColumnName(i);
                row[i - 1] = rs.getString(currentColumnName);
            }
            result.add(row);
        }
        return result;
    }

    private static void setParams(String[] params, PreparedStatement st) throws SQLException {
        if (params != null) {
            int paramNumber = 1;
            for (String param : params) {
                st.setString(paramNumber, param);
                paramNumber++;
            }
        }
    }


}
