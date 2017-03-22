package uk.gov.dvla.olev.email;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by ben on 27/02/17.
 */
public class EmailConfig {

    @Min(1) @Max(65535) private int proxyPort;
    private String proxyHost;

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

}
