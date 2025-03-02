package com.br.organizer.organization;

import com.br.organizer.constants.OrganizerLNGConstants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

/** Agente de organização dos arquivos. */
public class FileOrganizationAgent implements OrganizerAgent {

    /** Diretório de origem dos arquivos. */
    private final Path originPath;

    /** Diretório de destino dos arquivos. */
    private final Path destinationPath;

    /**
     * Construtor.
     *
     * @param originPath      {@link #originPath}
     * @param destinationPath {@link #destinationPath}
     */
    public FileOrganizationAgent(Path originPath, Path destinationPath) {
        this.originPath = originPath;
        this.destinationPath = destinationPath;
    }

    /**
     * Cria uma pasta para cada mês.
     *
     * @param directory Diretório onde a pasta será criada.
     * @return Caminho completo onde os arquivos serão movidos.
     */
    private Path createFolder(Path directory) {
        LocalDate today = LocalDate.now();
        String folderName = today.getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("pt-BR"));

        File folder = new File(directory.toFile() + "/" + folderName);

        if (folder.mkdir()) {
            System.out.printf((OrganizerLNGConstants.ORGANIZER_LNG_CREATED_FOLDER) + "%n", folderName);
        }
        return folder.toPath();
    }

    @Override
    public void run() throws IOException {
        if (!originPath.toFile().exists()) {
            Files.createDirectories(originPath);
            System.out.printf((OrganizerLNGConstants.ORGANIZER_LNG_CREATE_ORIGIN_DIRECTORY) + "%n", originPath);
        }

        try (Stream<Path> filesList = Files.list(originPath)) {
            List<Path> files = filesList.toList();

            if (files.isEmpty()) {
                System.out.print(OrganizerLNGConstants.ORGANIZER_LNG_NOT_EXIST_FILE_TO_MOVE);
                return;
            }

            for (Path file : files) {
                try {
                    // Se for um diretório, cria a pasta correspondente no destino e move os arquivos
                    if (Files.isDirectory(file)) {
                        Path finalDirectory = createFolder(destinationPath).resolve(file.getFileName());
                        Files.createDirectories(finalDirectory);

                        try (Stream<Path> subFiles = Files.list(file)) {
                            subFiles.forEach(subFile -> {
                                try {
                                    Files.move(subFile, finalDirectory.resolve(subFile.getFileName()),
                                            StandardCopyOption.REPLACE_EXISTING);
                                    System.out.printf(OrganizerLNGConstants.ORGANIZER_LNG_MOVED_FILE, subFile.getFileName(),
                                            finalDirectory);
                                    System.out.println("\n");
                                } catch (IOException e) {
                                    System.err.printf(OrganizerLNGConstants.ORGANIZER_LNG_ERROR_MOVED_FILE, subFile.getFileName(),
                                            e.getMessage());
                                }
                            });
                        }
                    } else {
                        // Move o arquivo diretamente para o destino
                        Path finalDirectory = createFolder(destinationPath).resolve(file.getFileName());
                        Files.move(file, finalDirectory, StandardCopyOption.REPLACE_EXISTING);
                        System.out.printf(OrganizerLNGConstants.ORGANIZER_LNG_MOVED_FILE, file.getFileName(), finalDirectory);
                        System.out.println("\n");
                    }
                } catch (IOException e) {
                    System.err.printf(OrganizerLNGConstants.ORGANIZER_LNG_ERROR_MOVED_FILE, file.getFileName(), e.getMessage());
                }
            }
        }
    }
}
