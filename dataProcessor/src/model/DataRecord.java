package model;

import java.time.LocalDate;

public class DataRecord {
    private String id;
    private String name;
    private Double value;
    private LocalDate date;

    public DataRecord(String id, String name, Double value, LocalDate date) {
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Double getDoubledValue() { return value * 2; }
    public Double getSquaredValue() { return value * value; }


}