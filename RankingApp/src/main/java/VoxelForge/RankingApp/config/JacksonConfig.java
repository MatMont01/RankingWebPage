// Archivo: src/main/java/VoxelForge/RankingApp/config/JacksonConfig.java
package VoxelForge.RankingApp.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public Module hibernate6Module() {
        Hibernate6Module module = new Hibernate6Module();

        // --- ¡AQUÍ ESTÁ LA CORRECCIÓN! ---
        // Habilitamos la característica para forzar la carga de relaciones "lazy"
        // antes de que Jackson las convierta a JSON.
        module.enable(Hibernate6Module.Feature.FORCE_LAZY_LOADING);

        return module;
    }
}