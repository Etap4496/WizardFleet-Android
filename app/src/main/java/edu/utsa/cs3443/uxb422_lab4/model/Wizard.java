package edu.utsa.cs3443.uxb422_lab4.model;


//Imported Wizard class from Lab 2 with slight modifications
public class Wizard {
    private String name;
    private String role;
    private String rank;
    private String species;
    private String assignment;

    public Wizard(String name, String role, String rank, String species, String assignment) {
        this.name = name;
        this.role = role;
        this.rank = rank;
        this.species = species;
        this.assignment = assignment;
    }

    public Wizard(String name, String role, String rank, String species) {
        this(name, role, rank, species, "");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getRank() {
        return rank;
    }

    public String getImageResourceName() {
        // Convert name to lowercase to match the drawable image naming convention
        return name.toLowerCase();
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public String getAssignment() {
        return assignment;
    }
    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    @Override
    public String toString() {
        return String.format("- %s (%s) - %s [%s]", name, role, rank, species, assignment);
    }
}