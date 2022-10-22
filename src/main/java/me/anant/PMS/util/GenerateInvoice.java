package me.anant.PMS.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Set;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import me.anant.PMS.model.OrderProduct;

public class GenerateInvoice {

	public static void exportToPdfBox(Set<OrderProduct> opList,float sum) {

		try {
			ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
			templateResolver.setSuffix(".html");
			templateResolver.setTemplateMode(TemplateMode.HTML);

			TemplateEngine templateEngine = new TemplateEngine();
			templateEngine.setTemplateResolver(templateResolver);

			Context context = new Context();
			context.setVariable("invoiceDate", new Date());
			context.setVariable("totalAmount", sum);
			context.setVariable("orderedProductList", opList);

			String html = templateEngine.process("InvoiceTemplate", context);

			String outputFolder = "invoice.pdf";
			System.out.println(outputFolder);
			OutputStream outputStream = new FileOutputStream(outputFolder);

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(html);
			renderer.layout();
			renderer.createPDF(outputStream);

			outputStream.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}