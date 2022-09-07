package org.brienze.biscoint.repository;

import org.brienze.biscoint.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, String> {

}
