package view.components.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

/**
 * Modelo personalizado de tabela que suporta armazenamento e manipulação de objetos genéricos.
 *
 * @param <T> O tipo dos objetos que serão armazenados na tabela.
 */
public class CustomTableModel<T> extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	
	private List<T> objects = new ArrayList<>();

    /**
     * Construtor que inicializa o modelo de tabela com nomes de colunas e número de linhas especificados.
     *
     * @param columnNames Nomes das colunas da tabela.
     * @param rowCount    Número inicial de linhas na tabela.
     */
    public CustomTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount); 
    }

    /**
     * Override do método isCellEditable para tornar todas as células não editáveis.
     *
     * @param row    Índice da linha da célula.
     * @param column Índice da coluna da célula.
     * @return Sempre retorna false para indicar que as células não são editáveis.
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        // Torna as células não editáveis
        return false;
    }

    /**
     * Adiciona um objeto genérico à tabela, juntamente com os dados da linha.
     *
     * @param obj     Objeto genérico a ser adicionado à tabela.
     * @param rowData Dados da linha a serem adicionados à tabela.
     */
    public void addObject(T obj, Object[] rowData) {
        objects.add(obj);
        addRow(rowData);
    }

    /**
     * Obtém o objeto genérico armazenado em uma linha específica da tabela.
     *
     * @param row Índice da linha da qual se deseja obter o objeto.
     * @return Objeto genérico na linha especificada.
     */
    public T getObjectAt(int row) {
        return objects.get(row);
    }
}