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

public class AddBook implements  IOOperation{

    @Override
    public void oper(Database database, User user) {

        JFrame frame = Main.frame(500, 6000);

        JLabel title = Main.title("Adicionar novo livro");
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        panel.setBackground(null);

        JLabel label1 = Main.label("Nome do livro: ");
        JLabel label2 = Main.label("Autor do Livro: ");
        JLabel label3 = Main.label("Editora: ");
        JLabel label4 = Main.label("Numero de coleção do livro: ");
        JLabel label5 = Main.label("Quantidade: ");
        JLabel label6 = Main.label("Preço: ");
        JLabel label7 = Main.label("Copias para alugar: ");

        JTextField name = Main.textfield();
        JTextField author = Main.textfield();
        JTextField pub = Main.textfield();
        JTextField bca = Main.textfield();
        JTextField qty = Main.textfield();
        JTextField price = Main.textfield();
        JTextField brwcpis = Main.textfield();

        JButton addbook = Main.button("Adicionar Livro");
        JButton cancel = Main.button("Cancelar");

        panel.add(label1);
        panel.add(name);

        panel.add(label2);
        panel.add(author);

        panel.add(label3);
        panel.add(pub);

        panel.add(label4);
        panel.add(bca);

        panel.add(label5);
        panel.add(qty);

        panel.add(label6);
        panel.add(price);

        panel.add(label7);
        panel.add(brwcpis);

        panel.add(addbook);
        panel.add(cancel);

        addbook.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(name.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "O nome do livro não pode ficar vazio!");
                    return;
                }

                if(author.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "O nome do autor não pode ficar vazio!");
                    return;
                }

                if(pub.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "O nome da editora não pode ficar vazio!");
                    return;
                }

                if(bca.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Informe o número na coleção!");
                    return;
                }

                if(qty.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Informe a quantidade!");
                    return;
                }

                if(price.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Informe o valor do livro!");
                    return;
                }

                if(brwcpis.getText().toString().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Informe a quantidade de copias para alugar!");
                    return;
                }



                try {
                    Integer.parseInt(qty.getText().toString());

                } catch (Exception eventValue) {
                    JOptionPane.showMessageDialog(new JFrame(), "A quantidade deve ser em números inteiros!");
                    return;
                }


                try {
                    Double.parseDouble(price.getText().toString());

                } catch (Exception eventValue) {
                    JOptionPane.showMessageDialog(new JFrame(), "O preço deve ser um número!");
                    return;
                }

                try {
                    Integer.parseInt(brwcpis.getText().toString());

                } catch (Exception eventValue) {
                    JOptionPane.showMessageDialog(new JFrame(), "A quantidade de copias para alugar deve ser um número inteiro!");
                    return;
                }

                Book book = new Book();

                if(database.getBook(name.getText().toString()) > - 1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Já há um livro com esse nome");
                    return;

                } else {

                    book.setName(name.getText().toString());
                    book.setAuthor(author.getText().toString());
                    book.setPublisher(pub.getText().toString());
                    book.setAdress(bca.getText().toString());


                    book.setQty(Integer.parseInt(qty.getText().toString()));
                    book.setPrice(Double.parseDouble(price.getText().toString()));
                    book.setBrwcopies(Integer.parseInt(brwcpis.getText().toString()));

                    database.AddBook(book);
                    JOptionPane.showMessageDialog(new JFrame(), "Livro adicionado com sucesso!");
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











