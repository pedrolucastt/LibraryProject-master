package Libary;

import javax.swing.JFrame;

public class NormalUser extends User{

    public NormalUser(String name) {
        super(name);
        this.operations = new IOOperation[] {
                /**
                 * atos relacionados a permissão do usuário
                 *
                 * this.operations: Refere-se ao atributo operations da instância atual de NormalUser
                 * new IOOperation[] ->
                 * @param Inicializa o atributo operations com um array de objetos que implementam a interface IOOperation
                 */

                new ViewBooks(),
                new Search(),
                new PlaceOrder(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }

    public NormalUser(String name, String email, String phonenumber) {

        super(name, email, phonenumber);

        /**
         *
         * Cada uma dessas operações teria sua própria implementação específica,
         * definindo como a operação deve ser executada no contexto do sistema
         */

        this.operations = new IOOperation[] {

                new ViewBooks(),
                new Search(),
                new PlaceOrder(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }

    /**
     *
     * @param database
     * @param user
     *
     * este método menu é uma implementação do método abstrato ou interface na classe User (ou outra classe relacionada),
     * que define o menu de opções disponíveis para um usuário
     */
    @Override
    public void menu(Database database, User user) {

                String[] data = new String[7]; // array de opções do menu inicial

                data[0] =  "Visualizar Livros";
                data[1] =  "Procurar";
                data[2] =  "Realizar Pedido";
                data[3] =  "Alugar Livro";
                data[4] =  "Taxa de Atraso de Entrega";
                data[5] =  "Devolver Livro";
                data[6] =  "Sair";
        /**
         * Chama um método frame, passando o array data, o objeto database, e o objeto user como parâmetros.
         * Este método frame cria um JFrame (janela) contendo as opções do menu
         */
        JFrame frame = this.frame(data, database, user);
                frame.setVisible(true);
    }

    public String toString() {
        return " Nome do usuário: " + name + "\n E-mail: " + email + "\n Telefone: " + phonenumber + "\n Cargo: Usuário padrão";
    }
}
















