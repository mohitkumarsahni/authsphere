package org.sahni.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.sahni.models.db.Users;

@ApplicationScoped
public class UsersRepository implements PanacheRepository<Users> {

    @WithTransaction
    public Uni<Users> fetchUserById(Long id) {
        return find("id", id).firstResult();
    }

    @WithTransaction
    public Uni<Users> fetchUserByEmailId(String emailId) {
        return find("emailID", emailId).firstResult();
    }

    @WithTransaction
    public Uni<Users> persistUser(Users user) {
        return persist(user);
    }
}
