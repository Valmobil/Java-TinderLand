package DAO;

import Models.Likes;

public abstract class AbstractDAO<T>
{
	abstract public void insert(T obj);

	abstract public void update(T obj);

	abstract public T get(Object pk);

	abstract public void delete(Object pk);
}
