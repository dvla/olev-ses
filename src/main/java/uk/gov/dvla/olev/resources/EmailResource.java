package uk.gov.dvla.olev.resources;

import javax.validation.Valid;
import javax.ws.rs.*;

import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.gov.dvla.olev.OlevSESConfiguration;
import uk.gov.dvla.olev.email.EmailSender;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * Created by ben on 22/03/17.
 */
@Path( "/email" )
@Api( "/email" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class EmailResource {

    private static Logger logger =  LoggerFactory.getLogger( EmailResource.class );

    private OlevSESConfiguration configuration;


    public EmailResource ( final OlevSESConfiguration configuration ) {
        this.configuration = configuration;

    }

    @GET
    @Timed
    @ApiOperation("test sending email by get")
    @Path( "/sendemail")
    public Response getTestEmail(@QueryParam("email") @Valid final String email)
    {

        logger.info("test email called");

        EmailSender sender = new EmailSender(configuration.getEmailConfig());

        if (email != null && email.length() > 3) {
            sender.sendEmail(email);
        }


        return Response.status( Response.Status.OK ).entity("attempted to send email to "+email).build();
    }

}
