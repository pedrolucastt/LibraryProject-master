package Libary;

/**
 * a classe Order é definida com quatro campos privados (book, user, price, qty) que armazenam os detalhes do pedido
 * construtor: Um construtor é definido para inicializar esses campos quando um novo objeto Order é criado. Ele recebe quatro parâmetros correspondentes aos campos da classe
 * encapsulamento: Os campos são privados para manter o encapsulamento, garantindo que os valores dos campos só possam ser alterados através de métodos específicos (não mostrados neste trecho, mas presumivelmente getters e setters)
 */
public class Order {

    private Book book;
    private User user;
    private double price;
    private int qty;

    public Order(Book book, User user, double price, int qty) {

        this.book = book;
        this.user = user;
        this.price = price;
        this.qty = qty;
    }

    public Order() {}


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * Os dois métodos fornecem flexibilidade na representação dos dados do pedido.
     * -> 'toString' é para exibição amigável para o usuaruio
     * -> 'toString2' é para uso em processamento de dados onde uma estrutura compacta e delimitada é necessária.
     * @return
     */
    public String toString() {
        return "Nome do Livro: " + book.getName() + "\n" +
               "Nome: " + user.getName() + "\n" +
               "Price: " + String.valueOf(price) + "\n" +
               "Livros para venda: " + String.valueOf(qty);
    }

    public String toString2() {
        return book.getName() + "<N/>" + user.getName() + "<N/>" + String.valueOf(price) + "<N/>" + String.valueOf(qty);
    }

}
