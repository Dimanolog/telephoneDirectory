package com.epam.telephonedirectory.views;

import com.epam.telephonedirectory.entities.TelephoneNumber;
import com.epam.telephonedirectory.entities.User;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("userPdf")
public class UserPdfView extends AbstractPdfView {

    public static final String USERS_KEY = "users";

    @Override
    @SuppressWarnings("unchecked")
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        Iterable<User> users = (Iterable<User>) model.get(USERS_KEY);

        Paragraph header = new Paragraph(new Chunk("User List", FontFactory.getFont(FontFactory.COURIER, 24)));
        document.add(header);


        for (User user : users) {
            List<TelephoneNumber> telephoneNumbers = user.getTelephoneNumbers();

            StringBuilder userDataSb = new StringBuilder()
                    .append("User: ")
                    .append(user.getFirstName())
                    .append(" ")
                    .append(user.getLastName())
                    .append(" telephone numbers:\n");

            for (TelephoneNumber telephoneNumber : telephoneNumbers) {
                userDataSb.append(telephoneNumber.getTelephoneNumber())
                        .append(" ")
                        .append(telephoneNumber.getTelephoneCompany().getName())
                        .append("\n");
            }

            Paragraph userPararaph = new Paragraph(new Chunk(userDataSb.toString(),
                    FontFactory.getFont(FontFactory.COURIER, 14)));

            document.add(userPararaph);
        }

        document.close();
    }
}
