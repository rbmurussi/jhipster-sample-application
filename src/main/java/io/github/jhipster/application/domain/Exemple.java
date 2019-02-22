package io.github.jhipster.application.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Exemple.
 */
@Document(collection = "exemple")
public class Exemple implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @Field("type_string")
    private String typeString;

    @NotNull
    @Size(min = 1, max = 42)
    @Pattern(regexp = "[A-Z]+")
    @Field("type_string_pattern")
    private String typeStringPattern;

    @Field("type_integer")
    private Integer typeInteger;

    @Field("type_long")
    private Long typeLong;

    @Field("type_big_decimal")
    private BigDecimal typeBigDecimal;

    @Field("type_local_date")
    private LocalDate typeLocalDate;

    @Field("type_instant")
    private Instant typeInstant;

    @Field("type_image_blob")
    private byte[] typeImageBlob;

    @Field("type_image_blob_content_type")
    private String typeImageBlobContentType;

    @Field("type_text_blob")
    private String typeTextBlob;

    @Field("type_any_blob")
    private byte[] typeAnyBlob;

    @Field("type_any_blob_content_type")
    private String typeAnyBlobContentType;

    @Field("type_blob")
    private byte[] typeBlob;

    @Field("type_blob_content_type")
    private String typeBlobContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeString() {
        return typeString;
    }

    public Exemple typeString(String typeString) {
        this.typeString = typeString;
        return this;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public String getTypeStringPattern() {
        return typeStringPattern;
    }

    public Exemple typeStringPattern(String typeStringPattern) {
        this.typeStringPattern = typeStringPattern;
        return this;
    }

    public void setTypeStringPattern(String typeStringPattern) {
        this.typeStringPattern = typeStringPattern;
    }

    public Integer getTypeInteger() {
        return typeInteger;
    }

    public Exemple typeInteger(Integer typeInteger) {
        this.typeInteger = typeInteger;
        return this;
    }

    public void setTypeInteger(Integer typeInteger) {
        this.typeInteger = typeInteger;
    }

    public Long getTypeLong() {
        return typeLong;
    }

    public Exemple typeLong(Long typeLong) {
        this.typeLong = typeLong;
        return this;
    }

    public void setTypeLong(Long typeLong) {
        this.typeLong = typeLong;
    }

    public BigDecimal getTypeBigDecimal() {
        return typeBigDecimal;
    }

    public Exemple typeBigDecimal(BigDecimal typeBigDecimal) {
        this.typeBigDecimal = typeBigDecimal;
        return this;
    }

    public void setTypeBigDecimal(BigDecimal typeBigDecimal) {
        this.typeBigDecimal = typeBigDecimal;
    }

    public LocalDate getTypeLocalDate() {
        return typeLocalDate;
    }

    public Exemple typeLocalDate(LocalDate typeLocalDate) {
        this.typeLocalDate = typeLocalDate;
        return this;
    }

    public void setTypeLocalDate(LocalDate typeLocalDate) {
        this.typeLocalDate = typeLocalDate;
    }

    public Instant getTypeInstant() {
        return typeInstant;
    }

    public Exemple typeInstant(Instant typeInstant) {
        this.typeInstant = typeInstant;
        return this;
    }

    public void setTypeInstant(Instant typeInstant) {
        this.typeInstant = typeInstant;
    }

    public byte[] getTypeImageBlob() {
        return typeImageBlob;
    }

    public Exemple typeImageBlob(byte[] typeImageBlob) {
        this.typeImageBlob = typeImageBlob;
        return this;
    }

    public void setTypeImageBlob(byte[] typeImageBlob) {
        this.typeImageBlob = typeImageBlob;
    }

    public String getTypeImageBlobContentType() {
        return typeImageBlobContentType;
    }

    public Exemple typeImageBlobContentType(String typeImageBlobContentType) {
        this.typeImageBlobContentType = typeImageBlobContentType;
        return this;
    }

    public void setTypeImageBlobContentType(String typeImageBlobContentType) {
        this.typeImageBlobContentType = typeImageBlobContentType;
    }

    public String getTypeTextBlob() {
        return typeTextBlob;
    }

    public Exemple typeTextBlob(String typeTextBlob) {
        this.typeTextBlob = typeTextBlob;
        return this;
    }

    public void setTypeTextBlob(String typeTextBlob) {
        this.typeTextBlob = typeTextBlob;
    }

    public byte[] getTypeAnyBlob() {
        return typeAnyBlob;
    }

    public Exemple typeAnyBlob(byte[] typeAnyBlob) {
        this.typeAnyBlob = typeAnyBlob;
        return this;
    }

    public void setTypeAnyBlob(byte[] typeAnyBlob) {
        this.typeAnyBlob = typeAnyBlob;
    }

    public String getTypeAnyBlobContentType() {
        return typeAnyBlobContentType;
    }

    public Exemple typeAnyBlobContentType(String typeAnyBlobContentType) {
        this.typeAnyBlobContentType = typeAnyBlobContentType;
        return this;
    }

    public void setTypeAnyBlobContentType(String typeAnyBlobContentType) {
        this.typeAnyBlobContentType = typeAnyBlobContentType;
    }

    public byte[] getTypeBlob() {
        return typeBlob;
    }

    public Exemple typeBlob(byte[] typeBlob) {
        this.typeBlob = typeBlob;
        return this;
    }

    public void setTypeBlob(byte[] typeBlob) {
        this.typeBlob = typeBlob;
    }

    public String getTypeBlobContentType() {
        return typeBlobContentType;
    }

    public Exemple typeBlobContentType(String typeBlobContentType) {
        this.typeBlobContentType = typeBlobContentType;
        return this;
    }

    public void setTypeBlobContentType(String typeBlobContentType) {
        this.typeBlobContentType = typeBlobContentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Exemple exemple = (Exemple) o;
        if (exemple.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), exemple.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Exemple{" +
            "id=" + getId() +
            ", typeString='" + getTypeString() + "'" +
            ", typeStringPattern='" + getTypeStringPattern() + "'" +
            ", typeInteger=" + getTypeInteger() +
            ", typeLong=" + getTypeLong() +
            ", typeBigDecimal=" + getTypeBigDecimal() +
            ", typeLocalDate='" + getTypeLocalDate() + "'" +
            ", typeInstant='" + getTypeInstant() + "'" +
            ", typeImageBlob='" + getTypeImageBlob() + "'" +
            ", typeImageBlobContentType='" + getTypeImageBlobContentType() + "'" +
            ", typeTextBlob='" + getTypeTextBlob() + "'" +
            ", typeAnyBlob='" + getTypeAnyBlob() + "'" +
            ", typeAnyBlobContentType='" + getTypeAnyBlobContentType() + "'" +
            ", typeBlob='" + getTypeBlob() + "'" +
            ", typeBlobContentType='" + getTypeBlobContentType() + "'" +
            "}";
    }
}
