package org.example;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import java.util.List;
import jakarta.ws.rs.PUT;

@Path("/students")
public class StudentResource {

    StudentRepository repository = new StudentRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudents() {
        return repository.getStudents();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(@PathParam("id") int id) {

        Student student = repository.getStudent(id);

        if (student == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response
                .ok(student)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(Student student) {

        if (repository.existsById(student.getId())) {

            return Response
                    .status(Response.Status.CONFLICT)
                    .entity("Student with ID " + student.getId() + " already exists")
                    .build();
        }

        repository.addStudent(student);

        return Response
                .status(Response.Status.CREATED)
                .entity(student)
                .build();
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(
            @PathParam("id") int id,
            Student student) {

        Student existingStudent = repository.getStudent(id);

        if (existingStudent == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        student.setId(id);

        repository.updateStudent(student);

        return Response
                .ok(student)
                .build();
    }

}