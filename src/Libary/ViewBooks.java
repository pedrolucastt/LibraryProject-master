package Libary;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Este código define a operação de visualização de livros.
 * -> calcula o número de linhas necessárias na interface do usuário com base no tamanho do banco de dados de livros.
 *
 * -> cria um JFrame para exibir a interface. Um título é adicionado ao topo do frame.
 * Um JPanel é configurado para conter os detalhes dos livros, usando um layout de grade para organizar os elementos.
 *
 * -> Em seguida, um array de strings é criado para representar os cabeçalhos das colunas da tabela de livros.
 * Para cada elemento neste array, um JLabel é criado usando o método label() da classe Main e adicionado ao painel.
 */
public class ViewBooks implements IOOperation{

    @Override
    public void oper(Database database, User user) {

        int rows = database.getAllBooks().size() + 1;
        int height = rows * 60 + 100;
        JFrame frame = Main.frame(1000, height);

        JLabel title = Main.title("Visualizar Livros");
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, 7, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        panel.setBackground(null);

        String[] row1 = new String[] {
                "Nome", "Autor", "Editora", "Local de Coleta", "Quantidade", "Preço", "Copias para alugar"
        };

        for(String stringValue : row1) {
            JLabel label = label(stringValue);
            panel.add(label);
        }

        /**
         * --> intera sobre todos os livros presentes no banco de dados e cria JLabels para cada atributo de cada livro
         * Os JLabels são então adicionados ao painel que contém os detalhes dos livros
         *
         * --> o painel é adicionado ao frame e o frame é exibido. Cada livro é representado como uma linha na interface,
         * com cada atributo do livro sendo exibido em uma coluna separada.
         */

        for(Book bookValue : database.getAllBooks()) {

           JLabel label1 = label(bookValue.getName());
           JLabel label2 = label(bookValue.getAuthor());
           JLabel label3 = label(bookValue.getPublisher());
           JLabel label4 = label(bookValue.getAdress());
           JLabel label5 = label(String.valueOf(bookValue.getQty()));
           JLabel label6 = label(String.valueOf(bookValue.getPrice()));
           JLabel label7 = label(String.valueOf(bookValue.getBrwcopies()));

           panel.add(label1);
           panel.add(label2);
           panel.add(label3);
           panel.add(label4);
           panel.add(label5);
           panel.add(label6);
           panel.add(label7);
       }

       frame.getContentPane().add(panel, BorderLayout.CENTER);
       frame.setVisible(true);
    }

    /**
     *
     * @param text
     * @return
     *
     *  --> label cria e retorna um JLabel com o texto fornecido como argumento.
     *  --> utiliza um método estático Main.label(text) para criar o JLabel, definindo o texto e as configurações de fonte padrão.
     *
     *
     * --> define o fundo do JLabel como branco (Color.white).
     * --> adiciona uma borda ao redor do JLabel com uma linha preta (BorderFactory.createLineBorder(Color.black, 1)).
     * --> torna o JLabel opaco (label.setOpaque(true)), o que permite que o fundo colorido seja visível.
     */

    private JLabel label(String text) {

        JLabel label = Main.label(text);
        label.setBackground(Color.white);
        label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        label.setOpaque(true);

        return label;
    }
}





