package ro.sci.digitalCookBook.domain;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


import com.itextpdf.layout.property.TextAlignment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

@Component("pdfview")
public class PdfRecipeView extends AbstractView {
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,

                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=reteta.pdf");

        Recipe recipe = (Recipe) model.get("reteta");
        String ingredient = (String) model.get("ingredients");
        RecipeIngredient recipeIngredient = (RecipeIngredient) model.get("instructions");


        PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(pdfWriter);
        Document pdfDocument = new Document(pdf);


        Paragraph title = new Paragraph("Instructiuni pentru reteta");
        title.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA));
        title.setFontSize(20f);
        title.setBold();
        title.setTextAlignment(TextAlignment.CENTER);
        pdfDocument.add(title);

        Paragraph recipeName = new Paragraph(recipe.getDenumire());
        recipeName.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA));
        recipeName.setFontSize(20f);
        recipeName.setBold();
        recipeName.setTextAlignment(TextAlignment.CENTER);
        recipeName.setItalic();
        pdfDocument.add(recipeName);

        Paragraph portions = new Paragraph("Portii: " + recipe.getPortii());
        portions.setFontSize(18f);
        pdfDocument.add(portions);

        Paragraph ingredienteTitle = new Paragraph("Ingrediente:");
        ingredienteTitle.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA));
        ingredienteTitle.setFontSize(18f);
        pdfDocument.add(ingredienteTitle);


        Paragraph ingredientsParagraph = new Paragraph(ingredient);
        ingredientsParagraph.setMarginLeft(25);
         pdfDocument.add(ingredientsParagraph);

        Paragraph instructionsTitle = new Paragraph("Pasi de urmat  ");
        instructionsTitle.setTextAlignment(TextAlignment.CENTER);
        instructionsTitle.setBold();
        instructionsTitle.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA));
        instructionsTitle.setFontSize(18f);
        pdfDocument.add(instructionsTitle);




        Paragraph instructions = new Paragraph(extractText(recipeIngredient.getInstructiuni()));
        instructions.setFirstLineIndent(25);

        pdfDocument.add(instructions);
        pdfDocument.close();
    }

    public static String extractText(String text) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(text);
        String textOnly = Jsoup.parse(sb.toString()).text();
        return textOnly;
    }

}
