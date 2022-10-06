package me.anant.PMS.Helper;

import me.anant.PMS.model.OrderProduct;
import me.anant.PMS.service.EmailService;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Set;

@Component
public class EmailHelper {

    EmailService emailService;

    public EmailHelper(EmailService emailService)
    {
        this.emailService=emailService;
    }

    public void  sendSuccessfulOrderEmail(Principal principal, Set<OrderProduct> opList) {
        String message = "Hello,<br><br>Your order has been placed successfuly. Following is the detail of your order.<br><br>"
                + "<table>" +
                "<tr>" +
                "<th>Name</th>" +
                "<th>Price</th>" +
                "<th>Qty</th>" +
                "<th>Amount</th>" +
                "</tr>";
        float sum = 0;
        for (OrderProduct op : opList)
        {
            sum = sum + op.getProduct().getProductPrice() * op.getBuyqty();
            message = message + "<tr>" +
                    "<td>"+op.getProduct().getProductName()+"</td>" +
                    "<td>Rs. "+op.getProduct().getProductPrice()+"</td>" +
                    "<td>"+op.getBuyqty()+"</td>" +
                    "<td>Rs. "+op.getProduct().getProductPrice() * op.getBuyqty()+"</td>" +
                    "</tr>";
        }
        message = message + "<tr><td  colspan=\"3\"><center><b>Total Amount</b></center></td><td>Rs. "+sum+"</td></tr>" +
                "</table>";
        emailService.send(principal.getName(), "Order Placed successfully", message);
    }

}
