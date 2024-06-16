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
 * - Interface de parte de deletar livros
 *
 * este código configura uma interface gráfica onde o usuário pode inserir o nome do livro que deseja deletar.
 * Os botões "Deletar Livro" e "Cancelar" permitem ao usuário confirmar ou cancelar a operação, respectivamente.
 * ao clicar em um desses botões, a janela é fechada e a operação correspondente é realizada.
 */
public class DeleteBook implements IOOperation{

    @Override
    public void oper(Database database, User user) {

        /**
         * @param Parâmetros: database, que é o banco de dados onde o livro será deletado,
         * e user, que é o usuário que está realizando a operação.
         */

        JFrame frame = Main.frame(400, 210);
        frame.setLayout(new BorderLayout());

        JLabel title = Main.title("Deletar Livro");
        frame.getContentPane().add(title, BorderLayout.NORTH);

        /**
         *
         * cria um JPanel com layout de grade (2 linhas, 2 colunas) e espaçamento de 15 pixels tanto horizontal quanto vertical
         * define uma borda vazia ao redor do painel
         * define o fundo do painel como transparente
         */

        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        panel.setBackground(null);

        /**
         * @param Especificações dos livros
         */
        JLabel label = Main.label("Nome do Livro: ");
        JTextField name = Main.textfield();

        JButton delete = Main.button("Deletar Livro ");
        JButton cancel = Main.button("Cencelar");
        panel.add(label);
        panel.add(name);
        panel.add(delete);
        panel.add(cancel);

        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(name.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "O nome do livro deve ser preenchido!");
                    return;
                }

                int i = database.getBook(name.getText().toString());

                if(i >- 1) {
                    database.deleteBook(i);
                    JOptionPane.showMessageDialog(new JFrame(), "Livro deletado com sucesso!");
                    frame.dispose();

                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "O livro não existe");
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









