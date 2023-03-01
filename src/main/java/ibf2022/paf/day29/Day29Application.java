package ibf2022.paf.day29;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ibf2022.paf.day29.services.TvShowService;

@SpringBootApplication
public class Day29Application implements CommandLineRunner {

	@Autowired
	private TvShowService tvShowService;
	public static void main(String[] args) {
		SpringApplication.run(Day29Application.class, args);
	}
	
	// Use this method to print out in the command line
	@Override
	public void run(String... args){
		// List<Document> results = tvShowService.countGenres();
		List<Document> results = tvShowService.histogramOfRatings();

		for (Document d: results)
			System.out.printf(">>> %s\n", d.toJson());
		
	}

}
