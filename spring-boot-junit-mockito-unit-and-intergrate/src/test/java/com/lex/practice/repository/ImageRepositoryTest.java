package com.lex.practice.repository;

import com.lex.practice.container.TestContainers;
import com.lex.practice.entity.Image;
import com.lex.practice.integration.BaseIntegrationTest;
import com.lex.practice.utils.ImageUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author : Lex Yu
 * @date : 2023/9/4
 */
@Slf4j
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ImageRepositoryTest extends BaseIntegrationTest {

	@Autowired
	private ImageRepository imageRepository;
	private static Image imageFixture;

	@BeforeEach
	void setUp() {
		imageFixture = Image.builder()
				.id(1L)
				.name("integration-test-image.png")
				.type("image/png")
				.imageData(new byte[ImageUtils.BITE_SIZE])
				.build();
		imageRepository.save(imageFixture);
	}

	@Test
	void should_context_not_null(){
		assertThat(imageRepository).isNotNull();
	}

	@Test
	@Order(1)
	void should_upload_an_image_in_database() {
		// Arrange & Act
		List<Image> imageList = imageRepository.findAll();

		log.info("{}", imageList);

		// Assert
		assertThat(imageList).hasSize(1);
		assertThat(imageList.get(0))
				.usingRecursiveComparison()
				.isEqualTo(imageFixture);
	}

	@Test
	@Order(2)
	void should_download_an_image_from_database() {
		// Arrange
		String nameOfImageToDownload = imageFixture.getName();

		// Act & Assert
		Optional<Image> imageDownloaded = imageRepository.findByName(nameOfImageToDownload);
		assertThat(imageDownloaded).isPresent()
				.map(Image::getId)
				.get().isEqualTo(2L);
		assertThat(imageDownloaded)
				.isPresent()
				.get()
				.usingRecursiveComparison()
				.ignoringFields("id")
				.isEqualTo(imageFixture);
	}
}
