package Services;

import java.sql.*;

public class DbService {

   private static String dbUrl;
   private static String dbUser = "root";
   private static String dbPass = "sabaka";

   private static void initParams (String dbName){
       dbUrl = "jdbc:mysql://192.168.1.108/" + dbName + "?useSSL=false&characterEncoding=utf8&useLegacyDatetimeCode=false&serverTimezone=UTC&useOldAliasMetadataBehavior=true";
   }

   public static Connection getConnection(String dbName) throws SQLException {

      initParams(dbName);

     return DriverManager.getConnection(dbUrl,dbUser,dbPass);
   }

   public static void executeQuery(String query, String[] params,String dbName) throws SQLException{

      Connection conn = getConnection(dbName);

      PreparedStatement st =conn.prepareStatement(query);

      setParams(params, st);

      st.executeUpdate();
   }

   private static void setParams(String[] params, PreparedStatement st) throws SQLException {
      if(params != null){
         int paramNumber = 1;
         for(String param : params){
            st.setString(paramNumber,param);
            paramNumber++;
         }
      }
   }


}
