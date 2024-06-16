package Libary;

/**
 Importação de bibliotecas
 */

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A classe Database é responsável por gerenciar os dados relacionados a
 * usuários, livros, pedidos e empréstimos em um sistema de gerenciamento de biblioteca.
 *
 * --> gerencia todas as operações CRUD (Create, Read, Update, Delete) relacionadas aos usuários, livros, pedidos e empréstimos. Isso inclui a leitura e escrita de dados nos arquivos correspondentes,
 * além de fornecer métodos para adicionar, remover e consultar esses dados.
 */

public class Database {

    private ArrayList<User> users;
    private ArrayList<String> usernames;
    private ArrayList<Book> books;
    private ArrayList<String> booknames;
    private ArrayList<Order> orders;
    private ArrayList<Borrowing> borrowings;

    private File usersfile = new File("D:\\Libary Management System\\Data\\Users");
    private File booksfile = new File("D:\\Libary Management System\\Data\\Books");
    private File ordersfile = new File("D:\\Libary Management System\\Data\\Orders");
    private File borrowingsfile = new File("D:\\Libary Management System\\Data\\Borrowings");
    private File folder = new File("D:\\Libary Management System\\Data");


    /**
     * O construtor da classe Database é responsável por inicializar a estrutura de armazenamento do
     * sistema de gerenciamento de biblioteca. Ele garante que todos os diretórios e arquivos necessários existam,
     * e inicializa as listas que irão armazenar os dados em memória.
     */
    public Database() {

        if (!folder.exists()) {
            folder.mkdirs();
        }

        if (!usersfile.exists()) {

            try {
                usersfile.createNewFile();
            } catch (Exception event) {
            }
        }


        if (!booksfile.exists()) {

            try {
                booksfile.createNewFile();
            } catch (Exception event) {

            }
        }


        if (!ordersfile.exists()) {

            try {
                ordersfile.createNewFile();
            } catch (Exception event) {
            }
        }


        if (!borrowingsfile.exists()) {

            try {
                borrowingsfile.createNewFile();
            } catch (Exception event) {
            }
        }

        /**
         * nicializa as listas que armazenarão os objetos User, Book, Order, e Borrowing,
         * bem como as listas de nomes (usernames e booknames).
         */
        users = new ArrayList<User>();
        usernames = new ArrayList<String>();
        books = new ArrayList<Book>();
        booknames = new ArrayList<String>();
        orders = new ArrayList<Order>();
        borrowings = new ArrayList<Borrowing>();

        /**
         * @param métodos (getUsers, getBooks, getOrders, getBorrowings)
         *
         * --> chama métodos para carregar os dados dos arquivos correspondentes para as listas em memória
         */
        getUsers();
        getBooks();
        getOrders();
        getBorrowings();
    }

    /**
     *
     * @param userValue
     * O método AddUser adiciona um novo usuário à lista de usuários e à lista de nomes de usuários,
     * e então salva o estado atualizado dos usuários no arquivo de armazenamento.
     */
    public void AddUser(User userValue) {

        users.add(userValue);
        usernames.add(userValue.getName());
        saveUsers();
    }

    // ------------------------------ login no sistema --------------------------------------------------------------------------------------

    public int login(String phonenumber, String email) {

        if(phonenumber == null || phonenumber.isEmpty() || email == null || email.isEmpty()) {
            System.err.println("O número ou email estão inválidos!");
            return -1;
        }

        /**
         * @param Loop
         * Inicia um loop que percorre todos os usuários na lista users.
         * Em cada iteração, recupera o usuário atual da lista e o armazena na variável userValue.
         */
        for(int i = 0; i < users.size(); i++) {

            User userValue = users.get(i);

            /**
             * Validação dos dados de login
             *
             * O método login tenta autenticar um usuário verificando se o número de telefone e o email
             * fornecidos correspondem aos de algum usuário na lista users.
             */
            if(userValue.getPhonenumber().equals(phonenumber) && userValue.getEmail().equals(email)) {
                return i;
            }
        }

        System.out.println("Falha ao tentar acessar a conta: O usuário não existe");
        return -1;

    }

