package jp.kobespiral.fukushima.todo.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import jp.kobespiral.fukushima.todo.entity.ToDo;

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long>{
    List<ToDo> findByMidAndDone(String mid, Boolean done);
    List<ToDo> findByDone(Boolean done);
}