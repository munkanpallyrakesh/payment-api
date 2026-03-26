package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.lang.Nullable;

import java.lang.Error;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * BadRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-25T15:00:23.532591213+05:30[Asia/Kolkata]", comments = "Generator version: 7.21.0")
public class BadRequest {

  @Valid
  private List< java.lang.Error> errors = new ArrayList<>();

  public BadRequest errors(List< java.lang.Error> errors) {
    this.errors = errors;
    return this;
  }

  public BadRequest addErrorsItem(java.lang.Error errorsItem) {
    if (this.errors == null) {
      this.errors = new ArrayList<>();
    }
    this.errors.add(errorsItem);
    return this;
  }

  /**
   * Get errors
   * @return errors
   */
  @Valid 
  @Schema(name = "errors", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("errors")
  public List< java.lang.Error> getErrors() {
    return errors;
  }

  @JsonProperty("errors")
  public void setErrors(List<@Valid Error> errors) {
    this.errors = errors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BadRequest badRequest = (BadRequest) o;
    return Objects.equals(this.errors, badRequest.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BadRequest {\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(@Nullable Object o) {
    return o == null ? "null" : o.toString().replace("\n", "\n    ");
  }
}

