package pl.edu.pk.hallreservation.service.hall.dto;


import java.util.HashSet;
import java.util.Set;


public class HallDTO {
    private String name;
    private Long id;
    private Set<LectureDTO> lectures = new HashSet<>();

    public HallDTO(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public HallDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<LectureDTO> getLectures() {
        return lectures;
    }

    public void setLectures(Set<LectureDTO> lectures) {
        this.lectures = lectures;
    }
}
