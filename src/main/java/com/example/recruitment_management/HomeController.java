package com.example.recruitment_management;

import java.util.List;

import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final CandidateRepository candidateRepository;

    public HomeController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/candidates")
    public String candidates(
            @RequestParam(required = false) String status,
            Model model) {

        List<Candidate> candidates;

        if (status == null || status.isEmpty()) {
            candidates = candidateRepository.findAll();
        } else {
            candidates = candidateRepository.findByStatus(status);
        }

        model.addAttribute("candidates", candidates);
        model.addAttribute("selectedStatus", status);

        return "candidates";
    }

    @GetMapping("/candidates/new")
    public String newCandidate(Model model) {
        model.addAttribute("candidate", new Candidate());
        return "candidate-form";
    }

    @GetMapping("/candidates/{id}")
    public String candidateDetail(@PathVariable Long id, Model model) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow();

        model.addAttribute("candidate", candidate);

        return "candidate-detail";
    }

    @GetMapping("/candidates/{id}/edit")
    public String editCandidate(@PathVariable Long id, Model model) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow();

        model.addAttribute("candidate", candidate);

        return "candidate-form";
    }   

    @PostMapping("/candidates")
    public String createCandidate(
            @Valid Candidate candidate,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "candidate-form";
        }

        candidateRepository.save(candidate);
        return "redirect:/candidates";
    }

    @PostMapping("/candidates/{id}")
    public String updateCandidate(
            @PathVariable Long id,
            @Valid @ModelAttribute("candidate") Candidate candidate,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("VALIDATION ERROR");
            return "candidate-form";
        }

        Candidate existingCandidate = candidateRepository.findById(id)
                .orElseThrow();

        existingCandidate.setName(candidate.getName());
        existingCandidate.setPosition(candidate.getPosition());
        existingCandidate.setStatus(candidate.getStatus());

        candidateRepository.save(existingCandidate);

        return "redirect:/candidates";
    }

    @PostMapping("/candidates/{id}/delete")
    public String deleteCandidate(@PathVariable Long id) {

        candidateRepository.deleteById(id);

        return "redirect:/candidates";
    }

}