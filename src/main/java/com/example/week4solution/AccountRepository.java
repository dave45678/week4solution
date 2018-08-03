package com.example.week4solution;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Transaction,Long> {

}
