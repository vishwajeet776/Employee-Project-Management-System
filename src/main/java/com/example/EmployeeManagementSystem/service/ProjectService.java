package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    public ProjectDTO createProject(ProjectDTO dto);

    public List<ProjectDTO> getAllProjects();

    public ProjectDTO getProjectById(Long id);

    public ProjectDTO updateProject(Long id, ProjectDTO dto);

    public void deleteProject(Long id);

    // 🔧 Helper method here helper method for update project data

    default  <T> void ifNonNull(T value, java.util.function.Consumer<T> setter)
    {
        if (value != null) {
            setter.accept(value);
        }
    }

    /*
    How does it work?
<T> means it works for any data type (like String, LocalDate, etc.)

value is the data you're checking (like dto.getTitle())

setter is the function to call if the value is not null (like existingTask.setTitle())
     */
    /*
    //// Update fields only if provided. Project data change if{ }  condition to write consize code to change perticular column data
        //if{ } condition write undustanding way in TaskServiceImpli class in update method

     */
}
