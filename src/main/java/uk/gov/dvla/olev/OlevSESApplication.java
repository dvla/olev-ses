package uk.gov.dvla.olev;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import uk.gov.dvla.olev.resources.EmailResource;

public class OlevSESApplication extends Application<OlevSESConfiguration> {

    public static void main(final String[] args) throws Exception {
        new OlevSESApplication().run(args);
    }

    @Override
    public String getName() {
        return "olev-ses";
    }

    @Override
    public void initialize(final Bootstrap<OlevSESConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final OlevSESConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application

        final EmailResource emailResource = new EmailResource(configuration);
        environment.jersey().register(emailResource);

    }

}
