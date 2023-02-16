package ibf2022.paf.day29;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ibf2022.paf.day29.services.TvShowService;

@SpringBootApplication
public class Day29Application {

	@Autowired
	private TvShowService tvShowService;
	public static void main(String[] args) {
		SpringApplication.run(Day29Application.class, args);
	}
	
	@Override
	public void run(String... args){

		
	}

}
