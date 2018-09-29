package com.eddy.endpoints;

import com.eddy.courses.*;
import com.eddy.domain.Course;
import com.eddy.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {


    @Autowired
    CourseDetailsService courseDetailsService;


    //create method
    //input request object
    //response Object

    //We need to tell Spring to process any request with
        //namespace (ns): http://eddy.com/courses
        //localPart: GetCourseDetailsRequest  -> section of xsd schema to be use for response
    // convert the response to the GetCourseDetailsResponse payload

    @PayloadRoot(namespace = "http://eddy.com/courses",
    localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetails
            (@RequestPayload GetCourseDetailsRequest request){
        Course course = courseDetailsService.getCourse(request.getId());
        CourseDetails courseDetails = courseDetailsService.mapCourse(course);
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        response.setCourseDetails(courseDetails);
        return response;
    }

    @PayloadRoot(namespace = "http://eddy.com/courses",
            localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processGetAllCourseDetails
            (@RequestPayload GetAllCourseDetailsRequest request){

        List<Course> courses = courseDetailsService.getCourses();
        List<CourseDetails> courseDetails = new ArrayList<>();
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        for (Course c: courses){
            response.getCourseDetails().add(courseDetailsService.mapCourse(c));
        }
        return response;
    }

}
