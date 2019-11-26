package ltd.webbiskools.quizmgr.model;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;

public abstract class DatabaseAccessor {

    public static final String ANSWER_TABLE_NAME = "answer";
    public static final String QUESTION_TABLE_NAME = "question";
    public static final String QUIZ_TABLE_NAME = "quiz";
    public static final String REF_PERMISSIONS_TABLE_NAME = "ref_permissions";
    public static final String USER_DETAILS_TABLE_NAME = "user_details";

    @Value("${dbUrl}")
    protected String dbUrl;

    @Value("${dbUserName}")
    protected String dbUserName;

    @Value("${dbPassword}")
    protected String dbPassword;

    protected ResultSet execute(String sql) {
        try {
            Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            Statement statement = conn.createStatement();
            boolean gotSet = statement.execute(sql);
            if (gotSet) {
                return statement.getResultSet();
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    protected ResultSet selectFrom(String tableName, String... fields) {
        StringBuilder sb =
            new StringBuilder("SELECT ")
            .append(String.join(",", fields))
            .append(" FROM ")
            .append(tableName);
        return execute(sb.toString());
    }

}
