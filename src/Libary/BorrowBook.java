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



public class BorrowBook implements IOOperation {

    /**
     * Metodo "oper" configura uma janela "JFrame" usada para emprestimos de livros.
     * Um botão parar confirmar o emprestimo e/ou cancelar a operação
     *
     */

    @Override
    public void oper(Database database, User user) {

        JFrame frame = Main.frame(400, 210);
        frame.setLayout(new BorderLayout());

        JLabel title = Main.title("Livro de Emprestimo: ");
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        panel.setBackground(null);

        JLabel label = Main.label("Nome do Livro: ");
        JTextField name = Main.textfield();
        JButton borrow = Main.button("Livro de Emprestimo");
        JButton cancel = Main.button("Cancelar");

        panel.add(label);
        panel.add(name);
        panel.add(borrow);
        panel.add(cancel);


        /**
         * @param InterfaceGrafica implementada para a parte de emprestimos de livros
         *
         * - Verifica se o nome do livro foi fornecido e se o livro existe no banco de dados,
         *   se o usuario já alugou o livro anterioramente e se o livro tem cópias disponíveis.
         *
         */
        borrow.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(name.getText().toString().matches("")) {

                    JOptionPane.showMessageDialog(new JFrame(), "O nome do livro deve ser preenchido!");
                    return;
                }

                int i = database.getBook(name.getText().toString());


                /**
                 Obtém o livro do banco de dados e inicializa uma variável n como true,
                 que será usada para verificar se o usuário já alugou o livro.
                 */
                if(i > -1) {
                    Book book = database.getBook(i);
                    boolean n =  true;

                    /**
                     Percorre todos os empréstimos no banco de dados e verifica se o usuário atual já alugou o livro.
                     Se já tiver alugado, define n como false e exibe uma mensagem de aviso.
                     */
                    for(Borrowing b : database.getBorrowingValue()) {
                        if(b.getBook().getName().matches(name.getText().toString()) &&
                        b.getUser().getName().matches(user.getName())) {

                            n = false;

                            JOptionPane.showMessageDialog(new JFrame(), "Voce já alugou esse livro antes!");
                        }
                    }

                    /**
                     *  Se o usuário não alugou o livro anteriormente (n é true) e o livro tem cópias disponíveis para empréstimo (book.getBrwcopies() > 1),
                     *  cria um novo empréstimo (Borrowing), atualiza o número de cópias emprestadas,
                     *  adiciona o empréstimo ao banco de dados, exibe uma mensagem de confirmação com a data de devolução e fecha a janela.
                     */
                    if(n) {
                        if(book.getBrwcopies() > 1) {

                            Borrowing borrowing = new Borrowing(book, user);
                            book.setBrwcopies(book.getBrwcopies() - 1);
                            database.borrowBook(borrowing, book, i);

                            JOptionPane.showMessageDialog(new JFrame(), "Voce deve fazer a devolução do livro em 14 dias a partir de agora\n" +
                                    "Prazo final: " + borrowing.getFinish() + "\n Aproveite!");
                            frame.dispose();

                        } else {
                            JOptionPane.showMessageDialog(new Frame(), "Esse livro não está disponivel para alugar no momento");
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "O livro selecionado não existe");
                }
            }
        });

        /**
             Define que quando o botão for clicado, a janela (frame) será fechada.
         */
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










