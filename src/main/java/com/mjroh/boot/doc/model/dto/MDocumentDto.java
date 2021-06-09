package com.mjroh.boot.doc.model.dto;

import com.mjroh.boot.doc.model.entity.MDocument;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MDocumentDto extends MDocument{
}
