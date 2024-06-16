package Libary;

/**
    Importação de bibliotecas
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {

    /**
    * @param Importações de leitura do Scanner e da classe de Database (banco de dados da aplicação)
    */

    static Scanner input;
    static Database database;

    public static void main(String[] args) {


        /**
         * Importação da classe do banco de dados
        */
        database = new Database();

        /**
         * @param Jframe - criação do conteiner de armazenamento de componentes
         * @return tamanho do canvas
         */

        JFrame frame = frame(500, 300);  // tamanho do canvas da aplicação

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 13, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
        panel.setOpaque(false);


        /**
         * Mensagem da Página inicial do Sistema
         */
        JLabel title = label("Seja bem vindo a E-Livroteca: Biblioteca Virtual Multidisciplinar!");

        /**
         * @param Criação JLabel de borda vazia de 15 pixels ao seu redor
         * para criar uma margem inteira.
         */
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        title.setFont(new Font("Bell MT", Font.BOLD, 21));
        title.setForeground(Color.decode("#2f3640"));
        frame.getContentPane().add(title, BorderLayout.NORTH);

        /**
         * @param Login do sistema.
         * @return dados necessarios para o login no sistema
         */
        JLabel firstLabel = label("Telefone: ");
        JLabel secondLabel = label("E-mail: ");

        JTextField phonenumber = textfield();
        JTextField email = textfield();
        JButton login = button("Entrar");
        JButton newUser = button("Novo Usuário");


        /**
         * @param EventListener adicionando um ouvinte de eventos
         * a um componente de interface gráfica
         */

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                /**
                 * @param Validação dos dados. Verefica se os dados de Telefone e Email estão preenchidos
                 * @return se ambos estiverem preenchidos, chama o método "login"
                 * com os valores do campos "phonenumber", "email" e "frame"
                 */
                if(phonenumber.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "O campo de telefone deve ser preenchido!");
                    return;
                }

                if(email.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "O campo de email deve ser preenchido!");
                    return;
                }

                login(phonenumber.getText().toString(), email.getText().toString(), frame);
            }
        });


        /**
         * @param Ouvinte de ação para o botão "newUser"
         * @return quando o botão é acionado, o métado "newuser" é chamado para realizar uma ação.
         */
        newUser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                newuser();
                frame.dispose();
            }
        });

        panel.add(firstLabel);
        panel.add(phonenumber);
        panel.add(secondLabel);
        panel.add(email);
        panel.add(login);
        panel.add(newUser);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     * -> Verifica as credencias de login ("phonenumber" e "email") no banco de dados
     * -> se as credenciais forem válidas (encontrado o usuário), obtem assim o objeto "user" correspondente.
     *
     * => caso não encontrado emite uma mensagem de erro, informando que o usuario não existe
     */
    private static void login(String phonenumber, String email, JFrame frame) {

        int databaseValue = database.login(phonenumber, email);

        /**
         O valor retornado é armazenado na variável "databaseValue".
         Normalmente, este valor poderia ser um ID de usuário ou um indicador de falha (como -1)
         */
        if(databaseValue != -1) {

            User user = database.getUser(databaseValue);
            user.menu(database, user);
            frame.dispose();

        } else {
            JOptionPane.showMessageDialog(new JFrame(), "O usuário não existe!");
        }
    }

    /**
     * - Cria um novo "jframe" com dimensões especificas
     * - Configura um "jPanel" com layout de grade e uma borda vazia
     * - "JRadioButtons" para selecionar o usuário
     * - "jButtons" para as ações de criar conta e cancelar
     */
    private static void newuser() {

        JFrame frame = frame(500, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
        panel.setOpaque(false);

        /**
            Criação de uma nova conta
         */
        JLabel title = label("Criar uma nova conta");
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        title.setFont(new Font("Tohoma", Font.BOLD, 21));
        title.setForeground(Color.decode("#40739e"));
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JLabel label_0 = label("Nome: ");
        JLabel label_1 = label("Telefone: ");
        JLabel label_2 = label("Email: ");

        JTextField name = textfield();
        JTextField phonenumber = textfield();
        JTextField email = textfield();

        JRadioButton admin = radioButton("Admin");
        JRadioButton normaluser = radioButton("Usuario Padrão");
        JButton createAcount = button("Criar Conta");
        JButton cancel = button("Cancelar");


        /**
            Adiciona um ActionListener ao botão de opção "admin" que é um "jRaadioButton".
            @return quando o botão "admin" é clicado, o código dentro do método "actionPerfomed" é executado.
         */
        admin.addActionListener(event -> {

            if(normaluser.isSelected()) {
                normaluser.setSelected(false);
            }
        });

        normaluser.addActionListener(event -> {

            if(admin.isSelected()) {
                admin.setSelected(false);
            }
        });


        panel.add(label_0);
        panel.add(name);
        panel.add(label_1);
        panel.add(phonenumber);
        panel.add(label_2);
        panel.add(email);
        panel.add(admin);
        panel.add(normaluser);
        panel.add(createAcount);
        panel.add(cancel);


        /**
            @param Adiciona um ActionListener ao botao "createAcount".
            @return quando o botão "createAcount" for acionado, "actionPerformed" é executado
         */

        createAcount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /**
                     Validações de espaços vazios e registros já realizados
                 */
                if(database.userExists(name.getText().toString())) {
                    JOptionPane.showMessageDialog(new JFrame(), "Esse nome já foi registrado! \n tente outro");
                    return;
                }

                if(name.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "O campo de nome deve ser preenchido!");
                    return;
                }

                if(phonenumber.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "O campo de telefone deve ser preenchido!");
                    return;
                }

                if(email.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "O campo de email deve ser preenchido!");
                    return;
                }

                if(!admin.isSelected() && !normaluser.isSelected()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Você deve escolher o tipo de conta que será criada!");
                    return;
                }

                User user;

                if(admin.isSelected()) {

                    user = new Admin(name.getText().toString(),
                    email.getText().toString(), phonenumber.getText().toString());

                } else {
                    user = new NormalUser(name.getText().toString(),
                            email.getText().toString(), phonenumber.getText().toString());
                }

                frame.dispose();
                database.AddUser(user);
                user.menu(database, user);
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



    /**
        Confirgução e manipulação do JFrame
     */
    public static JFrame frame(int width, int height) {

        JFrame frame = new JFrame();

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle(" Sistema de Gerenciamento de Blibioteca ");
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.white);
        frame.getContentPane().setBackground(Color.white);

        return frame;
    }

    /**
     Confirgução e manipulação do JLabel
     */
    public  static JLabel label(String text) {

        JLabel label_1 = new JLabel(text);

        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setFont(new Font("Tahamo", Font.BOLD, 17));
        label_1.setForeground(Color.black);

        return label_1;
    }

    /**
     Confirgução e manipulação do textField
     */
    public static JTextField textfield() {

        JTextField textField_1 = new JTextField();

        textField_1.setFont(new Font("Tahoma", Font.BOLD, 17));
        textField_1.setForeground(Color.black);
        textField_1.setHorizontalAlignment(SwingConstants.CENTER);

        return textField_1;
    }

    /**
     Confirgução e manipulação do JButton
     */
    public static JButton button(String text) {

        JButton button = new JButton(text);

        button .setFont(new Font("Tohoma", Font.BOLD, 17));
        button.setForeground(Color.white);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBackground(Color.decode("#1daf2"));
        button.setBorder(null);

        return button;
    }

    /**
     Confirgução e manipulação do JRadioButton
     */
    public static JRadioButton radioButton(String text) {

        JRadioButton button = new JRadioButton();
        button.setForeground(Color.black);
        button.setText(text);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setFont(new Font("Tohama", Font.BOLD, 17));
        button.setBackground(null);

        return button;
    }

    /**
     Outra Confirgução e manipulação do JLabel em relação a aparencia grafica
     */
    public static JLabel title(String text) {

        JLabel title = Main.label(text);

        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        title.setFont(new Font("Tohama", Font.BOLD, 21));
        title.setForeground(Color.decode("#1da1f2"));

        return title;
    }
}




















