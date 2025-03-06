package com.RTN.RESTJobApp.repo;

import com.RTN.RESTJobApp.model.JobPost;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JobRepo {

    @Getter
    @Setter
    @Autowired
    private JdbcTemplate jdbc;

    public void addJob(JobPost jobPost)
    {
        String query="Insert into jobs(postId,postProfile,postDesc,reqExperience,postTechStack) values(?,?,?,?,?)";
        String tecstackJson=new Gson().toJson(jobPost.getPostTechStack());
        System.out.println(jobPost.getPostProfile());
        int rows=jdbc.update(query,jobPost.getPostId(),jobPost.getPostProfile(),jobPost.getPostDesc(),jobPost.getReqExperience(),tecstackJson);
        System.out.println(rows+"affected");
    }
    public List<JobPost> getAllJobs()
    {
            String sql="select * from jobs";

            return jdbc.query(sql,new RowMapper<JobPost>()
            {


                @Override
                public JobPost mapRow(ResultSet rs, int rowNum) throws SQLException {
                    JobPost setjob=new JobPost();

                    setjob.setPostId(rs.getInt("postId"));
                    setjob.setPostProfile(rs.getString("postProfile"));
                    setjob.setPostDesc(rs.getString("postDesc"));
                    setjob.setReqExperience(rs.getInt("reqExperience"));
                    String listTechStack=rs.getString(5);

                    List<String> techStack=new Gson().fromJson(listTechStack,List.class);

                    setjob.setPostTechStack(techStack);

                    return setjob;

                }
            });

    }

    public JobPost getJob(int jobId) {
        String sql="Select * from jobs where postId=?";
        List<JobPost> jobs=jdbc.query(sql, new Object[]{jobId}, new RowMapper<JobPost>() {
            @Override
            public JobPost mapRow(ResultSet rs, int rowNum) throws SQLException {
                JobPost job=new JobPost();
                job.setPostId(rs.getInt(1));
                job.setPostProfile(rs.getString(2));
                job.setPostDesc(rs.getString(3));
                job.setReqExperience(rs.getInt(4));
                List<String> techstack=new Gson().fromJson(rs.getString(5), List.class);
                job.setPostTechStack(techstack);
                return job;
            }
        });
        return jobs.isEmpty()?null:jobs.get(0);
    }

    public JobPost updateJob(int id) {

        String usql="update jobs set postProfile=?,postDesc=?,reqExperience=?,postTechStack=? where postId=?";
        String techstack=new Gson().toJson(jobPost.getPostTechStack());
        int rows=jdbc.update(usql,jobPost.getPostProfile(),jobPost.getPostDesc(),jobPost.getReqExperience(),techstack,id);
        System.out.println(rows+"Updated Successfully");

        return getJob(jobPost.getPostId());
    }

    public int deleteJob(int postId) {

        String sql="delete from jobs where postId=?";
        int rows=jdbc.update(sql,postId);
        return rows;
    }
}
