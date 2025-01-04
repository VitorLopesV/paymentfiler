package com.br.core.service;

import com.br.core.model.DataSourceConfigurator;
import com.br.core.repository.DataSourceConfigurationRepository;
import org.springframework.stereotype.Service;

@Service
public class DataSourceConfigurationService {

    private final DataSourceConfigurationRepository repository;

    public DataSourceConfigurationService(DataSourceConfigurationRepository repository) {
        this.repository = repository;
    }

    public void save(DataSourceConfigurator dataSourceConfigurator) {
        DataSourceConfigurator existing = repository.findByName(dataSourceConfigurator.getName());
        if (existing != null) {
            dataSourceConfigurator.setId(existing.getId());
        }
        repository.save(dataSourceConfigurator);
    }

    public DataSourceConfigurator findByName(String name) {
        return this.repository.findByName(name);
    }
}
