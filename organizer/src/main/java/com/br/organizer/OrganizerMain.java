package com.br.organizer;

import com.br.admin.SpringBootInitializer;
import com.br.core.service.DataSourceConfigurationService;
import com.br.organizer.organization.FileOrganizationAgent;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Path;

@Configuration
@ComponentScan(basePackages = "com.br")
public class OrganizerMain {

    private final DataSourceConfigurationService dataSourceConfigurationService;

    private static ConfigurableApplicationContext springContext;

    public OrganizerMain(DataSourceConfigurationService dataSourceConfigurationService) {
        this.dataSourceConfigurationService = dataSourceConfigurationService;
    }

    public static void main(String[] args) throws IOException {
        // Inicializar o contexto do Spring
        springContext = SpringApplication.run(SpringBootInitializer.class);

        // Obter a instância gerenciada pelo Spring
        OrganizerMain organizerMain = springContext.getBean(OrganizerMain.class);

        // Executar a lógica principal
        organizerMain.run();
    }

    public void run() throws IOException {
        // Obter os caminhos de origem e destino
        String originPath = dataSourceConfigurationService.findByName("Caminho de origem").getPath();
        String destinationPath = dataSourceConfigurationService.findByName("Caminho de destino").getPath();

        // Instanciar e executar o agente de organização de arquivos
        FileOrganizationAgent fileOrganizationAgent = new FileOrganizationAgent(
                Path.of(originPath),
                Path.of(destinationPath)
        );
        fileOrganizationAgent.run();
    }
}
