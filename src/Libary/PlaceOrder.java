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
 * A classe PlaceOrder configura uma interface gráfica que permite ao usuário realizar uma encomenda de livros.
 * Ela utiliza um layout de grade para organizar os componentes de entrada (nome do livro e quantidade) e os botões de ação (encomendar e cancelar).
 * O método oper monta essa interface e a exibe ao usuário, mas ainda não adicionamos a lógica dos botões, que será responsável por processar a encomenda ou cancelar a operação.
 */
public class PlaceOrder implements IOOperation{

    @Override
    public void oper(Database database, User user) {

        JFrame frame = Main.frame(400, 270);
        frame.setLayout(new BorderLayout());

        JLabel title = Main.title("Fazer Encomenda");
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(3, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        panel.setBackground(null);

        /**
         * @param Descrição para fazer o pedido
         */
        JLabel label = Main.label("Nome do Livro: ");
        JTextField name = Main.textfield();
        JLabel label2 = Main.label("Quantidade: ");
        JTextField qty = Main.textfield();
        JButton placeorder = Main.button("encomanda");
        JButton cancel = Main.button("Cancelar");

        panel.add(label);
        panel.add(name);
        panel.add(label2);
        panel.add(qty);
        panel.add(placeorder);
        panel.add(cancel);


        placeorder.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                /**
                 * @param Validação de dados. Se estao preenchidos de maneira correta e/ou os campos estão vazio.
                 */

                if(name.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "O nome do livro deve ser preechido!");
                    return;
                }

                if(qty.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Quantidade nao pode estar vazia!");
                    return;
                }

                try {
                    Integer.parseInt(qty.getText().toString());

                } catch (Exception event1) {
                    JOptionPane.showMessageDialog(new JFrame(), "A descrição de quantidade deve ser dita em número inteiros!");
                    return;
                }

                Order order = new Order();
                int i = database.getBook(name.getText().toString());

                if(i <= -1) {
                    JOptionPane.showMessageDialog(new JFrame(), "O livro não existe");

                } else {

                    /**
                     * @param Lógica de processamento de um pedido de livros
                     * Ele obtém o livro do banco de dados, configura o pedido com o livro e o usuário, calcula o preço
                     * total com base na quantidade solicitada, atualiza a quantidade disponível do livro no banco de dados,
                     * @return adiciona o pedido ao banco de dados, e finalmente, exibe uma mensagem de confirmação ao usuário e fecha a janela atual
                     *
                     * Este fluxo garante que o pedido seja registrado corretamente e que o estoque de livros seja atualizado de acordo
                     */
                    Book book = database.getBook(i);
                    order.setBook(book);
                    order.setUser(user);

                    int valueQt = Integer.parseInt(qty.getText().toString());
                    order.setBook(book);
                    order.setPrice(book.getPrice() * valueQt);

                    int bookindex = database.getBook(book.getName());
                    book.setQty(book.getQty() - valueQt);
                    database.addOrder(order, book, bookindex);
                    JOptionPane.showMessageDialog(new JFrame(), "Pedido feito com sucesso!!");
                    frame.dispose();
                }
            }
        });

        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
            }
        });

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}




