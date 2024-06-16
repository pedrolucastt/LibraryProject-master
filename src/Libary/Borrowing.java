package Libary;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Borrowing {

    /**
     * @param Metodo da classe de alguel de livros
     *
     LocalDate start: Armazena a data de início do empréstimo
     LocalDate finish: Armazena a data de término do empréstimo
     */

    LocalDate start;
    LocalDate finish;
    int daysleft;
    Book book;
    User user;


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public Borrowing(Book book, User user) {

        start = LocalDate.now();
        finish = start.plusDays(14);
        daysleft = Period.between(start, finish).getDays();
        this.book = book;
        this.user = user;
    }

    public Borrowing(LocalDate start,LocalDate finish, Book book, User user) {

        this.start = start;
        this.finish = finish;
        this.daysleft = Period.between(finish, LocalDate.now()).getDays();
        this.book = book;
        this.user = user;
    }

   public String getStart() {
        return formatter.format(start);
   }

    public String getFinish() {
        return formatter.format(finish);
    }

    public int getDaysLeft() {
        return Period.between(finish, LocalDate.now()).getDays();
    }

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

    /**
      - String mostrando as informações salvas no banco de dados

     * obtem uma visão rápida e legível do empréstimo, exibindo as datas e os dias restantes.
     */
    public String toString() {
        return "Inicio do emprestimo: " + start + "\n Data de entrega: " + finish + "\n Dias Restantes: " + daysleft;
    }

    /**
     *  para uma representação mais compacta e delimitada do empréstimo, incluindo informações sobre o livro e o usuário, possivelmente para armazenamento ou transmissão de dados.
     */

    public String toString2() {
        return getStart() + "<N/>" + getFinish() + "<N/>" + getDaysLeft() + "<N/>" +
                book.getName() + "<N/>" + user.getName() + "<N/>";
    }

}








