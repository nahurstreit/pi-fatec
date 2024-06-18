/**
 * Package que contém as classes utilitárias da camada de view.
 */
package view.components.utils;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

/**
 * Uma implementação de Border para desenhar uma borda arredondada ao redor de um componente Swing.
 */
public class RoundedBorder implements Border {
    private int radius;

    /**
     * Cria uma nova instância de RoundedBorder com o raio especificado.
     * 
     * @param radius o raio do arredondamento da borda
     */
    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    /**
     * Retorna as margens da borda ao redor do componente.
     * 
     * @param c o componente para o qual a borda está sendo desenhada
     * @return as margens da borda
     */
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    /**
     * Indica se a borda deve ser pintada como opaca.
     * 
     * @return true se a borda deve ser opaca, caso contrário false
     */
    public boolean isBorderOpaque() {
        return true;
    }


    /**
     * Desenha a borda arredondada ao redor do componente.
     * 
     * @param c o componente para o qual a borda está sendo desenhada
     * @param g o contexto gráfico no qual a borda deve ser pintada
     * @param x a coordenada x do canto superior esquerdo da borda
     * @param y a coordenada y do canto superior esquerdo da borda
     * @param width a largura da borda
     * @param height a altura da borda
     */
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}