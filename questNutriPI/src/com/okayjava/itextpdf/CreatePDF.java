package com.okayjava.itextpdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.entities.Customer;

public class CreatePDF {

	private static final Customer TESTE_CUSTOMER = Customer.findByPK(1);
	private static final String IMG_PATH = "../img/QuestNutri.png";

	public static void main(String[] args) {
		generate(TESTE_CUSTOMER);
	}
	
	public static void generate(Customer customer) {
		String pdfName = customer.name +".pdf";
		System.out.println("Criar PDF usando iText");

		// Caminho para a pasta "Downloads" do usuário atual
		String userHome = System.getProperty("user.home");
		String downloadPath = Paths.get(userHome, "Downloads", pdfName).toString();

		
		
		// 1. Documento
		Document document = new Document();

		// Escritor de PDF
		try {
			PdfWriter.getInstance(document, new FileOutputStream(new File(downloadPath)));

			// Abrir documento
			document.open();

			// Adicionar Parágrafo
			String space1 = "\n\n\n\n";

			// Criar objeto de parágrafo
			Paragraph paragraph = new Paragraph(space1);

			// Adicionar parágrafo ao PDF
			document.add(paragraph);

			// 3. Criar um chunk
			Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 14, BaseColor.BLUE);
			Chunk chunk = new Chunk("Nome da Nutricionista - Dieta do cliente: " + Customer.findByPK(1).name, font);

			// Adicionar texto ao pdf
			document.add(chunk);

			// Adicionar Parágrafo
			String multiLineText = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

			// Criar objeto de parágrafo
			Paragraph paragraph1 = new Paragraph(multiLineText);

			// Adicionar parágrafo ao PDF
			document.add(paragraph1);

			// Adicionar imagem ao pdf
			Path imgPath = Paths.get(IMG_PATH);

			// Criar objeto de Imagem
			Image image = Image.getInstance(imgPath.toAbsolutePath().toString());
			image.scaleAbsolute(100f, 100f);

			// Centralizar a imagem
			float x = (document.getPageSize().getWidth() - image.getScaledWidth()) / 2;
			float y = (document.getPageSize().getHeight() - image.getScaledHeight()) - 10;

			image.setAbsolutePosition(x, y);

			// Adicionar imagem ao pdf
			document.add(image);

			// Adicionar atributos - título, data de criação, autor
			document.addAuthor("QUESTNUTRI");
			document.addCreationDate();
			document.addProducer();
			document.addCreator("Teste do PI");
			document.addTitle("Dieta");
			document.setPageSize(PageSize.LETTER);
			document.addSubject("QuestNutri - pdf");
			document.addKeywords("QuestNutri, PI, Dieta");

			// Inserir uma Tabela
			float[] columns = { 50F, 50F, 50F, 50F, 50F, 50F, 50F };
			PdfPTable table = new PdfPTable(columns);

			// Criar uma célula
			PdfPCell cell1 = new PdfPCell();
			cell1.setPhrase(new Phrase("Domingo"));
			table.addCell(cell1);

			// Criar 2ª célula
			PdfPCell cell2 = new PdfPCell();
			cell2.setPhrase(new Phrase("Segunda-feira"));
			table.addCell(cell2);

			// Criar 3ª célula
			PdfPCell cell3 = new PdfPCell();
			cell3.setPhrase(new Phrase("Terça-feira"));
			table.addCell(cell3);

			// Valores de Dados
			// Criar 4ª célula
			PdfPCell cell4 = new PdfPCell();
			cell4.setPhrase(new Phrase("Quarta-feira"));
			table.addCell(cell4);

			// Criar 5ª célula
			PdfPCell cell5 = new PdfPCell();
			cell5.setPhrase(new Phrase("Quinta-feira"));
			table.addCell(cell5);

			// Criar 6ª célula
			PdfPCell cell6 = new PdfPCell();
			cell6.setPhrase(new Phrase("Sexta-feira"));
			table.addCell(cell6);

			// Criar 7ª célula
			PdfPCell cell7 = new PdfPCell();
			cell7.setPhrase(new Phrase("Sábado"));
			table.addCell(cell7);

			// Adicionar tabela ao documento
			document.add(table);

			// Fechar o documento
			document.close();

			System.out.println("PDF criado.");

		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}
}
