package DAO;
import java.util.List;

public interface InterfaceDAO<T>
{
	void insert(T obj);

    void update(T obj);

	List<T> get();

    void delete(T obj);
}
