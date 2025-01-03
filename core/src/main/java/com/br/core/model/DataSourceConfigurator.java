package com.br.core.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "dataSourceConfiguration")
@Data
public class DataSourceConfigurator {

    @Id
    protected String id;

    protected String sourcePath;

    protected String destinationPath;

    public DataSourceConfigurator(String sourcePath, String destinationPath) {
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
    }

}
