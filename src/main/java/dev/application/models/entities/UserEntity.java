package dev.application.models.entities;

import dev.application.models.entities.auth.RoleUserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "user_chat",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id")
    )
    public Set<ChatEntity> chats;
    private String username;
    private boolean enabled;
    private String password;

    private String photo;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "user_id", updatable = true)
    private Set<RoleUserEntity> roles;
    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "user_id", updatable = true)
    private Set<MessageEntity> messages;

    public UserEntity(boolean enabled, String username, String password, Set roles) {
        this.password = passwordEncoder.encode(password);
        this.enabled = enabled;
        this.username = username;
        this.roles = roles;
    }


//    public void removeChat(Chat chat){
//        this.chats.remove(chat);
//        chat.getUsers().remove(this);
//    }
//
//    public void addChat(Chat chat){
//        this.chats.add(chat);
//        chat.getUsers().add(this);
//    }
}
