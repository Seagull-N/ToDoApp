package jp.kobespiral.fukushima.todo.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.kobespiral.fukushima.todo.dto.LoginForm;
import jp.kobespiral.fukushima.todo.dto.ToDoForm;
import jp.kobespiral.fukushima.todo.entity.ToDo;
import jp.kobespiral.fukushima.todo.service.ToDoService;

@Controller
public class ToDoController {
   @Autowired
   ToDoService tService;

   /**
    * ログインページ
    *
    * @param model
    * @return
    */
   @GetMapping("/")
   String showLoginForm(Model model) {
    LoginForm form = new LoginForm();
    model.addAttribute("LoginForm", form);
    
       return "index";
   }

   /**
    * ログインできるかチェックする
    * いなくてもログインするガバガバ設定
    *
    * @param mid
    * @param model
    * @return
    */
   @GetMapping("/login")
   String checkLogin(@RequestParam String mid, Model model) {
        return ("redirect:" + mid + "/todos/");
   }

   /**
    * タスク登録する

    * @param title
    * @param mid
    * @param model
    * @return
    */
   @PostMapping("/{mid}/register")
    String checkRegister(ToDoForm form, @PathVariable String mid, RedirectAttributes r, Model model) {
        tService.createToDo(mid, form);
        //r.addFlashAttribute("mid", mid);  // モデル消えるから引数としてこれあげたいけど今回URLからとれるやつやからいらん。RequestParam取りたいときには使うこと
        return ("redirect:/" + mid + "/todos");
    }

   /**
    * 自分用のToDoリスト表示・登録 HTTP-GET /ユーザー名/todos
    *
    * @param model
    * @return
    */
   @GetMapping("/{mid}/todos")
   String showToDos(@PathVariable String mid, Model model) {
       List<ToDo> todos = tService.getToDoList(mid);
       List<ToDo> dones = tService.getDoneList(mid);
       model.addAttribute("todos", todos);
       model.addAttribute("dones", dones);
       model.addAttribute("mid", mid);
       ToDoForm form = new ToDoForm();
       model.addAttribute("ToDoForm", form);

       
       return "list";
   }

   /**
    * 全員のToDoリストを見る
    */
   @GetMapping("/{mid}/allToDos")
   String showAllToDos(@PathVariable String mid, Model model) {
        List<ToDo> todos = tService.getToDoList();
        List<ToDo> dones = tService.getDoneList();
        model.addAttribute("todos", todos);
        model.addAttribute("dones", dones);
        model.addAttribute("mid", mid);

    return "alllist";
   }

    /**
     * ToDoを完了する
     */
    @GetMapping("/{mid}/complete/{seq}")
    String doDone(@PathVariable String mid, @PathVariable long seq,Model model) {
        tService.doDone(seq);
        model.addAttribute("mid", mid);
        
        return ("redirect:/" + mid + "/todos");
    }
}
    