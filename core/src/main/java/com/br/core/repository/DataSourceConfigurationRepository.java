package com.br.core.repository;

import com.br.core.model.DataSourceConfigurator;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataSourceConfigurationRepository extends MongoRepository<DataSourceConfigurator, String> {

    DataSourceConfigurator findByName(String name);

}
