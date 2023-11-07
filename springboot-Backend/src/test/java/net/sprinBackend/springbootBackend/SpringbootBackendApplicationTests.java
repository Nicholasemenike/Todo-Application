package net.sprinBackend.springbootBackend;

import net.sprinBackend.springbootBackend.models.User;
import net.sprinBackend.springbootBackend.repository.UserRepository;
import net.sprinBackend.springbootBackend.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SpringbootBackendApplicationTests {
	@Autowired
	private UserService userService;
	@MockBean
	private UserRepository userRepository;

	@BeforeEach
	void contextLoads() {
		User user = User.builder()
//				.name("emenike")
				.build();
		Mockito.when(userRepository.save(user)).thenReturn(user);
	}

	@Test
	public void saveUser(){
		userRepository.save(new User());
	}
}
