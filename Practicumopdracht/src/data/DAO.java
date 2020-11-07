package data;

import java.io.FileNotFoundException;
import java.util.List;

public interface DAO<T> {

    public List<T> getAll();

    public T get(int id);

    public void addOrUpdate(T object);

    public void remove(T object);

    public boolean save() throws FileNotFoundException;

    public boolean load() throws FileNotFoundException;

}
