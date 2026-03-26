package org.openapitools.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Account
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-25T15:00:23.532591213+05:30[Asia/Kolkata]", comments = "Generator version: 7.21.0")
public class Account {

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    SORT_CODE_ACCOUNT_NUMBER("SORT_CODE_ACCOUNT_NUMBER");

    private final String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private TypeEnum type;

  private String accountNumber;

  private String sortCode;

  public Account() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Account(TypeEnum type, String accountNumber, String sortCode) {
    this.type = type;
    this.accountNumber = accountNumber;
    this.sortCode = sortCode;
  }

  public Account type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @NotNull 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(TypeEnum type) {
    this.type = type;
  }

  public Account accountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  /**
   * Get accountNumber
   * @return accountNumber
   */
  @NotNull @Size(min = 8, max = 8) 
  @Schema(name = "accountNumber", example = "12345678", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("accountNumber")
  public String getAccountNumber() {
    return accountNumber;
  }

  @JsonProperty("accountNumber")
  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public Account sortCode(String sortCode) {
    this.sortCode = sortCode;
    return this;
  }

  /**
   * Get sortCode
   * @return sortCode
   */
  @NotNull @Size(min = 6, max = 6) 
  @Schema(name = "sortCode", example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sortCode")
  public String getSortCode() {
    return sortCode;
  }

  @JsonProperty("sortCode")
  public void setSortCode(String sortCode) {
    this.sortCode = sortCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.type, account.type) &&
        Objects.equals(this.accountNumber, account.accountNumber) &&
        Objects.equals(this.sortCode, account.sortCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, accountNumber, sortCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
    sb.append("    sortCode: ").append(toIndentedString(sortCode)).append("\n");
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

