package com.itays123.javanotebook;

import com.itays123.javanotebook.block.Block;
import com.itays123.javanotebook.block.BlockType;
import com.itays123.javanotebook.note.Note;
import com.itays123.javanotebook.note.NoteRepository;
import com.itays123.javanotebook.user.User;
import com.itays123.javanotebook.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class JavaNotebookApplication {

	static final Logger log =
			LoggerFactory.getLogger(JavaNotebookApplication.class);

	public static void main(String[] args) {
		log.info("Using loggers");
		SpringApplication.run(JavaNotebookApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(NoteRepository noteRepository, UserRepository userRepository) {
		return args -> {
			User user = new User("Me", "email@gmail.com", "password");
			userRepository.save(user);

			Note note = new Note("My First Note");

			Block block1 = new Block("A Paragraph", BlockType.P);
			Block block2 = new Block("A Heading", BlockType.H3);

			note.getContent().add(block1);
			note.getContent().add(block2);

			note.setUser(user);

			noteRepository.save(note);
		};
	}

}
