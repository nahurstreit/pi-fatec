package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.entities.Customer;
import view.QuestNutri;

public class PdfGneratorController {

	public static final String IMG_PATH = "../img/QuestNutri.png";

	/**
	 * Gera um PDF com as informações do cliente.
	 * 
	 * @param customer O cliente cujas informações serão usadas para gerar o PDF.
	 */
	public static void generate(Customer customer) {
		String pdfName = customer.name + ".pdf";
		System.out.println("Criar PDF usando iText");

		// Criar um JFileChooser para permitir que o usuário escolha o local para salvar
		// o PDF.
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Escolha o local para salvar o PDF");
		fileChooser.setSelectedFile(new File(pdfName));

		int userSelection = fileChooser.showSaveDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			String downloadPath = fileToSave.getAbsolutePath();

			// Criar um documento
			Document document = new Document();

			try {
				// Inicializar o escritor de PDF
				PdfWriter.getInstance(document, new FileOutputStream(downloadPath));

				// Abrir o documento
				document.open();

				// Adicionar um parágrafo inicial com espaçamento
				String space1 = "\n\n\n\n";
				Paragraph paragraph = new Paragraph(space1);
				document.add(paragraph);

				// Criar um chunk com o nome da nutricionista e do cliente
//				Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 14, BaseColor.BLUE);
				String fontPath = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-Italic.ttf").toString();
				FontFactory.register(fontPath, "Montserrat-Italic");
				Font font = FontFactory.getFont("Montserrat-Italic", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

				Chunk chunk = new Chunk("Nome da Nutricionista - Dieta do cliente: " + customer.name, font);
				document.add(chunk);

				// Adicionar mais espaçamento
				String multiLineText = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
				Paragraph paragraph1 = new Paragraph(multiLineText);
				document.add(paragraph1);

				// Adicionar uma imagem ao PDF
				Path imgPath = Paths.get(IMG_PATH);
				Image image = Image.getInstance(imgPath.toAbsolutePath().toString());
				image.scaleAbsolute(100f, 100f);

				// Centralizar a imagem
				float x = (document.getPageSize().getWidth() - image.getScaledWidth()) / 2;
				float y = (document.getPageSize().getHeight() - image.getScaledHeight()) - 10;
				image.setAbsolutePosition(x, y);
				document.add(image);

				// Adicionar atributos ao documento
				document.addAuthor("QUESTNUTRI");
				document.addCreationDate();
				document.addProducer();
				document.addCreator("Teste do PI");
				document.addTitle("Dieta");
				document.setPageSize(PageSize.LETTER);
				document.addSubject("QuestNutri - pdf");
				document.addKeywords("QuestNutri, PI, Dieta");

				// Criar e adicionar uma tabela ao documento
				float[] columns = { 50F, 50F, 50F, 50F, 50F, 50F, 50F };
				PdfPTable table = new PdfPTable(columns);

				String[] days = { "Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira",
						"Sexta-feira", "Sábado" };
				for (String day : days) {
					PdfPCell cell = new PdfPCell();
					cell.setPhrase(new Phrase(day));
					table.addCell(cell);
				}

				document.add(table);

				// Fechar o documento
				document.close();

				System.out.println("PDF criado.");
			} catch (IOException | DocumentException e) {
				e.printStackTrace();
			}
		}
	}
}
