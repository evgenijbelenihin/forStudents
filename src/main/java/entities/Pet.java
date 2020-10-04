package main.java.entities;

import main.java.data.Statuses;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//@JsonDeserialize
public class Pet {
    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Category> tags;
    private String status;

    public Pet(){

    }

    public Pet(long id, Category category, String name, List<String> photoUrls, List<Category> tags, String status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public Pet(long id, Category dogs, String crazy, List<String> urls, List<Category> tags, Statuses available) {
    }

    public long getID() {
        return id;
    }

    public void setID(long value) {
        this.id = value;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category value) {
        this.category = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> value) {
        this.photoUrls = value;
    }

    public List<Category> getTags() {
        return tags;
    }

    public void setTags(List<Category> value) {
        this.tags = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    @Override
    public String toString() {
        return "Pet: \n" +
                "Id = " + this.id + "\n" +
                "Category = " + this.category.getName() + "\n" +
                "Name = " + this.name + "\n" +
                "PhotoUrls = " + Arrays.toString(this.photoUrls.toArray()) + "\n" +
                "Tags = " + Arrays.toString(this.tags.stream().map(Category::getName).toArray()) + "\n" +
                "Status = " + this.status + "\n";
    }
}