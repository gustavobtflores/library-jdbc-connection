import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DbFunctions db = new DbFunctions();
        Connection conn = db.connect_to_db("library", "postgres", "admin");
        int option = 0;

        while(true){
            System.out.println("1 - Inserir funcionário\n2 - Deletar funcionário\n3 - Listar funcionário\n4 - Atualizar funcionário");
            System.out.println("Digite uma opção: ");
            option = sc.nextInt();

            switch(option){
                case 1:
                    sc.nextLine();
                    System.out.println("Digite o nome do funcionário: ");
                    String name = sc.nextLine();
                    System.out.println("Digite o CPF do funcionário: ");
                    String cpf = sc.nextLine();

                    db.insert_row(conn, "Funcionario", name, cpf);
                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("Qual o CPF do funcionário que deseja apagar: ");
                    String cpfToDelete = sc.nextLine();
                    db.delete_row(conn, "Funcionario", cpfToDelete);
                    break;
                case 3:
                    db.list(conn, "Funcionario");
                    break;
                case 4:
                    sc.nextLine();
                    System.out.println("Qual o CPF do funcionário que deseja mudar o nome: ");
                    String cpf = sc.nextLine();
                    System.out.println("Qual o novo nome: ");
                    String newName = sc.nextLine();
                    db.uptade(conn, "Funcionario", cpf, newName);
                    break;
            }
        }
    }
}