package DAO;
import Models.Users;

import java.util.List;

public interface InterfaceDAO<T>
{
	void insert(T obj);

    void update(T obj);

    abstract List<Users> get();

    void delete(T obj);
}
