package jp.kobespiral.fukushima.todo.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import jp.kobespiral.fukushima.todo.dto.ToDoForm;
import jp.kobespiral.fukushima.todo.entity.ToDo;
import jp.kobespiral.fukushima.todo.exception.ToDoAppException;
import jp.kobespiral.fukushima.todo.repository.ToDoRepository;

@Service
public class ToDoService {
   @Autowired
   ToDoRepository tRepo;
   
   /**
    * ToDoを作成する (C)
    * @param form
    * @return
    */
   public ToDo createToDo(String mid, ToDoForm form) {
        ToDo t = form.toEntity(mid);
        return tRepo.save(t);
   }
   
   /**
    * ToDoを取得する (R)
    *
    * @param mid
    * @return
    */
   public ToDo getToDo(long seq) {
       ToDo t = tRepo.findById(seq).orElseThrow(
               () -> new ToDoAppException(ToDoAppException.NO_SUCH_MEMBER_EXISTS, seq + ": No such member exists"));
       return t;
   }

   /**
    * 完了にする
    *
    * @param seq
    */
   public ToDo doDone(long seq) {
        Date date = new Date();
        ToDo t = tRepo.findById(seq).orElseThrow(
            () -> new ToDoAppException(ToDoAppException.NO_SUCH_MEMBER_EXISTS, seq + ": No such member exists"));
        
        t.setDone(true);
        t.setDoneAt(date);

        return tRepo.save(t); //弄ったらセーブすること
   }

   /**
    * midのToDoリストを取得する (R)
    *
    * @param mid
    * @return
    */
   public List<ToDo> getToDoList(String mid) {
       return tRepo.findByMidAndDone(mid, false);
   }

    /**
    * midのDoneリストを取得する (R)
    *
    * @param mid
    * @return
    */
    public List<ToDo> getDoneList(String mid) {
        return tRepo.findByMidAndDone(mid, true);
    }

    /**
    * 全員のToDoリストを取得する (R)
    *
    * @return
    */
    public List<ToDo> getToDoList() {
        return tRepo.findByDone(false);
    }

    /**
    * 全員のDoneリストを取得する (R)
    *
    * @return
    */
    public List<ToDo> getDoneList() {
        return tRepo.findByDone(true);
    }

    /**
     * TODOを削除する
     * 
     * @param seq
     */
    public void deleteToDo(long seq) {
        ToDo t = tRepo.findById(seq).orElseThrow(
            () -> new ToDoAppException(ToDoAppException.NO_SUCH_MEMBER_EXISTS, seq + ": No such member exists"));
        
        tRepo.delete(t);

        return;
    }

    /**
     * TODOのタイトルを変更する
     * 
     * @return
     */
    public ToDo updateToDo(long seq, ToDoForm form) {
        ToDo t  = tRepo.findById(seq).orElseThrow(
            () -> new ToDoAppException(ToDoAppException.NO_SUCH_MEMBER_EXISTS, seq + ": No such member exists"));
        
        t.setTitle(form.getTitle()); 
        return tRepo.save(t);
    }
}