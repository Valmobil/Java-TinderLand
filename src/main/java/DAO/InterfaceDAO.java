package DAO;
import Models.Likes;

import java.util.List;

public interface InterfaceDAO<T>
{
	void insert(T obj);

    void update(T obj);

    List<T> get(String filter);

    void delete(T obj);
}
