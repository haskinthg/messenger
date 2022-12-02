package dev.application.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Chat extends BaseEntity {

    private String content;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "users", updatable = true)
    private Set<User> users;

}
