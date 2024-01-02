package com.zettamine.bank.dao;

import java.util.Optional;

public interface UserDao {

	Optional<String> getPassword(int id,char type);
}
