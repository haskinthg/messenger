package dev.application.models.entities;

import dev.application.models.ChatStatus;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chats")
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatEntity extends BaseEntity {

    public ChatEntity() {
        this.users = new HashSet<UserEntity>();
        this.messages = new ArrayList<MessageEntity>();
    }

    @ManyToMany(mappedBy = "chats", cascade = CascadeType.ALL)
    private Set<UserEntity> users;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @JoinColumn(name = "chat_id", updatable = true)
    private List<MessageEntity> messages;

    @Enumerated(EnumType.STRING)
    private ChatStatus chatStatus;

    public void addUser(UserEntity user) {
        this.users.add(user);
        user.getChats().add(this);
    }

    public void removeUser(UserEntity user) {
        this.users.remove(user);
        user.getChats().add(this);
    }
}
