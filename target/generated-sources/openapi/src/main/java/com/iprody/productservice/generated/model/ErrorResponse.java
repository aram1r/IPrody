package com.iprody.productservice.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ErrorResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-04-27T23:14:36.864308200+04:00[Asia/Yerevan]", comments = "Generator version: 7.9.0")
public class ErrorResponse {

  private String message;

  @Valid
  private List<String> details = new ArrayList<>();

  private String resourceGuid;

  public ErrorResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ErrorResponse(String message, List<String> details, String resourceGuid) {
    this.message = message;
    this.details = details;
    this.resourceGuid = resourceGuid;
  }

  public ErrorResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  @NotNull 
  @Schema(name = "message", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorResponse details(List<String> details) {
    this.details = details;
    return this;
  }

  public ErrorResponse addDetailsItem(String detailsItem) {
    if (this.details == null) {
      this.details = new ArrayList<>();
    }
    this.details.add(detailsItem);
    return this;
  }

  /**
   * Get details
   * @return details
   */
  @NotNull 
  @Schema(name = "details", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("details")
  public List<String> getDetails() {
    return details;
  }

  public void setDetails(List<String> details) {
    this.details = details;
  }

  public ErrorResponse resourceGuid(String resourceGuid) {
    this.resourceGuid = resourceGuid;
    return this;
  }

  /**
   * Get resourceGuid
   * @return resourceGuid
   */
  @NotNull 
  @Schema(name = "resourceGuid", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("resourceGuid")
  public String getResourceGuid() {
    return resourceGuid;
  }

  public void setResourceGuid(String resourceGuid) {
    this.resourceGuid = resourceGuid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorResponse errorResponse = (ErrorResponse) o;
    return Objects.equals(this.message, errorResponse.message) &&
        Objects.equals(this.details, errorResponse.details) &&
        Objects.equals(this.resourceGuid, errorResponse.resourceGuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, details, resourceGuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorResponse {\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
    sb.append("    resourceGuid: ").append(toIndentedString(resourceGuid)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

