package org.sahni.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.sahni.models.db.Users;

@ApplicationScoped
public class UsersRepository implements PanacheRepository<Users> {

    @WithSession
    public Uni<Users> fetchUserById(Long id) {
        return find("id", id).firstResult();
    }

    @WithSession
    public Uni<Users> createUser(Users user) {
        return persist(user);
    }
}
