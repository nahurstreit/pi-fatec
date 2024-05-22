package com.okayjava.itextpdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

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

public class CreatePDF {

    // Nome do arquivo PDF a ser criado
    private static final String PDF_NAME = "QuestNutri.pdf";

    // Caminho relativo para a imagem a partir do diretório 'questNutriPI/src'
    private static final String IMG_NAME = "../img/QuestNutri.png";

    /**
     * Método principal para a criação do PDF.
     *
     * @param args argumentos da linha de comando (não utilizado)
     */
    public static void main(String[] args) {
        System.out.println("Criar PDF usando iText");

        // Determinar o diretório de downloads do usuário
        Path downloadPath = getDownloadDirectory();
        if (downloadPath == null) {
            System.err.println("Não foi possível determinar o diretório de downloads.");
            return;
        }

        // 1. Criar um objeto de Documento
        Document document = new Document();

        // Criar um escritor de PDF
        try {
            File pdfFile = new File(downloadPath.toFile(), PDF_NAME);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

            // Abrir o Documento
            document.open();

            // 3. Criar um chunk (um pedaço de texto)
            Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 14, BaseColor.BLUE);
            Chunk chunk = new Chunk("QuestNutri", font);

            // Adicionar texto ao PDF
            document.add(chunk);

            // Adicionar um Parágrafo
            String multiLineText = "Consultas";

            // Criar um objeto de parágrafo
            Paragraph paragraph = new Paragraph(multiLineText);

            // Adicionar o parágrafo ao PDF
            document.add(paragraph);

            // Adicionar uma imagem ao PDF
            Path imgPath = Paths.get(IMG_NAME); // Usar o caminho relativo

            // Criar um objeto de imagem
            Image image = Image.getInstance(imgPath.toAbsolutePath().toString());
            image.scaleAbsolute(500f, 500f);

            // Adicionar a imagem ao PDF
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
            float[] columns = { 150F, 150F, 150F };
            PdfPTable table = new PdfPTable(columns);

            // Criar uma célula
            PdfPCell cell1 = new PdfPCell();
            cell1.setPhrase(new Phrase("Primeiro Nome"));
            table.addCell(cell1);

            // Criar a 2ª Célula
            PdfPCell cell2 = new PdfPCell();
            cell2.setPhrase(new Phrase("Último Nome"));
            table.addCell(cell2);

            // Criar a 3ª Célula
            PdfPCell cell3 = new PdfPCell();
            cell3.setPhrase(new Phrase("Email"));
            table.addCell(cell3);

            // Valores de Dados
            // Criar a 4ª célula
            PdfPCell cell4 = new PdfPCell();
            cell4.setPhrase(new Phrase("Bruno"));
            table.addCell(cell4);

            // Criar a 5ª Célula
            PdfPCell cell5 = new PdfPCell();
            cell5.setPhrase(new Phrase("Soares"));
            table.addCell(cell5);

            // Criar a 6ª Célula
            PdfPCell cell6 = new PdfPCell();
            cell6.setPhrase(new Phrase("bruno@gmail.com"));
            table.addCell(cell6);

            // Adicionar a tabela ao documento
            document.add(table);

            // Fechar o documento
            document.close();

            System.out.println("PDF criado e salvo em " + pdfFile.getAbsolutePath());

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtém o diretório de downloads do usuário de forma multiplataforma.
     *
     * @return o Path para o diretório de downloads do usuário, ou null se não puder ser determinado.
     */
    private static Path getDownloadDirectory() {
        String userHome = System.getProperty("user.home");
        if (userHome == null) {
            return null;
        }

        // Determina o diretório de downloads com base no sistema operacional
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        if (os.contains("win")) {
            return Paths.get(userHome, "Downloads");
        } else if (os.contains("mac")) {
            return Paths.get(userHome, "Downloads");
        } else if (os.contains("nix") || os.contains("nux")) {
            return Paths.get(userHome, "Downloads");
        }

        return null;
    }
}
