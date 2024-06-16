package Libary;

import javax.swing.JFrame;

public class Admin extends User {

    public Admin(String name) {
        super(name);

        this.operations = new IOOperation[] {

                new ViewBooks(),
                new AddBook(),
                new DeleteBook(),
                new Search(),
                new DeleteAllData(),
                new ViewOrders(),
                new Exit(),
        };
    }

    public Admin(String name, String email, String phonenumber) {
        super(name, email, phonenumber);

        this.operations = new IOOperation[] {

                new ViewBooks(),
                new AddBook(),
                new DeleteBook(),
                new Search(),
                new DeleteAllData(),
                new ViewOrders(),
                new Exit(),
        };
    }

    @Override
    public void menu(Database database, User user) {

        String[] data = new String[7];

        data[0] =  "Visualizar Livros";
        data[1] =  "Adicionar Livro";
        data[2] =  "Deletar Livro";
        data[3] =  "Procurar";
        data[4] =  "Deletar Dados Salvos";
        data[5] =  "Visualizar Pedidos";
        data[6] =  "Sair";

        JFrame frame = this.frame(data, database, user);
        frame.setVisible(true);
    }

    public String toString() {
        return " Nome do usuário: " + name + "\n E-mail: " + email + "\n Telefone: " + phonenumber + "\n Cargo: Admin";

    }
}













