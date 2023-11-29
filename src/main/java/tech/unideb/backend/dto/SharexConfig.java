package tech.unideb.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.unideb.backend.BackendApplication;

/**
 * Represents a ShareX upload configuration file.
 *
 * @see <a href="https://getsharex.com/docs/custom-uploader#sxcu-file">documentation</a>
 */
public class SharexConfig {
    @JsonProperty("Version")
    private final String version = "14.0.0";
    @JsonProperty("Name")
    private final String name = "unideb.tech uploader";
    @JsonProperty("DestinationType")
    private final String destination = "ImageUploader";
    @JsonProperty("RequestMethod")
    private final String method = "POST";
    @JsonProperty("RequestURL")
    private final String url = BackendApplication.API_BASE_URL + "/upload";
    @JsonProperty("Body")
    private final String body = "MultipartFormData";
    @JsonProperty("Arguments")
    private Arguments args;
    @JsonProperty("FileFormName")
    private final String file = "image";
    @JsonProperty("URL")
    private final String viewUrl = "{json:url}";
    @JsonProperty("ThumbnailURL")
    private final String thumbnail = "{json:rawUrl}";
    @JsonProperty("ErrorMessage")
    private final String error = "{json:message}";

    public record Arguments(@JsonProperty("secret") String secret) { }

    public SharexConfig(String secret) {
        this.args = new Arguments(secret);
    }
}
