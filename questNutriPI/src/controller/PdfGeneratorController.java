package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;

import com.itextpdf.text.BaseColor;
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
import model.entities.Food;
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

                // Adicionar um parágrafo inicial com espaçamento de quatro linhas na primeira página
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

                // Armazenando as fontes que serão utilizadas com a cor Azul
                String fontPathBlue1 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-Black.ttf")
                        .toString();
                FontFactory.register(fontPathBlue1, "Montserrat-Black-Blue");
                Font fontBlue1 = FontFactory.getFont("Montserrat-Black-Blue", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14,
                        Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue2 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-BlackItalic.ttf")
                        .toString();
                FontFactory.register(fontPathBlue2, "Montserrat-BlackItalic-Blue");
                Font fontBlue2 = FontFactory.getFont("Montserrat-BlackItalic-Blue", BaseFont.IDENTITY_H,
                        BaseFont.EMBEDDED, 14, Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue3 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-Bold.ttf").toString();
                FontFactory.register(fontPathBlue3, "Montserrat-Bold-Blue");
                Font fontBlue3 = FontFactory.getFont("Montserrat-Bold-Blue", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14,
                        Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue4 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-BoldItalic.ttf")
                        .toString();
                FontFactory.register(fontPathBlue4, "Montserrat-BoldItalic-Blue");
                Font fontBlue4 = FontFactory.getFont("Montserrat-BoldItalic-Blue", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
                        14, Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue5 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-ExtraBold.ttf")
                        .toString();
                FontFactory.register(fontPathBlue5, "Montserrat-ExtraBold-Blue");
                Font fontBlue5 = FontFactory.getFont("Montserrat-ExtraBold-Blue", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
                        14, Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue6 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-ExtraBoldItalic.ttf")
                        .toString();
                FontFactory.register(fontPathBlue6, "Montserrat-ExtraBoldItalic-Blue");
                Font fontBlue6 = FontFactory.getFont("Montserrat-ExtraBoldItalic-Blue", BaseFont.IDENTITY_H,
                        BaseFont.EMBEDDED, 14, Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue7 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-ExtraLight.ttf")
                        .toString();
                FontFactory.register(fontPathBlue7, "Montserrat-ExtraLight-Blue");
                Font fontBlue7 = FontFactory.getFont("Montserrat-ExtraLight-Blue", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
                        14, Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue8 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-ExtraLightItalic.ttf")
                        .toString();
                FontFactory.register(fontPathBlue8, "Montserrat-ExtraLightItalic-Blue");
                Font fontBlue8 = FontFactory.getFont("Montserrat-ExtraLightItalic-Blue", BaseFont.IDENTITY_H,
                        BaseFont.EMBEDDED, 14, Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue9 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-Italic.ttf").toString();
                FontFactory.register(fontPathBlue9, "Montserrat-Italic-Blue");
                Font fontBlue9 = FontFactory.getFont("Montserrat-Italic-Blue", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14,
                        Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue10 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-Light.ttf")
                        .toString();
                FontFactory.register(fontPathBlue10, "Montserrat-Light-Blue");
                Font fontBlue10 = FontFactory.getFont("Montserrat-Light-Blue", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14,
                        Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue11 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-LightItalic.ttf")
                        .toString();
                FontFactory.register(fontPathBlue11, "Montserrat-LightItalic-Blue");
                Font fontBlue11 = FontFactory.getFont("Montserrat-LightItalic-Blue", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
                        14, Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue12 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-Medium.ttf")
                        .toString();
                FontFactory.register(fontPathBlue12, "Montserrat-Medium-Blue");
                Font fontBlue12 = FontFactory.getFont("Montserrat-Medium-Blue", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14,
                        Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue13 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-MediumItalic.ttf")
                        .toString();
                FontFactory.register(fontPathBlue13, "Montserrat-MediumItalic-Blue");
                Font fontBlue13 = FontFactory.getFont("Montserrat-MediumItalic-Blue", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
                        14, Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue14 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-Regular.ttf")
                        .toString();
                FontFactory.register(fontPathBlue14, "Montserrat-Regular-Blue");
                Font fontBlue14 = FontFactory.getFont("Montserrat-Regular-Blue", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14,
                        Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue15 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-SemiBold.ttf")
                        .toString();
                FontFactory.register(fontPathBlue15, "Montserrat-SemiBold-Blue");
                Font fontBlue15 = FontFactory.getFont("Montserrat-SemiBold-Blue", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14,
                        Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue16 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-SemiBoldItalic.ttf")
                        .toString();
                FontFactory.register(fontPathBlue16, "Montserrat-SemiBoldItalic-Blue");
                Font fontBlue16 = FontFactory.getFont("Montserrat-SemiBoldItalic-Blue", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
                        14, Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue17 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-Thin.ttf").toString();
                FontFactory.register(fontPathBlue17, "Montserrat-Thin-Blue");
                Font fontBlue17 = FontFactory.getFont("Montserrat-Thin-Blue", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14,
                        Font.NORMAL, BaseColor.BLUE);

                String fontPathBlue18 = QuestNutri.class.getResource("/view/assets/fonts/Montserrat-ThinItalic.ttf")
                        .toString();
                FontFactory.register(fontPathBlue18, "Montserrat-ThinItalic-Blue");
                Font fontBlue18 = FontFactory.getFont("Montserrat-ThinItalic-Blue", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
                        14, Font.NORMAL, BaseColor.BLUE);

                // Calcular a idade do cliente
                LocalDate birthDate = customer.birth;
                LocalDate currentDate = LocalDate.now();
                int age = Period.between(birthDate, currentDate).getYears();

                // Criar um parágrafo com a informação do cliente
                Paragraph info = new Paragraph("Informações do Cliente:\n", fontBlue1);
                Paragraph customerInfo = new Paragraph("\nNome: " + customer.name + "\n" + "Idade: " + age + " anos\n\n", fontBlue12);
                document.add(info);
                document.add(customerInfo);

                // Organiza as refeições do cliente por dia da semana
                List<Meal> meals = MealDAO.findAllByCustomerPK(customer.getId());
                
                /**
                 *Cria um mapa para armazenar as refeições organizadas por dia da semana
                 *A chave é um número representando o dia da semana (1 = Domingo, 2 = Segunda, ..., 7 = Sábado)
                 *O valor é uma lista de refeições (Meal) associadas a esse dia.
                 */
                Map<Integer, List<Meal>> mealsByDayOfWeek = new HashMap<>();
                for (int i = 1; i <= 7; i++) {
                    mealsByDayOfWeek.put(i, new ArrayList<>());
                }

                for (Meal meal : meals) {
                    if ((meal.daysOfWeek & 64) != 0) {
                        mealsByDayOfWeek.get(1).add(meal); 
                    }
                    if ((meal.daysOfWeek & 32) != 0) {
                        mealsByDayOfWeek.get(2).add(meal); 
                    }
                    if ((meal.daysOfWeek & 16) != 0) {
                        mealsByDayOfWeek.get(3).add(meal);
                    }
                    if ((meal.daysOfWeek & 8) != 0) {
                        mealsByDayOfWeek.get(4).add(meal);
                    }
                    if ((meal.daysOfWeek & 4) != 0) {
                        mealsByDayOfWeek.get(5).add(meal);
                    }
                    if ((meal.daysOfWeek & 2) != 0) {
                        mealsByDayOfWeek.get(6).add(meal);
                    }
                    if ((meal.daysOfWeek & 1) != 0) {
                        mealsByDayOfWeek.get(7).add(meal);
                    }
                }

                // Adiciona as refeições ao documento separadas por dia da semana
                String[] daysOfWeekNames = {"\nDomingo", "\nSegunda-Feira", "\nTerça-Feira", "\nQuarta-Feira", "\nQuinta-Feira", "\nSexta-Feira", "\nSábado"};
                for (int i = 1; i <= 7; i++) {
                    List<Meal> dayMeals = mealsByDayOfWeek.get(i);
                    if (!dayMeals.isEmpty()) {
                        document.add(new Paragraph(daysOfWeekNames[i - 1] + ":", fontBlue15));
                        for (Meal meal : dayMeals) {
                            document.add(new Paragraph(" - " + meal.name, font1));
                            
                            List<Food> foods = meal.getFoods(); 
                            for(Food food : foods) {
                            	document.add(new Paragraph("        " + food.aliment.name,font3));
                            	document.add(new Paragraph("        " + food.quantity + " " + food.unityQt,font12));
                             	if(!(food.obs == null) ) {
                             		document.add(new Paragraph("        observação:" + food.obs,font12));
                             	}
                            }
                        }
                    }
                }

                // Fechar o documento
                document.close();

            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
