package jp.kobespiral.fukushima.todo.dto;
import java.util.Date;

import jp.kobespiral.fukushima.todo.entity.ToDo;
import lombok.Data;

@Data
public class ToDoForm {
    String title;     //題目

    public ToDo toEntity(String mid) {
        Date createdAt = new Date();
        ToDo t = new ToDo(null, title, mid, false, createdAt, null);
        return t;
    }
}