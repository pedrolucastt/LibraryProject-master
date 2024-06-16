package Libary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * a classe ViewOrders cria uma interface gráfica para visualizar os pedidos no sistema
 *
 * definem a estrutura básica da interface gráfica para visualizar os pedidos e
 * permitem que o usuário insira o nome do livro desejado para visualizar os pedidos associados a esse livro.
 */
public class ViewOrders implements IOOperation{

    @Override
    public void oper(Database database, User user) {

        JFrame frame = Main.frame(400, 210);
        frame.setLayout(new BorderLayout());

        JLabel title = Main.title("Visualizar Pedidos");
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2,2,15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
        panel.setBackground(null);

        JLabel label = Main.label("Nome do Livro");
        JTextField name = Main.textfield();

        JButton view = Main.button("VIsualizar Pedidos");
        JButton cancel = Main.button("Cancelar");

        panel.add(label);
        panel.add(name);
        panel.add(view);
        panel.add(cancel);


        /**
         * --> a ação realizada quando o botão "Visualizar Pedidos" é clicado em uma interface gráfica. Primeiramente, ele verifica se o campo onde o
         * usuário deve inserir o nome do livro está vazio e exibe uma mensagem de aviso se estiver.
         *
         *
         * --> Em seguida, ele procura o livro no banco de dados com base no nome inserido pelo usuário. Se o livro for encontrado,
         * o sistema exibe os pedidos relacionados a esse livro e fecha a janela atual.
         *
         *
         * --> Caso o livro não seja encontrado, uma mensagem informando isso é exibida ao usuário.
         */
        view.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(name.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "O nome do livro deve ser preenchido");
                    return;
                }

                int i = database.getBook(name.getText().toString());

                if(i >- 1) {
                    viewOrders(name.getText().toString(), database);
                    frame.dispose();

                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "O livro não existe!");
                }
            }
        });

        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     *
     * @param bookname
     * @param database
     *
     *
     * --> O método viewOrders é responsável por calcular o número de linhas necessárias para exibir os pedidos relacionados a um livro específico.
     * Ele recebe o nome do livro (bookname) e o banco de dados (database) como parâmetros.
     *
     * --> Dentro do método, inicialmente, é definido um contador de linhas (rows) com valor inicial de 1.
     * Em seguida, há um loop que percorre todos os pedidos no banco de dados.
     *
     * --> Para cada pedido, verifica-se se o nome do livro associado a ele corresponde ao nome do livro fornecido como entrada.
     * Se a correspondência for encontrada, incrementa-se o contador de linhas (rows).
     *
     * --> Ao final do loop, o valor de rows representa o número total de pedidos relacionados ao livro fornecido.
     */
    private void viewOrders(String bookname, Database database) {

        int rows = 1;

        for(Order order : database.getAllOrders()) {

            if(order.getBook().getName().matches(bookname)) {
                rows++;
            }
        }

        int height = rows * 55 + 100;

        JFrame frame = Main.frame(500, height);

        JLabel title = Main.title("Visualizar Pedidos");
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, 4, 0, 0));
        panel.setBackground(null);

        String[] row1 = new String[] { "Livro", "User", "Quantidade", "Preço" };

        /**
         *
         --> Esses loops são responsáveis por adicionar rótulos (JLabel) ao painel (panel)
         para exibir informações sobre os pedidos de livros.


         --> No primeiro loop, são percorridos os elementos do array row1, que contém os cabeçalhos das colunas para a exibição dos dados dos pedidos. Para cada elemento do array,
         é criado um rótulo com o texto correspondente e adicionado ao painel.


         --> No segundo loop, são percorridos todos os pedidos no banco de dados. Para cada pedido, verifica-se se o nome do livro associado a ele
         corresponde ao nome do livro fornecido como entrada (bookname).


         --> Se houver uma correspondência, são criados rótulos para exibir o nome do livro,
            o nome do usuário que fez o pedido, a quantidade de livros solicitados e o preço total do pedido.
            Esses rótulos são então adicionados ao painel para exibição.
         */
        for(String stringValue : row1) {

            JLabel label = label(stringValue);
            panel.add(label);
        }

        for(Order order : database.getAllOrders()) {

            if(order.getBook().getName().matches(bookname)) {

                JLabel label1 = label(order.getBook().getName());
                JLabel label2 = label(order.getUser().getName());
                JLabel label3 = label(String.valueOf(order.getQty()));
                JLabel label4 = label(String.valueOf(order.getPrice()));

                panel.add(label1);
                panel.add(label2);
                panel.add(label3);
                panel.add(label4);
            }
        }

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     *
     * @param text
     * @retur Essa função é útil para criar rótulos com uma aparência consistente e pode ser reutilizada em várias partes
     * do código onde rótulos semelhantes são necessários.
     */
    private JLabel label(String text) {

        JLabel label = Main.label(text);
        label.setBackground(Color.white);
        label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        label.setOpaque(true);

        return label;
    }
}
