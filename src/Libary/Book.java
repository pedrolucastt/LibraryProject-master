package Libary;

public class Book {

    /**
        @param Descrição de requisitos para criar um livro
     */
    private String name;
    private String author;
    private String publisher;
    private String adress;
    private String status;
    private int qty;
    private double price;
    private int brwcopies;

    public Book() {};

    /**
     * Construtor da classe Book
     */
    public Book(String name, String author, String publisher,
                String adress, int qty, double price, int brwcopies) {

        this.name = name; // nome do livro
        this.author = author; // autor do livro
        this.publisher = publisher; // editora
        this.qty = qty; // quantidade de livros disponiveis
        this.adress = adress; // onde o livro esta disponivel
        this.price = price; // valor do livro
        this.brwcopies = brwcopies; // número de copias emprestadas
    }

    /**
     *  @return Esse método cria uma string que apresenta todos os atributos do objeto "Book"
     *  em formato mais legível, colocando cada atributp em uma nova linha.
     */

    public String toString2() {

        String text = "Nome do Livro: " + name + "\n" + author + "\n" + publisher + "\n" + adress + "\n" + String.valueOf(qty) +
                "\n" + String.valueOf(price) + "\n" + String.valueOf(brwcopies);

            return  text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBrwcopies() {
        return brwcopies;
    }

    public void setBrwcopies(int brwcopies) {
        this.brwcopies = brwcopies;
    }
}
