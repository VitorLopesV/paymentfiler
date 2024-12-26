package com.br.organizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.stream.Stream;

public class OrganizerMain {

    public static Path createFolder(Path directory) {
        LocalDate today = LocalDate.now();
        String folderName = today.getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pt-BR"));

        File folder = new File(directory.toFile() + "/" + folderName);

        if (folder.mkdir()) {
            System.out.println("Folder " + folderName + " created");
        } else {
            System.out.println("Folder " + folderName + " already exists");
        }
        return folder.toPath();
    }

    public static void main(String[] args) throws IOException {
        Path originPath = Path.of("C:/Users/vitor/Desktop/Comprovantes de pagamento");
        Path destinationPath = Path.of("G:/Meu Drive/PROGRAMAÇÃO");

        if (originPath.toFile().exists()) {
            try (Stream<Path> filesList = Files.list(originPath)) {
                filesList.filter(Files::isRegularFile).forEach(file -> {
                    try {
                        Path finalDirectory = createFolder(destinationPath).resolve(file.getFileName());
                        Files.move(file, finalDirectory, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Moved: " + file + " -> " + finalDirectory);
                    } catch (IOException e) {
                        System.err.println("Error moving file: " + file + " - " + e.getMessage());
                    }
                });
            }
        } else {
            Files.createDirectories(destinationPath);
        }
    }
}