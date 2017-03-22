OLEV SES integration
====================

This dropwizard app sends an email using AWS SES when a webservice is invoked.   The EmailSender class connects to SES and sends the email, it uses the SES client builder and is configured to use a proxy server.
The current AWS documentation shows a deprecated method and does not show how to use a proxy server.
This example uses version 1.11 of the aws-java-sdk.  It is expected the dropwizard app will be deployed to an AWS instance with SES configured and the instance has been given permission to use SES (i.e. no api keys required).

How to start the olev-ses application
---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/olev-ses-1.0-SNAPSHOT.jar server config.yml`
3. To send an email enter url `http://localhost:8000/email/sendemail?email=name@domain.com` replacing the email parameter with your test email address.

