package com.RTN.RESTJobApp.service;

import com.RTN.RESTJobApp.model.JobPost;
import com.RTN.RESTJobApp.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    public JobRepo repo;


  public void addjob(JobPost jobPost)
  {
        repo.addJob(jobPost);
  }

  public List<JobPost> getAllJobs()
  {
      return repo.getAllJobs();
  }

    public JobPost getJob(int jobId) {
      return repo.getJob(jobId);
    }

    public JobPost updateJob(int id) {

      return repo.updateJobid;
    }

    public int deleteJob(int postId) {
      return repo.deleteJob(postId);
    }
}
