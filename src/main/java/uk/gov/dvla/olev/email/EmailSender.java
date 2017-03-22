package uk.gov.dvla.olev.email;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.regions.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.*;
import com.amazonaws.services.simpleemail.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The email sender sends an email via SES using a proxy
 *
 *
 * Created by ben on 20/02/17.
 */
public class EmailSender {

    private static Logger logger =  LoggerFactory.getLogger( EmailSender.class );

    private EmailConfig emailConfig;

    public EmailSender(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }



    public void sendEmail(String email) {
        //send the email via AWS

        String to = email;
        String subjectStr = "test";
        String bodyStr = "body of email";
        String from = email;

        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(new String[]{to});

        // Create the subject and body of the message.
        Content subject = new Content().withData(subjectStr);
        Content textBody = new Content().withData(bodyStr);
        Body body = new Body().withText(textBody);

        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(from).withDestination(destination).withMessage(message);


        try {
            logger.info("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

            logger.debug("proxy port : "+emailConfig.getProxyPort());
            logger.debug("proxy host : "+emailConfig.getProxyHost());


            AmazonSimpleEmailServiceClientBuilder builder = AmazonSimpleEmailServiceClientBuilder.standard();

            builder.setRegion(Regions.EU_WEST_1.getName());

            builder.setClientConfiguration(
                new ClientConfiguration()
                    .withConnectionTimeout(3000)
                    .withRequestTimeout(3000)
                    .withProxyPort(emailConfig.getProxyPort())
                    .withProxyHost(emailConfig.getProxyHost())
            );

            AmazonSimpleEmailService service = builder.build();

            SendEmailResult sendEmailResult = service.sendEmail(request);

            logger.info("Email sent!");
        } catch (Exception ex) {
            logger.info("The email was not sent.");
            logger.info("Error message: " + ex.getMessage());

        }

    }


}
