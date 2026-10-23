package com.restaurant.service;

import com.restaurant.model.Table;
import com.restaurant.repository.TableRepository;
import java.util.List;

public class TableService {

    private final TableRepository repository;

    public TableService() {
        repository = new TableRepository();
    }

    public List<Table> getAllTables() {
        return repository.findAll();
    }

    public Table getTableById(int id) {
        Table table = repository.findById(id);

        if (table == null) {
            throw new IllegalArgumentException("Table not found.");
        }

        return table;
    }
}
