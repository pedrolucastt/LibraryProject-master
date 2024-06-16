package Libary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Essa classe serve como uma base para tipos específicos de usuários, como Admin e NormalUser, que estendem esta classe e podem implementar funcionalidades específicas de acordo com suas necessidades.
 * é utilizada como uma classe base para outras classes de usuário.
 */
public abstract class User {

    protected String name;
    protected String email;
    protected String phonenumber;
    protected IOOperation[] operations;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public User(String name, String email, String phonenumber) {

        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * responsável por exibir um menu para o usuário.
     * Ele recebe um objeto Database, que contém os dados do sistema, e um objeto User, que representa o usuário atual.
     * implementação específica deste método dependerá do tipo de usuário (por exemplo, Admin ou NormalUser) e das operações que esse usuário pode realizar.
     * @return
     */
    abstract public String toString();
    abstract public void menu(Database database, User user);


    /**
     *
     * @param data
     * @param database
     * @param user
     * @return
     *
     *
     * -> O método frame cria e configura um JFrame para exibir um menu de opções em uma aplicação de biblioteca virtual. Ele recebe um array de strings contendo as opções do menu,
     * bem como objetos Database e User que provavelmente mantêm os dados do sistema e informações do usuário, respectivamente.
     *
     * -> Frame é dimensionado e posicionado centralmente na tela, com título e layout definidos.
     *
     *
     * -> Um JLabel de saudação é adicionado à região norte do layout, enquanto um JPanel é usado para organizar as opções do menu em uma coluna com espaçamento adequado.
     * Este método fornece uma estrutura básica para a criação de menus dinâmicos em aplicações Java.
     */
    public JFrame frame(String[] data, Database database, User user) {

        JFrame frame = new JFrame();

        frame.setSize(400, 500);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle(" E-Livroteca: Biblioteca Virtual Multidisciplinar ");
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(null);
        frame.setBackground(null);

        JLabel label1 = Main.title("Seja vindo " + this.name + "!");
        frame.getContentPane().add(label1, BorderLayout.NORTH);

        JPanel panel_1 = new JPanel();

        panel_1.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        panel_1.setLayout(new GridLayout(data.length, 1, 15, 15));
        panel_1.setBackground(null);


        /**
         * Este trecho de código cria botões para cada uma das opções do menu, com base nos dados fornecidos no array data. Um loop é utilizado para percorrer cada elemento do array. Para cada elemento, um novo botão é instanciado e configurado.
         * O texto do botão é definido como o elemento atual do array.
         *
         */
        for(int i = 0; i < data.length; i++) {

            JButton button = new JButton(data[i]);

            button.setFont(new Font("Tahoma", Font.BOLD, 17));
            button.setForeground(Color.white);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setBackground(Color.decode("#1da1f2"));
            button.setBorder(null);

            int index = i;


            /**
             *  adiciona um ouvinte de ação aos botões do menu. Quando um botão é clicado, a ação correspondente é executada,
             *  com base no índice do botão no array data. O método oper() é chamado no objeto de operação correspondente ao botão clicado, passando o banco de dados e o usuário como parâmetros.
             *  Se a ação do botão for "Sair" ou "Excluir dados salvos!", a janela é fechada.
             */
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent event) {
                    operations[index].oper(database, user);

                    if(data[index].matches("Sair") || data[index].matches("Excluir dados salvos!")) {
                        frame.dispose();
                    }
                }
            });

            panel_1.add(button);
        }

        /**
         * Após a configuração dos botões e adição ao painel,
         * o painel é adicionado ao conteúdo do frame e o frame é retornado.
         */
        frame.getContentPane().add(panel_1, BorderLayout.CENTER);
        return frame;
    }
}









