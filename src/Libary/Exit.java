package Libary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Este código configura uma interface gráfica que permite ao usuário entrar com um número de telefone e um email para fazer login ou criar um novo usuário.
 * A interface inclui campos de texto para o número de telefone e email, e botões para "Login" e "Novo Usuário".
 * Quando o botão "Login" é clicado, o código verifica se os campos estão preenchidos e, se estiverem, chama o método login para processar o login.
 */
public class Exit implements IOOperation    {

    Database database;

    @Override
    public void oper(Database database, User user) {

        JFrame frame = Main.frame(500, 300);
        this.database = new Database();

        /**
         *
         * Cria um JPanel com layout de grade (3 linhas, 2 colunas) e espaçamento de 15 pixels tanto horizontal quanto vertical
         * Define uma borda vazia ao redor do painel
         * Define o fundo do painel como transparente
         */
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
        panel.setBackground(null);

        /**
         * Mensagem de boas vindas do sistema
         */
        JLabel title = Main.label("Bem vindo a E-esqueci o nome do trabalho");
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        title.setFont(new Font("Tahoma", Font.BOLD, 21));
        title.setForeground(Color.decode("#1da1f2"));

        frame.getContentPane().add(title, BorderLayout.NORTH);

        JLabel label1 = Main.label("Número de telefone: ");
        JLabel label2 = Main.label("Email: ");
        JTextField phonenumber = Main.textfield();

        JTextField email = Main.textfield();
        JButton login = Main.button("Login");
        JButton newUser = Main.button("New User");

        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {

                /**
                 * Verifica se o campo de número de telefone está vazio.
                 * Se estiver, exibe uma mensagem de erro e retorna.
                 */
                if(phonenumber.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Voce deve informar o telefone!");
                    return;
                }

                /**
                 * Verifica se o campo de email está vazio.
                 * Se estiver, exibe uma mensagem de erro e retorna.
                 */
                if(email.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Voce deve informar o email!");
                    return;
                }

                login(phonenumber.getText().toString(), email.getText().toString(), frame);
            }
        });

        newUser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                newUser();
                frame.dispose();
            }
        });


        panel.add(label1);
        panel.add(phonenumber);
        panel.add(label2);
        panel.add(email);
        panel.add(email);
        panel.add(login);
        panel.add(newUser);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     *
     * @param phonenumber
     * @param email
     * @param frame
     *
     * O método login autentica um usuário com base no número de telefone e email fornecidos. Se a autenticação for bem-sucedida, obtém os dados do usuário e exibe o menu principal do usuário, fechando a janela de login.
     * Se a autenticação falhar, exibe uma mensagem de erro informando que o usuário não existe.
     */
    private void login(String phonenumber, String email, JFrame frame) {

        int value = database.login(phonenumber, email);

        /**
         * @param Verifica se o valor retornado pelo método database.login é diferente de -1
         * -1 indica que a autenticação falhou (usuário não encontrado)
         */
        if(value != -1) {

            /**
             * User user = database.getUser(value)
             * obtém o objeto User correspondente ao valor value retornado
             *
             * user.menu(database, user)
             * chama o método menu do usuário autenticado, passando o banco de dados e o usuário como parâmetros
             *
             * frame.dispose() --> Fecha a janela de login, pois o usuário foi autenticado com sucesso
             */
            User user = database.getUser(value);
            user.menu(database, user);
            frame.dispose();

        } else {
            JOptionPane.showMessageDialog(new JFrame(), "O usuário não existe");
        }
    }

    /**
     * O método newUser configura uma janela com campos de entrada para o nome, telefone e email do usuário,
     * opções para selecionar o tipo de conta (Admin ou Usuário Padrão) e botões para criar a conta ou cancelar a operação
     * A interface é projetada para permitir a criação de uma nova conta de usuário de forma intuitiva e organizada
     *
     */

    private void newUser() {

        JFrame frame = Main.frame(500, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
        panel.setBackground(null);

        JLabel title = Main.label("Criar uma nova conta: ");
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        title.setFont(new Font("Tohoma", Font.BOLD, 21));
        title.setForeground(Color.decode("#1da1f2"));

        frame.getContentPane().add(title, BorderLayout.NORTH);

        JLabel label0 = Main.label("Nome: ");
        JLabel label1 = Main.label("Telefone: ");
        JLabel label2 = Main.label("Email: ");

        JTextField name = Main.textfield();
        JTextField phonenumber = Main.textfield();
        JTextField email = Main.textfield();

        JRadioButton admin = Main.radioButton("Admin");
        JRadioButton normaluser = Main.radioButton("Usuário Padrão");
        JButton createacc = Main.button("Criar Conta");
        JButton cancel = Main.button("Cancelar");


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

        panel.add(label0);
        panel.add(name);

        panel.add(label1);
        panel.add(phonenumber);

        panel.add(label2);
        panel.add(email);

        panel.add(admin);
        panel.add(normaluser);

        panel.add(createacc);
        panel.add(cancel);


        /**
         * @param ActionListener adicionado ao botão "Criar Conta" realiza várias verificações para garantir que os dados fornecidos pelo usuário são válidos antes de criar um novo usuário e adicioná-lo ao banco de dados
         * @param Verifica se o nome do usuário já existe, se os campos de entrada estão preenchidos, se o tipo de usuário foi selecionado
         * @param Cria o usuário e o adiciona ao banco de dados, fechando a janela de criação de usuário
         */

        createacc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {

                if (database.userExists(name.getText().toString())) {
                    JOptionPane.showMessageDialog(new JFrame(), "Nome já cadastrado!\nTente outro, por favor");
                    return;
                }

                if (name.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "O nome deve ser preenchido!");
                    return;
                }

                if (phonenumber.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "O telefone deve ser preenchido!");
                    return;
                }

                if (email.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "O e-mail deve ser preenchido!");
                    return;
                }

                if (!admin.isSelected() && !normaluser.isSelected()) {
                    JOptionPane.showMessageDialog(new JFrame(), "");
                    return;
                }

                /**
                 * cria uma instância de User
                 * se a opção "Admin" estiver selecionada, cria um novo Admin
                 * se a opção "Usuário Padrão" estiver selecionada, cria um novo NormalUser
                 */
                User user;

                if (admin.isSelected()) {

                    user = new Admin(name.getText().toString(), email.getText().toString(),
                            phonenumber.getText().toString());

                } else {

                    user = new NormalUser(name.getText().toString(), email.getText().toString(),
                            phonenumber.getText().toString());
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
}








