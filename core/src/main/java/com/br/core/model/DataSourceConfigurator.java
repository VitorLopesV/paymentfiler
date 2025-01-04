package com.br.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dataSourceConfiguration")
public class DataSourceConfigurator {

    @Id
    protected String id;

    protected String name;

    protected String path;

    public DataSourceConfigurator(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
