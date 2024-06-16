package Libary;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
 * A classe ReturnBook é responsável por criar a interface gráfica e lidar com a lógica de devolução de livros. Ela cria um frame com campos para inserir o nome do livro e botões para devolver o livro ou cancelar a operação. A lógica de devolução verifica se o livro existe no banco de dados e atualiza a quantidade disponível do livro,
 * enquanto mensagens apropriadas são exibidas ao usuário para informar o sucesso ou falha da operação
 */

public class ReturnBook implements IOOperation {

        @Override
    public void oper(Database database, User user) {

            JFrame frame = Main.frame(400, 210);
            frame.setLayout(new BorderLayout());

            JLabel title = Main.title("Devolver Livro");
            frame.getContentPane().add(title, BorderLayout.NORTH);

            JPanel panel = new JPanel(new GridLayout(2,2,15,15));
            panel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
            panel.setBackground(null);


            JLabel label = Main.label("Nome do Livro: ");
            JTextField name = Main.textfield();
            JButton returnbook = Main.button("Devolver Livro");
            JButton cancel = Main.button("Cancelar");

            panel.add(label);
            panel.add(name);
            panel.add(returnbook);
            panel.add(cancel);

            /**
             * O propósito desse trecho de código é garantir que o campo de texto onde o usuário deve inserir
             * o nome do livro não esteja vazio antes de continuar com o processo de devolução do livro.
             * Se o campo estiver vazio, uma mensagem de erro é mostrada e o processo é interrompido
             */
            returnbook.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent event) {

                    if(name.getText().toString().matches("")) {
                        JOptionPane.showMessageDialog(new JFrame(), "O nome do livro deve ser preenchido!");
                        return;
                    }
                    /**
                     * Este trecho de código permite que o usuário devolva um livro, verificando primeiro se há algum empréstimo pendente para o livro e usuário especificados. Se houver,
                     * o livro é marcado como devolvido no banco de dados e uma mensagem é exibida ao usuário
                     */
                    if(!database.getBorrowingValue().isEmpty()) {

                        for(Borrowing borrowValue : database.getBorrowingValue()) {
                            if(borrowValue.getBook().getName().matches(name.getText().toString()) &&
                               borrowValue.getUser().getName().matches(user.getName())) {


                                Book book = borrowValue.getBook();

                                int i = database.getAllBooks().indexOf(book);

                                /**
                                 *
                                 * Se houver atraso na entrega, exibe uma mensagem indicando ao usuário
                                 * que ele está em atraso e informa a taxa devido ao atraso
                                 */
                                if(borrowValue.getDaysLeft() > 0) {
                                    JOptionPane.showMessageDialog(new JFrame(), "Voce está em atraso com sua entrega!\n" +
                                    "Voce devera pegar " + Math.abs(borrowValue.getDaysLeft() * 50) + " de taxa");
                                }

                                /**
                                 *
                                 * Atualiza o status do livro, marcando-o como disponível para empréstimo.
                                 * Remove o empréstimo do banco de dados, indicando que o livro foi devolvido.
                                 */
                                book.setBrwcopies(book.getBrwcopies() + 1);
                                database.returnBook(borrowValue, book, i);
                                JOptionPane.showMessageDialog(new JFrame(), "O livro foi devolvido, obrigado!");
                                frame.dispose();
                                break;

                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Voce nao alugou esse livro!");
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Voce nao alugou esse livro!");
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
