    /**
     *
     * @param value
     * @return Esses métodos fazem parte de uma classe que gerencia dados de usuários e livros
     *
     * getUser(int value): Este método simplesmente retorna o usuário localizado no índice especificado na lista users.
     * AddBook(Book book): Este método adiciona um novo livro à lista de livros e à lista de nomes de livros, e depois salva a lista de livros atualizada usando o método saveBooks.
     */

    public User getUser(int value) {
        return users.get(value);
    }

    public void AddBook(Book book) {

        books.add(book);
        booknames.add(book.getName());
        saveBooks();
    }


    /**
     * --> Validação de Dados: O método valida se cada entrada de usuário tem o
     * número correto de campos antes de criar os objetos User.
     *
     * --> permite a criação de diferentes tipos de usuários (Admin e NormalUser), baseado em informações fornecidas no arquivo.
     *
     * --> manipulação de erros durante a leitura do arquivo e processamento dos dados dos usuários.
     *
     *
     * --> O método verifica se há pelo menos 4 campos (nome, email, telefone e tipo de usuário) para cada usuário
     * --> Dependendo do valor do quarto campo (tipo de usuário), ele cria um objeto Admin ou NormalUser
     * --> O objeto User criado é adicionado à lista users, e o nome do usuário é adicionado à lista usernames
     */
    private void getUsers() {

        String firstText = "";

        try {
            BufferedReader br_1 = new BufferedReader(new FileReader(usersfile));
            String string_1;

            while ((string_1 = br_1.readLine()) != null) {
                firstText += string_1 + "\n";
            }

            br_1.close();

        } catch (Exception event) {
            System.err.println("Erro na leitura do arquivo: " + event.toString());
        }

        if (!firstText.isEmpty()) {
            String[] arrayValue1 = firstText.split("<NewUser/>"); // passando a string para um array de usuarios

            for (String stringValue : arrayValue1) {
                String[] arrayValue2 = stringValue.split("<N/>");

               if(arrayValue2.length >= 4) {

                   if (arrayValue2[3].matches("Admin")) {

                       User user = new Admin(arrayValue2[0], arrayValue2[1], arrayValue2[2]);
                       users.add(user);
                       usernames.add(user.getName());

                   } else {

                       User user = new NormalUser(arrayValue2[0], arrayValue2[1], arrayValue2[2]);
                       users.add(user);
                       usernames.add(user.getName());
                   }

               } else {
                   System.err.println("Erro na leitura do arquivo: " + stringValue);
               }
            }
        } else {
            System.err.println("Os dados do usuário não foram encontrados");
        }
    }


// -----------------------------------------------------------------

    /**
     * O método saveUsers salva a lista de usuários (users) em um arquivo (usersfile)
     *
     * --> O método começa inicializando uma string firstText vazia
     * -->Em seguida, percorre a lista users e para cada User, chama o método toString para obter a representação em string do usuário
     * --> Cada string de usuário é concatenada à string firstText, seguida do delimitador <NewUser/>\n para separar os usuários no arquivo
     */
    private void saveUsers() {

        String firstText = "";

        for (User user : users) {
            firstText = firstText + user.toString() + "<NewUser/>\n";
        }

        try {

            PrintWriter printwriter_value = new PrintWriter(usersfile);
            printwriter_value.print(firstText);
            printwriter_value.close();

        } catch (Exception event) {
            System.err.println(event.toString());
        }
    }

// -----------------------------------------------------------------

    /**
     * O método saveBooks é responsável por salvar a lista de livros em um arquivo. Ele converte cada livro em uma string formatada, concatena todas as strings e escreve o resultado em um arquivo,
     * garantindo que os dados dos livros sejam armazenados de maneira persistente.
     */
    private void saveBooks() {

        String firstText = "";

        for (Book book : books) {
            firstText = firstText + book.toString2() + "<NewBook/>\n";
        }

        try {
            PrintWriter printwriter_value = new PrintWriter(booksfile);
            printwriter_value.print(firstText);
            printwriter_value.close();

        } catch (Exception event) {
            System.out.println(event.toString());
        }
    }

// -----------------------------------------------------------------

