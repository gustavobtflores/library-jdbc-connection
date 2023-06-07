import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbFunctions {
    public Connection connect_to_db(String dbname, String user, String pass){
        Connection conn=null;

        try {
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname, user, pass);

            if(conn!=null){
                System.out.println("Connection Established");
            }else {
                System.out.println("Connection failed");
            }


        }catch(Exception e){
            System.out.println(e);
        }

        return conn;
    }

    public void insert_row(Connection conn,String table_name, String name, String cpf){
        Statement statement;

        try {
            String query = String.format("INSERT INTO %s(nome, cpf) values('%s', '%s');", table_name, name, cpf);
            statement = conn.createStatement();
            statement.executeUpdate(query);

            System.out.println("Row inserted");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void delete_row(Connection conn,String table_name, String cpf){
        Statement statement;

        try {
            String query = String.format("DELETE FROM %s WHERE cpf='%s'", table_name, cpf);
            statement = conn.createStatement();
            statement.executeUpdate(query);

            System.out.println("Row updated");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void list(Connection conn,String table_name){
        Statement statement;

        try {
            String query = String.format("SELECT * FROM %s", table_name);
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()){
                System.out.println("Nome: " + rs.getString("nome") + ", CPF: " + rs.getString("cpf"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void update(Connection conn,String table_name, String cpf, String nome){
        Statement statement;

        try {
            String query = String.format("UPDATE %s SET nome = '%s' WHERE cpf = '%s' ", table_name, nome, cpf);
            statement = conn.createStatement();
            statement.executeUpdate(query);

            System.out.println("Row deleted");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
