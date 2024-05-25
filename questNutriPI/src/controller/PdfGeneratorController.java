package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import model.dao.MealDAO;
import model.entities.Customer;
import model.entities.Meal;
import view.QuestNutri;

public class PdfGeneratorController {

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
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(downloadPath));

				// Adicionar evento para imagem e parágrafo em cada página
				writer.setPageEvent(new PdfPageEventHelper() {
					@Override
					public void onEndPage(PdfWriter writer, Document document) {
						try {
//							// Adicionar um parágrafo inicial com espaçamento de quatro linhas
//							Paragraph paragraph = new Paragraph("\n\n\n\n");
//							document.add(paragraph);

							// Adicionar uma imagem ao PDF
							Path imgPath = Paths.get(IMG_PATH);
							Image image = Image.getInstance(imgPath.toAbsolutePath().toString());
							image.scaleAbsolute(100f, 100f);

							// Centralizar a imagem
							float x = (document.getPageSize().getWidth() - image.getScaledWidth()) / 2;
							float y = document.getPageSize().getHeight() - image.getScaledHeight() - 10;
							image.setAbsolutePosition(x, y);

							PdfContentByte canvas = writer.getDirectContentUnder();
							canvas.addImage(image);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				// Abrir o documento
				document.open();

				// Adicionar um parágrafo inicial com espaçamento de quatro linhas na primeira
				// página
				Paragraph initialParagraph = new Paragraph("\n\n\n\n");
				document.add(initialParagraph);

				// Armazenando as fontes que serão utilizadas
				String fontPath1 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-Black.ttf").toString();
				FontFactory.register(fontPath1, "Montserrat-Black");
				Font font1 = FontFactory.getFont("Montserrat-Black", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

				String fontPath2 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-BlackItalic.ttf")
						.toString();
				FontFactory.register(fontPath2, "Montserrat-BlackItalic");
				Font font2 = FontFactory.getFont("Montserrat-BlackItalic", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

				String fontPath3 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-Bold.ttf").toString();
				FontFactory.register(fontPath3, "Montserrat-Bold");
				Font font3 = FontFactory.getFont("Montserrat-Bold", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

				String fontPath4 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-BoldItalic.ttf")
						.toString();
				FontFactory.register(fontPath4, "Montserrat-BoldItalic");
				Font font4 = FontFactory.getFont("Montserrat-BoldItalic", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

				String fontPath5 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-ExtraBold.ttf")
						.toString();
				FontFactory.register(fontPath5, "Montserrat-ExtraBold");
				Font font5 = FontFactory.getFont("Montserrat-ExtraBold", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

				String fontPath6 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-ExtraBoldItalic.ttf")
						.toString();
				FontFactory.register(fontPath6, "Montserrat-ExtraBoldItalic");
				Font font6 = FontFactory.getFont("Montserrat-ExtraBoldItalic", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
						14);

				String fontPath7 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-ExtraLight.ttf")
						.toString();
				FontFactory.register(fontPath7, "Montserrat-ExtraLight");
				Font font7 = FontFactory.getFont("Montserrat-ExtraLight", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

				String fontPath8 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-ExtraLightItalic.ttf")
						.toString();
				FontFactory.register(fontPath8, "Montserrat-ExtraLightItalic");
				Font font8 = FontFactory.getFont("Montserrat-ExtraLightItalic", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
						14);

				String fontPath9 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-Italic.ttf").toString();
				FontFactory.register(fontPath9, "Montserrat-Italic");
				Font font9 = FontFactory.getFont("Montserrat-Italic", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

				String fontPath10 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-Light.ttf").toString();
				FontFactory.register(fontPath10, "Montserrat-Light");
				Font font10 = FontFactory.getFont("Montserrat-Light", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

				String fontPath11 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-LightItalic.ttf")
						.toString();
				FontFactory.register(fontPath11, "Montserrat-LightItalic");
				Font font11 = FontFactory.getFont("Montserrat-LightItalic", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

				String fontPath12 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-Medium.ttf").toString();
				FontFactory.register(fontPath12, "Montserrat-Medium");
				Font font12 = FontFactory.getFont("Montserrat-Medium", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

				String fontPath13 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-MediumItalic.ttf")
						.toString();
				FontFactory.register(fontPath13, "Montserrat-MediumItalic");
				Font font13 = FontFactory.getFont("Montserrat-MediumItalic", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
						14);

				String fontPath14 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-Regular.ttf")
						.toString();
				FontFactory.register(fontPath14, "Montserrat-Regular");
				Font font14 = FontFactory.getFont("Montserrat-Regular", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

				String fontPath15 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-SemiBold.ttf")
						.toString();
				FontFactory.register(fontPath15, "Montserrat-SemiBold");
				Font font15 = FontFactory.getFont("Montserrat-SemiBold", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

				String fontPath16 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-SemiBoldItalic.ttf")
						.toString();
				FontFactory.register(fontPath16, "Montserrat-SemiBoldItalic");
				Font font16 = FontFactory.getFont("Montserrat-SemiBoldItalic", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
						14);

				String fontPath17 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-Thin.ttf").toString();
				FontFactory.register(fontPath17, "Montserrat-Thin");
				Font font17 = FontFactory.getFont("Montserrat-Thin", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

				String fontPath18 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-ThinItalic.ttf")
						.toString();
				FontFactory.register(fontPath18, "Montserrat-ThinItalic");
				Font font18 = FontFactory.getFont("Montserrat-ThinItalic", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

				// Adicionar atributos ao documento
				document.addAuthor("QUESTNUTRI");
				document.addCreationDate();
				document.addProducer();
				document.addCreator("Teste do PI");
				document.addTitle("Dieta");
				document.setPageSize(PageSize.LETTER);
				document.addSubject("QuestNutri - pdf");
				document.addKeywords("QuestNutri, PI, Dieta");

				// Armazenando as fontes que serão utilizadas
				// (Código de registro de fontes omitido para simplificação)

				// Criar um parágrafo com a informação do cliente
				Paragraph info = new Paragraph("Informações do Cliente:\n", font1);
				Paragraph customerInfo = new Paragraph("Nome: " + customer.name + "\n" + "Idade: " + customer.birth + "\n", font12);
				document.add(info);
				document.add(customerInfo);
				
				// Adicionar parágrafos com as refeições do cliente
				java.util.List<Meal> meals = MealDAO.findAllByCustomerPK(customer.getId());
				if (!meals.isEmpty()) {
					for (Meal meal : meals) {
						Paragraph mealDayParagraph = new Paragraph("\n\n\n\n" + meal.daysOfWeek + ":\n", font1);
						Paragraph mealParagraph = new Paragraph(
								"\nRefeição: " + meal.name + "\n" + meal.getFoods() + "Descrição: " + meal.obs + "\n\n\n\n", font15);
						document.add(mealDayParagraph);
						document.add(mealParagraph);
					}
				} else {
					Paragraph mealParagraph = new Paragraph("A lista de refeições está vazia.", font15);
					document.add(mealParagraph);
				}

				// Fechar o documento
				document.close();

				System.out.println("PDF criado.");
			} catch (IOException | DocumentException e) {
				e.printStackTrace();
			}
		}
	}
}
