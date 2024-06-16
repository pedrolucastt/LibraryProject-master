package Libary;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Implementa a interface 'Iooperation'.
 *
 * esta classe exibe uma interface para confirmar a exclusão de todos os dados
 * e executa a exclusão se o usuário assim a confirmar
 */
public class DeleteAllData implements IOOperation{


    @Override
    public void oper(Database database, User user) {

        JFrame frame = Main.frame(600, 150);
        frame.setLayout(new BorderLayout());

        JLabel title = Main.title("Tem certeza que deseja deletar todos os dados?");
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(1, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 20, 30));
        panel.setBackground(null);

        JButton del = Main.button("Continuar");
        JButton cancel = Main.button("Cancelar");
        panel.add(del);
        panel.add(cancel);

        del.addActionListener(new ActionListener() {

            /**
             Adiciona um ActionListener ao botão 'del'
             no método actionPerformed, quando o botão 'del' é clicado:

             1- chama database.deleteAllData() para excluir todos os dados do banco de dados
             2- fecha a janela (frame.dispose())
             3- chama o método 'oper' de uma nova instância da classe 'Exit', que  finaliza a aplicação ou realiza alguma outra ação de saída
             */

            @Override
            public void actionPerformed(ActionEvent event) {

                database.deleteAllData();
                frame.dispose();
                new Exit().oper(database, user);
            }
        });


        /**
         finaliza a configuração da interface gráfica da classe 'DeleteAllData'. Após a configuração dos botões e seus respectivos ActionListeners, o JPanel é adicionado ao JFrame e este é exibido ao usuário.
         Quando o botão "Cancelar" é clicado, a janela é fechada e o usuário é redirecionado para o menu principal
         */
            cancel.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent event) {

                    frame.dispose();
                    user.menu(database, user);
                }
            });

            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.setVisible(true);
    }
}







