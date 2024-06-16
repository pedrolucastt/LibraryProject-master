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
     * Classe responsavel por calcular a multa de um livro atrasado.
     */

public class CalculateFine implements IOOperation {

    @Override
    public void oper(Database database, User user) {

        JFrame frame = Main.frame(400, 210);
        frame.setLayout(new BorderLayout());

        JLabel title = Main.title("Calcular Taxa");
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2,2,15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
        panel.setBackground(null);

        /**
         Cria um JLabel para o texto "Nome do Livro:" usando um método label da classe Main.
         */

        JLabel label = Main.label("Nome do Livro: ");
        JTextField name = Main.textfield();

        /**
         Cria dois JButtons, um para calcular a taxa e
         outro para cancelar usando um método button da classe Main.
         */
        JButton calc = Main.button("Calcular Taxa");
        JButton cancel = Main.button("Cancelar");

        panel.add(label);
        panel.add(name);
        panel.add(calc);
        panel.add(cancel);

        calc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {


                /**
                 Verifica se o campo de texto 'name' está vazio
                 Se estiver vazio, exibe uma mensagem de erro e retorna, interrompendo a execução do restante do código
                 */
                if(name.getText().toString().matches("")) {

                    JOptionPane.showMessageDialog(new JFrame(), "O nome do livro deve ser preenchido!");
                    return;
                }

                /**
                 * @param inicializa uma variável value com 'true'.
                 * @return verifica se o livro foi encontrado no banco de dados
                 * */
                boolean value = true;


                /**
                 *  agrega sobre todos os empréstimos (Borrowing) presentes no banco de dados (database)
                 * */
                for(Borrowing borrowingValue : database.getBorrowingValue()) {

                    /**
                     * @param Verifica se o nome do livro no empréstimo (borrowingValue.getBook().getName()) corresponde ao nome inserido pelo usuário
                     * @param Verifica se o nome do usuário no empréstimo (borrowingValue.getUser().getName()) corresponde ao nome do usuário atual
                     * */
                    if(borrowingValue.getBook().getName().matches(name.getText().toString()) &&
                       borrowingValue.getUser().getName().matches(user.getName())) {

                        if(borrowingValue.getDaysLeft() > 0) {
                            JOptionPane.showMessageDialog(new JFrame(), "Retorno em atraso!\n" +
                                    "Voce tera que pagar " + borrowingValue.getDaysLeft() * 50 + " de taxa de atraso");

                            frame.dispose();

                        } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Voce não terá que pagar nehuma taxa.");
                                frame.dispose();
                        }

                        value = false;
                        break;
                    }
                }

                if(value) {
                    JOptionPane.showMessageDialog(new JFrame(), "Voce não alugou esse livro!");
                }
            }
        });

            cancel.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent event) {
                    frame.dispose();
                }
            });
    }
}
















