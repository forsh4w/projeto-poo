package eleicao_poo.data;

import java.util.ArrayList;

public interface Database<T> {

    T find(String field);

    ArrayList<T> findAll();

    void add(T entity) throws Exception;

    boolean remove(T entity);

}
