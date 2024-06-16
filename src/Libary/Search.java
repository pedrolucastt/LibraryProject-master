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
 * Este código define a estrutura básica para permitir que os usuários realizem pesquisas de livros por nome.
 * Ao inserir um nome de livro e clicar no botão "Procurar", a aplicação pode iniciar a busca no banco de dados por livros correspondentes.
 * O botão "Cancelar" oferece uma opção para interromper a operação de pesquisa e fechar a janela
 *
 *
 * Quando o botão "Procurar" é pressionado, uma ação é acionada para iniciar
 * a pesquisa com base no nome do livro fornecido pelo usuário.
 */

public class Search implements IOOperation{

    @Override
    public void oper(Database database, User user) {

        JFrame frame = Main.frame(400, 210);
        frame.setLayout(new BorderLayout());

        JLabel title = Main.title("Procurar");
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        panel.setBackground(null);

        JLabel label = Main.label("Nome do Livro");
        JTextField name = Main.textfield();
        JButton search = Main.button("Procurar");
        JButton cancel = Main.button("Cancelar");
        panel.add(label);
        panel.add(name);
        panel.add(search);
        panel.add(cancel);

        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {

                if(name.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Informe o nome do livro!");
                    return;
                }

                /**
                 * permite que os usuários realizem uma busca por um livro pelo nome no banco de dados.
                 * Se o livro for encontrado, seus detalhes são exibidos em uma caixa de mensagem; caso contrário,
                 * uma mensagem informando que o livro não existe é exibida.
                 */
                int i = database.getBook(name.getText().toString());

                if(i > - 1) {
                     JOptionPane.showMessageDialog(new JFrame(), database.getBook(i).toString());
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









