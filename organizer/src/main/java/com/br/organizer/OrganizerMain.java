package com.br.organizer;

import com.br.organizer.organization.FileOrganizationAgent;

import java.io.IOException;
import java.nio.file.Path;

public class OrganizerMain {

    public static void main(String[] args) throws IOException {
        Path originPath = Path.of("C:/Users/vitor/Desktop/Comprovantes de pagamento");
        Path destinationPath = Path.of("G:/Meu Drive/PROGRAMAÇÃO");

        FileOrganizationAgent fileOrganizationAgent = new FileOrganizationAgent(originPath, destinationPath);
        fileOrganizationAgent.run();
    }
}