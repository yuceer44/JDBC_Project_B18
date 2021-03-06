package com.cybertek.jdbc.day2;

import java.sql.*;

public class DB_Utility {
    // adding static field so we can access in all static methods
     private static Connection conn ;
     private static  ResultSet rs ;

     /*
     * a method to display all the data in the result set
     *
     * */
     public static void displayAllData(){

         // get the first row data  | without knowing the column names
         int colCount = DB_Utility.getColumnCNT() ;
         // in order to get whole result cursor must be at before first location !

         try {
             // in order to start from beginning , we should be at beforefirst location
             rs.beforeFirst(); // this is for below loop to work
             while (rs.next() == true) { // row iteration

                 for (int i = 1; i <= colCount; i++) { // column iteration
                     System.out.print(rs.getString(i) + "\t");
                 }
                 System.out.println(); /// adding a blank line for next line
             }
             // now the cursor is at after last location
             // move it back to before first location so we can have no issue calling the method again
             rs.beforeFirst(); // this is for next method that might need to be at before first location

         }catch(SQLException e){
             System.out.println("ERROR WHILE GETTING ALL DATA");
             e.printStackTrace();
         }

     }



     /*
     * a method to get the column count of the current ResultSet
     *
     *   getColumnCNT()
     *
     * */
     public static int getColumnCNT(){

         int colCount = 0  ;

         try {
             ResultSetMetaData rsmd = rs.getMetaData();
             colCount = rsmd.getColumnCount() ;

         } catch (SQLException e) {
             System.out.println("ERROR WHILE COUNTING THE COLUMNS");
             e.printStackTrace();
         }

        return colCount ;
     }



    /*
     * a static method to create connection
     * with valid url and username password
     * */
    public static void createConnection() {

        String connectionStr = "jdbc:oracle:thin:@52.71.242.164:1521:XE";
        String username = "hr";
        String password = "hr";

        try {
            conn = DriverManager.getConnection(connectionStr, username, password);
            System.out.println("CONNECTION SUCCESSFUL");
        } catch (SQLException e) {
            System.out.println("CONNECTION HAS FAILED!");
            e.printStackTrace();
        }

    }

     /*
     * a static method to get the ResultSet object
     * with valid connection by executing query
     * */
    public static ResultSet runQuery(String query){

        try {
            Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs =  stmnt.executeQuery(query) ;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  rs ;
    }







}
