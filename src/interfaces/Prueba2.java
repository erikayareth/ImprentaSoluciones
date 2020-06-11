/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Avril
 */
public class Prueba2 {
       public static void main(String[] args) {
        try {
            System.out.println("H");
            InputStream S = Prueba2.class.getResourceAsStream("FORMATO.docx");
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport
        (S, TemplateEngineKind.Velocity);

            IContext context = report.createContext();
            context.put("CONCEPTO", "Impresión\nPapel bond\nSerigrafía\nPapel\nCouché");
            context.put("PIEZAS", "3\n4\n1\n10\n2");
            context.put("PRECIO", "$1\n$5\n$20\n$0.5\n$2");
            context.put("IMPORTE", "$3\n$20\n$20\n$5\n$4");
            context.put("SUBTOTAL", "$50");
            context.put("IVA", "$8");
            context.put("TOTAL", "$58");

            Options options = Options.getTo(ConverterTypeTo.PDF);
            
            OutputStream out = new FileOutputStream(new File("Formato_Out.pdf"));
            report.convert(context, options, out);
            System.out.println("Éxito");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
       }
}
