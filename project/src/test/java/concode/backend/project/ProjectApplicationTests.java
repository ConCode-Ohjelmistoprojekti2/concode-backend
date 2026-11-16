package concode.backend.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import concode.backend.project.repository.SongRepository;
import concode.backend.project.repository.ScoreRepository;

@SpringBootTest(properties = {
		"spring.autoconfigure.exclude="
				+ "org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration,"
				+ "org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration,"
				+ "org.springframework.boot.data.jpa.autoconfigure.DataJpaRepositoriesAutoConfiguration"
})
class ProjectApplicationTests {

	@MockitoBean
	private SongRepository songRepository;

	@MockitoBean
	private ScoreRepository scoreRepository;

	@Test
	void contextLoads() {
	}
}
