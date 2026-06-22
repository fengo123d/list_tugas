package com.example.list_tugas;

import java.io.Serializable;
import java.util.List;

public class Fruit implements Serializable {
    private String name;
    private String icon;
    private String origin;
    private String taste;
    private String description;
    private List<String> healthBenefits;

    public Fruit(String name, String icon, String origin, String taste, String description, List<String> healthBenefits) {
        this.name = name;
        this.icon = icon;
        this.origin = origin;
        this.taste = taste;
        this.description = description;
        this.healthBenefits = healthBenefits;
    }

    public String getName() { return name; }
    public String getIcon() { return icon; }
    public String getOrigin() { return origin; }
    public String getTaste() { return taste; }
    public String getDescription() { return description; }
    public List<String> getHealthBenefits() { return healthBenefits; }
}
