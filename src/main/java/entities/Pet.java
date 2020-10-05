package main.java.entities;

import lombok.*;
import main.java.data.Statuses;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Category> tags;
    private String status;

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

    public Pet createPet() {
        //        Preparing test data
        Category dogs = new Category(1, "dogs");
        Category patrol = new Category(43, "patrol");

        return new Pet(
                100000 + (long) (Math.random() * 999999),
                dogs,
                "Crazy " + RandomStringUtils.randomAlphabetic(5),
                Collections.singletonList("urls"),
                Arrays.asList(dogs, patrol),
                Statuses.AVAILABLE.name());
    }
}