package org.sahni.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.sahni.models.db.UsersVerify;

@ApplicationScoped
public class UsersVerifyRepository implements PanacheRepository<UsersVerify> {

    @WithTransaction
    public Uni<UsersVerify> fetchUserByEmailId(String emailId) {
        return find("emailID", emailId).firstResult();
    }

    @WithTransaction
    public Uni<UsersVerify> persistUser(UsersVerify usersVerify) {
        return persist(usersVerify);
    }
}
