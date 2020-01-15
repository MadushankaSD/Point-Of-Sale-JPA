package io.github.madushanka.pos.dao;



import java.util.List;

public interface CrudDAO<T,ID> extends SuperDAO {

    public abstract List<T> findAll() throws Exception;

    public abstract T find(ID id)throws Exception;

    public abstract void save(T entity)throws Exception;

    public abstract void update(T entity)throws Exception;

    public abstract void delete(ID id)throws Exception;

}
