package org.jbug.devping.interfaces.api;

import org.jbug.devping.interfaces.common.RestApiController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by nadal on 14. 9. 16.
 */
@Controller
@RequestMapping("/v1")
public class ApiTestController extends RestApiController {
    @RequestMapping(value ="/projectName")
    @ResponseBody
    public Project projectName() {
        return new Project("devping");
    }

    class Project {
        private String projectName;

        Project(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }
    }
}
