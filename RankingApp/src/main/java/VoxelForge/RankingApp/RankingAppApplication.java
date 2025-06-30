// Archivo: src/main/java/VoxelForge/RankingApp/RankingAppApplication.java
package VoxelForge.RankingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling; // <-- IMPORTA ESTO

@SpringBootApplication
@EnableScheduling
public class RankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RankingAppApplication.class, args);
	}
}