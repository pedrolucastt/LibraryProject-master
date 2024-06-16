package Libary;

/**
 * interface IOOperation é usada para definir um contrato para classes que realizarão operações específicas no sistema,
 * como emprestar livros, calcular taxas, deletar dados, etc.
 */
public interface IOOperation {
    public void oper(Database database, User user);
}
