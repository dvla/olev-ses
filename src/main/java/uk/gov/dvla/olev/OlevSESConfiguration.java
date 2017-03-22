package uk.gov.dvla.olev;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import uk.gov.dvla.olev.email.EmailConfig;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by ben on 22/03/17.
 */
public class OlevSESConfiguration extends Configuration {

    // SWAGGER
    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    // EMAIL

    /**EmailConfig instance.*/
    @Valid
    @NotNull
    private EmailConfig emailConfig = new EmailConfig();

    @JsonProperty
    public final EmailConfig getEmailConfig() {
        return emailConfig;
    }

    @JsonProperty
    public final void setEmailConfig(final EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }


}