    /**
     * O método getBooks realiza a leitura de um arquivo que contém informações de livros, converte essas informações em objetos Book,
     * e armazena esses objetos em uma lista.
     *
     * --> gerencia possíveis erros durante a leitura do arquivo e a conversão das strings em objetos.
     */
    private void getBooks() {
        String firstText = "";

        /**
         * @param BufferedReader é utilizado para ler o arquivo booksfile linha por linha.
         * Cada linha lida é adicionada à string firstText.
         * Se ocorrer algum erro durante a leitura do arquivo, uma mensagem de erro é exibida no console.
         */

        try {
            BufferedReader bufferValue = new BufferedReader(new FileReader(booksfile));
            String stringValue;

            while ((stringValue = bufferValue.readLine()) != null) {
                firstText = firstText + stringValue;
            }

            bufferValue.close();

        } catch (Exception event) {
            System.err.println(event.toString());
        }

        /**
         * Se firstText não está vazio, a string é dividida em um array (bookArray) usando o delimitador <NewBook/>.
         * Cada elemento do array representa um livro em formato de string.
         */

        if (!firstText.isEmpty()) {
            String[] bookArray = firstText.split("<NewBook/>");

            /**
             * Para cada string no array bookArray, o método parseBook é chamado para convertê-la em um objeto Book.
             * Se a conversão for bem-sucedida (book não é null), o objeto Book é adicionado à lista books e o nome do livro é adicionado à lista booknames.
             * Se a conversão falhar, uma mensagem de erro é exibida.
             */

            for (String stringValue : bookArray) {

                Book book = parseBook(stringValue);

                if(book != null) {
                    books.add(book);
                    booknames.add(book.getName());

                } else {
                    System.err.println("Formato inválido!");
                }
            }
        }
    }


