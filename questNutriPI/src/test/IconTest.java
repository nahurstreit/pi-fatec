package test;

import javax.swing.JFrame;

import org.apache.batik.swing.JSVGCanvas;

import view.QuestNutri;

public class IconTest {
	public static void main(String[] args) {
        // Crie um JFrame para hospedar o JPanel
        JFrame frame = new JFrame("Exemplo SVG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Carregue o arquivo SVG
        JSVGCanvas canvas = new JSVGCanvas();
        canvas.setURI(QuestNutri.class.getResource("/view/assets/QuestNutri.svg").toString());

        // Adicione o JSVGCanvas ao JFrame
        frame.getContentPane().add(canvas);

        // Exiba o JFrame
        frame.setVisible(true);
    }
}