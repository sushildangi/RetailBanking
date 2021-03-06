package com.isolutions4u.retailbanking.controllers;


import com.isolutions4u.retailbanking.model.Branch;
import com.isolutions4u.retailbanking.model.User;
import com.isolutions4u.retailbanking.service.BranchService;
import com.isolutions4u.retailbanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class BankController {

    @Autowired
    @Qualifier("branchService")
    private BranchService branchService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @GetMapping(value = "/branchForm")
    public ModelAndView bankCreateForm() {
        ModelAndView modelAndView = new ModelAndView();
        Branch branch = new Branch();
        userInfo(modelAndView);
        modelAndView.addObject("branch", branch);
        modelAndView.setViewName("admin/branch-creation");
        //Shailesh Change
        return modelAndView;
    }

    private void userInfo(ModelAndView modelAndView) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
    }


    @PostMapping(value = "/branchForm")
    public ModelAndView createNewUser(@Valid Branch branch, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        userInfo(modelAndView);
        Branch branchExists = branchService.findBranchByBranchNo(branch.getBranchNo());
        if (branchExists != null) {
            bindingResult
                    .rejectValue("branchNo", "error.branch",
                            "There is already a Branch registered with the Same Branch");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/branch-creation");
        } else {
            branchService.saveBranch(branch);
            String branchNo = branch.getBranchNo();
            modelAndView.addObject("successMessage", "Branch created successfully and Branch code :" + branchNo);
            modelAndView.addObject("branch", new Branch());
            modelAndView.setViewName("admin/branch-creation");

        }
        return modelAndView;
    }


    @GetMapping("/listBranches")
    public ModelAndView listBranches(){
        ModelAndView modelAndView = new ModelAndView();
        Branch branch = new Branch();
        userInfo(modelAndView);
        modelAndView.addObject("branch", branch);
        modelAndView.setViewName("admin/branches-list");
        return modelAndView;
    }

    /*@GetMapping(value="/employee")
    public ModelAndView Employee() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/branches-list");
        return
    }*/
    @GetMapping("/empl")
    public String Empl()
    {
        return "employeeCreation";
    }

}
