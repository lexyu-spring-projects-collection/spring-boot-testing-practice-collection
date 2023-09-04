package com.lex.practice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * @author : Lex Yu
 * @date : 2023/9/4
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Images")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	private String type;

	@Lob
	@Type(type = "org.hibernate.type.ImageType")
	private byte[] imageData;
}
