package com.okayjava.itextpdf;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

public class PrinterPDF {

    /**
     * Método para imprimir o PDF
     *
     * @param pdfPath  Caminho para o arquivo PDF
     * @param colorido Se a impressão será colorida ou em preto e branco
     */
    public static void printPDF(String pdfPath, boolean colorido) {
       
    	try {
            PDDocument document = PDDocument.load(new File(pdfPath));
            PrinterJob job = PrinterJob.getPrinterJob();

            PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
           
            if (colorido) {
                attributes.add(Chromaticity.COLOR);
            } else {
                attributes.add(Chromaticity.MONOCHROME);
            }

            job.setPrintable(new PDFPrintable(document, Scaling.SHRINK_TO_FIT));
            
            if (job.printDialog(attributes)) {
                job.print(attributes);
            }
            
            document.close();
        
    	} catch (IOException | PrinterException e) {
            e.printStackTrace();
        }
    }
}