    /**
     *
     * @param stringValue
     * @return O método parseBook é responsável por converter uma string em um objeto Book.
     *
     * O método parseBook é essencial para converter uma string formatada adequadamente em um objeto Book, permitindo assim que os dados dos livros sejam recuperados e manipulados no sistema.
     * Ele desempenha um papel fundamental no processo de leitura e carregamento dos dados de livros armazenados em um arquivo ou banco de dados.
     */
    public Book parseBook(String stringValue) {

        String[] array = stringValue.split("N/");

        /**
         * Se o array tiver pelo menos 7 elementos, cria-se um novo objeto Book.
         */
        if(array.length >= 7) {

            Book book = new Book();
            book.setName(array[0]);
            book.setAuthor(array[1]);
            book.setPublisher(array[2]);
            book.setAdress(array[3]);

            book.setQty(Integer.parseInt(array[4]));
            book.setPrice(Double.parseDouble(array[5]));
            book.setBrwcopies(Integer.parseInt(array[6]));

            return book;
        } else {

            /**
             * e caso o array não tenha elementos suficiente
             *
             */
            System.err.println("Formato inválido.");
            return null;
        }
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    /**
     *
     *
     * @param bookname
     * @return
     *
     * O método getBook fornece uma maneira conveniente de procurar um livro na lista de livros pelo seu nome.
     * Ele retorna o índice do livro na lista, permitindo que outras partes do código acessem e manipulem facilmente as informações desse livro.
     */
    public int getBook(String bookname) {

        /**
         * Inicializa-se a variável 'i' com o valor -1.
         * Este valor é retornado caso o livro não seja encontrado na lista.
         *
         */
        int i = -1;

        for (Book book : books) {
            if (book.getName().matches(bookname)) {
                i = books.indexOf(book);
            }
        }

        return i;
    }

    public Book getBook(int i) {
        return books.get(i);
    }

    /**
     *
     * @param i
     *
     * O método deleteBook fornece uma maneira simples e direta de excluir um livro da lista de livros com base no índice fornecido.
     * Após a exclusão, as alterações são salvas, garantindo que elas persistam além da execução do programa.
     */
    public void deleteBook(int i) {

        books.remove(i);
        booknames.remove(i);
        saveBooks();
    }


    /**
     * O método deleteAllData oferece uma maneira de limpar todos os dados do sistema de gerenciamento de biblioteca, excluindo os arquivos de dados correspondentes.
     */
    public void deleteAllData() {

        if (usersfile.exists()) {
            try {
                usersfile.delete();
            } catch (Exception event) {

            }

            if (booksfile.exists()) {

                try {
                    booksfile.delete();
                } catch (Exception event) {
                }
            }

            if (ordersfile.exists()) {

                try {
                    ordersfile.delete();
                } catch (Exception event) {
                }
            }

            if (borrowingsfile.exists()) {

                try {
                    borrowingsfile.delete();
                } catch (Exception event) {}
            }
        }
    }

    /**
     *
     * @param order
     * @param book
     * @param bookindex
     *
     *
     * --> O método addOrder oferece uma maneira de adicionar um novo pedido ao sistema de gerenciamento da biblioteca.
     * --> atualiza o estado do livro associado ao pedido e salva essas alterações nos arquivos de dados correspondentes.
     * Isso garante que as alterações sejam persistentes e estejam disponíveis mesmo após o encerramento do programa.
     */
    public void addOrder(Order order, Book book, int bookindex) {

        orders.add(order);
        books.set(bookindex, book);
        saveOrders();
        saveBooks();
    }

    /**
     * Este método saveOrders é responsável por salvar os pedidos no arquivo correspondente,
     * garantindo a persistência dos dados.
     */
    private void saveOrders() {

        String firstText = "";

        /**
         * @param Método percorre a lista de pedidos (orders) e para cada pedido, obtém uma representação
         * em formato de string utilizando o método toString2.
         *
         * Cada representação de pedido é concatenada à variável firstText,
         * juntamente com a tag <NewOrder/> para separar os pedidos no arquivo.
         */
        for (Order order : orders) {

            firstText = firstText + order.toString2() + "<NewOrder/>\n";
        }

        try {
            PrintWriter printValue = new PrintWriter(ordersfile);
            printValue.print(firstText);
            printValue.close();

        } catch (Exception event) {
            System.out.println(event.toString());
        }
    }

    /**
     * O método getOrders garante que todos os pedidos presentes no arquivo de pedidos sejam recuperados e armazenados na lista orders,
     * permitindo que possam ser acessados e manipulados pelo programa.
     */
    private void getOrders() {

        String firstText = "";


        /**
         * --> O método cria um objeto BufferedReader associado ao arquivo de pedidos (ordersfile).
         * --> Em seguida, ele lê o conteúdo do arquivo linha por linha usando o método readLine, concatenando cada linha à variável firstText.
         */
        try {
            BufferedReader bufferValue = new BufferedReader(new FileReader(ordersfile));
            String stringValue;

            while ((stringValue = bufferValue.readLine()) != null) {
                firstText = firstText + stringValue;
            }
            bufferValue.close();

        } catch (Exception event) {
            System.out.println(event.toString());
        }

        /**
         * Esse bloco de código garante que apenas o texto com informações de pedidos seja processado e que cada pedido contido no texto seja convertido em um objeto Order e adicionado à lista orders.
         * Isso assegura que os dados dos pedidos sejam recuperados e disponibilizados para o programa.
         *
         *
         * Se o texto não estiver vazio, o método split("<NewOrder/>") é chamado em firstText, dividindo o texto em substrings sempre que encontrar a tag <NewOrder/>.
         * O resultado é armazenado em um array de strings chamado stringArray.
         */
        if (!firstText.matches("") || !firstText.isEmpty()) {
            String[] stringArray = firstText.split("<NewOrder/>");

            for (String stringValue : stringArray) {

                Order order = parseOrder(stringValue);
                orders.add(order);
            }
        }
    }

    /**
     *
     * @param name
     * @return
     *
     *
     * --> Esse método userExists verifica se um usuário com um nome específico já existe na lista de usuários.
     * Ele percorre a lista de usuários e compara o nome de cada usuário (convertido para minúsculas)
     * com o nome fornecido (também convertido para minúsculas) usando o método matches da classe String.
     *
     * --> Se encontrar um usuário com o mesmo nome, define a variável isTrue como verdadeira e interrompe o loop.
     * Caso contrário, mantém isTrue como falsa.
     *
     * --> No final, retorna o valor de isTrue, indicando se o usuário com o nome especificado foi encontrado na lista.
     * Isso ajuda a evitar a criação de usuários com nomes duplicados.
     */
    public boolean userExists(String name) {
        boolean isTrue = false;

        for (User user : users) {
            if (user.getName().toLowerCase().matches(name.toLowerCase())) {
                isTrue = true;
                break;
            }
        }
        return isTrue;
    }

    /**
     *
     * @param name
     * @return
     *
     * --> Esse método getUserByName busca um usuário na lista de usuários pelo nome fornecido. E
     * le percorre a lista de usuários e compara o nome de cada usuário com o nome especificado usando o método matches da classe String.
     *
     *
     * --> Se encontrar um usuário com o mesmo nome, retorna esse usuário. Caso contrário, retorna null
     *
     * -->  Isso permite recuperar um usuário específico com base no nome, facilitando a manipulação dos dados relacionados a esse usuário.
     */
    private User getUserByName(String name) {

        for(User user : users) {
            if(user != null && user.getName().matches(name)) {
                return user;
            }
        }

        return null;
    }

    /**
     *
     * @param stringValue
     * @return
     *
     *  função parseOrder recebe uma string stringValue como entrada e a divide em
     *  partes usando <N/> como marcador de separação.
     */
    private Order parseOrder(String stringValue) {

        /**
         * books.get(getBook(arrayValue[0])): Usa o primeiro elemento do array dividido (arrayValue[0]) como chave para buscar um livro em uma estrutura de dados chamada books.
         * O método getBook provavelmente converte o nome do livro em uma chave que é usada para buscar o livro na estrutura books.
         */
        String[] arrayValue = stringValue.split("<N/>");
        Order order = new Order(


                /**
                 * getUserByName(arrayValue[1]): Usa o segundo elemento do array dividido (arrayValue[1]) como nome de usuário para buscar um usuário em alguma estrutura de dados.
                 * O método getUserByName realiza essa busca e retorna o usuário correspondente.
                 */
                books.get(getBook(arrayValue[0])),
                getUserByName(arrayValue[1]),
                Double.parseDouble(arrayValue[2]),
                Integer.parseInt(arrayValue[3])
        );

        return order;
    }

    /**
     *
     * @return return orders;:  retorna a lista de pedidos (orders) presente na estrutura de dados ArrayList.
     */
    public ArrayList<Order> getAllOrders() {
        return orders;
    }

    /**
     * essa função itera sobre todos os empréstimos registrados,
     * os converte em formato de string e os salva em um arquivo especificado.
     */
    private void saveBorrowings() {
        String firstText = "";

        for (Borrowing borrowing : borrowings) {
            firstText = firstText + borrowing.toString2() + "<NewBorrowing/>\n";
        }

        /**
         * PrintWriter printValue = new PrintWriter(borrowingsfile)
         * Cria um objeto PrintWriter para escrever no arquivo onde os empréstimos serão salvos.
         */
        try {
                PrintWriter printValue = new PrintWriter(borrowingsfile);
                printValue.print(firstText);
                printValue.close();

            } catch (Exception event) {
                System.out.println(event.toString());
            }
        }


    /**
     *  a função getBorrowings é responsável por recuperar os dados de empréstimo de algum arquivo e processá-los para armazenamento em uma estrutura de dados,
     *  possivelmente uma lista de empréstimos chamada borrowings.
     */
    private void getBorrowings () {
            String firstText = "";

        /**
         *
         * --> Cria um objeto BufferedReader para ler o conteúdo do arquivo onde os empréstimos estão armazenados.
         * O arquivo em questão é representado pela variável borrowingsfile.
         */
        try {
            BufferedReader bufferValue = new BufferedReader(new FileReader(borrowingsfile));
                String stringValue;

                while ((stringValue = bufferValue.readLine()) != null) {
                    firstText = firstText + stringValue;
                }
                bufferValue.close();

            } catch (Exception event) {
                System.out.println(event.toString());
            }

        /**
         * configura um mecanismo para ler dados do arquivo borrowingsfile usando um BufferedReader
         *
         * new FileReader(borrowingsfile) --> cria um novo objeto FileReader que aponta para o arquivo especificado pela variável borrowingsfile.
         */
        if (!firstText.matches("") || !firstText.isEmpty()) {
                String[] arrayValue = firstText.split("<NewBorrowing/>");

                for (String stringValue : arrayValue) {
                    Borrowing borrowing = parseBorrowing(stringValue);
                    borrowings.add(borrowing);
                }
            }
        }


    /**
     *
     * @param stringBorrow
     * @return a função parseBorrowing recebe uma string stringBorrow como entrada, que contém informações sobre um empréstimo.
     * A função então processa essa string e cria um objeto Borrowing com os detalhes do empréstimo.
     *
     */
    private Borrowing parseBorrowing (String stringBorrow){

        /**
         * String[] arrayValue = stringBorrow.split("<N/>")
         * --> Divide a string stringBorrow em partes usando '<N/>' como delimitador.
         */
        String[] arrayValue = stringBorrow.split("<N/>");

        /**
         * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
         * --> Cria um objeto DateTimeFormatter para ajudar na formatação das datas no padrão "ano-mês-dia".
         *
         *
         * LocalDate start = LocalDate.parse(arrayValue[0], formatter)
         * --> Converte a primeira parte da string dividida (arrayValue[0]),
         * que provavelmente representa a data de início do empréstimo, em um objeto LocalDate usando o DateTimeFormatter definido.
         *
         *
         */

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(arrayValue[0], formatter);
            LocalDate finish = LocalDate.parse(arrayValue[1], formatter);
            Book book = getBook(getBook(arrayValue[3]));
            User user = getUserByName(arrayValue[4]);
            Borrowing borrowingValue = new Borrowing(start, finish, book, user);

            return borrowingValue;
        }

    /**
     *
     * @param borrowingValue
     * @param book
     * @param bookindex
     *
     *
     * a função adiciona um novo empréstimo à lista de empréstimos, atualiza o estado do livro emprestado na lista de livros,
     * e então salva essas informações atualizadas em arquivos para garantir que elas sejam persistidas.
     */
        public void borrowBook (Borrowing borrowingValue, Book book,int bookindex){

            borrowings.add(borrowingValue);
            books.set(bookindex, book);
            saveBorrowings();
            saveBooks();
        }

    /**
     * Em resumo, estas funções trabalham juntas para registrar o retorno de um livro emprestado.
     * A primeira função fornece acesso aos empréstimos registrados, enquanto a segunda função realiza as operações necessárias para registrar o retorno do livro e atualizar as informações relevantes.
     *
     *
     * @return public ArrayList<Borrowing> getBorrowingValue(): Esta função retorna a lista de todos os empréstimos registrados (borrowings).
     */
    public ArrayList<Borrowing> getBorrowingValue () {
            return borrowings;
        }

    /**
     *
     * @param btw
     * @param book
     * @param bookindex
     *
     * Esta função returnBook é responsável por registrar o retorno de um livro emprestado, onde btw é o objeto de empréstimo a ser removido,
     * book é o objeto de livro que está sendo devolvido e bookindex é o índice do livro na lista de livros (books).
     */
    public void returnBook (Borrowing btw, Book book, int bookindex){

            borrowings.remove(btw);
            books.set(bookindex, book);
            saveBorrowings();
            saveBooks();
        }
    }

