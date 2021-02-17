package com.itays123.javanotebook;

import com.itays123.javanotebook.block.Block;
import com.itays123.javanotebook.block.BlockType;
import com.itays123.javanotebook.note.Note;
import com.itays123.javanotebook.note.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class JavaNotebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaNotebookApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(NoteRepository noteRepository) {
		return args -> {
			Note note = new Note("My First Note");

			Block block1 = new Block("A Paragraph", BlockType.P);
			Block block2 = new Block("A Heading", BlockType.H3);

			note.getContent().add(block1);
			note.getContent().add(block2);

			noteRepository.save(note);
		};
	}

}
