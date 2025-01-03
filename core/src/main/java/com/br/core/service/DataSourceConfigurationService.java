package com.br.core.service;

import com.br.core.model.DataSourceConfigurator;
import com.br.core.repository.DataSourceConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSourceConfigurationService {

    private final DataSourceConfigurationRepository repository;

    @Autowired
    public DataSourceConfigurationService(DataSourceConfigurationRepository repository) {
        this.repository = repository;
    }

    public void saveDataSource(String sourcePath, String destinationPath) {
        if (sourcePath == null || sourcePath.isEmpty() || destinationPath == null || destinationPath.isEmpty()) {
            throw new IllegalArgumentException("SourcePath and DestinationPath cannot be empty.");
        }

        DataSourceConfigurator dataSource = new DataSourceConfigurator(sourcePath, destinationPath);
        repository.save(dataSource);
    }
}
