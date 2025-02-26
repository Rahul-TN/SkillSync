package com.RTN.RESTJobApp.controller;


import com.RTN.RESTJobApp.model.JobPost;
import com.RTN.RESTJobApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class JobController {

    @Autowired
    public JobService service;
    @GetMapping("/jobPosts")
    public List<JobPost> getAllJobs()
    {
        return service.getAllJobs();
    }

    @PostMapping("/jobPost")
    public String addJob(@RequestBody JobPost job)
    {
        service.addjob(job);
        return "Added Successfully";
    }

    @GetMapping("/jobPost/{id}")
    public JobPost getJob(@PathVariable("id") int jobId)
    {
        return service.getJob(jobId);

    }

    @PutMapping("/jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost)
    {
        return service.updateJob(jobPost);
    }
    @DeleteMapping("/jobPost/{id}")
    public String deleteJob(@PathVariable("id") int postId)
    {

        if(service.deleteJob(postId)>0)
        {
            return "Deleted Successflly";
        }
        return "Delete Unsucessful";
    }



}
