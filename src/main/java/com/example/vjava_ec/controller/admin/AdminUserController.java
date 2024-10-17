package com.example.vjava_ec.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 会員コントローラ
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private MemberService memberService;

    // 会員一覧の表示
    @GetMapping("/list")
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "member_list";
    }

    // 会員詳細の表示
    @GetMapping("/detail/{id}")
    public String memberDetail(@PathVariable Long id, Model model) {
        model.addAttribute("member", memberService.getMemberById(id));
        return "member_detail";
    }

    // 会員ステータス変更確認ページの表示
    @PostMapping("/status/confirm/{id}")
    public String confirmStatusChange(@PathVariable Long id, Model model) {
        model.addAttribute("member", memberService.getMemberById(id));
        return "member_status_confirm";
    }

    // ステータスを変更するアクション
    @PostMapping("/status/change/{id}")
    public String changeMemberStatus(@PathVariable Long id) {
        memberService.changeMemberStatus(id);
        return "redirect:/member/detail/" + id;
    }
    
}

